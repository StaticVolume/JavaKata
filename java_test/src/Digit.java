public class Digit {

    private int digit;
    private  int digit_length;
    private boolean is_roman = false;
    private String str_digit = "";

    Digit(){
        digit = 0;
        is_roman = false;
    }
    Digit (Digit digit) {
        this.digit = digit.digit;
        this.is_roman = digit.is_roman;
        this.str_digit = digit.str_digit;
    }

    Digit(int digit, boolean is_roman) {
        this.digit = digit;
        this.is_roman = is_roman;
    }

    public String GetStrDigit() {return  this.str_digit;}
    public int GetDigit() {
        return this.digit;
    }
    public boolean GetIsRoman(){
        return  this.is_roman;
    }
    public int GetDigLength(){ return  this.digit_length; }
    public void SetDigit(int digit) {
        this.digit = digit;
    }
    public void SetIsRoman(boolean is_roman) {
        this.is_roman = is_roman;
    }
    public void SetSrtDigit(String str_digit) {this.str_digit = str_digit;}

    public void SetDigLength(int digit_length) {this.digit_length = digit_length;}


    static public Digit ConvertFromStr(String input) throws DigitExeption, StringExeption {
        Digit result = new Digit();
        RomanDigits[] roman_digits = RomanDigits.values();

        if (input.matches("[0-9]+")) { //  простой парсинг обычного числа
            result.SetDigit(Integer.parseInt(input));
            if (result.GetDigit() < -10 || result.GetDigit() > 10) { // проверка диапазона целого цисла
                throw new DigitExeption("Sorry digit must be in range from -10 to 10 ");
            }
        } else if (input.matches("[VXLCDMIvxlcdmi]+")) {//  парсинг Римских чисел

            char[] char_buffer = input.toUpperCase().toCharArray();
            int integer_buffer = 0;

            for (int in_count = 0; in_count < char_buffer.length; in_count++) {
                for (int rome_count = 0; rome_count < roman_digits.length; rome_count++) {

                    if (char_buffer[in_count] == roman_digits[rome_count].GetCharDigit()) {
                        if (integer_buffer < roman_digits[rome_count].GetDigit()) {
                            integer_buffer =  roman_digits[rome_count].GetDigit() - integer_buffer ;
                            result.SetDigit(integer_buffer);
                        }
                        else {
                            result.SetDigit(result.GetDigit() + Math.abs(roman_digits[rome_count].GetDigit()));
                        }
                    }
                }
            }
            result.SetIsRoman(true); // флаг Римского числа, будет использоваться в результирующем вычислении
            if ( result.GetDigit() > 10) { // проверка римского числа на величину после преобразования
                throw new DigitExeption("Digit is more than 10 or less then 1");
            }
        } else { //  Блок исключения ввода не цифр
            throw new StringExeption("Sorry input is not a digit");
        }

        return result;
    }

    public void ConvertFromRomanToStr() {// конвентирование десятичного числа в римское
        RomanDigits[] roman_digits = RomanDigits.values();
        int roman_digits_length = roman_digits.length-1;
        int buffer = 0;

        while (this.digit > 0){ // формировать будем через вычитание максимальных значений roman_digits[roman_digits_length].GetDigit() из числа this.digit
            while (roman_digits[roman_digits_length].GetDigit() > this.digit) {
                --roman_digits_length;
            }
            this.str_digit += roman_digits[roman_digits_length].GetStrDigit();
            this.digit -= roman_digits[roman_digits_length].GetDigit();
        }

    }
}


