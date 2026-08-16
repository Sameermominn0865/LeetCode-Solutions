class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean allZero = true;
        for (int num : nums) {
            xor ^= num;
            if (num != 0) {
                allZero = false;
            }
        }
        if (allZero) {
            return 0; // every subsequence has XOR 0
        }
        if (xor != 0) {
            return nums.length; // whole array works
        }
        return nums.length - 1; // remove one element → remaining XOR ≠ 0
    }
}