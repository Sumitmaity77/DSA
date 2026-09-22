class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n, k;
    int[][] tree;
    int[] prod;

    // Merge two nodes:
    // left segment + right segment
    void merge(int node, int left, int right) {

        prod[node] = (prod[left] * prod[right]) % k;

        // First, prefixes completely inside left
        for (int r = 0; r < k; r++) {
            tree[node][r] = tree[left][r];
        }

        // Then prefixes containing the whole left
        // and some prefix of right
        for (int r = 0; r < k; r++) {

            int newRemainder = (prod[left] * r) % k;

            tree[node][newRemainder] += tree[right][r];
        }
    }

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            int rem = nums[l] % k;

            prod[node] = rem;

            tree[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node, node * 2, node * 2 + 1);
    }

    void update(int node, int l, int r, int idx, int value) {

        if (l == r) {

            int rem = value % k;

            prod[node] = rem;

            // Clear previous values
            for (int i = 0; i < k; i++) {
                tree[node][i] = 0;
            }

            tree[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, value);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, value);
        }

        merge(node, node * 2, node * 2 + 1);
    }

    Node query(int node, int l, int r, int ql, int qr) {

        // Completely outside
        if (r < ql || l > qr) {
            return null;
        }

        // Completely inside
        if (ql <= l && r <= qr) {

            Node res = new Node(k);

            res.prod = prod[node];

            for (int i = 0; i < k; i++) {
                res.cnt[i] = tree[node][i];
            }

            return res;
        }

        int mid = (l + r) / 2;

        Node leftNode = query(node * 2, l, mid, ql, qr);
        Node rightNode = query(node * 2 + 1, mid + 1, r, ql, qr);

        if (leftNode == null) return rightNode;
        if (rightNode == null) return leftNode;

        Node res = new Node(k);

        res.prod = (leftNode.prod * rightNode.prod) % k;

        // Prefixes from left
        for (int i = 0; i < k; i++) {
            res.cnt[i] = leftNode.cnt[i];
        }

        // Prefixes that contain all of left
        // and then a prefix of right
        for (int i = 0; i < k; i++) {

            int newRemainder =
                    (leftNode.prod * i) % k;

            res.cnt[newRemainder] += rightNode.cnt[i];
        }

        return res;
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new int[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // Get information for nums[start ... n-1]
            Node res = query(
                    1,
                    0,
                    n - 1,
                    start,
                    n - 1
            );

            ans[i] = res.cnt[x];
        }

        return ans;
    }
}