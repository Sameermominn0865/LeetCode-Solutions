class Solution {
    public int minimumPushes(String word) {
        int[] freq = countLetterFreq(word);
        sortDescending(freq);
        int totalPushes = 0;
        int numKeys = 8;

        for(int i = 0; i< 26; i++){
            int positionOnKey = (i / numKeys) + 1;
            totalPushes += freq[i] * positionOnKey;
        }
        return totalPushes;
    }

    private int[] countLetterFreq(String word){
        int[] freq = new int[26];
        for(char c : word.toCharArray())
            freq[c - 'a']++;
        return freq;

    }

    private void sortDescending(int[] freq){
        Arrays.sort(freq);
        int left = 0;
        int right = freq.length - 1;
        while(left < right){
            int temp = freq[left];
            freq[left] = freq[right];
            freq[right]= temp;
            left++;
            right--;
        }
    }
}