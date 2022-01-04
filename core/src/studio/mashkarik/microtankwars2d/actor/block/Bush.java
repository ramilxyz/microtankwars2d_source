package studio.mashkarik.microtankwars2d.actor.block;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

import studio.mashkarik.microtankwars2d.actor.Grid;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Bush {

    private final Grid grid;
    private final Color type;
    ArrayList<Integer> moveBuffer = new ArrayList<>();
    private int position;
    private boolean anim = true;
    private float animation_time = 0;

    public Bush(Grid grid, int position, Color type) {
        this.grid = grid;
        this.position = position;
        this.type = type;
        initBlock();
    }

    private void initBlock() {
        for (int i = 0; i < 5; i++) {
            for (int j = position; j < position + 5; j++) {
                moveBuffer.add(j);
            }
            position = position + 112;
        }
    }

    private void initColorBlock(boolean anim) {

        for (int i = 0; i < moveBuffer.size(); i++) {
            if (grid.table.length > moveBuffer.get(i)) {
                grid.table[moveBuffer.get(i)].setColor(Color.CLEAR);

                if (i % 2 == 0) {
                    if (!grid.table[moveBuffer.get(i)].getColor().equals(GameScreen.FIRE_COLOR))
                        if (anim)
                            grid.table[moveBuffer.get(i)].setColor(type);
                } else {

                    if (!anim) {
                        grid.table[moveBuffer.get(i)].setColor(type);
                    }
                }
            }
        }
    }

    public void render(float v) {
        animation_time += v;
        if (animation_time < 1) {
            anim = false;
        } else if (animation_time < 2) {
            anim = true;
        } else if (animation_time > 2) {
            animation_time = 0;
        }
        initColorBlock(anim);
    }
}


