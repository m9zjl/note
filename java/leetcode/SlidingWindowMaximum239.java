
public class SlidingWindowMaximum239 {

    public static void main(String[] args) {
        int[] in = new int[] { 2, 1, 3, 4, 6, 3, 8, 9, 10, 12, 56 };
        int[] map = (SlidingWindowMaximum239.slidingWindowMax(in, 4));
        for (int i = 0; i < map.length; i++) {
            System.out.println(map[i]);
        }

        SlidingWindowMaximum239.topK(in, 4);
    }

    public static void topK(int[] in, int w) {
        int[] map = new int[w];
        for (int i = 0; i < w; i++) {
            map[i] = Integer.MIN_VALUE;
        }
        // build heap
        for (int i = 0; i < in.length; i++) {
            int val = in[i];
            if (val > map[i]) {
                map[0] = val;
                sort(map);
            }
        }
    }

    private static void sort(int[] map) {
        int i = 0;
        while (2 * i + 1 < map.length && map[i] > map[2 * i + 1]) {
            swap(map, i, 2 * i + 1);
            int j = i;
            while (j * 2 + 2 < map.length && map[j] > map[2 * i + 2]) {
                swap(map, j, j * 2 + 2);
                j = j * 2 + 2;
            }
            i = i * 2 + 1;
        }
    }

    private static void swap(int[] map, int l, int r) {
        int tmp = map[l];
        map[l] = map[r];
        map[r] = tmp;
    }

    public static int[] slidingWindowMax(final int[] in, final int w) {
        int[] left = new int[in.length];
        int[] right = new int[in.length];
        int[] map = new int[in.length + w - 1];
        left[0] = in[0];
        right[in.length - 1] = in[in.length - 1];
        for (int i = 1; i < in.length; i++) {
            left[i] = i % w == 0 ? in[i] : Math.max(left[i - 1], in[i]);
            int j = in.length - i - 1;
            right[j] = j % w == 0 ? in[j] : Math.max(right[j + 1], in[j]);
        }
        for (int i = 0, j = 0; i + w <= in.length; i++) {
            map[j++] = Math.max(right[i], left[i + w - 1]);
        }
        return map;
    }

}
