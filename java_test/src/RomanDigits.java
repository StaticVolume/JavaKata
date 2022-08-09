public enum RomanDigits { // перечисление основных римских цифр, выбирались специфичные неповторяющиеся цифры.

    I(1, 'I',"I"),IV(4,' ',"IV"), V(5, 'V',"V"),IX(9,' ',"IX"), X(10, 'X', "X"),
    XL(40,' ',"XL"),L(50, 'L',"L"),XC(90,' ',"XC"), C(100, 'C',"C"),D(500, 'D',"D"), M(1000, 'M',"M");
    private final int digit;
    private final char char_digit;

    private final String str_digit;
    RomanDigits(int digit, char char_digit, String str_digit) {
        this.digit = digit;
        this.char_digit = char_digit;
        this.str_digit = str_digit;
    }

    public int GetDigit() {
        return this.digit;
    }

    public char GetCharDigit() {
        return this.char_digit;
    }
    public  String GetStrDigit() {return  this.str_digit; }
}
