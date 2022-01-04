package studio.mashkarik.microtankwars2d.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import studio.mashkarik.microtankwars2d.actor.Grid;
import studio.mashkarik.microtankwars2d.actor.Tank;
import studio.mashkarik.microtankwars2d.maps.Parser;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Lee {

    private final int[][] matrix = new int[112][112];
    private final int[][] realMatrix = new int[112][112];
    private final ArrayList<Integer> wayArray = new ArrayList<>();
    private final Set<Integer> set = new HashSet<>();
    public ArrayList<Integer> searchArray = new ArrayList<>();
    private final ArrayList<Integer> lockElement = new ArrayList<>();
    private int startPosX;
    private int startPosY;
    private final Grid grid;

    public Lee(Grid grid) {

        wayArray.clear();
        searchArray.clear();
        lockElement.clear();

        set.addAll(GameScreen.lockElement);

        this.grid = grid;

        lockElement.addAll(set);

        Collections.sort(lockElement);

        for (int i = 0; i < GameScreen.ARRAY_SIZE; i++) {
            searchArray.add(i);
        }

        searchArray.removeAll(lockElement);

        initMatrix();
    }

    private void initMatrix() {
        int z = 0;
        for (int i = 0; i < GameScreen.TABLE_ROW_SIZE; i++) {
            for (int j = 0; j < GameScreen.TABLE_ROW_SIZE; j++) {
                realMatrix[i][j] = z++;
            }
        }
    }

    public ArrayList<Integer> wayArray(int centerPosition, Tank tank) {
        initLeeMatrix(centerPosition, tank);

        return wayArray;
    }

    private void initLeeMatrix(int centerPosition, Tank tank) {

        int z = 0;
        int k = 0;
        for (int i = 0; i < GameScreen.TABLE_ROW_SIZE; i++) {
            for (int j = 0; j < GameScreen.TABLE_ROW_SIZE; j++) {
                matrix[i][j] = z++;
                if (centerPosition == matrix[i][j]) {
                    startPosX = j;
                    startPosY = i;
                }

                if (lockElement.get(k) == matrix[i][j]) {
                    matrix[i][j] = -1;
                    k++;
                } else {
                    matrix[i][j] = -2;
                }
            }
        }


        if (tank.getType().equals(GameScreen.COLORBLUE) || tank.getType().equals(GameScreen.RESPAWN_BLUE_COLOR)) {
            for (int i = 0; i < GameScreen.TABLE_ROW_SIZE; i++) {
                for (int j = 0; j < GameScreen.TABLE_ROW_SIZE; j++) {
                    if (realMatrix[i][j] == Parser.RED_FLAG_POSITION) {
                        //    Gdx.app.log("Map.RED_FLAG_POSITION", "" + Map.RED_FLAG_POSITION);
                        //     grid.table[realMatrix[i + 2][j + 2]].setColor(Color.CYAN);

                        lee(startPosX, startPosY, j + 2, i + 2);
                    }
                }
            }
        }

        if (tank.getType().equals(GameScreen.COLORRED) || tank.getType().equals(GameScreen.RESPAWN_RED_COLOR)) {
            for (int i = 0; i < GameScreen.TABLE_ROW_SIZE; i++) {
                for (int j = 0; j < GameScreen.TABLE_ROW_SIZE; j++) {
                    if (realMatrix[i][j] == Parser.BLUE_FLAG_POSITION) {
                        //   Gdx.app.log("Map.BLUE_FLAG_POSITION", "" + Map.BLUE_FLAG_POSITION);
                        //     grid.table[realMatrix[i + 2][j + 2]].setColor(Color.CYAN);
                        lee(startPosX, startPosY, j + 2, i + 2);
                    }
                }
            }
        }
    }

    private boolean lee(int ax, int ay, int bx, int by) {
        wayArray.clear();
        int W = GameScreen.TABLE_ROW_SIZE;
        int H = GameScreen.TABLE_ROW_SIZE;
        int WALL = -1;
        int BLANK = -2;

        int[] px = new int[W * H];
        int[] py = new int[W * H];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int d, x, y, k;
        boolean stop;

        if (matrix[ay][ax] == WALL || matrix[by][bx] == WALL)
            return false;

        d = 0;
        matrix[ay][ax] = 0;
        do {
            stop = true;
            for (y = 0; y < H; ++y)
                for (x = 0; x < W; ++x)
                    if (matrix[y][x] == d) {
                        for (k = 0; k < 4; ++k) {
                            int iy = y + dy[k], ix = x + dx[k];
                            if (iy >= 0 && iy < H && ix >= 0 && ix < W &&
                                    matrix[iy][ix] == BLANK) {
                                stop = false;
                                matrix[iy][ix] = d + 1;
                            }
                        }
                    }
            d++;
        } while (!stop && matrix[by][bx] == BLANK);

        if (matrix[by][bx] == BLANK) return false;

        int len = matrix[by][bx];
        x = bx;
        y = by;
        d = len;
        while (d > 0) {
            px[d] = x;
            py[d] = y;

            try {
                wayArray.add(realMatrix[y][x]);
            } catch (Exception e) {
                Gdx.app.log("ERROR::LEE", "" + e.getMessage());
            }

            grid.table[realMatrix[y][x]].setColor(Color.CYAN);

            d--;
            for (k = 0; k < 4; ++k) {
                int iy = y + dy[k], ix = x + dx[k];
                if (iy >= 0 && iy < H && ix >= 0 && ix < W &&
                        matrix[iy][ix] == d) {
                    x = x + dx[k];
                    y = y + dy[k];
                    break;
                }
            }
        }
        return true;
    }
}
