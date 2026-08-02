class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        Integer[][] memo = new Integer[n][n];

        int[] prefixSum = new int[n + 1];
        for(int i = 0;i<n;i++)
            prefixSum[i+1] = prefixSum[i] + piles[i];
        int total = prefixSum[n];
        int aliceScore = optimalScore(0, n-1, piles,memo, prefixSum);
        return aliceScore > total - aliceScore;
    }

    private int optimalScore(int i, int j, int[] piles, Integer[][] memo, int[] prefixSum){
        if(i>j) return 0;
        if(i == j) return piles[i];
        if(memo[i][j] != null) return memo[i][j];

        int rangeSum = prefixSum[j + 1] - prefixSum[i];

        int takeLeft = rangeSum - optimalScore(i+1, j, piles, memo, prefixSum);
        int takeRight = rangeSum - optimalScore(i, j-1, piles, memo, prefixSum);
        return memo[i][j] = Math.max(takeLeft, takeRight);
    }


}