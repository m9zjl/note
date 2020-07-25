import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WordBreak140 {
    public static void main(String[] args) {
        WordBreak140 wo = new WordBreak140();
        List<String> res = wo.wordBreak("pineapplepenapple",
                Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        System.out.println(res);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        // Map<String, Object> tree = buildTrieTree(wordDict);
        Set<String> set = new HashSet<>(wordDict);
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        dfs(s, set, 0, res, tmp);
        tmp.clear();
        res.forEach(item->tmp.add(String.join(" ", item)));
        return tmp;
    }

    private void dfs(String s, Set<String> set, int index, List<List<String>> res, List<String> tmp) {
        if (index == s.length()) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (set.contains(s.substring(index, i + 1))) {
                tmp.add(s.substring(index, i + 1));
                dfs(s, set, i + 1, res, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}