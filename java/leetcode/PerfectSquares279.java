import java.util.Arrays;

public class PerfectSquares279 {

    public int numSquares(int n) {
        int[] map = new int[n + 1];
        for (int i = 0; i < map.length; i++) {
            map[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                map[i] = Math.min(1 + map[i - j * j], map[i]);
            }
        }
        return map[n];
    }

    public int numSquares1(int n) {
        // 4
        int tmp = n;
        while (tmp > 0 && tmp % 4 == 0) {
            tmp /= 4;
        }
        if ((tmp - 7) % 8 == 0) {
            return 4;
        }

        // 1
        tmp = (int) Math.sqrt(n);
        if (tmp * tmp == n) {
            return 1;
        }

        // 2
        for (int i = 1; i * i <= n / 2; i++) {
            tmp = (int) Math.sqrt(n - i * i);
            if (tmp * tmp == n - i * i) {
                return 2;
            }
        }
        // 3;
        return 3;

    }

    public static void main(String[] args) {
        PerfectSquares279 solution = new PerfectSquares279();
        System.out.println(solution.numSquares1(4));
    }
}
