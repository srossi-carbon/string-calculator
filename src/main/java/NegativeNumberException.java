import java.util.Arrays;
import java.util.stream.Collectors;

public class NegativeNumberException extends RuntimeException {

    public NegativeNumberException(int[] negativeNumbers) {
        super(createErrorMessage(negativeNumbers));
    }

    private static String createErrorMessage(int[] negativeNumbers) {
        String baseMessage = "Les nombres négatifs ne sont pas autorisés";
        if (negativeNumbers != null && negativeNumbers.length > 0) {
            String negativeNumbersList = Arrays.stream(negativeNumbers)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(", "));
            return baseMessage + " : " + negativeNumbersList;
        }
        return baseMessage;
    }
}