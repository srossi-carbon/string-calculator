import java.util.regex.*;

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

        for (String s : elementsToSum) {
            int number = Integer.parseInt(s.trim());
            sum += number;
        }

        return sum;
    }


    public static void main(String[] args) {
        String input = "";

        int sum = add(input);
        System.out.println("Somme = " + sum);
    }
}
