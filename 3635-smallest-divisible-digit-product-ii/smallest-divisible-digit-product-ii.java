class Solution {
    // digit → prime exponents {2,3,5,7}
    private static final int[][] DIGIT_PF = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {
        int[] need = new int[4]; // counts of 2,3,5,7
        for (int p : new int[]{2, 3, 5, 7}) {
            int i = p == 2 ? 0 : p == 3 ? 1 : p == 5 ? 2 : 3;
            while (t % p == 0) {
                t /= p;
                need[i]++;
            }
        }
        if (t != 1) return "-1"; // impossible prime factor

        int[] minDigits = pack(need); // multiset of digits 2..9 (as counts)
        int minLen = total(minDigits);
        int n = num.length();

        // Need more digits than num → answer is just the packed digits (sorted asc)
        if (minLen > n) return build(minDigits, 0);

        // Prefix prime coverage of whole num
        int[] have = new int[4];
        for (char c : num.toCharArray()) add(have, DIGIT_PF[c - '0']);

        int firstZero = num.indexOf('0');
        if (firstZero < 0) {
            firstZero = n;
            if (covers(have, need)) return num; // already valid
        }

        // Try to bump a digit from the right (same length)
        for (int i = n - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            sub(have, DIGIT_PF[d]); // remove this digit from left-prefix coverage
            if (i > firstZero) continue;

            int space = n - 1 - i;
            for (int nd = d + 1; nd <= 9; nd++) {
                int[] rem = remNeed(need, have, DIGIT_PF[nd]);
                int[] packRem = pack(rem);
                int used = total(packRem);
                if (used <= space) {
                    return num.substring(0, i)
                            + nd
                            + "1".repeat(space - used)
                            + build(packRem, 0);
                }
            }
        }

        // Same length impossible → length n+1, smallest: 1's then packed digits
        return build(minDigits, n + 1 - minLen);
    }

    // Pack remaining prime need into fewest digits (greedy 8,9,6,4,...)
    private int[] pack(int[] cnt) {
        int c2 = cnt[0], c3 = cnt[1], c5 = cnt[2], c7 = cnt[3];
        int n8 = c2 / 3; c2 %= 3;
        int n9 = c3 / 2; c3 %= 2;
        int n4 = c2 / 2; c2 %= 2;
        int n6 = 0, n2 = c2, n3 = c3;
        if (n2 == 1 && n3 == 1) { n2 = n3 = 0; n6 = 1; }
        if (n3 == 1 && n4 == 1) { n3 = n4 = 0; n2 = 1; n6 = 1; }
        // index: digit value
        return new int[]{0, 0, n2, n3, n4, c5, n6, c7, n8, n9};
    }

    private String build(int[] digitCnt, int ones) {
        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(ones));
        for (int d = 2; d <= 9; d++)
            sb.append(String.valueOf(d).repeat(digitCnt[d]));
        return sb.toString();
    }

    private int[] remNeed(int[] need, int[] have, int[] extra) {
        int[] r = new int[4];
        for (int i = 0; i < 4; i++)
            r[i] = Math.max(0, need[i] - have[i] - extra[i]);
        return r;
    }

    private boolean covers(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) if (have[i] < need[i]) return false;
        return true;
    }

    private void add(int[] a, int[] b) { for (int i = 0; i < 4; i++) a[i] += b[i]; }
    private void sub(int[] a, int[] b) { for (int i = 0; i < 4; i++) a[i] -= b[i]; }
    private int total(int[] digitCnt) {
        int s = 0; for (int d = 2; d <= 9; d++) s += digitCnt[d]; return s;
    }
}