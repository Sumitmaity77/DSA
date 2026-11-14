/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class pair {
    long max;
    long min;

    pair(long max, long min) {
        this.max = max;
        this.min = min;
    }
}

class Solution {
    static boolean flag;

    public boolean isValidBST(TreeNode root) {
        flag = true;
        maxMin(root);
        return flag;
    }

    pair maxMin(TreeNode root) {
        if (root == null)
            return new pair(Long.MIN_VALUE, Long.MAX_VALUE);
        pair lst = maxMin(root.left);
        pair rst = maxMin(root.right);
        long max = Math.max(root.val, Math.max(lst.max, rst.max));
        long min = Math.min(root.val, Math.min(lst.min, rst.min));
        if (lst.max >= root.val || rst.min <= root.val)
            flag = false;
        return new pair(max, min);
    }
}