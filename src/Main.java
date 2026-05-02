import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //task1
        Scanner scanner = new Scanner(System.in);
        System.out.println("Anagram Checker");
        System.out.print("Enter first word: ");
        String firstWord = scanner.nextLine().trim();
        System.out.print("Enter second word: ");
        String secondWord = scanner.nextLine().trim();

        if (areAnagrams(firstWord, secondWord)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        //task2
        Scanner sc = new Scanner(System.in);
        System.out.println("K-th Smallest Element Finder");
        System.out.print("Enter size of array: ");
        int size = sc.nextInt();

        int[] numbers = new int[size];
        System.out.print("Enter " + size + " numbers: ");
        for (int index = 0; index < size; index++) {
            numbers[index] = sc.nextInt();
        }

        System.out.print("Enter k ( smallest element to find): ");
        int kPosition = sc.nextInt();

        if (kPosition < 1 || kPosition > size) {
            System.out.println("Invalid k! Must be between 1 and " + size);
        } else {
            int result = findKthSmallest(numbers, kPosition);
            System.out.println("The " + kPosition + "-th smallest element is: " + result);
        }
        sc.close();
    }


    //task1
    // Checks if two strings are anagrams by sorting both and comparing
    private static boolean areAnagrams(String firstWord, String secondWord) {
        if (firstWord.length() != secondWord.length()) {
            return false;
        }
        char[] firstSorted = sortCharacters(firstWord.toLowerCase().toCharArray());
        char[] secondSorted = sortCharacters(secondWord.toLowerCase().toCharArray());

        for (int index = 0; index < firstSorted.length; index++) {
            if (firstSorted[index] != secondSorted[index]) {
                return false;
            }
        }
        return true;
    }

    //task2
    // Finds the k-th smallest element by sorting and picking index k-1
    private static int findKthSmallest(int[] numbers, int kPosition) {
        sortAscending(numbers);
        return numbers[kPosition - 1];
    }

    public static int[] bubblesort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        }
        return arr;
    }

    public static char[] sortCharacters(char[] charachters) {
        int length = charachters.length;
        for (int out = 0; out < length - 1; out++) {
            for (int inn = 0; inn < length - out - 1; inn++) {
                if (charachters[inn] > charachters[inn + 1]) {
                    char temp = charachters[inn];
                    charachters[inn] = charachters[inn + 1];
                    charachters[inn + 1] = temp;

                }

            }
        }
        return charachters;

    }
    // Sorts an integer array using insertion sort
    private static void sortAscending(int[] numbers) {
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