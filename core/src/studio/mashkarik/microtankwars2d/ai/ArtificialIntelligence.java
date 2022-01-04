package studio.mashkarik.microtankwars2d.ai;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Collections;

import studio.mashkarik.microtankwars2d.actor.Tank;

public class ArtificialIntelligence {

    private final Tank tank;
    private final ArrayList<Integer> wayArray = new ArrayList<>();
    private int centerPosition;
    private int i = 0;

    public ArtificialIntelligence(Tank tank) {
        this.tank = tank;

        getCenterPosition();
        wayArray.addAll(tank.getLee().wayArray(centerPosition, tank));
        Collections.reverse(wayArray);
    }

    private void getCenterPosition() {
        this.centerPosition = this.tank.getMoveBuffer()[12];
    }

    public void control() {

        try {
            getCenterPosition();
            if (wayArray.size() > 2) {
                if (centerPosition + 1 == wayArray.get(i)) { //right
                    tank.setMove(true);
                    tank.setRight(true);
                    tank.setLeft(false);
                    tank.setUpb(false);
                    tank.setDown(false);
                    wayArray.remove(i);
                    i++;
                } else if (centerPosition - 1 == wayArray.get(i)) { //left
                    tank.setMove(true);
                    tank.setRight(false);
                    tank.setLeft(true);
                    tank.setUpb(false);
                    tank.setDown(false);
                    wayArray.remove(i);
                    i++;
                } else if (centerPosition - 112 == wayArray.get(i)) {

                    tank.setMove(true);
                    tank.setRight(false);
                    tank.setLeft(false);
                    tank.setUpb(true);
                    tank.setDown(false);
                    wayArray.remove(i);
                    i++;
                } else if (centerPosition + 112 == wayArray.get(i)) { //up
                    tank.setMove(true);
                    tank.setRight(false);
                    tank.setLeft(false);
                    tank.setUpb(false);
                    tank.setDown(true);
                    wayArray.remove(i);
                    i++;
                } else {
                    i = 0;
                }
            }

        } catch (Exception e) {
            Gdx.app.log("ERROR::AI", "" + e.getMessage());
        }
    }
}




