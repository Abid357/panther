package Facade;

public class NumberToWord  
 
{
    private static final String[] specialNames = {
        "",
        " THOUSAND",
        " MILLION",
        " BILLION",
        " TRILLION",
        " QUADRILLION",
        " QUITILLION"
    };
    
    private static final String[] tensNames = {
        "",
        " TEN",
        " TWENTY",
        " THIRTY",
        " FORTY",
        " FIFTY",
        " SIXTY",
        " SEVENTY",
        " EIGHTY",
        " NINETY"
    };
    
    private static final String[] numNames = {
        "",
        " ONE",
        " TWO",
        " THREE",
        " FOUR",
        " FIVE",
        " SIX",
        " SEVEN",
        " EIGHT",
        " NINE",
        " TEN",
        " EVELEN",
        " TWELVE",
        " THIRTEEN",
        " FOURTEEN",
        " FIFTEEN",
        " SIXTEEN",
        " SEVENTEEN",
        " EIGHTEEN",
        " NINETEEN"
    };
    
    private static String convertLessThanOneThousand(int number) {
        String current;
        
        if (number % 100 < 20){
            current = numNames[number % 100];
            number /= 100;
        }
        else {
            current = numNames[number % 10];
            number /= 10;
            
            current = tensNames[number % 10] + current;
            number /= 10;
        }
        if (number == 0) return current;
        return numNames[number] + " HUNDRED" + current;
    }
    
    public static String convert(int number) {

        if (number == 0) { return "ZERO"; }
        
        if (number < 0) {
            return "NEGATIVE VALUE";
        }
        
        String current = "";
        int place = 0;
        
        do {
            int n = number % 1000;
            if (n != 0){
                String s = convertLessThanOneThousand(n);
                current = s + specialNames[place] + current;
            }
            place++;
            number /= 1000;
        } while (number > 0);
        
        return current.trim();
    }
}