class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isScramble("abc", "bca"));
    }

    public boolean isScramble(String s1, String s2) {
        if (null == s1 || null == s2 || s1.isEmpty() || s2.isEmpty()) {
            return s1 == s2;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 1) {
            return s1.charAt(0) == s2.charAt(0);
        }
        if (s1.length() == 2) {
            return (s1.charAt(0) == s2.charAt(0) && s1.charAt(1) == s2.charAt(1))
                    || (s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0));
        }
        if(s1.equals(s2)){
            return true;
        }
        int len = s1.length();
        for (int i = 1; i < s1.length(); i++) {
            boolean isMatch = (isScramble(s1.substring(i), s2.substring(i))
                    && isScramble(s1.substring(0, i), s2.substring(0, i)))
                    || (isScramble(s1.substring(0, i), s2.substring(len - i, len))
                            && isScramble(s1.substring(i, len), s2.substring(0,len-i)));
            if (isMatch) {
                return true;
            }
        }
        return false;
    }
}