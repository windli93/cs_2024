package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class FindTwoSum_LCR179 {

    // 购物车内的商品价格按照升序记录于数组 price。请在购物车中找到两个商品的价格总和刚好是 target。若存在多种情况，返回任一结果即可。

    // 示例 1：

    // 输入：price = [3, 9, 12, 15], target = 18
    // 输出：[3,15] 或者 [15,3]
    // 示例 2：

    // 输入：price = [8, 21, 27, 34, 52, 66], target = 61
    // 输出：[27,34] 或者 [34,27]

    public int[] twoSum(int[] price, int target) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < price.length; i++) {
            int k = target - price[i];
            if (set.contains(k)) {
                return new int[]{k, price[i]};
            }
            set.add(price[i]);
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] param1 = new int[]{8, 21, 27, 34, 52, 66};
        int target = 61;

        FindTwoSum_LCR179 lcr179 = new FindTwoSum_LCR179();
        int[] result = lcr179.twoSum(param1, target);
        System.out.println(Arrays.toString(result));
    }
}