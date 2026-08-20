import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Step 1: Group reserved seats by row using bitmasks
        //         seat i is encoded as bit (1 << i)
        Map<Integer, Integer> rowMap = new HashMap<>();
        for (int[] seat : reservedSeats) {
            rowMap.merge(seat[0], 1 << seat[1], (a, b) -> a | b);
        }

        // Step 2: Define block bitmasks for the three valid seating blocks
        int leftMask   = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5); // seats 2-5  = 60
        int rightMask  = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9); // seats 6-9  = 960
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); // seats 4-7  = 240

        // Step 3: Count families for each row that has reservations
        int families = 0;
        for (int mask : rowMap.values()) {
            boolean leftFree  = (mask & leftMask)   == 0;
            boolean rightFree = (mask & rightMask)  == 0;
            boolean midFree   = (mask & middleMask) == 0;

            if (leftFree && rightFree) {
                families += 2;                          // both non-overlapping blocks available
            } else if (leftFree || rightFree || midFree) {
                families += 1;                          // at least one block available
            }
        }

        // Step 4: Each completely empty row can always fit 2 families
        families += (n - rowMap.size()) * 2L > Integer.MAX_VALUE
                    ? Integer.MAX_VALUE
                    : (n - rowMap.size()) * 2;

        return families;
    }
}