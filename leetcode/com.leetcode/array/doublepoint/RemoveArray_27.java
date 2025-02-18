package array.doublepoint;

/**
 * @Author hongjian.li
 * @Description
 * @Date 2/18/2025 4:39 PM
 **/
public class RemoveArray_27 {

    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int fast = 0, slow = 0;

        while (fast < length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        RemoveArray_27 array26 = new RemoveArray_27();
        int result = array26.removeElement(nums, 2);
        for (int i = 0; i < result; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
