/* Коллеги, добрый день!
 * Предоставляю на анализ тестовое задание!
 * Предполагаю, что резделителем будет являться знак пробела в выражении(" ") так как в условии ничего не сказано,
 * Но для подстаховки я избавляюсь от пробельных символов, чтобы не формировать ещё больше исключений.
Автор : Бобылев Филипп Викторович
Дата формирования : 09.08.2022г.

 * */

import java.util.Arrays;
import java.util.Scanner;

public class main {

        /*-------------------------------------------------------------------------------------------------------------*/

        public static void main(String[] argv) throws StringExeption, DigitExeption {
            int counts = 3; // назначенное колличество компонентов выражения (2 операнда(a,b), 1 операция(+,-,*,/) )
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter expression :");
            System.out.println(calc(scanner.nextLine(), counts));

            scanner.close();
        }

        /*-------------------------------------------------------------------------------------------------------------*/
        public static String calc(String input, int counts) throws StringExeption, DigitExeption { //функция финального просчёта

            String result;

            if (input.length() == 0) { // отработка исключения нулевой длины строки
                throw new StringExeption("Sorry, length of string line is too small");
            }

            input = input.replaceAll("\\s+",""); // удаление пробелов перед дальнейшим парсингов

            String[] components = input.split("(?=[+\\-*/])|(?<=[+\\-*/])"); // разбитие строк на
                // компоненты для дальнейшего парсинга с сохранением знака операции

            if ((components.length > counts) || (components.length < counts)) { // обработка исключения размера компонентов больше или меньше чем задано в параметре counts
                throw  new StringExeption("Sorry, too many  or to few arithmetic components");
            }

            result = Sum(components);
            return result;
        }

        /*-------------------------------------------------------------------------------------------------------------*/

        public static String Sum(String[] components) throws DigitExeption, StringExeption { // выполение необходимой операции с операндами.

            Digit operand_1 =  new Digit(Digit.ConvertFromStr(Arrays.stream(components).findFirst().get()));
            Digit operand_2 = new Digit(Digit.ConvertFromStr(components[components.length-1]));

            if( (!operand_1.GetIsRoman() && operand_2.GetIsRoman()) || (operand_1.GetIsRoman() && !operand_2.GetIsRoman()) ) { //  проверка разные типы операндов
                throw new DigitExeption("Sorry, all operands need be Roman digits or arithmetic digits");
            }

            switch (components[1]) {

                case "+": {
                    operand_1.SetDigit( operand_1.GetDigit() + operand_2.GetDigit());
                }
                break;

                case "-": {
                    operand_1.SetDigit( operand_1.GetDigit() - operand_2.GetDigit());
                    if (operand_1.GetIsRoman() && operand_1.GetDigit() < 1) {
                        throw  new DigitExeption("Sorry, value of Roman digit must be more or equal than 1 ");
                    }
                }
                break;

                case "*": {
                    operand_1.SetDigit(operand_1.GetDigit() * operand_2.GetDigit());
                }
                break;

                case "/": {
                    if ( operand_2.GetDigit() == 0) { // проверка деления на ноль
                        throw  new DigitExeption("Cant divide by zero");
                    }
                    operand_1.SetDigit(operand_1.GetDigit() / operand_2.GetDigit());
                }
                break;
                default: { //ветка исключения  несовпадения с ожидаемой операцией
                   throw  new DigitExeption("Operation with operands is not defined");
                }
            }

            if (!operand_1.GetIsRoman()) { // конвертация в String  в зависимости от типа входящих операндов
                operand_1.SetSrtDigit(String.valueOf(operand_1.GetDigit()));
            } else {
                operand_1.ConvertFromRomanToStr();
            }
            return operand_1.GetStrDigit();
        }

}

