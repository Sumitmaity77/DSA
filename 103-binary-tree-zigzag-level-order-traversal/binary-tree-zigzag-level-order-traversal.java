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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      List<List<Integer>> result = new ArrayList<>();
        if ( root == null ){
            return result ;
        }
        Queue <TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(root);
        boolean isLeftToRight = true;
        while ( !treeNodeQueue.isEmpty()){
            int levelSize = treeNodeQueue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0;i<levelSize;i++){
            TreeNode treeNode = treeNodeQueue.poll();
            if ( isLeftToRight){
                currentLevel.add (treeNode .val);

            }else { 
                currentLevel.add(0,treeNode.val);
            }

            
            if(treeNode.left != null){
            treeNodeQueue.add(treeNode.left);}

            if ( treeNode.right != null){
            treeNodeQueue.add ( treeNode.right);
            }
        }
        result.add ( currentLevel);
        isLeftToRight = !isLeftToRight;
    }
    return result ;
}
}


