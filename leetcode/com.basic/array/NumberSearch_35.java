package array;

/**
 * @Author hongjian.li
 * @Description 使用二分查找
 * @Date 9/10/2024 8:20 PM
 **/

/*    35. Search Insert Position

    Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

    You must write an algorithm with O(log n) runtime complexity.



    Example 1:

    Input: nums = [1,3,5,6], target = 5
    Output: 2
    Example 2:

    Input: nums = [1,3,5,6], target = 2
    Output: 1
    Example 3:

    Input: nums = [1,3,5,6], target = 7
    Output: 4


    Constraints:

            1 <= nums.length <= 104
            -104 <= nums[i] <= 104
    nums contains distinct values sorted in ascending order.
            -104 <= target <= 104*/

public class NumberSearch_35 {



    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 二分查找
        while (left <= right) {  //这里要重点注意
            int mid = left + (right - left) / 2; // 防止溢出的写法
            if (nums[mid] > target) {
                right = mid - 1; // 目标值在左侧
            } else if (nums[mid] < target) {
                left = mid + 1; // 目标值在右侧
            } else {
                return mid;
            }
            System.out.println("left " + left + " right: " + right);
        }
        // 如果没有找到，返回应该插入的位置
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        NumberSearch_35 search35 = new NumberSearch_35();
        int result = search35.searchInsert(nums, 7);
        System.out.println("result: " + result);
    }
}
