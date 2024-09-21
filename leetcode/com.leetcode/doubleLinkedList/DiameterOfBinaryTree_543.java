package doubleLinkedList;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/21/2024 8:03 PM
 **/
public class DiameterOfBinaryTree_543 {

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


    //记录当前最大直径
    private int maxDiameter = 0;

    //计算二叉树的直径
    public int diameterOfBinaryTree(TreeNode node) {
        //递归计算树的高度，同时更新直径
        calculateHeight(node);
        return maxDiameter;
    }

    //递归计算树的高度
    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        //递归计算左右子树的高度
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        //更新最大直接：左右子树高度之和
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        // 构建一个二叉树：[1, 2, 3, 4, 5]
        // 树结构：
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfBinaryTree_543 solution = new DiameterOfBinaryTree_543();
        int result = solution.diameterOfBinaryTree(root);

        // 输出结果: 3 (路径是 [4, 2, 1, 3] 或 [5, 2, 1, 3])
        System.out.println("二叉树的直径: " + result);
    }
}
