package studio.mashkarik.microtankwars2d.actor;

import com.badlogic.gdx.graphics.Color;

import studio.mashkarik.microtankwars2d.MicroTankWarsGame;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Bullet {

    private final int tankDirection;
    private final Color typeTank;
    private final Grid grid;
    private int positionStart;
    private boolean destroyBullet = false;
    private boolean clear = false;
    private float speed;

    Bullet(int positionStart, int tankDirection, Color typeTank, Grid grid) {

        this.positionStart = positionStart;
        this.tankDirection = tankDirection;
        this.typeTank = typeTank;
        this.grid = grid;
    }

    public void render(float v) {

        speed += v;

        if (speed > 0.015) {

            if (typeTank.equals(GameScreen.COLORBLUE) || typeTank.equals(GameScreen.USER_TANK_COLOR_BLUE)) {

                if (tankDirection == 1) {

                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {

                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED)) {
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        }
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE))
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;

                        clear = true;
                        destroyBullet = true;
                    }
                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORRED)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORRED) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                            GameScreen.blue++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart - 1].setColor(Color.CLEAR);

                    if (!grid.table[positionStart + 1].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart + 1;
                    }

                    //если бетон или свой танк
                    if (grid.table[positionStart + 1].getColor().equals(GameScreen.BRICK_COLOR)
                            || grid.table[positionStart + 1].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE) || grid.table[positionStart + 1].getColor().equals(GameScreen.COLORBLUE) || grid.table[positionStart + 1].getColor().equals(GameScreen.FIRE_COLOR)) {
                        grid.table[positionStart].setColor(Color.CLEAR);
                        grid.table[positionStart - 1].setColor(Color.CLEAR);

                        clear = true;
                        destroyBullet = true;
                    }
                }
                if (tankDirection == 2) {

                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED)) {
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        }


                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE))
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;
                        clear = true;
                        destroyBullet = true;
                    }

                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORRED)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORRED) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                            GameScreen.blue++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart - 112].setColor(Color.CLEAR);
                    if (!grid.table[positionStart + 112].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart + 112;
                    }

                    //если бетон или свой танк
                    if (grid.table[positionStart + 112].getColor().equals(GameScreen.BRICK_COLOR) ||
                            grid.table[positionStart + 112].getColor().equals(GameScreen.COLORBLUE)
                            || grid.table[positionStart + 112].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE) ||
                            grid.table[positionStart + 112].getColor().equals(GameScreen.FIRE_COLOR)) {

                        grid.table[positionStart].setColor(Color.CLEAR);
                        grid.table[positionStart - 112].setColor(Color.CLEAR);

                        clear = true;
                        destroyBullet = true;
                    }

                }

                if (tankDirection == 3) {

                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED)) {
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        }
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE))
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;
                        clear = true;
                        destroyBullet = true;
                    }

                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORRED)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORRED) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                            GameScreen.blue++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart + 1].setColor(Color.CLEAR);
                    if (!grid.table[positionStart - 1].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart - 1;
                    }

                    //если бетон или свой танк
                    if (grid.table[positionStart - 1].getColor().equals(GameScreen.BRICK_COLOR) ||
                            grid.table[positionStart - 1].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE) ||
                            grid.table[positionStart - 1].getColor().equals(GameScreen.COLORBLUE) || grid.table[positionStart - 1].getColor().equals(GameScreen.FIRE_COLOR)) {
                        grid.table[positionStart].setColor(Color.CLEAR);
                        grid.table[positionStart + 1].setColor(Color.CLEAR);

                        clear = true;
                        destroyBullet = true;
                    }

                }
                if (tankDirection == 4) {
                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED)) {
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        }
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE))
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;
                        clear = true;
                        destroyBullet = true;
                    }

                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORRED)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORRED) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_RED)) {
                            GameScreen.blue++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart + 112].setColor(Color.CLEAR);
                    if (!grid.table[positionStart - 112].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart - 112;
                    }

                    //если бетон или свой танк
                    if (grid.table[positionStart - 112].getColor().equals(GameScreen.BRICK_COLOR) ||
                            grid.table[positionStart - 112].getColor().equals(GameScreen.COLORBLUE) ||
                            grid.table[positionStart - 112].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE) ||
                            grid.table[positionStart - 112].getColor().equals(GameScreen.FIRE_COLOR)) {

                        grid.table[positionStart].setColor(Color.CLEAR);
                        grid.table[positionStart + 112].setColor(Color.CLEAR);

                        clear = true;
                        destroyBullet = true;
                    }

                }
            }

            if (typeTank.equals(GameScreen.COLORRED) || typeTank.equals(GameScreen.USER_TANK_COLOR_RED)) {


                if (tankDirection == 1) {

                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED))
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;
                        }
                        clear = true;
                        destroyBullet = true;
                    }
                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                            GameScreen.red++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart - 1].setColor(Color.CLEAR);
                    if (!grid.table[positionStart + 1].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart + 1;
                    }
                    //если бетон или свой танк
                    if (grid.table[positionStart + 1].getColor().equals(GameScreen.BRICK_COLOR) || grid.table[positionStart + 1].getColor().equals(GameScreen.USER_TANK_COLOR_RED) || grid.table[positionStart + 1].getColor().equals(GameScreen.COLORRED) || grid.table[positionStart + 1].getColor().equals(GameScreen.FIRE_COLOR)) {

                        grid.table[positionStart].setColor(Color.CLEAR);
                        grid.table[positionStart - 1].setColor(Color.CLEAR);

                        clear = true;
                        destroyBullet = true;
                    }
                }
                if (tankDirection == 2) {
                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED))
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;
                        }
                        clear = true;
                        destroyBullet = true;
                    }

                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                            GameScreen.red++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart - 112].setColor(Color.CLEAR);

                    if (!grid.table[positionStart + 112].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart + 112;
                    }

                    //если бетон или свой танк
                    if (grid.table[positionStart + 112].getColor().equals(GameScreen.BRICK_COLOR) ||
                            grid.table[positionStart + 112].getColor().equals(GameScreen.COLORRED) ||
                            grid.table[positionStart + 112].getColor().equals(GameScreen.USER_TANK_COLOR_RED)
                            || grid.table[positionStart + 112].getColor().equals(GameScreen.FIRE_COLOR)) {
                        grid.table[positionStart].setColor(Color.CLEAR);

                        grid.table[positionStart - 112].setColor(Color.CLEAR);

                        clear = true;
                        destroyBullet = true;
                    }

                }

                if (tankDirection == 3) {
                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED))
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;
                        }
                        clear = true;
                        destroyBullet = true;
                    }

                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                            GameScreen.red++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart + 1].setColor(Color.CLEAR);
                    if (!grid.table[positionStart - 1].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart - 1;
                    }

                    //если бетон или свой танк
                    if (grid.table[positionStart - 1].getColor().equals(GameScreen.BRICK_COLOR) ||
                            grid.table[positionStart - 1].getColor().equals(GameScreen.COLORRED) ||
                            grid.table[positionStart - 1].getColor().equals(GameScreen.USER_TANK_COLOR_RED) ||
                            grid.table[positionStart - 1].getColor().equals(GameScreen.FIRE_COLOR)) {

                        grid.table[positionStart].setColor(Color.CLEAR);
                        grid.table[positionStart + 1].setColor(Color.CLEAR);


                        clear = true;
                        destroyBullet = true;
                    }

                }
                if (tankDirection == 4) {
                    if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED) || grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_RED))
                            GameScreen.blue = GameScreen.blue + GameScreen.WIN_SCORE;
                        if (grid.table[positionStart].getColor().equals(GameScreen.FLAG_BLUE)) {
                            GameScreen.red = GameScreen.red + GameScreen.WIN_SCORE;
                        }
                        clear = true;
                        destroyBullet = true;
                    }

                    //если танк или дерево
                    if (grid.table[positionStart].getColor().equals(GameScreen.WOOD_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.FIRE_COLOR) ||
                            grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE)
                            || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                        if (grid.table[positionStart].getColor().equals(GameScreen.COLORBLUE) || grid.table[positionStart].getColor().equals(GameScreen.USER_TANK_COLOR_BLUE)) {
                            GameScreen.red++;
                        }
                        clear = true;
                        destroyBullet = true;

                    }

                    grid.table[positionStart].setColor(GameScreen.FIRE_COLOR);
                    grid.table[positionStart + 112].setColor(Color.CLEAR);
                    if (!grid.table[positionStart - 112].getColor().equals(GameScreen.BRICK_COLOR)) {
                        positionStart = positionStart - 112;
                    }

                    //если бетон или свой танк
                    if (grid.table[positionStart - 112].getColor().equals(GameScreen.BRICK_COLOR) ||
                            grid.table[positionStart - 112].getColor().equals(GameScreen.COLORRED) ||
                            grid.table[positionStart - 112].getColor().equals(GameScreen.USER_TANK_COLOR_RED) ||
                            grid.table[positionStart - 112].getColor().equals(GameScreen.FIRE_COLOR)) {
                        grid.table[positionStart].setColor(Color.CLEAR);
                        grid.table[positionStart + 112].setColor(Color.CLEAR);

                        clear = true;
                        destroyBullet = true;
                    }
                }
            }

            speed = 0;
        }
    }

    void clear() {

        if (positionStart - 1 > 0 && positionStart - 112 > 0) {
            grid.table[positionStart - 1].setColor(Color.CLEAR);
            grid.table[positionStart - 112].setColor(Color.CLEAR);
        }

        if (positionStart + 1 < GameScreen.ARRAY_SIZE && positionStart + 112 < GameScreen.ARRAY_SIZE) {
            grid.table[positionStart + 1].setColor(Color.CLEAR);
            grid.table[positionStart + 112].setColor(Color.CLEAR);
        }
        grid.table[positionStart].setColor(Color.CLEAR);
        clear = true;
    }

    boolean getDestory() {
        if (clear) {
            return destroyBullet;
        } else return false;
    }
}