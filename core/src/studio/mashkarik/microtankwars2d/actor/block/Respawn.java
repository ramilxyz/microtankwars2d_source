package studio.mashkarik.microtankwars2d.actor.block;

import java.util.ArrayList;

import studio.mashkarik.microtankwars2d.actor.Grid;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Respawn {
    Grid grid;
    int position;

    String direction;

    ArrayList<Integer> moveBuffer = new ArrayList<>();

    public Respawn(Grid gird, int position, String direction) {
        this.grid = gird;
        this.position = position;
        this.direction = direction;

        initBlock();
    }

    private void initBlock() {

        for (int i = 0; i < 5; i++) {
            for (int j = position; j < position + 5; j++) {
                moveBuffer.add(j);
            }
            position = position + 112;
        }
        initColorBlock();
    }

    private void initColorBlock() {
        for (int i = 0; i < moveBuffer.size(); i++) {

            if (i != 0 && i != 1 && i != 2 && i != 3 && i != 4 && i != 9 && i != 14 && i != 19 && i != 24 && i != 23 && i != 22 && i != 21 && i != 20 && i != 15 && i != 10 && i != 5) {
                if (direction.equals("down") || direction.equals("upb")) {
                    if (i != 1 && i != 6 && i != 11 && i != 16 && i != 21 &&
                            i != 3 && i != 8 && i != 13 && i != 18 && i != 23)
                        if (grid.table.length > moveBuffer.get(i))
                            grid.table[moveBuffer.get(i)].setColor(GameScreen.RESPAWN_BLOCK_COLOR1);
                        else if (grid.table.length > moveBuffer.get(i))
                            grid.table[moveBuffer.get(i)].setColor(GameScreen.RESPAWN_BLOCK_COLOR0);
                }

                if (direction.equals("left") || direction.equals("right")) {
                    if (i != 5 && i != 6 && i != 7 && i != 8 && i != 9 &&
                            i != 15 && i != 16 && i != 17 && i != 18 && i != 19)
                        grid.table[moveBuffer.get(i)].setColor(GameScreen.RESPAWN_BLOCK_COLOR1);
                    else
                        grid.table[moveBuffer.get(i)].setColor(GameScreen.RESPAWN_BLOCK_COLOR0);
                }
            }
        }
    }

    public void render(float v) {
        initColorBlock();
    }
}
