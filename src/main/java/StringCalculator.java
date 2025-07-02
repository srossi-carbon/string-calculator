import java.util.Arrays;
import java.util.regex.*;

public class StringCalculator {

    private static final int MAX_INT_VALUE = 1000;
    private static final String DEFAULT_DELIMITERS = ",\n";

    public static int add(String input) {
        if (input.isEmpty()) {return 0;}

        String delimiters = DEFAULT_DELIMITERS;
        String calculatorStr = input;

        DelimiterParsingResult parsingResult = parseCustomDelimiter(input);

        if (parsingResult != null) {
            delimiters = DEFAULT_DELIMITERS + parsingResult.delimiter();
            calculatorStr = parsingResult.remainingString();
        }

        int [] elementsToSum = parseIntegers(calculatorStr, delimiters);

        int [] negativeNumbers = findNegativeNumbers(elementsToSum);

        if (negativeNumbers.length > 0) {
            throw new NegativeNumberException(negativeNumbers);
        }

        return sumNumbersUnderMaxValue(elementsToSum);
    }


    private static Integer sumNumbersUnderMaxValue(int [] values) {
        return Arrays.stream(values).filter(value -> value <= MAX_INT_VALUE).sum();
    };

    private static int [] parseIntegers(String input, String delimiters) {
        return Arrays.stream(input.split("[" + delimiters + "]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    };

    private static int [] findNegativeNumbers(int[] values) {
        return Arrays.stream(values).filter(element -> element < 0).toArray();
    }

    private record DelimiterParsingResult(String delimiter, String remainingString) {}

    private static DelimiterParsingResult parseCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile("^//(.)\\n(.*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return new DelimiterParsingResult(matcher.group(1), matcher.group(2));
        }
        return null;
    }

    public static void main(String[] args) {
        String input = "2,3";

        int sum = add(input);
        System.out.println("Somme = " + sum);
    }
}

