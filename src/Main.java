import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Choose task:");
        System.out.println("1 - Anagram Checker");
        System.out.println("2 - K-th Smallest Element");
        System.out.println("3 - Median Finder");
        System.out.println("4 - Optimal Shipping");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: runAnagramChecker(); break;
            case 2: runKthSmallestFinder(); break;

            default: System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    // TASK 1
    private static void runAnagramChecker() {
        System.out.println("\n Anagram Checker ");
        System.out.print("Enter first word: ");
        String firstWord = scanner.nextLine().trim();
        System.out.print("Enter second word: ");
        String secondWord = scanner.nextLine().trim();

        if (areAnagrams(firstWord, secondWord)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean areAnagrams(String firstWord, String secondWord) {
        if (firstWord.length() != secondWord.length()) {
            return false;
        }
        char[] firstSorted  = sortCharactersByBubble(firstWord.toLowerCase().toCharArray());
        char[] secondSorted = sortCharactersByBubble(secondWord.toLowerCase().toCharArray());

        for (int index = 0; index < firstSorted.length; index++) {
            if (firstSorted[index] != secondSorted[index]) {
                return false;
            }
        }
        return true;
    }

    private static char[] sortCharactersByBubble(char[] characters) {
        int length = characters.length;
        for (int outer = 0; outer < length - 1; outer++) {
            for (int inner = 0; inner < length - outer - 1; inner++) {
                if (characters[inner] > characters[inner + 1]) {
                    char temp        = characters[inner];
                    characters[inner]     = characters[inner + 1];
                    characters[inner + 1] = temp;
                }
            }
        }
        return characters;
    }

    // TASK 2
    private static void runKthSmallestFinder() {
        System.out.println("\n K-th Smallest Element ");
        System.out.print("Enter size of array: ");
        int size = scanner.nextInt();

        int[] numbers = new int[size];
        System.out.print("Enter " + size + " numbers: ");
        for (int index = 0; index < size; index++) {
            numbers[index] = scanner.nextInt();
        }

        System.out.print("Enter k: ");
        int kPosition = scanner.nextInt();

        if (kPosition < 1 || kPosition > size) {
            System.out.println("Invalid k! Must be between 1 and " + size);
        } else {
            System.out.println(findKthSmallest(numbers, kPosition));
        }
    }

    private static int findKthSmallest(int[] numbers, int kPosition) {
        sortAscendingByInsertion(numbers);
        return numbers[kPosition - 1];
    }

    private static void sortAscendingByInsertion(int[] numbers) {
        int length = numbers.length;
        for (int outer = 1; outer < length; outer++) {
            int currentElement = numbers[outer];
            int inner = outer - 1;
            while (inner >= 0 && numbers[inner] > currentElement) {
                numbers[inner + 1] = numbers[inner];
                inner--;
            }
            numbers[inner + 1] = currentElement;
        }
    }
}