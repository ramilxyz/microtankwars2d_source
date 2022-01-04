package studio.mashkarik.microtankwars2d.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import studio.mashkarik.microtankwars2d.screen.GameScreen;

class PixelButton extends Pixmap {

    PixelButton(int[][] map) {
        super(22, 22, Pixmap.Format.RGBA8888);
        this.setColor(Color.BLACK);
        this.fill();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {

                if (map[j][i] == 1) {
                    this.setColor(Color.DARK_GRAY);
                    this.drawPixel(i, j);
                }

                if (map[j][i] == 2) {
                    this.setColor(GameScreen.FIRE_COLOR);
                    this.drawPixel(i, j);
                }

                if (map[j][i] == 3) {
                    this.setColor(Color.CYAN);
                    this.drawPixel(i, j);
                }

                if (map[j][i] == 4) {
                    this.setColor(Color.GREEN);
                    this.drawPixel(i, j);
                }

                if (map[j][i] == 50 || map[j][i] == 60 || map[j][i] == 70 || map[j][i] == 80 || map[j][i] == 85 || map[j][i] == 100) {
                    this.setColor(GameScreen.COLORRED);
                    this.drawPixel(i, j);
                }

                if (map[j][i] == 10 || map[j][i] == 20 || map[j][i] == 30 || map[j][i] == 40 || map[j][i] == 45 || map[j][i] == 200) {
                    this.setColor(GameScreen.COLORBLUE);
                    this.drawPixel(i, j);
                }
            }
        }
    }
}
