class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int left = 0; 
        int max_len = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(int right = 0; right< nums.length; right++){
            freq.put(nums[right], freq.getOrDefault(nums[right], 0)+1);
            while(freq.get(nums[right]) > k){
                freq.put(nums[left], freq.get(nums[left])-1);
                left++;
            }
            max_len = Math.max(max_len, right-left+1);
            
        }
        return max_len;
    }
}