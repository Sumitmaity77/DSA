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
class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return count;
    }

    // Returns an int array where [0] is the sum of the subtree and [1] is the count of nodes.
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

        // Check if the node's value equals the average of its subtree
        if (currentSum / currentCount == node.val) {
            count++;
        }

        return new int[] {currentSum, currentCount};
    }
}
