package doubleLinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/21/2024 7:31 PM
 **/
public class BinaryTreePreorderTraversal_144 {

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

    //递归实现前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    //辅助函数
    private void preOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return; //如果基础节点为空，直接返回
        }
        //访问根节点
        result.add(node.val);
        //递归访问左子树
        preOrderHelper(node.left, result);
        //递归访问右子树
        preOrderHelper(node.right, result);
    }

    public static void main(String[] args) {
        //构建二叉树[1,null,2,3]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        BinaryTreePreorderTraversal_144 traversal144 = new BinaryTreePreorderTraversal_144();
        List<Integer> integers = traversal144.preorderTraversal(root);
        System.out.println(integers);
    }
}
