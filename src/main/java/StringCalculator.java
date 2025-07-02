import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
import java.util.stream.Collectors;

public class StringCalculator {

    public static int add(String input) {
        int sum = 0;

        if (input.isEmpty()) {return sum;}

        String delimitersRegex = "[,\n]";
        String calculatorStr = input;

        Pattern pattern = Pattern.compile("^//(.)\\n(.*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String specialDelimiter = matcher.group(1);
            calculatorStr = matcher.group(2);
            delimitersRegex = "[,\n" + specialDelimiter + "]";
        }

        int[] elementsToSum = Arrays.stream(calculatorStr.split(delimitersRegex))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        int [] negativeNumbers = Arrays.stream(elementsToSum).filter(element -> element < 0).toArray();
        if (negativeNumbers.length > 0) {
            throw new IllegalArgumentException("Les nombres négatifs ne sont pas autorisés : " +
                    Arrays.stream(negativeNumbers)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(", ")));
        }
        for (Integer element : elementsToSum) {
            sum += element;
        }

        return sum;
    }


    public static void main(String[] args) {
        String input = "";

        int sum = add(input);
        System.out.println("Somme = " + sum);
    }
}
