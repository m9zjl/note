import java.util.*;
import java.lang.*;

public class MergeIntervals {

    public static void main(String[] args) {
        MedianOfTwoSortedArray4 ladder = new MedianOfTwoSortedArray4();
        int[] tmp0 = new int[] { 1, 3 };
        int[] tmp1 = new int[] { 4, 5 };
        int[][] tmp = new int[][] { tmp0, tmp1 };
        System.out.println(Arrays.toString(ladder.merge(tmp)));
    }

    public int[][] merge(int[][] intervals) {
        int[] left = new int[intervals.length];
        int[] right = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            left[i] = intervals[i][0];
            right[i] = intervals[i][1];
        }
        Arrays.sort(left);
        Arrays.sort(right);
        List<int[]> list = new ArrayList<>();
        int rIndex = 0;
        int lIndex = 0;
        for (int i = 1; i < left.length; i++) {
            while (i < left.length && left[i] <= right[rIndex]) {
                i++;
            }
            if (i == left.length) {
                i--;
            }
            while (right[rIndex] <= left[i]) {
                rIndex++;
            }
            if(rIndex == right.length){
                rIndex++;
            }
            int[] tmp = new int[2];
            tmp[0] = left[lIndex];
            tmp[1] = right[rIndex - 1];
            lIndex = i;
            list.add(tmp);
        }
        // int[] tmp = new int[2];
        // tmp[0] = left[lIndex];
        // tmp[1] = right[right.length - 1];
        // list.add(tmp);
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}