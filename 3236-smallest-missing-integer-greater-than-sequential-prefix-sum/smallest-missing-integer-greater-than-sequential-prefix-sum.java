class Solution {
    public int missingInteger(int[] nums) {
        int i = 0;
        int n = nums.length;
        // Longest prefix where each value is previous + 1
        while (i + 1 < n && nums[i + 1] == nums[i] + 1) {
            i++;
        }
        // Sum of nums[0..i]
        int sequentialSum = 0;
        for (int j = 0; j <= i; j++) {
            sequentialSum += nums[j];
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int x = sequentialSum;
        while (set.contains(x)) {
            x++;
        }
        return x;
    }
}