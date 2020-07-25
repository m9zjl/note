
public class Dp174 {

    public static void main(String[] args) {
        Dp174 dp174 = new Dp174();
        int[][] map = new int[][] { { 1, -4, 5, -99 }, { 2, -2, -2, -1 } };

        dp174.calculateMinimumHP(map);
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int[] map = new int[dungeon[0].length];
        int[] minValue = new int[dungeon[0].length];
        map[0] = dungeon[0][0];
        minValue[0] = dungeon[0][0] > 0 ? 1 : dungeon[0][0];
        for (int i = 1; i < dungeon[0].length; i++) {
            map[i] = dungeon[0][i] + map[i - 1];
            minValue[i] = Math.max(minValue[i], map[i] < 0 ? 1 - map[i] : 1);
        }
        for (int i = 1; i < dungeon.length; i++) {
            map[0] += dungeon[i][0];
            minValue[0] = Math.min(minValue[0], map[0]);
            for (int j = 1; j < dungeon[0].length; j++) {

                

                minValue[j] = Math.max(Math.min(minValue[j], map[j] + dungeon[i][j]),
                        Math.min(minValue[j - 1], map[j - 1] + dungeon[i][j]));
                map[j] = Math.max(map[j - 1], map[j]) + dungeon[i][j];
            }
        }
        int value = 1 - minValue[minValue.length - 1];
        return value > 0 ? 1 : value;
    }

}