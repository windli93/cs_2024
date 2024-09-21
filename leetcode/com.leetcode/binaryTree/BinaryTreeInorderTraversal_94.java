package binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/15/2024 5:42 PM
 **/


/*
        Given the root of a binary tree, return the inorder traversal of its nodes' values.

        Example 1:

        Input: root = [1,null,2,3]

        Output: [1,3,2]

        Explanation:
*/
public class BinaryTreeInorderTraversal_94 {


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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        //中序遍历就是->先左子树->再根节点->再右子树
        //先遍历左子树
        traverse(node.left, result);
        //访问根节点
        result.add(node.val);
        //遍历右子树
        traverse(node.right, result);
    }

    public static void main(String[] args) {
        //初始化树
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        BinaryTreeInorderTraversal_94 traversal94 = new BinaryTreeInorderTraversal_94();
        List<Integer> result =  traversal94.inorderTraversal(root);
        System.out.println(result);
    }
}
