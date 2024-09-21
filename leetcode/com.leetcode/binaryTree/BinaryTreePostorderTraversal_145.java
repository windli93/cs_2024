package binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/21/2024 8:53 PM
 **/
public class BinaryTreePostorderTraversal_145 {

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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null){
            return; // 节点为空，直接返回
        }

        //后序遍历就是->先根节点->再右子树->再左子树
        traversal(root.left, list);
        traversal(root.right, list);
        list.add(root.val);
    }

    public static void main(String[] args) {

        // 构建二叉树：[1, null, 2, 3]
        // 树结构：
        //   1
        //    \
        //     2
        //    /
        //   3
        //初始化树
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        BinaryTreePostorderTraversal_145 traversal145 = new BinaryTreePostorderTraversal_145();
        List<Integer> result =  traversal145.postorderTraversal(root);
        System.out.println(result); //Output: [3,2,1]
    }
}
