class Solution {
    public int smallestNumber(int n, int t) {
        int cand = n;
        while(true){
            if(digitPrd(cand) % t == 0)
                return cand;
            cand++;
        }
    }
    
    public int digitPrd(int num){
        int prod = 1;
        while(num > 0){
            prod *= num % 10;
            num /= 10;
        }
        return prod;
    }
}