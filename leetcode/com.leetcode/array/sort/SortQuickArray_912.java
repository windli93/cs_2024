package array.sort;

import java.util.Random;

/**
 * @Author hongjian.li
 * @Description
 * @Date 2/18/2025 8:02 PM
 **/
public class SortQuickArray_912 {

    public int[] sortArray(int[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        //对nums[lo..hi]进行切分，将nums[p]排好序
        //使得nums[lo...p-1] <= nums[p] < nums[p+1...hi]
        int p = partition(nums, lo, hi);
        //去左右子数组进行切分
        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }


    // 分区操作，将比基准元素小的放左边，大的放右边
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high]; // 选择最后一个元素为基准元素
        int i = low - 1; // i 是比基准元素小的区域的指针

        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                i++;
                // 交换 nums[i] 和 nums[j]
                swap(nums, i, j);
            }
        }
        // 最后，将基准元素放到正确的位置
        swap(nums, i + 1, high);
        return i + 1;
    }

    // 洗牌算法，将输入的数组随机打乱
    private void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0 ; i < n; i++) {
            // 生成 [i, n - 1] 的随机数
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }

    // 交换数组中的两个元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SortQuickArray_912 solution = new SortQuickArray_912();

        int[] nums1 = {5, 2, 3, 1};
        int[] sorted1 = solution.sortArray(nums1);
        System.out.println("Sorted array 1: " + java.util.Arrays.toString(sorted1));

        int[] nums2 = {5, 1, 1, 2, 0, 0};
        int[] sorted2 = solution.sortArray(nums2);
        System.out.println("Sorted array 2: " + java.util.Arrays.toString(sorted2));
    }
}
