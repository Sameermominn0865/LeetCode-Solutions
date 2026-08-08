class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] last = new int[m];
        Arrays.fill(last, -1);

        // Rightmost subsequence matching of word2 in word1
        // last[j] = index in word1 used for word2[j] in that matching
        for (int i = n - 1, j = m - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j--] = i;
            }
        }

        int[] ans = new int[m];
        boolean canSkip = true;
        int j = 0;

        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            } else if (canSkip && (j == m - 1 || i < last[j + 1])) {
                // Use the one change; suffix word2[j+1..] must fit in word1[i+1..]
                canSkip = false;
                ans[j++] = i;
            }
        }

        return j == m ? ans : new int[0];
    }
}