# Given a set of distinct integers, nums, return all possible subsets

## solution1 

each number has two state , in or not in 

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> emptyList = new ArrayList<>();
        res.add(emptyList);
        for (int n : nums) {
            int index = res.size();
            for (int i = 0; i < index; i++) {
                // copy one and add the specific number and the origin one didn`t add n
                List<Integer> tmp = new ArrayList<>(res.get(i));
                tmp.add(n);
                res.add(tmp);
            }
        }
        return res;
    }
}
```