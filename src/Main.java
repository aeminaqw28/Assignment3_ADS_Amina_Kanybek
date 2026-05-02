import java.util.Scanner;

public class Main {
    public static void main(String[] args){

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
        scanner.close();
        int[] arr={1,2,3,4,89,5,67};
        arr=bubblesort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);

        }




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

    public static int[] bubblesort(int[] arr){
        for (int i=0; i<arr.length-1;i++){
            for(int j=0; j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }

            }
        }
        return arr;
    }
    public static char[] sortCharacters(char[] charachters){
        int length= charachters.length;
        for(int out=0; out<length-1;out++){
            for(int inn=0; inn<length-out-1;inn++){
                if(charachters[inn]>charachters[inn+1]){
                    char temp=charachters[inn];
                    charachters[inn]=charachters[inn+1];
                    charachters[inn+1]=temp;

                }

            }
        }
        return charachters;

    }
}