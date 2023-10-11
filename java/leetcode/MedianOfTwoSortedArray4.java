import java.util.*;
import java.lang.*;

public class MedianOfTwoSortedArray4 {

    public static void main(String[] args) {
        MedianOfTwoSortedArray4 ladder = new MedianOfTwoSortedArray4();
        int[] tmp0 = new int[] { 2 };
        int[] tmp1 = new int[] {};
        int[][] tmp = new int[][] { tmp0, tmp1 };
        System.out.println(ladder.findMedianSortedArrays(tmp0, tmp1));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2,nums1);
        }

        int leftX = 0;
        int rightX = nums1.length;
        int avgX = (rightX + leftX) / 2;
        int lengthX = nums1.length;
        int lengthY = nums2.length;
        int rightY = nums2.length;
        int avgY = (lengthX + lengthY + 1) / 2 - avgX;

        int leftXV = avgX == 0 ? Integer.MIN_VALUE : nums1[avgX - 1];
        int leftYV = avgY == 0 ? Integer.MIN_VALUE : nums2[avgY - 1];
        int rightXV = avgX == lengthX ? Integer.MAX_VALUE : nums1[avgX];
        int rightYV = avgY == lengthY ? Integer.MAX_VALUE : nums2[avgY];

        while (!(leftXV <= rightYV && leftYV <= rightXV)) {
            if (leftXV <= rightYV) {
                leftX = avgX + 1;
            } else {
                rightX = avgX - 1;
            }
            avgX = (rightX + leftX) / 2;
            avgY = (lengthX + lengthY + 1) / 2 - avgX;
            leftXV = avgX == 0 ? Integer.MIN_VALUE : nums1[avgX - 1];
            leftYV = avgY == 0 ? Integer.MIN_VALUE : nums2[avgY - 1];
            rightXV = avgX == lengthX ? Integer.MAX_VALUE : nums1[avgX];
            rightYV = avgY == lengthY ? Integer.MAX_VALUE : nums2[avgY];
        }
        if ((lengthX + lengthY) % 2 == 1) {
            return Math.max(leftXV, leftYV);
        } else {
            return (Math.max(leftXV, leftYV) + Math.min(rightXV, rightYV)) / 2.0;
        }
    }
}