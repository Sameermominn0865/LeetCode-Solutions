class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // 1. Build adjacency list (ai invokes bi → edge ai → bi)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : invocations) {
            adj.get(e[0]).add(e[1]);
        }
        // 2. Find all suspicious methods reachable from k
        boolean[] suspicious = new boolean[n];
        dfs(k, adj, suspicious);
        // 3. Check if any non-suspicious method invokes a suspicious one
        for (int[] e : invocations) {
            if (!suspicious[e[0]] && suspicious[e[1]]) {
                // cannot remove → return all methods
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    all.add(i);
                }
                return all;
            }
        }
        // 4. Safe to remove → return non-suspicious methods
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                remaining.add(i);
            }
        }
        return remaining;
    }
    private void dfs(int u, List<List<Integer>> adj, boolean[] suspicious) {
        if (suspicious[u]) return;
        suspicious[u] = true;
        for (int v : adj.get(u)) {
            dfs(v, adj, suspicious);
        }
    }
    
}