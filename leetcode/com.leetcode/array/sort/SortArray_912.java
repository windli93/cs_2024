package array.sort;

import java.util.Arrays;

/**
 * @Author hongjian.li
 * @Description 冒泡排序
 * @Date 2/18/2025 7:13 PM
 **/
public class SortArray_912 {

    public int[] sortArray(int[] nums) {
        int length = nums.length;

        //外层控制循环次数
        for (int i = 0; i < length; i++) {
            boolean swapFlag = false;
            //内层进行相邻元素的比较和交换,交换次数随着循环次数越来越少
            for (int j = 0; j < length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    //设置中间变量
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swapFlag = true;
                }
            }
            //如果没有发生交换，说明就是有序的了
            if (!swapFlag){
                break;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        SortArray_912 array912 = new SortArray_912();
        int[] nums = {5, 2, 3, 1};
        int[] nums1 = array912.sortArray(nums);
        System.out.println(Arrays.toString(nums1));

        int[] nums11 = {5,1,1,2,0,0};
        int[] nums111 = array912.sortArray(nums11);
        System.out.println(Arrays.toString(nums111));
    }
}
