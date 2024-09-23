package array.doublepoint;

import java.util.Arrays;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/23/2024 10:08 PM
 **/
public class MoveZeroes_283 {

    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int fast = 0, slow = 0;
        while (fast < length) {
            //这里是关键，需要符合条件，然后把走的快的数据给慢的数据
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        //其他地方补充0
        while (slow < length) {
            nums[slow] = 0;
            slow++;
        }
    }

    public static void main(String args[]) {
        int[] arrays = new int[]{0, 1, 0, 3, 12};
        MoveZeroes_283 zero283 = new MoveZeroes_283();
        zero283.moveZeroes(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}
