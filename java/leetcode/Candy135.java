import java.util.*;

class Candy135 {
    public static void main(String[] args) {
        Candy135 candy = new Candy135();
        System.out.println(candy.candy3(new int[] { 1, 0, 1 }));
    }

    /**
     * solution 3 using two indicator up and down
     * 
     * @param ratings
     * @return
     */
    public int candy3(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }
        int sum = 1;
        int up = 0, down = 0, peek = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                down = 0;
                up++;
                sum += 1 + up;
                peek = up;
            } else if (ratings[i] == ratings[i - 1]) {
                up = peek = down = 0;
                sum += 1;
            } else {
                up = 0;
                down++;
                sum += down + (down >= peek ? 1 : 0);
            }
        }
        return sum;
    }

    /**
     * solution 2 using two arrary each representing
     * 
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        // get left max arrya
        left[0] = 1;
        for (int i = 1; i < left.length; i++) {
            left[i] = ratings[i] > ratings[i - 1] ? left[i - 1] + 1 : 1;
        }
        right[right.length - 1] = 1;
        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = ratings[i] > ratings[i + 1] ? right[i + 1] + 1 : 1;
        }
        // sum up
        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }

    /**
     * solution 1 o(n) time complexity o(n) space complexity
     * 
     * @param ratings
     * @return
     */
    public int candy1(int[] ratings) {
        if (ratings == null) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }
        List<Integer> indexs = new ArrayList<>();
        if (ratings[0] <= ratings[1]) {
            indexs.add(0);
        }
        for (int i = 1; i < ratings.length - 1; i++) {
            if (ratings[i] < ratings[i - 1] && ratings[i] < ratings[i + 1]
                    || ratings[i] == ratings[i - 1] && ratings[i] < ratings[i + 1]
                    || ratings[i] < ratings[i - 1] && ratings[i] == ratings[i + 1]) {
                indexs.add(i);
            }

        }
        int[] value = new int[ratings.length];
        if (ratings[ratings.length - 1] < ratings[ratings.length - 2]) {
            indexs.add(ratings.length - 1);
        }
        for (Integer index : indexs) {
            // flood left
            int l = index - 1;
            value[index] = 1;
            while (l >= 0 && ratings[l] > ratings[l + 1]) {
                value[l] = value[l + 1] + 1;
                l--;
            }
            // when conflict
            if (l > 0) {
                if (ratings[l + 1] == ratings[l]) {
                } else {
                    value[l + 1] = Math.max(value[l] + 1, value[l + 1]);
                }
                if (value[l + 1] == value[l]) {
                    while (l >= 0 && value[l] == 0) {
                        value[l] = 1;
                    }
                }
            }
            // flood right
            int r = index + 1;
            while (r < ratings.length && ratings[r] > ratings[r - 1]) {
                value[r] = value[r - 1] + 1;
                r++;
            }
            // when meet conflict
            if (r < ratings.length) {
                while (r < ratings.length && ratings[r] == ratings[r - 1]) {
                    value[r] = 1;
                    r++;
                }

            }
        }
        int sum = 0;
        for (int i = 0; i < value.length; i++) {
            sum += value[i];
        }
        return sum;
    }
}