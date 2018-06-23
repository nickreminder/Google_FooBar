import java.util.Arrays;
import java.lang.*;

public class Level_2_2 {   
    public static void main(String[] args) {
        int[] test1 = new int[] {3, 1, 4, 1};
        int[] test2 = new int[] {3, 1, 4, 1, 5, 9};
        int[] test3 = new int[] {3, 3, 4, 8, 4};
        int[][] testPkg = new int[][] {test1, test2, test3};
        for (int[] test : testPkg) {
            System.out.println("Testing: " + Arrays.toString(test) + ", Result: " + answer(test));
        }
    }

    public static int answer(int[] l) { 
        Arrays.sort(l);
;        return makeLargestNumber(l);
    }
    
    private static int makeLargestNumber(int[] sortedL) {
        if (sortedL.length == 1) {
            return sortedL[0];
        }
        int sum = 0;
        for (int i : sortedL) {
            sum += i;
        }
        int remainder = sum % 3;
        if (remainder == 0) {
            return digitsToInt(sortedL);
        } else {
            return digitsToInt(makeDivisibleByThree(sortedL, remainder));
        }
    }
    
    private static int digitsToInt(int[] sortedL) {
        int total = 0;
        for (int i=0; i<sortedL.length; i++) {
            total += ((Math.pow(10, i)) * sortedL[i]);
        }
        return total;
    }
    
    private static int[] makeDivisibleByThree(int[] sortedL, int remainder) {
        // With remainder 1, remove first element with remainder 1 or first 2 with remainder 2
        if (remainder == 1) {
            for (int i=0; i<sortedL.length; i++) {
                if (sortedL[i] % 3 == 1) {
                    return removeElement(sortedL, i);
                } 
            }
            boolean seenDivByTwo = false;
            int firstIndex = 0;
            for (int i=0; i<sortedL.length; i++) {
                if (sortedL[i] % 3 == 2) {
                    if (seenDivByTwo) {
                        return removeElement(removeElement(sortedL, i), firstIndex);
                    } else {
                        firstIndex = i;
                        seenDivByTwo = true;
                    }
                }
            }
        // With remainder 2, remove first element with remainder 2 or first 2 with remainder 1
        } else if (remainder == 2) {
            for (int i=0; i<sortedL.length; i++) {
                if (sortedL[i] % 3 == 2) {
                    return removeElement(sortedL, i);
                }
            }
            boolean seenDivByOne = false;
            int firstIndex = 0;
            for (int i=0; i<sortedL.length; i++) {
                if (sortedL[i] % 3 == 1) {
                    if (seenDivByOne) {
                        return removeElement(removeElement(sortedL, i), firstIndex);
                    } else {
                        firstIndex = i;
                        seenDivByOne = true;
                    }
                }
            }
        }
        return sortedL;
    }
    
    private static int[] removeElement(int[] l, int index) {
        int[] myReturn = new int[l.length-1];
        for (int i=0; i<index; i++) {
            myReturn[i] = l[i];
        }
        for (int i=index; i<myReturn.length; i++) {
            myReturn[i] = l[i+1];
        }
        return myReturn;
    }
}