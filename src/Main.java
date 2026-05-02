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
            case 3: runMedianFinder();      break;
            case 4: runOptimalShipping();   break;
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
    // TASK 3: MEDIAN FINDER

    private static void runMedianFinder() {
        System.out.println("\n Task 3: Median Finder ");
        System.out.print("Enter size of array: ");
        int size = scanner.nextInt();

        int[] numbers = new int[size];
        System.out.print("Enter " + size + " numbers: ");
        for (int index = 0; index < size; index++) {
            numbers[index] = scanner.nextInt();
        }

        int median = findMedian(numbers);
        System.out.println("Output: " + median);
    }

    // Finds median by sorting and picking the middle element
    private static int findMedian(int[] numbers) {
        sortAscendingBySelection(numbers);
        int middleIndex = numbers.length / 2;
        return numbers[middleIndex];
    }

    // Sorts integer array using Selection Sort
    private static void sortAscendingBySelection(int[] numbers) {
        int length = numbers.length;
        for (int outer = 0; outer < length - 1; outer++) {
            // Find index of minimum element in remaining unsorted part
            int minimumIndex = outer;
            for (int inner = outer + 1; inner < length; inner++) {
                if (numbers[inner] < numbers[minimumIndex]) {
                    minimumIndex = inner;
                }
            }
            // Swap minimum element with first unsorted element
            int temp               = numbers[minimumIndex];
            numbers[minimumIndex]  = numbers[outer];
            numbers[outer]         = temp;
        }
    }

    // TASK 4: OPTIMAL SHIPPING CAPACITY

    private static void runOptimalShipping() {
        System.out.println("\n Task 4: Optimal Shipping Capacity ");
        System.out.print("Enter number of packages: ");
        int packageCount = scanner.nextInt();

        int[] weights = new int[packageCount];
        System.out.print("Enter weights of packages: ");
        for (int index = 0; index < packageCount; index++) {
            weights[index] = scanner.nextInt();
        }

        System.out.print("Enter number of days: ");
        int days = scanner.nextInt();

        int minimumCapacity = findMinimumCapacity(weights, days);
        System.out.println("Output: " + minimumCapacity);
        printShippingSchedule(weights, minimumCapacity);
    }

    // Finds minimum truck capacity using Binary Search
    // Left boundary  = max single weight  (can't go lower)
    // Right boundary = sum of all weights (ship all in 1 day)
    private static int findMinimumCapacity(int[] weights, int days) {
        int leftBound  = 0;
        int rightBound = 0;

        for (int weight : weights) {
            if (weight > leftBound) {
                leftBound = weight;
            }
            rightBound += weight;
        }

        // Binary search for minimum valid capacity
        while (leftBound < rightBound) {
            int midCapacity = leftBound + (rightBound - leftBound) / 2;

            if (canShipWithinDays(weights, days, midCapacity)) {
                rightBound = midCapacity;     // works, try smaller
            } else {
                leftBound = midCapacity + 1;  // doesn't work, need more
            }
        }
        return leftBound;
    }

    // Checks if all packages can be shipped within given days at this capacity
    private static boolean canShipWithinDays(int[] weights, int days, int capacity) {
        int currentDayLoad = 0;
        int daysUsed       = 1;

        for (int weight : weights) {
            // If adding this package exceeds daily capacity, start a new day
            if (currentDayLoad + weight > capacity) {
                daysUsed++;
                currentDayLoad = 0;
            }
            currentDayLoad += weight;
        }
        return daysUsed <= days;
    }

    // Prints the day-by-day shipping schedule
    private static void printShippingSchedule(int[] weights, int capacity) {
        System.out.println("\nShipping schedule:");
        int currentDayLoad = 0;
        int dayNumber      = 1;
        StringBuilder dayPackages = new StringBuilder("Day " + dayNumber + ": [");

        for (int index = 0; index < weights.length; index++) {
            if (currentDayLoad + weights[index] > capacity && currentDayLoad > 0) {
                dayPackages.append("] (total: ").append(currentDayLoad).append(")");
                System.out.println(dayPackages);
                dayNumber++;
                currentDayLoad = 0;
                dayPackages = new StringBuilder("Day " + dayNumber + ": [");
            }
            if (currentDayLoad > 0) {
                dayPackages.append(", ");
            }
            dayPackages.append(weights[index]);
            currentDayLoad += weights[index];
        }
        dayPackages.append("] (total: ").append(currentDayLoad).append(")");
        System.out.println(dayPackages);
    }
}
