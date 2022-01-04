 package studio.mashkarik.microtankwars2d.user;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import studio.mashkarik.microtankwars2d.actor.Tank;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Control {
    private Tank tankUser;

    private Touchpad touchpad;
    private static Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;

    private Image fireBtn;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private boolean isFire = false;


    Texture fireTexture;
    Texture fireTextureDown;

    public Control(Tank tankUser, Stage stage) {

        touchpadSkin = new Skin();
        touchpadSkin.add("touchKnob", new Texture("touch_knob.png"));

        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");;

        Pixmap background = new Pixmap(200, 200, Pixmap.Format.RGBA8888);
        background.setBlending(Pixmap.Blending.None);
        background.setColor(1, 1, 1, .1f);
        background.fillCircle(100, 100, 100);

        touchpadStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(background)));

        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(20, 170, 140, 140);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // This is run when anything is changed on this actor.
                float X =  ((Touchpad) actor).getKnobPercentX();
                float Y =  ((Touchpad) actor).getKnobPercentY();

                if(X>0&&X>Math.abs(Y)) {
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    right = true;
                } else if(X<0&&Math.abs(X)>Math.abs(Y)) {
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    left = true;
                } else  if(Y>Math.abs(X)&&Y>0) {
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    up = true;
                } else if (Y < 0 && Math.abs(Y) > Math.abs(X)) {
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    down = true;
                } else {
                    up = false;
                    down = false;
                    left = false;
                    right = false;
                    down = false;
                }

                Gdx.app.log("X "+X, "Y "+Y);
            }
        });

        fireTexture = new Texture("btn_fire.png");
        fireTextureDown = new Texture("btn_fire_down.png");


        fireBtn = new Image(fireTexture);

        fireBtn.setAlign(Align.bottom);

        fireBtn.setPosition(648, 180);
        stage.addActor(fireBtn);


        fireBtn.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                fireBtn.setDrawable(new SpriteDrawable(new Sprite(fireTexture)));
                isFire = false;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fireBtn.setDrawable(new SpriteDrawable(new Sprite(fireTextureDown)));
                isFire = true;
                return true;
            }

        });

        stage.addActor(touchpad);
        this.tankUser = tankUser;
    }

    public void render() {
        if (tankUser != null) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                if (GameScreen.MAP != null && GameScreen.TEAM_TYPE == -1) {
                    GameScreen.MAP = null;
                    GameScreen.labelGold.setText("");
                }

                if (!GameScreen.SELECT_MAP) {
                    GameScreen.OPENMENU = !GameScreen.OPENMENU;

                } else {
                    GameScreen.SELECT_MAP = false;
                    GameScreen.labelGold.setText("");
                }

                if (!GameScreen.GAMEOVER && GameScreen.MAP != null) {
                    GameScreen.labelGold.setText("        Pause");
                }
            }
            if (up) {
                tankUser.setMove(true);
                tankUser.setRight(false);
                tankUser.setLeft(false);
                tankUser.setUpb(true);
                tankUser.setDown(false);
            } else if (down) {
                tankUser.setMove(true);
                tankUser.setRight(false);
                tankUser.setLeft(false);
                tankUser.setUpb(false);
                tankUser.setDown(true);
            } else if (left) {
                tankUser.setMove(true);
                tankUser.setRight(false);
                tankUser.setLeft(true);
                tankUser.setUpb(false);
                tankUser.setDown(false);
            } else if (right) {
                tankUser.setMove(true);
                tankUser.setRight(true);
                tankUser.setLeft(false);
                tankUser.setUpb(false);
                tankUser.setDown(false);
            } else {
                tankUser.setMove(false);
            }

//            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//                tankUser.setMove(true);
//                tankUser.setRight(false);
//                tankUser.setLeft(false);
//                tankUser.setUpb(true);
//                tankUser.setDown(false);
//            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//                tankUser.setMove(true);
//                tankUser.setRight(false);
//                tankUser.setLeft(false);
//                tankUser.setUpb(false);
//                tankUser.setDown(true);
//            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//                tankUser.setMove(true);
//                tankUser.setRight(false);
//                tankUser.setLeft(true);
//                tankUser.setUpb(false);
//                tankUser.setDown(false);
//            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//                tankUser.setMove(true);
//                tankUser.setRight(true);
//                tankUser.setLeft(false);
//                tankUser.setUpb(false);
//                tankUser.setDown(false);
//            } else {
//                tankUser.setMove(false);
//            }

//            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//                if (!tankUser.getRespawn())
//                    tankUser.fire();
//            }

            if (isFire) {
                if (!tankUser.getRespawn())
                    tankUser.fire();
            }
        }
    }

    public void setTankUser(Tank tankUser) {
        this.tankUser = tankUser;
    }

}
