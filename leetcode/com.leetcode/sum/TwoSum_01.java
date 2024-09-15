package sum;

import java.util.Arrays;
import java.util.HashMap;

class TwoSum_01 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int i1 = target - nums[i];
            if (hashMap.containsKey(i1)) {
                return new int[]{hashMap.get(i1), i};
            }
            hashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 7, 11, 15};
        TwoSum_01 sum01 = new TwoSum_01();
        int[] result = sum01.twoSum(nums1, 9);
        System.out.println(Arrays.toString(result));
    }
}