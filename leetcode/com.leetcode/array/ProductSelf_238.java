package array;

import java.util.Arrays;

/*
       238. Product of Array Except Self

       Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

        The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

        You must write an algorithm that runs in O(n) time and without using the division operation.



        Example 1:

        Input: nums = [1,2,3,4]
        Output: [24,12,8,6]
        Example 2:

        Input: nums = [-1,1,0,-3,3]
        Output: [0,0,9,0,0]


        Constraints:

        2 <= nums.length <= 105
        -30 <= nums[i] <= 30
        The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


        Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)*/

public class ProductSelf_238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // 初始化，左侧乘积的第一个元素为1
        answer[0] = 1;

        // 计算每个元素的左侧乘积
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
            System.out.println("i: " + i + " answer: " + Arrays.toString(answer));
        }

        // 变量来保存右侧的乘积
        int right = 1;

        // 从左到右遍历，同时计算右侧乘积与左侧乘积的相乘结果
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * right;
            right *= nums[i];
            System.out.println("right: " + right + " answer: " + Arrays.toString(answer));
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        ProductSelf_238 solution238 = new ProductSelf_238();
        int[] result = solution238.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }
}