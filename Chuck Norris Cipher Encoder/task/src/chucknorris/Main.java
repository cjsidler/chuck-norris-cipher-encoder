package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine();
            switch (operation) {
                case "encode" -> {
                    String plainString = getInput("Input string:");
                    StringBuilder encodedString = build7BitBinaryString(plainString);
                    System.out.println("Encoded string:");
                    int i = 0;
                    while (i < encodedString.length()) {
                        char digit = encodedString.charAt(i);
                        int digitCount = 0;
                        while (i < encodedString.length() && encodedString.charAt(i) == digit) {
                            digitCount++;
                            i++;
                        }

                        if (digit == '0') {
                            System.out.print("00 ");
                        } else {
                            System.out.print("0 ");
                        }

                        for (int j = 0; j < digitCount; j++) {
                            System.out.print("0");
                        }
                        System.out.print(" ");
                    }

                    System.out.println();
                    System.out.println();
                }
                case "decode" -> {
                    String encodedString = getInput("Input encoded string:");
                    String[] splitInput = encodedString.split(" ");
                    boolean isEncodingValid = validateSplitEncodedString(splitInput);
                    if (!isEncodingValid) {
                        System.out.println("Encoded string is not valid");
                        System.out.println();
                        break;
                    }

                    StringBuilder fullBinaryString = new StringBuilder();

                    for (int i = 0; i < splitInput.length; i += 2) {
                        fullBinaryString.append(buildFullBinaryString(splitInput[i], splitInput[i + 1]));
                    }

                    // verify the fullBinaryString length is divisible by 7
                    if (fullBinaryString.length() % 7 != 0) {
                        System.out.println("Encoded string is not valid");
                        System.out.println();
                        break;
                    }

                    String[] binaryStrings = new String[fullBinaryString.length() / 7];

                    int index = 0;
                    for (int i = 0; i < fullBinaryString.length(); i += 7) {
                        binaryStrings[index] = fullBinaryString.substring(i, i + 7);
                        index++;
                    }

                    System.out.println("Decoded string:");
                    for (String binaryString : binaryStrings) {
                        int charInt = Integer.parseInt(binaryString, 2);
                        System.out.printf("%c", charInt);
                    }
                    System.out.println();
                    System.out.println();
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.printf("There is no '%s' operation.\n\n", operation);
            }
        }
    }

    private static boolean validateSplitEncodedString(String[] splitInput) {
        // Must have an even number of elements
        if (splitInput.length % 2 != 0) {
            return false;
        }
        for (int i = 0; i < splitInput.length; i++) {
            if (i % 2 == 0) {
                // First block of each sequence should be 00 or 0
                if (!splitInput[i].equals("00") && !splitInput[i].equals("0")) {
                    return false;
                }
            } else {
                // Every character in the array should be '0'
                for (int j = 0; j < splitInput[i].length(); j++) {
                    if (splitInput[i].charAt(j) != '0') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static String getInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    public static StringBuilder buildFullBinaryString(String digit, String numDigits) {
        StringBuilder result = new StringBuilder();
        String binaryDigit = digit.equals("00") ? "0" : "1";
        result.append(binaryDigit.repeat(numDigits.length()));
        return result;
    }

    public static StringBuilder build7BitBinaryString(String inputString) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            String binaryString = String.format("%7s", Integer.toBinaryString(currentChar)).replace(" ", "0");
            result.append(binaryString);
        }

        return result;
    }
}