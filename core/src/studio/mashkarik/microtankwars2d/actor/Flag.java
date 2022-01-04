package studio.mashkarik.microtankwars2d.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;

import studio.mashkarik.microtankwars2d.MicroTankWarsGame;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Flag {

    private final Grid grid;
    private final Color type;
    private final int[] moveBuffer = new int[25];
    private final Sound flagWav;
    private int position;
    private float t = 0;

    public Flag(Grid grid, int position, Color type) {
        this.grid = grid;
        this.position = position;
        this.type = type;

        flagWav = Gdx.audio.newSound(Gdx.files.internal("sound/flag.wav"));

        initBlock(type, true);
    }

    private void initBlock(Color type, boolean create) {

        if (create) {

            int z = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = position; j < position + 5; j++) {
                    moveBuffer[z] = j;
                    z++;

                }
                if (position < grid.table.length)
                    position = position + 112;
            }
        }


        for (int i = 0; i < moveBuffer.length; i++) {
            grid.table[moveBuffer[i]].setColor(Color.CLEAR);
            if (t < 1) {
                if (!grid.table[moveBuffer[i]].getColor().equals(GameScreen.FIRE_COLOR)
                        && i != 16 && i != 17 && i != 18 && i != 19 && i != 21 && i != 22 && i != 23 && i != 24 && i != 9) {
                    grid.table[moveBuffer[i]].setColor(type);
                } else {
                    grid.table[moveBuffer[i]].setColor(Color.CLEAR);
                }
            }

            if (t > 1) {
                if (!grid.table[moveBuffer[i]].getColor().equals(GameScreen.FIRE_COLOR)
                        && i != 16 && i != 17 && i != 18 && i != 19 && i != 21 && i != 22 && i != 23 && i != 24 && i != 14 && i != 4) {
                    grid.table[moveBuffer[i]].setColor(type);
                } else {
                    grid.table[moveBuffer[i]].setColor(Color.CLEAR);
                }
            }

            if (t > 2) {
                t = 0;
            }

        }
    }

    public void render(float v) {
        t += v;

        initBlock(type, false);

        if (type.equals(GameScreen.FLAG_BLUE)) {
            for (int value : moveBuffer) {
                if (GameScreen.red >= GameScreen.WIN_SCORE) {
                    initBlock(GameScreen.FLAG_GREY, false);
                    disposeFlagSound();
                    GameScreen.GAMEOVER = true;
                }
            }

        }

        if (type.equals(GameScreen.FLAG_RED)) {
            for (int value : moveBuffer) {
                if (GameScreen.blue >= GameScreen.WIN_SCORE) {
                    initBlock(GameScreen.FLAG_GREY, false);
                    disposeFlagSound();
                    GameScreen.GAMEOVER = true;

                }
            }
        }
    }

    private void disposeFlagSound() {
        if (!GameScreen.GAMEOVER) {
            if (MicroTankWarsGame.preferences.getBoolean("sound"))
                flagWav.play();
        }
    }
}
