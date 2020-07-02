package leetcode;

import java.util.*;

public class Strings {
    // 8. 字符串转换整数 (atoi)
    // 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
    public static int atoi(String str) {
        char[] c = str.toCharArray();
        int i = 0;
        while (i < c.length && c[i] == ' ') {
            i++;
        }
        int neg = 1;
        if (i < c.length && c[i] == '-' || c[i] == '+') {
            if (c[i] == '-') neg = -1;
            i++;
        }
        int res = 0;
        int threshold = 214748364;
        if (i < c.length && c[i] >= '0' && c[i] <= '9') {
            while (i < c.length && c[i] >= '0' && c[i] <= '9') {
                if (res == threshold) {
                    if (neg == 1 && ((c[i] - '0') > 7)) {
                        return Integer.MAX_VALUE;
                    }
                    if (neg == -1 && ((c[i] - '0') > 8)) {
                        return Integer.MIN_VALUE;
                    }
                }
                if (res > threshold) {
                    return neg == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = 10 * res + (c[i] - '0');
                i++;
            }
        }
        return res * neg;
    }


    // 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, tmp = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.getOrDefault(s.charAt(i), 0) == 0) {
                tmp++;
                max = Math.max(max, tmp);
                map.put(s.charAt(i), 1);
            } else {
                while (j < i && s.charAt(j) != s.charAt(i)) {
                    map.put(s.charAt(j), 0);
                    j++;
                    tmp--;
                }
                j++;
            }
        }
        return max;
    }

    // 编写一个函数来查找字符串数组中的最长公共前缀。
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        int i = 0;
        while (true) {
            if (strs[0].length() <= i) return strs[0].substring(0, i);
            char stand = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != stand) return strs[0].substring(0, i);
            }
            i++;
        }
    }

    // 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null) return true;
        if (s2 == null) return false;
        int l1 = s1.length(), l2 = s2.length();
        if (l2 < l1) return false;
        char[] alpha = new char[26];
        char[] tmp = new char[26];
        for (char c : s1.toCharArray()) alpha[c - 'a']++;
        int i = 0, j = 0;
        for (; i < l1; i++) tmp[s2.charAt(i) - 'a']++;
        if (check(alpha, tmp)) return true;
        for (; i < l2; i++, j++) {
            tmp[s2.charAt(i) - 'a']++;
            tmp[s2.charAt(j) - 'a']--;
            if (check(alpha, tmp)) return true;
        }
        return false;
    }

    public boolean check(char[] a1, char[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) return false;
        }
        return true;
    }

    // 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "";
        if (num1.equals("0") || num2.equals("0")) return "0";
        String res = "0", tmp;
        int l = num2.length() - 1;
        for (int i = l; i >= 0; i--) {
            tmp = multiplySingle(num1, num2.charAt(i) - '0');
            res = addWithFo(res, tmp, l - i);
        }
        return res;
    }

    public String multiplySingle(String num, int x) {
        if (x == 0) return num;
        int fo = 0;
        String res = "";
        int tmp;
        for (int i = num.length() - 1; i >= 0; i--) {
            tmp = (num.charAt(i) - '0') * x + fo;
            fo = tmp / 10;
            res = (tmp % 10) + res;
        }
        if (fo != 0) res = fo + res;
        return res;
    }

    public String addWithFo(String num1, String num2, int fo) {
        if (num1.equals("0")) return num2;
        if (num2.equals("0")) return num1;
        StringBuilder sb1 = new StringBuilder(num1);
        StringBuilder sb2 = new StringBuilder(num2);
        int f = 0, sum;
        int i = sb1.length() - 1 - fo, j = sb2.length() - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            sum = sb1.charAt(i) - '0' + sb2.charAt(j) - '0' + f;
            f = sum / 10;
            sb1.setCharAt(i, (char) ('0' + sum % 10));
        }
        while (f != 0 && j >= 0) {
            sum = sb2.charAt(j) - '0' + f;
            f = sum / 10;
            sb2.setCharAt(j, (char) ('0' + sum % 10));
        }
        if (f != 0) return f + sb2.toString().substring(0, j + 1) + sb1.toString();
        else return sb2.toString().substring(0, j + 1) + sb1.toString();
    }

    // 给定一个字符串，逐个翻转字符串中的每个单词。
    public String reverseWords(String s) {
        if (s == null || s.equals("")) return "";
        String[] re = s.split(" ");
        if (re.length == 0) return "";
        StringBuilder res = new StringBuilder();
        for (int i = re.length - 1; i >= 0; i--) {
            if (!re[i].equals(""))
                res.append(re[i]).append(" ");
        }
        return res.substring(0, res.length() - 1);
    }

    // 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
    public List<String> restoreIpAddresses(String s) {
        List<String> ips = new LinkedList<>();
        int len = s.length();
        if (len < 4) return ips;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int n = 1; n <= 3; n++) {
                    int c1 = i, c2 = c1 + j, c3 = c2 + n;

                    if (c1 < len && c2 < len && c3 < len && ipCheck(s.substring(0, c1)) && ipCheck(s.substring(c1, c2))
                            && ipCheck(s.substring(c2, c3)) && ipCheck(s.substring(c3))) {
                        ips.add(s.substring(0, c1) + "." + s.substring(c1, c2)
                                + "." + s.substring(c2, c3) + "." + s.substring(c3));

                    }
                }
            }
        }
        return ips;
    }

    public boolean ipCheck(String code) {
        if (code.equals("") || code.length() > 3) return false;
        if (code.length() > 1 && code.charAt(0) == '0') return false;
        return Integer.parseInt(code) <= 255;
    }

    // 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
    public String simplifyPath(String path) {
        String[] re = path.split("/");
        Deque<String> sre = new LinkedList<>();
        for (String node : re) {
            if (node.equals(".") || node.equals("")) {
            } else if (node.equals("..")) {
                if (sre.size() > 0) sre.removeLast();
            } else sre.addLast(node);
        }
        if (sre.size() == 0) return "/";
        StringBuilder res = new StringBuilder();
        for (String s : sre) {
            res.append("/").append(s);
        }
        return res.toString();
    }

    public static String addBinary(String a, String b) {
        if (a == null && b == null) return "0";
        if (a == null) return b;
        if (b == null) return a;
        int l1 = a.length(), l2 = b.length();
        if (l2 > l1) {
            String tmp = b;
            b = a;
            a = tmp;
            l1 = a.length();
            l2 = b.length();
        }
        String tmp = "0";
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= l2; i++) {
            if (a.charAt(l1 - i) == '1' && b.charAt(l2 - i) == '1') {
                sb.insert(0, tmp);
                tmp = "1";
            } else if (a.charAt(l1 - i) == '0' && b.charAt(l2 - i) == '0') {
                sb.insert(0, tmp);
                tmp = "0";
            } else {
                if (tmp.equals("0")) {
                    sb.insert(0, "1");
                    tmp = "0";
                } else {
                    sb.insert(0, "0");
                    tmp = "1";
                }
            }
        }
        for (int i = l1 - l2 - 1; i > -1; i--) {
            if (tmp.equals("0"))
                sb.insert(0, a.charAt(i));
            else {
                if (a.charAt(i) == '0') {
                    sb.insert(0, "1");
                    tmp = "0";
                } else {
                    sb.insert(0, "0");
                    tmp = "1";
                }
            }
        }
        if (tmp.equals("1"))
            sb.insert(0, tmp);
        return sb.toString();
    }

    public static void main(String[] args) {
        Strings s = new Strings();
        System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(s.lengthOfLongestSubstring("pwwkew"));
        System.out.println(s.lengthOfLongestSubstring("bbbb"));
        System.out.println(s.lengthOfLongestSubstring(""));
        System.out.println(s.lengthOfLongestSubstring(null));

        System.out.println(s.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(s.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(s.longestCommonPrefix(new String[]{"", "", ""}));
        System.out.println(s.longestCommonPrefix(new String[]{"dogggg", "do", "dog"}));

        System.out.println(s.checkInclusion("ab", "eidbaoo"));
        System.out.println(s.checkInclusion("ab", "eidboaoo"));


        System.out.println(s.multiply("2", "3"));
        // "121932631112635269"
        System.out.println(s.multiply("123456789", "987654321"));

        System.out.println(s.reverseWords("  hello world!  "));
        System.out.println(s.reverseWords("a good   example"));
        System.out.println(s.reverseWords("     "));
        System.out.println(s.reverseWords(""));

        System.out.println(s.restoreIpAddresses("25525511135"));
        System.out.println(s.restoreIpAddresses("25511135"));
        System.out.println(s.restoreIpAddresses("2551"));
        System.out.println(s.restoreIpAddresses("010010"));
        System.out.println(s.restoreIpAddresses("0279245587303"));

        System.out.println(s.simplifyPath("/a//b////c/d//././/.."));
        System.out.println(s.simplifyPath("/home/"));
        System.out.println(s.simplifyPath("/../"));
        System.out.println(s.simplifyPath("/a/./b/../../c/"));


        System.out.println(atoi("42"));
        System.out.println(atoi("    -42"));
        System.out.println(atoi("    4193 word"));
        System.out.println(atoi("word aa 53"));
        System.out.println(atoi("91283472332"));
        System.out.println(atoi("2147483646"));

        System.out.println(addBinary("111", "101"));
        System.out.println(addBinary("11011", "101"));
        System.out.println(addBinary("101111", "10"));
    }
}
