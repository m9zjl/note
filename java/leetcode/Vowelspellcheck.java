import java.util.*;

public class Vowelspellcheck {

    public static void main(String[] args) {
        Vowelspellcheck vowelspellcheck = new Vowelspellcheck();
        String[] wordlist = new String[]{
                "KiTe", "kite", "hare", "Hare"
        };
        String queries[] = new String[]{
                "kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"
        };

        for (String s : vowelspellcheck.spellchecker(wordlist, queries)) {
            System.out.println(s);
        }
    }


    public String[] spellchecker(String[] wordlist, String[] queries) {
        // order
        // same->captilization->vowel->no match
        Map<Character, Map> trie = buildTree(wordlist);
        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = getResult(queries[i], trie);
        }
        return result;
    }

    private String getResult(String query, Map<Character, Map> trie) {
        // same->captilization->vowel->no match
        final Set<Character> sets = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        StringBuilder sb = new StringBuilder();
        return dfs(trie, query, 0, sb, sets) ? sb.toString() : "";
    }

    private boolean dfs(Map<Character, Map> trie, String query, int index, StringBuilder sb, Set<Character> sets) {
        if (index >= query.length()) {
            return true;
        }
        char c = query.charAt(index);
        char capitalSensitiveChar = c >= 'a' && c <= 'z' ? (char) (c - 'a' + 'A') : (char) (c - 'A' + 'a');
        for (int i = 0; i < 3; i++) {
            if (i == 0 && trie.containsKey(c)) {
                sb.append(c);
                if (dfs(trie.get(c), query, index + 1, sb, sets)) {
                    return true;
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else if (i == 1 && trie.containsKey(capitalSensitiveChar)) {
                sb.append(c);
                if (dfs(trie.get(capitalSensitiveChar), query, index + 1, sb, sets)) {
                    return true;
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else if (sets.contains(c)) {
                return getVowel(trie, query, index, sb, sets);
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean getVowel(Map<Character, Map> trie, String query, int index, StringBuilder sb, Set<Character> sets) {
        for (Character c : sets) {
            if (trie.containsKey(c)) {
                sb.append(c);
                if (dfs(trie.get(c), query, index + 1, sb.append(c), sets)) {
                    return true;
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return false;
    }

    private Map<Character, Map> buildTree(String[] wordlist) {
        Map<Character, Map> map = new HashMap<>();
        for (String s : wordlist) {
            Map<Character, Map> cur = map;
            for (char c : s.toCharArray()) {
                cur = cur.computeIfAbsent(c, HashMap::new);
            }
        }
        return map;
    }

}
