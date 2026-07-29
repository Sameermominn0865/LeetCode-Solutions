class Solution {
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> events = new ArrayList<>();
        for(int[] e : intervals){
            events.add(new int[]{e[0], +1});
            events.add(new int[]{e[1], -1});
        }

        events.sort((a, b)-> {
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int rooms = 0;
        int maxRooms = 0;

        for(int[] e: events){
            rooms += e[1];
            maxRooms = Math.max(maxRooms, rooms);
        }
        return maxRooms;

    }
}