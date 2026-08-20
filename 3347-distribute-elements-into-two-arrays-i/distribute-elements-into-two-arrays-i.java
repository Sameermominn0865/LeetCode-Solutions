import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] resultArray(int[] nums) {

        // Step 1: Initialize two dynamic lists — arr1 and arr2
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        // Step 2: Perform the first operation: append nums[0] to arr1
        arr1.add(nums[0]);

        // Step 3: Perform the second operation: append nums[1] to arr2
        arr2.add(nums[1]);

        // Step 4: Iterate from the 3rd element to the end of nums:
        //         - Compare the last element of arr1 with the last element of arr2
        //         - If last of arr1 > last of arr2, append current element to arr1
        //         - Otherwise, append current element to arr2
        for (int i = 2; i < nums.length; i++) {
            int lastArr1 = arr1.get(arr1.size() - 1);
            int lastArr2 = arr2.get(arr2.size() - 1);
            if (lastArr1 > lastArr2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        // Step 5: Merge arr1 and arr2 into a single result array by concatenation
        //         - Copy all elements of arr1 first, then all elements of arr2
        int[] result = new int[arr1.size() + arr2.size()];
        int idx = 0;
        for (int val : arr1) {
            result[idx++] = val;
        }
        for (int val : arr2) {
            result[idx++] = val;
        }

        // Step 6: Return the merged result array
        return result;
    }
}