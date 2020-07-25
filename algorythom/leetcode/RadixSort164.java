
public class RadixSort164 {

    public static void main(String[] args) {
        RadixSort164 solution = new RadixSort164();
        System.out.println( solution.maximumGap(new int[] { 1,10000000 }));
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        int maxLen = 0;
        while (max > 0) {
            maxLen++;
            max = max / 10;
        }
        int radix[][] = new int[10][nums.length];
        int radixIndexs[] = new int[10];
        for (int i = 0; i < maxLen; i++) {
            for (int num : nums) {
                int index = (num / (int) Math.pow(10, i)) % 10;
                radix[index][radixIndexs[index]++] = num;
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < radixIndexs[j]; k++) {
                    nums[index++] = radix[j][k];
                }
                radixIndexs[j] = 0;
            }
        }

        int maxGap = 0;
        for (int i = 1; i < nums.length; i++) {
            maxGap = Math.max(nums[i] - nums[i - 1], maxGap);
        }
        return maxGap;
    }
}