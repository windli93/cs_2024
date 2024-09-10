package sum;

import java.util.*;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/10/2024 11:59 PM
 **/
public class GroupAnagrams_49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashMap = new HashMap();
        //先将字符串里面的字符进行排序分组，然后放入Hash中，然后在返回二维数组
        //遍历数组
        for (String str : strs) {
            //获取到单个字符
            char[] chars = str.toCharArray();
            //排序
            Arrays.sort(chars);
            //形成一个有序的key
            String sortedStrs = new String(chars);
            //在hashMap未找到数据则新建
            if (!hashMap.containsKey(sortedStrs)) {
                hashMap.put(sortedStrs, new ArrayList());
            }
            hashMap.get(sortedStrs).add(str);
        }
        //return result
        return new ArrayList(hashMap.values());
    }

    public static void main(String[] args) {
        String[] words = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams_49 grams = new GroupAnagrams_49();
        List<List<String>> result = grams.groupAnagrams(words);
        System.out.println(result.toString());
    }
}
