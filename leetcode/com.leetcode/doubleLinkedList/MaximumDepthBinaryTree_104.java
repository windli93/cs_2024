package doubleLinkedList;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/16/2024 8:50 PM
 **/
public class MaximumDepthBinaryTree_104 {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //递归计算左子树和右子树，取最大值加1
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        // 构建二叉树：[3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaximumDepthBinaryTree_104 binaryTree104 = new MaximumDepthBinaryTree_104();
        int level = binaryTree104.maxDepth(root);
        System.out.println("Level + " + level);
    }
}
