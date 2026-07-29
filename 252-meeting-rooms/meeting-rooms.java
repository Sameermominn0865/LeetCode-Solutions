class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length <= 1 || intervals == null)
            return true;
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int prevEnd = intervals[0][1];
        for(int i=1; i< intervals.length; i++){
            int start = intervals[i][0];
            int end = intervals[i][1];
            if(start < prevEnd)
                return false;
            prevEnd = end;
        }
        return true;
    }
}