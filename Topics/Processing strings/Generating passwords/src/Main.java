import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfUpperCaseLetters = scanner.nextInt();
        int numOfLowerCaseLetters = scanner.nextInt();
        int numOfDigits = scanner.nextInt();
        int totalChars = scanner.nextInt();
        int numRemainingChars = totalChars - (numOfUpperCaseLetters + numOfLowerCaseLetters + numOfDigits);

        if (totalChars == 0) {
            System.out.println();
            return;
        }

        StringBuilder password = new StringBuilder();

        password.append(buildLetters(numOfUpperCaseLetters, true));
        password.append(buildLetters(numOfLowerCaseLetters, false));
        password.append(buildDigits(numOfDigits));

        if (password.length() == 0) {
            password.append(buildFinalChars(numRemainingChars, 'A'));
        } else {
            char startingChar = password.charAt(password.length() - 1) == 'A' ? 'B' : 'A';
            password.append(buildFinalChars(numRemainingChars, startingChar));
        }

        System.out.println(password);
    }

    public static StringBuilder buildLetters(int length, boolean isUppercase) {
        StringBuilder result = new StringBuilder();
        int repeats = length / 2;
        int remainder = length % 2;

        String repeatString = isUppercase ? "AB" : "ab";
        String remainderString = isUppercase ? "A" : "a";

        result.append(repeatString.repeat(repeats));
        result.append(remainderString.repeat(remainder));
        return result;
    }

    public static StringBuilder buildFinalChars(int length, char startingChar) {
        StringBuilder result = new StringBuilder();
        int repeats = length / 2;
        int remainder = length % 2;

        String repeatString = startingChar == 'A' ? "AB" : "BA";
        String remainderString = startingChar == 'A' ? "A" : "B";

        result.append(repeatString.repeat(repeats));
        result.append(remainderString.repeat(remainder));
        return result;
    }

    public static StringBuilder buildDigits(int length) {
        StringBuilder result = new StringBuilder();
        int repeats = length / 2;
        int remainder = length % 2;

        result.append("12".repeat(repeats));
        result.append("1".repeat(remainder));
        return result;
    }
}