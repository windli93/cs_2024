package array.hash;

import java.util.HashSet;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/12/2024 5:36 PM
 **/

/*      Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

        You must write an algorithm that runs in O(n) time.



        Example 1:

        Input: nums = [100,4,200,1,3,2]
        Output: 4
        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
        Example 2:

        Input: nums = [0,3,7,2,5,8,4,6,0,1]
        Output: 9


        Constraints:

        0 <= nums.length <= 105
        -109 <= nums[i] <= 109*/
public class LongestConsecutiveSequence_128 {

    public int longestConsecutive(int[] nums) {
        //异常处理
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //使用HashSet去重
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestBreak = 0;
        for (int num : numSet) {
            //判断起点
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentSteak = 1;
                //迭代寻找后续序列元素
                while (numSet.contains(currentNum + 1)) {
                    currentNum = currentNum + 1;
                    currentSteak = currentSteak + 1;
                }
                longestBreak = Math.max(currentSteak, longestBreak);
            }
        }
        return longestBreak;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        LongestConsecutiveSequence_128 sequence128 = new LongestConsecutiveSequence_128();
        int result = sequence128.longestConsecutive(nums);
        System.out.println("result " + result);
    }
}
