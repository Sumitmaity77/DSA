import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] numbers, int target) {

        // Arrays.sort(numbers); // Not needed because the array is already sorted

        int i = 0, j = numbers.length - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] == target)
                return new int[]{i + 1, j + 1};   // Return 1-based indices
            else if (numbers[i] + numbers[j] > target)
                j--;
            else
                i++;
        }

        return new int[]{-1, -1}; // This line is never reached as per the problem guarantee
    }
}