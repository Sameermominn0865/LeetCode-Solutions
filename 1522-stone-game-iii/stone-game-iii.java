class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] sufix = new int[n+1];
        for(int i = n-1; i>=0; i--)
            sufix[i] = sufix[i+1] + stoneValue[i];
        int[] dp = new int[n+1];
        dp[n] = 0;
        for(int i = n-1; i>=0; i--){
            dp[i] = computeOptimalChoice(i, stoneValue, sufix, dp);
        }
        return determineWinner(dp[0]);

    }

    public int computeOptimalChoice(int i, int[] stoneValue, int[] sufix, int[] dp){
        int best = Integer.MIN_VALUE;
        for(int k=1; k<=3 && i+k <= stoneValue.length; k++){
            int stoneTaken = sufix[i] - sufix[i+k];
            int currentScoreDifference = stoneTaken - dp[i+k];
            best = Math.max(best, currentScoreDifference);
        }
        return best;
    }

    public String determineWinner(int scoreDifference){

        if(scoreDifference > 0) return "Alice";
        if (scoreDifference < 0) return "Bob";
        return "Tie";
    }

}