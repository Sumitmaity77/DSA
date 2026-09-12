import java.util.*;

class Solution {

    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {

        int n = intervalsList.size();

        long[][] intervals = new long[n][4];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = intervalsList.get(i).get(0); // start
            intervals[i][1] = intervalsList.get(i).get(1); // end
            intervals[i][2] = intervalsList.get(i).get(2); // weight
            intervals[i][3] = i;                            // original index
        }

        // Sort by ending time
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[3], b[3]);
        });

        // prev[i] = last interval whose end < current interval's start
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {

            int low = 0;
            int high = i - 1;
            int ans = -1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (intervals[mid][1] < intervals[i][0]) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            prev[i] = ans;
        }

        /*
         * dp[k][i] = best solution using AT MOST k intervals
         * from intervals 0...i
         */
        State[][] dp = new State[5][n];

        // k = 0 : choose nothing
        for (int i = 0; i < n; i++) {
            dp[0][i] = new State(0, new ArrayList<>());
        }

        for (int k = 1; k <= 4; k++) {

            for (int i = 0; i < n; i++) {

                // Option 1: skip current interval
                State skip;

                if (i == 0) {
                    skip = new State(0, new ArrayList<>());
                } else {
                    skip = dp[k][i - 1];
                }

                // Option 2: take current interval
                State take = null;

                if (prev[i] == -1) {

                    // No previous compatible interval.
                    // We can still take this as the first interval.
                    State base = dp[0][i];

                    List<Integer> list =
                            new ArrayList<>(base.indices);

                    list.add((int) intervals[i][3]);

                    take = new State(
                            intervals[i][2],
                            list
                    );

                } else {

                    State base = dp[k - 1][prev[i]];

                    List<Integer> list =
                            new ArrayList<>(base.indices);

                    list.add((int) intervals[i][3]);

                    Collections.sort(list);

                    take = new State(
                            base.weight + intervals[i][2],
                            list
                    );
                }

                dp[k][i] = better(skip, take);
            }
        }

        // Best answer using at most 4 intervals
        State best = dp[0][n - 1];

        for (int k = 1; k <= 4; k++) {
            best = better(best, dp[k][n - 1]);
        }

        int[] result = new int[best.indices.size()];

        for (int i = 0; i < best.indices.size(); i++) {
            result[i] = best.indices.get(i);
        }

        return result;
    }

    // Compare two solutions
    static State better(State a, State b) {

        if (b == null) {
            return a;
        }

        // Larger weight is better
        if (a.weight != b.weight) {
            return a.weight > b.weight ? a : b;
        }

        // Equal weight -> lexicographically smaller indices
        if (lexicographicallySmaller(a.indices, b.indices)) {
            return a;
        }

        return b;
    }

    static boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        // If one is prefix of the other,
        // shorter one is lexicographically smaller.
        return a.size() < b.size();
    }
}