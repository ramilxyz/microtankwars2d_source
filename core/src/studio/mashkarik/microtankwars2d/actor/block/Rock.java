package studio.mashkarik.microtankwars2d.actor.block;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

import studio.mashkarik.microtankwars2d.actor.Grid;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Rock {
    private final Grid grid;
    private final Color type;
    private final ArrayList<Integer> moveBuffer = new ArrayList<>();
    private final ArrayList<Integer> collisionArrayList = new ArrayList<>();
    private int position;
    private boolean aCollis = false;
    private boolean eCollis = false;
    private boolean dCollis = false;
    private boolean mCollis = false;

    public Rock(Grid grid, int position, Color type) {
        this.grid = grid;
        this.position = position;
        this.type = type;
        initBlockCollision();
        initBlock();
    }

    private void initBlockCollision() {

        int xCollisionPosition = position;
        int xCollisionPositionS = position - 1 - 1;
        int collisionPositionA = position - 112 - 112 - 1 - 1;
        int collisionPositionB = position - 112 - 112;
        int collisionPositionC = position - 112 - 112 + 1 + 1 + 1 + 1;
        int collisionPositionD = position - 112 - 112 + 1 + 1 + 1 + 1 + 1 + 1;

        int collisionPositionE = collisionPositionA + 112 * 8;
        int collisionPositionF = collisionPositionB + 112 * 8;
        int collisionPositionG = collisionPositionC + 112 * 8;
        int collisionPositionM = collisionPositionD + 112 * 8;

        for (int i = 0; i < GameScreen.borderArray.size(); i++) {

            int ii = GameScreen.borderArray.get(i);

            if (collisionPositionA == ii) {
                aCollis = true;
            }
            if (collisionPositionE == ii) {
                eCollis = true;
            }
            if (collisionPositionD == ii) {
                dCollis = true;
            }
            if (collisionPositionM == ii) {
                mCollis = true;
            }
        }

        //        _____
        //        |0

        if (xCollisionPosition == 73) {
            for (int i = 0; i < 7; i++) {
                for (int j = xCollisionPosition; j < xCollisionPosition + 7; j++) {
                    collisionArrayList.add(j);
                }
                xCollisionPosition = xCollisionPosition + 112;
            }

        }
        //             |
        //             |0
        //             |
        else if (aCollis && eCollis && collisionPositionB > 0 && collisionPositionF > 0 && xCollisionPosition != 73 && xCollisionPosition != 4753 && xCollisionPosition != 4818 && xCollisionPosition != 138) {
            for (int i = 0; i < 9; i++) {
                for (int j = collisionPositionB; j < collisionPositionB + 7; j++) {
                    collisionArrayList.add(j);
                }
                collisionPositionB = collisionPositionB + 112;
            }

        }
        //         |0
        //         -----
        else if (xCollisionPosition == 4753) {

            for (int i = 0; i < 7; i++) {
                for (int j = collisionPositionB; j < collisionPositionB + 7; j++) {
                    collisionArrayList.add(j);
                }
                collisionPositionB = collisionPositionB + 112;
            }

        }
        //              0
        //         ------------
        else if (collisionPositionE > GameScreen.ARRAY_SIZE && collisionPositionM > GameScreen.ARRAY_SIZE && collisionPositionA > 0 && collisionPositionD > 0 & xCollisionPosition != 73 && xCollisionPosition != 4753 && xCollisionPosition != 4818 && xCollisionPosition != 138) {
            for (int i = 0; i < 7; i++) {
                for (int j = collisionPositionA; j < collisionPositionA + 9; j++) {
                    collisionArrayList.add(j);
                }
                collisionPositionA = collisionPositionA + 112;
            }

        }
        //               0|
        //          -------
        else if (xCollisionPosition == 4818) {
            for (int i = 0; i < 7; i++) {
                for (int j = collisionPositionA; j < collisionPositionA + 7; j++) {
                    collisionArrayList.add(j);
                }
                collisionPositionA = collisionPositionA + 112;
            }

        }
        //          |
        //         0|
        //          |
        else if (mCollis && dCollis & xCollisionPosition != 73 && xCollisionPosition != 4753 && xCollisionPosition != 4818 && xCollisionPosition != 138) {
            for (int i = 0; i < 9; i++) {
                for (int j = collisionPositionA; j < collisionPositionA + 7; j++) {
                    collisionArrayList.add(j);
                }
                collisionPositionA = collisionPositionA + 112;
            }

        }
        //         ________
        //                0|
        //                 |
        //                 |
        else if (xCollisionPosition == 138) {
            for (int i = 0; i < 7; i++) {
                for (int j = xCollisionPositionS; j < xCollisionPositionS + 7; j++) {
                    collisionArrayList.add(j);
                }
                xCollisionPositionS = xCollisionPositionS + 112;
            }

        }
        //        ___________
        //             0
        else if (collisionPositionA < 0 && collisionPositionD < 0 && collisionPositionE > 0 && collisionPositionM > 0) {
            for (int i = 0; i < 7; i++) {
                for (int j = xCollisionPositionS; j < xCollisionPositionS + 9; j++) {
                    collisionArrayList.add(j);
                }
                xCollisionPositionS = xCollisionPositionS + 112;
            }

        } else {

            for (int i = 0; i < 9; i++) {
                for (int j = collisionPositionA; j < collisionPositionA + 9; j++) {
                    collisionArrayList.add(j);
                }
                collisionPositionA = collisionPositionA + 112;
            }
        }
        GameScreen.lockElement.addAll(collisionArrayList);
    }

    private void initBlock() {

        for (int i = 0; i < 5; i++) {
            for (int j = position; j < position + 5; j++) {
                moveBuffer.add(j);
            }
            position = position + 112;
        }
    }

    private void initColorBlock() {
        for (int i = 0; i < moveBuffer.size(); i++) {
            if (grid.table.length > moveBuffer.get(i))
                if (!grid.table[moveBuffer.get(i)].getColor().equals(GameScreen.FIRE_COLOR))
                    if (i % 2 == 0)
                        grid.table[moveBuffer.get(i)].setColor(type);
                    else
                        grid.table[moveBuffer.get(i)].setColor(new Color(57 / 255f, 91 / 255f, 94 / 255f, 1));
        }
    }

    public void render(float v) {
        initColorBlock();
    }
}
