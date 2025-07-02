import java.util.ArrayList;
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

        String[] elementsToSum = calculatorStr.split(delimitersRegex);
        ArrayList<Integer> negativeNumbers = new ArrayList<>();
        for (String s : elementsToSum) {
            int number = Integer.parseInt(s.trim());
            if (number < 0) {
                negativeNumbers.add(number);
            }
            sum += number;
        }

        if (!negativeNumbers.isEmpty()) {
            String negativeNumbersStr =
                    negativeNumbers
                            .stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(", "));

            throw new IllegalArgumentException("Les nombres négatifs ne sont pas autorisés : " + negativeNumbersStr);
        }

        return sum;
    }


    public static void main(String[] args) {
        String input = "";

        int sum = add(input);
        System.out.println("Somme = " + sum);
    }
}
