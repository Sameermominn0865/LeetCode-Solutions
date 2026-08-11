class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] = true iff the player to move with i stones wins
        boolean[] dp = new boolean[n + 1];
        // dp[0] = false → no moves → lose
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) { // leave opponent in a losing state
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}