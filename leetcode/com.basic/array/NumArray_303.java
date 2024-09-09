package array;

import java.util.Arrays;

/*
        303. Range Sum Query - Immutable
        Given an integer array nums, handle multiple queries of the following type:

        Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
        Implement the NumArray class:

        NumArray(int[] nums) Initializes the object with the integer array nums.
        int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


        Example 1:

        Input
        ["NumArray", "sumRange", "sumRange", "sumRange"]
        [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
        Output
        [null, 1, -1, -3]

        Explanation
        NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
        numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
        numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
        numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3


        Constraints:

        1 <= nums.length <= 104
        -105 <= nums[i] <= 105
        0 <= left <= right < nums.length
        At most 104 calls will be made to sumRange.
        */

public class NumArray_303 {

    private int[] prefixSum;

    public NumArray_303(int[] nums) {
        // 初始化前缀和数组，长度比nums长1，方便计算前缀和
        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        System.out.println(Arrays.toString(prefixSum));
    }

    // 返回区间和
    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }


    public static void main(String[] args) {
        //前缀法解题
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray_303 array = new NumArray_303(nums);
        int result1 = array.sumRange(0, 2);
        System.out.println(result1);
        int result2 = array.sumRange(2, 5);
        System.out.println(result2);
        int result3 = array.sumRange(0, 5);
        System.out.println(result3);
    }
}