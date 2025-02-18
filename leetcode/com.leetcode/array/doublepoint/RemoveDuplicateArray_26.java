package array.doublepoint;

/**
 * @Author hongjian.li
 * @Description
 * @Date 2/18/2025 4:39 PM
 **/
public class RemoveDuplicateArray_26 {

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int fast = 0, slow = 0;
        while (fast < length) {
            //满足条件，slow才递增，否则快指针继续往前走
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        RemoveDuplicateArray_26 array26 = new RemoveDuplicateArray_26();
        int result = array26.removeDuplicates(nums);
        System.out.println("Result " + result);
    }
}
