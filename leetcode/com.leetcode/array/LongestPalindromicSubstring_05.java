package array;

/**
 * @Author hongjian.li
 * @Description
 * @Date 9/23/2024 9:40 PM
 **/
public class LongestPalindromicSubstring_05 {

    public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            //以s[i]为中心的最长回文字符
            String str1 = palindrome(s, i, i);
            //以s[i]和s[i+1]为中心的最长回文字符
            String str2 = palindrome(s, i, i + 1);
            result = result.length() > str1.length() ? result : str1;
            result = result.length() > str2.length() ? result : str2;
        }
        return result;
    }

    // 在 str 中寻找以 str[l] 和 s[r] 为中心的最长回文串
    private String palindrome(String str, int left, int right) {
        // 防止索引越界
        while (left >= 0 && right < str.length()
                && str.charAt(left) == str.charAt(right)) {
            //双指针，向两边展开
            left--;
            right++;
        }
        //返回以str[left]和str[right]为中心的最长回文字符
        return str.substring(left + 1, right);
    }

    public static void main(String[] args) {
        String str1 = "babad";
        LongestPalindromicSubstring_05 subStr = new LongestPalindromicSubstring_05();
        String result= subStr.longestPalindrome(str1);
        System.out.println(result);
    }
}
