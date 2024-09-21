package binaryTree;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/16/2024 10:20 PM
 **/
public class InvertBinaryTree_226 {

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

    //处理反转逻辑
    public TreeNode invertTree(TreeNode root) {
        //反转二叉树
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        //前序节点，每个节点需要做的事情就是交换它的左右节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        traverse(root.left);
        traverse(root.right);
    }

    public static void main(String[] args) {
        // 构建二叉树：[4,2,7,1,3,6,9]
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        InvertBinaryTree_226 binaryTree226 = new InvertBinaryTree_226();
        TreeNode invertedRoot = binaryTree226.invertTree(root);

        // 预期输出的翻转后的二叉树：[4,7,2,9,6,3,1]
        System.out.println("Root after inversion: " + invertedRoot.val); // 输出: 4
    }
}
