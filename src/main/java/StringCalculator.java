import java.util.Arrays;
import java.util.regex.*;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final int MAX_INT_VALUE = 1000;
    private static final String DEFAULT_DELIMITERS = ",\n";


    public static int add(String input) {
        if (input.isEmpty()) {return 0;}

        String delimiters = DEFAULT_DELIMITERS;
        String calculatorStr = input;

        Pattern pattern = Pattern.compile("^//(.)\\n(.*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String specialDelimiter = matcher.group(1);
            calculatorStr = matcher.group(2);
            delimiters = DEFAULT_DELIMITERS + specialDelimiter;
        }

        int[] elementsToSum = Arrays.stream(calculatorStr.split("[" + delimiters + "]"))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        int [] negativeNumbers = Arrays.stream(elementsToSum).filter(element -> element < 0).toArray();

        if (negativeNumbers.length > 0) {
             String negativeNumberErrorMessage = "Les nombres négatifs ne sont pas autorisés";
             if (negativeNumbers.length > 1) {
                 negativeNumberErrorMessage += " : " + Arrays.stream(negativeNumbers)
                         .mapToObj(String::valueOf)
                         .collect(Collectors.joining(", "));
             }
            throw new IllegalArgumentException(negativeNumberErrorMessage);

        }


        return sumNumberUnderMaxValue(elementsToSum);
    }


    private static Integer sumNumberUnderMaxValue(int [] values) {
        int sum = 0;

        for (Integer element : values) {
            if (element <= StringCalculator.MAX_INT_VALUE) {
                sum += element;
            }
        }
        return sum;
    };


    public static void main(String[] args) {
        String input = "2,3";

        int sum = add(input);
        System.out.println("Somme = " + sum);
    }
}
