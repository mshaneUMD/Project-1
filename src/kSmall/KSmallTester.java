package kSmall;

import java.util.*;

public class KSmallTester {

    public final static int SIZE_OF_ARRAY = 10;
    public final static String PROMPT = "Please  enter an integer k, 1<=k<=" +
        SIZE_OF_ARRAY + ", or 'R' to refill the array: ";

    public static void printArray(int[] array) {

        System.out.println("");
        System.out.print("array = [");
        for (int i=0; i < SIZE_OF_ARRAY-1; i++)
            System.out.print(""+ array[i]+" | ");
        System.out.println(""+ array[SIZE_OF_ARRAY-1]+"]");
        System.out.println("-------------------------------------------------"
                         + "-------------------");
    }

    public static void randFillArray(int[] array) {
        Random random = new Random();

        for (int i=0; i < SIZE_OF_ARRAY; i++)
            array[i] = random.nextInt(100);
    }

    public static void main(String argv[]) {
        int k = 1, kthSmallest = 0;
        int[] array = new int[SIZE_OF_ARRAY];
        int[] arrayTmp = new int[SIZE_OF_ARRAY];

        randFillArray(array);
        printArray(array);

        // ToDo: Read input k and print out the k-th smallest item of the array.

        // Note that your program should allow finding k-th smallest item from an array
        // continuously (i.e., more than once) , refilling the array, and exiting the
        // program when typing in "Q" or "q".

        // Create a Scanner object to take in user input.
        Scanner scanner = new Scanner(System.in);

        // Print the prompt.
        System.out.print(PROMPT);

        // Store the value of the inputted text.
        String input = scanner.nextLine();

        // While the value of the input does not equal "Q" or "q".
        while(!input.equalsIgnoreCase("Q")) {
            // Check if the input is equal to "R" or "r".
            if(input.equalsIgnoreCase("R")) {
                // Refill the array.
                randFillArray(array);

                // Reprint the array.
                printArray(array);

                // Reprint the prompt.
                System.out.print(PROMPT);

                // Get the value of the input again.
                input = scanner.nextLine();

                // Have the while loop try to parse the input again.
                continue;
            } else {
                // Check if the input can be parsed as an integer.
                try {
                    k = Integer.parseInt(input);

                    // Check to see if the value of k is outside of the range of 1 <= k <= SIZE_OF_ARRAY.
                    if(k < 1 || k > SIZE_OF_ARRAY) {
                        // Inform the user that the value must be within 1 <= k <= SIZE_OF_ARRAY.
                        System.out.println(String.format("[Error] The valid must be between 1 and %d!", SIZE_OF_ARRAY));

                        // Reprint the array.
                        printArray(array);

                        // Reprint the prompt.
                        System.out.print(PROMPT);

                        // Get the value of the input again.
                        input = scanner.nextLine();

                        // Have the while loop try to parse the input again.
                        continue;
                    }
                } catch(NumberFormatException e) {
                    // The input could not be parsed as an integer.
                    // Inform the user that the value is invalid.
                    System.out.println("[Error] Invalid input!");

                    // Reprint the array.
                    printArray(array);

                    // Reprint the prompt.
                    System.out.print(PROMPT);

                    // Get the value of the input again.
                    input = scanner.nextLine();

                    // Have the while loop try to parse the input again.
                    continue;
                }

                // If the program has reached this point, the inputted value is valid and stored in k.
                
                // Define arrayTmp as a deep copy of array.
                for(int i = 0; i < arrayTmp.length; i++) {
                    arrayTmp[i] = array[i];
                }
                
                // Get the Kth smallest value in the array.
                kthSmallest = KthSmallest.kSmall(k, arrayTmp, 0, SIZE_OF_ARRAY - 1);

                // Print the Kth smallest value of the array.
                System.out.println(String.format("The %d%s smallest item is %d.", k, k == 1 ? "st" : k == 2 ? "nd" : k == 3 ? "rd" : "th", kthSmallest));

                // Print out the prompt to retry.
                System.out.print("\nTry again? Press 'Enter' to continue, or 'Q' to quit: ");

                // Store the value of this input in a String.
                String retryInput = scanner.nextLine();

                // If the value of this input is "Q" or "q", then quit the program.
                if(retryInput.equalsIgnoreCase("Q")) break;

                // If the value is not "Q" or "q", then assume the user wants to retry.
                // Reprint the array.
                printArray(array);

                // Reprint the prompt.
                System.out.print(PROMPT);

                // Store the value of the inputted text.
                input = scanner.nextLine();

                // Have the while loop try to parse the input again.
                continue;
            }
        }

        // Print out a done message.
        System.out.println("Done!");

        // Close the scanner.
        scanner.close();

    }

}
