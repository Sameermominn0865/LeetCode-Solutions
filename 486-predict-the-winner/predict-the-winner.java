class Solution {
    public boolean predictTheWinner(int[] nums) {
        // Get the number of elements in the input array
        int n = nums.length;
        // Initialize a memoization table to store results for subproblems
        Integer[][] memo = new Integer[n][n];
        // Compute the maximum score difference Player 1 can achieve
        int scoreDifference = solve(nums, 0, n-1, memo);
        // Player 1 wins if the score difference is non-negative
        return scoreDifference>=0;

    }
    private int solve(int[] nums, int left, int right, Integer [][] memo){
        if(left == right) return nums[left];
        if(memo[left][right] != null) return memo[left][right];
        int pickLeft = nums[left] - solve(nums, left+1, right, memo);
        int pickRight = nums[right] - solve(nums, left, right-1, memo);
        memo[left][right] = Math.max(pickLeft, pickRight);
        return memo[left][right];
    }
}