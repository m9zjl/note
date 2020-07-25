
public class Manacher214 {
    public static void main(String[] args) {
        Manacher214 manacher214 = new Manacher214();
        String palindrom = manacher214.shortestPalindrome("a");
        System.out.println(palindrom);
    }

    public String shortestPalindrome(String s) {
        if(s.length()==0 || s.length() ==1){
            return s;
        }
        char[] chars = s.toCharArray();
        char[] map = new char[chars.length * 2 + 1];
        map[0] = '#';
        for (int i = 0; i < chars.length; i++) {
            map[2 * i + 1] = chars[i];
            map[2 * i + 2] = '#';
        }
        int id = 0;
        int max = 1;
        int validId = 0;
        int[] index = new int[map.length];
        index[0] = 1;
        for (int i = 1; i <= map.length / 2 + 1; i++) {
            index[i] = i < id + index[id] ? 
            Math.min( index[2 * id - i],id+ index[id] - i) :1;
            while (i - index[i] >= 0 && i + index[i] < map.length && map[i - index[i]] == map[i + index[i]]) {
                index[i]++;
            }
            if (id + max < i + index[i]) {
                id = i;
                max = index[i];
                if (id + 1 == max) {
                    validId = id;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = map.length - 1; i >= validId; i--) {
            if (map[i] != '#') {
                sb.append(map[i]);
            }
        }
        for (int i = validId + 1; i < map.length; i++) {
            if (map[i] != '#') {
                sb.append(map[i]);
            }
        }
        return sb.toString();
    }
}