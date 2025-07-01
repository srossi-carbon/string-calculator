public class StringCalculator {

    public static int add(String input) {
        int sum = 0;

        if (input.isEmpty()) {return sum;}

        String[] elementsToSum = input.split(",");

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
