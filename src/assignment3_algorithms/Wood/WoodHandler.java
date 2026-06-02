package assignment3_algorithms.Wood;

import java.util.ArrayList;
import java.util.List;

public class WoodHandler {

    // The available piece sizes, largest first — this order is what makes it "greedy"
    private static final int[] PIECES = {7, 5, 2};

    public static List<Integer> calculateWood(int target) {
        List<Integer> result = new ArrayList<>();
        int remaining = target;

        while (remaining > 0) {
            int chosen = -1;

            // Try each piece size from largest to smallest
            for (int piece : PIECES) {
                if (piece <= remaining) {
                    chosen = piece;
                    break; // Take the first (largest) one that fits
                }
            }

            // No piece fits — we're stuck, return what we have (closest match)
            if (chosen == -1) {
                break;
            }

            result.add(chosen);
            remaining -= chosen;
        }

        return result;
    }
}
