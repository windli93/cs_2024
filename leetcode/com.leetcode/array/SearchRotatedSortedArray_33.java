package array;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/11/2024 2:11 PM
 **/

/*There is an integer array nums sorted in ascending order (with distinct values).

        Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

        Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

        You must write an algorithm with O(log n) runtime complexity.



        Example 1:

        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4
        Example 2:

        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1
        Example 3:

        Input: nums = [1], target = 0
        Output: -1


        Constraints:

        1 <= nums.length <= 5000
        -104 <= nums[i] <= 104
        All values of nums are unique.
        nums is an ascending array that is possibly rotated.
        -104 <= target <= 104*/

public class SearchRotatedSortedArray_33 {

//    核心思想：利用旋转数组的一部分是有序的这个特点，通过二分查找找到目标。
//    有序性判断：判断当前的左半部分或右半部分是否有序，然后根据目标值是否落在有序区间内，决定向哪一边继续查找。
//    时间复杂度：由于二分查找的本质没有改变，因此时间复杂度仍然是 O(log n)。

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        int i = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            i++;
            // 如果找到目标值，直接返回其索引
            if (nums[mid] == target) {
                return mid;
            }

            // 判断左半部分是否有序
            if (nums[left] <= nums[mid]) {
                // 如果左半部分有序，检查目标是否在左半部分
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                System.out.println("i " + i + " left start " + " right " + right + " left " + left);
            } else {
                // 否则右半部分有序
                // 如果右半部分有序，检查目标是否在右半部分
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
                System.out.println("i " + i + " right start " + " right " + right + " left " + left);
            }
        }
        // 如果没有找到目标值，返回 -1
        return -1;
    }


    public static void main(String[] args) {
        int[] arrays = new int[]{4, 5, 6, 9, 0, 1, 2};
        SearchRotatedSortedArray_33 sortedArray33 = new SearchRotatedSortedArray_33();
        int result = sortedArray33.search(arrays, -1);
        System.out.println(result);
    }
}
