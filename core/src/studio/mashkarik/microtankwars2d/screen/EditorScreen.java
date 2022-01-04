package studio.mashkarik.microtankwars2d.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.ArrayList;
import java.util.Arrays;

public class EditorScreen implements Screen {

    private final Image redTank = new Image(new Texture("editor/red_tank.png"));
    private final Image blueTank = new Image(new Texture("editor/blue_tank.png"));
    private final Image bush = new Image(new Texture("editor/bush.png"));
    private final Image wood = new Image(new Texture("editor/wood.png"));
    private final Image rock = new Image(new Texture("editor/rock.png"));
    private final Image redFlag = new Image(new Texture("editor/red_flag.png"));
    private final Image blueFlag = new Image(new Texture("editor/blue_flag.png"));
    private final Image empty = new Image(new Texture("editor/empty.png"));
    public int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50,},
            {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 70, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},};
    private final Stage stage;
    private final Game game;
    private final ArrayList<Integer> arrayBlueTank = new ArrayList<>(Arrays.asList(50, 60, 70, 80, 85));
    private final ArrayList<Integer> arrayRedTank = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 45));
    private int value = -1;

    public EditorScreen(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
        int x = 18;
        int y;

        redTank.setSize(18f, 18f);
        redTank.setPosition(500, 400);
        stage.addActor(redTank);

        InputListener inputRedTank = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 0;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        redTank.addListener(inputRedTank);

        blueTank.setSize(18f, 18f);
        blueTank.setPosition(518, 400);
        stage.addActor(blueTank);

        InputListener inputBlueTank = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 1;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        blueTank.addListener(inputBlueTank);

        rock.setSize(18f, 18f);
        rock.setPosition(500, 380);
        stage.addActor(rock);

        InputListener inputRock = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 2;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        rock.addListener(inputRock);

        bush.setSize(18f, 18f);
        bush.setPosition(518, 380);
        stage.addActor(bush);

        InputListener inputBush = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 3;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        bush.addListener(inputBush);

        wood.setSize(18f, 18f);
        wood.setPosition(500, 362);
        stage.addActor(wood);

        InputListener inputWood = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 4;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        wood.addListener(inputWood);


        empty.setSize(18f, 18f);
        empty.setPosition(518, 362);
        stage.addActor(empty);

        InputListener inputEmpty = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 5;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        empty.addListener(inputEmpty);

        redFlag.setSize(18f, 18f);
        redFlag.setPosition(500, 342);
        stage.addActor(redFlag);

        InputListener inputFlagRed = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 6;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        redFlag.addListener(inputFlagRed);

        blueFlag.setSize(18f, 18f);
        blueFlag.setPosition(518, 342);
        stage.addActor(blueFlag);

        InputListener inputFlagBlue = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                value = 7;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };

        blueFlag.addListener(inputFlagBlue);


        for (int i = 0; i < map.length; i++) {
            x = x + 18;
            y = 18;
            for (int j = map.length - 1; j >= 0; j--) {
                y = y + 18;
                final Image image;
                if (map[i][j] == 10)
                    image = new Image(new Texture("editor/red_tank.png"));
                else if (map[i][j] == 20)
                    image = new Image(new Texture("editor/red_tank.png"));
                else if (map[i][j] == 30)
                    image = new Image(new Texture("editor/red_tank.png"));
                else if (map[i][j] == 40)
                    image = new Image(new Texture("editor/red_tank.png"));
                else if (map[i][j] == 50)
                    image = new Image(new Texture("editor/blue_tank.png"));
                else if (map[i][j] == 60)
                    image = new Image(new Texture("editor/blue_tank.png"));
                else if (map[i][j] == 70)
                    image = new Image(new Texture("editor/blue_tank.png"));
                else if (map[i][j] == 80)
                    image = new Image(new Texture("editor/blue_tank.png"));
                else if (map[i][j] == 1)
                    image = new Image(new Texture("editor/rock.png"));
                else if (map[i][j] == 2)
                    image = new Image(new Texture("editor/wood.png"));
                else if (map[i][j] == 4)
                    image = new Image(new Texture("editor/bush.png.png"));
                else if (map[i][j] == 200)
                    image = new Image(new Texture("editor/red_flag.png"));
                else if (map[i][j] == 100)
                    image = new Image(new Texture("editor/blue_flag.png"));
                else image = new Image(new Texture("editor/empty.png"));
                image.setSize(18, 18);
                image.setPosition(x, y);

                final Integer ii = i;
                final Integer jj = j;

                image.addListener(new InputListener() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);

                        switch (value) {
                            case (0):
                                map[jj][ii] = arrayRedTank.get(0);
                                arrayRedTank.remove(0);
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/red_tank.png"))));
                                break;
                            case (1):
                                map[jj][ii] = arrayBlueTank.get(0);
                                arrayBlueTank.remove(0);
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/blue_tank.png"))));
                                break;
                            case (2):
                                map[jj][ii] = 1;
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/rock.png"))));
                                break;
                            case (3):
                                map[jj][ii] = 4;
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/bush.png"))));
                                break;
                            case (4):
                                map[jj][ii] = 2;
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/wood.png"))));
                                break;
                            case (5):
                                map[jj][ii] = 0;
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/empty.png"))));
                                break;
                            case (6):
                                map[jj][ii] = 200;
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/red_flag.png"))));
                                break;
                            case (7):
                                map[jj][ii] = 100;
                                image.setDrawable(new SpriteDrawable(new Sprite(new Texture("editor/blue_flag.png"))));
                                break;
                            default:
                                break;
                        }

                        for (int i = 0; i < map.length; i++) {
                            if (i == 0)
                                System.out.println();
                            System.out.println();
                            for (int j = 0; j < map.length; j++) {
                                if (j == 0)
                                    System.out.print("{" + map[i][j] + ",");
                                else if (j == map.length - 1)
                                    System.out.print(map[i][j] + "},");
                                else System.out.print(map[i][j] + ",");
                            }
                        }

                    }

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                });
                stage.addActor(image);

            }
        }

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(67 / 255f, 163 / 255f, 104 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        redTank.setColor(redTank.getColor().r, redTank.getColor().g, redTank.getColor().b, 1f);
        blueTank.setColor(blueTank.getColor().r, blueTank.getColor().g, blueTank.getColor().b, 1f);
        rock.setColor(rock.getColor().r, rock.getColor().g, rock.getColor().b, 1f);
        bush.setColor(bush.getColor().r, bush.getColor().g, bush.getColor().b, 1f);
        wood.setColor(wood.getColor().r, wood.getColor().g, wood.getColor().b, 1f);
        empty.setColor(empty.getColor().r, empty.getColor().g, empty.getColor().b, 1f);
        redFlag.setColor(redFlag.getColor().r, redFlag.getColor().g, redFlag.getColor().b, 1f);
        blueFlag.setColor(blueFlag.getColor().r, blueFlag.getColor().g, blueFlag.getColor().b, 1f);

        switch (value) {
            case (0):
                redTank.setColor(redTank.getColor().r, redTank.getColor().g, redTank.getColor().b, 0.5f);
                break;
            case (1):
                blueTank.setColor(blueTank.getColor().r, blueTank.getColor().g, blueTank.getColor().b, 0.5f);
                break;
            case (2):
                rock.setColor(rock.getColor().r, rock.getColor().g, rock.getColor().b, 0.5f);
                break;
            case (3):
                bush.setColor(bush.getColor().r, bush.getColor().g, bush.getColor().b, 0.5f);
                break;
            case (4):
                wood.setColor(wood.getColor().r, wood.getColor().g, wood.getColor().b, 0.5f);
                break;
            case (5):
                empty.setColor(empty.getColor().r, empty.getColor().g, empty.getColor().b, 0.5f);
                break;
            case (6):
                redFlag.setColor(redFlag.getColor().r, redFlag.getColor().g, redFlag.getColor().b, 0.5f);
                break;
            case (7):
                blueFlag.setColor(blueFlag.getColor().r, blueFlag.getColor().g, blueFlag.getColor().b, 0.5f);
                break;
            default:
                break;
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
