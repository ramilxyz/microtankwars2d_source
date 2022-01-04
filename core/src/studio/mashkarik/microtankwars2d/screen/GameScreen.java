package studio.mashkarik.microtankwars2d.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;

import studio.mashkarik.microtankwars2d.actor.Grid;
import studio.mashkarik.microtankwars2d.actor.Menu;
import studio.mashkarik.microtankwars2d.actor.Tank;
import studio.mashkarik.microtankwars2d.maps.Parser;
import studio.mashkarik.microtankwars2d.user.Control;

public class GameScreen implements Screen {

    public static final int ARRAY_SIZE = 12544; // TABLE_ROW_SIZExTABLE_ROW_SIZE
    public static final int TABLE_ROW_SIZE = 112;
    public static Control userControl;
    public static int TEAM_TYPE = -1;
    public static int red = 0;
    public static int blue = 0;
    public static int WIN_SCORE = 20;
    public static boolean GAMEOVER = false;
    public static boolean OPENMENU = false;

    public static boolean ROUND_ONE = false;
    public static boolean ROUND_TWO = false;
    public static boolean ROUND_THREE = false;

    public static boolean CLOSE_VICTORY = false;

    public static ArrayList<Integer> borderArray = new ArrayList<>();
    public static ArrayList<Integer> lockElement = new ArrayList<>();
    public static ArrayList<Integer> border = new ArrayList<>();

    public static boolean FIRST_START = true;

    public static Color RESPAWN_BLOCK_COLOR0 = new Color(30 / 255f, 30 / 255f, 30 / 255f, 0.5f);
    public static Color RESPAWN_BLOCK_COLOR1 = new Color(80f / 255f, 80 / 255f, 80 / 255f, 0.5f);

    public static Color WOOD_COLOR = new Color(94 / 255f, 60 / 255f, 9 / 255f, 1);
    public static Color ICE_COLOR = new Color(58 / 255f, 115 / 255f, 180 / 255f, 1);
    public static Color BUSH_COLOR = new Color(20 / 255f, 107 / 255f, 23 / 255f, 1f);

    public static Color BRICK_COLOR = Color.GRAY;

    public static Color FLAG_BLUE = new Color(20 / 255f, 50 / 255f, 200 / 255f, 1f);
    public static Color FLAG_RED = new Color(200 / 255f, 0, 0f, 0.8f);

    public static Color FLAG_GREY = new Color(170 / 255f, 170 / 255f, 170 / 255f, 1f);

    public static Color USER_TANK_COLOR_RED = new Color(230 / 255f, 100 / 255f, 45 / 255f, 1f);
    public static Color RESPAWN_USER_RED_COLOR = new Color(230 / 255f, 100 / 255f, 45 / 255f, 0.5f);

    public static Color USER_TANK_COLOR_BLUE = new Color(130 / 255f, 130 / 255f, 230 / 255f, 1f);
    public static Color RESPAWN_USER_BLUE_COLOR = new Color(130 / 255f, 130 / 255f, 230 / 255f, 0.5f);

    public static Color RESPAWN_BLUE_COLOR = new Color(74 / 255f, 65 / 255f, 209 / 255f, 0.5f);
    public static Color COLORBLUE = new Color(74 / 255f, 65 / 255f, 209 / 255f, 1);

    public static Color FIRE_COLOR = new Color(255 / 255f, 153 / 255f, 0 / 255f, 1);

    public static Color RESPAWN_RED_COLOR = new Color(169 / 255f, 20 / 255f, 20 / 255f, 0.5f);
    public static Color COLORRED = new Color(169 / 255f, 20 / 255f, 20 / 255f, 1);

    public static int RED_SCORE = 0;
    public static int BLUE_SCORE = 0;

    public static boolean SELECT_MAP = false;

    public static int[][] MAP = null;
    public static Label labelGold;
    public static Label roundOneBlueLabel;
    public static Label roundOneRedLabel;
    public static Label roundTwoBlueLabel;
    public static Label roundTwoRedLabel;
    public static Label roundThreeBlueLabel;
    public static Label roundThreeRedLabel;

    public static float tvictory = 0;
    private static Parser parser;
    private final Stage stage;
    float t = 0;
    float loadingAnimation_t;
    Menu menu;
    private boolean round = false;
    private Label.LabelStyle labelStyleBlueBig;
    private Label.LabelStyle labelStyleRedBig;
    private Label.LabelStyle labelStyleGoldBig;
    private Label.LabelStyle labelStyleBlue;
    private Label.LabelStyle labelStyleRed;
    private Label.LabelStyle labelStyleGold;

    private Grid grid;
    private Label labelBlueVictory;
    private Label labelRedVictory;
    private Label labelTie;
    private float victory_t;
    private Label labelBlue;
    private Label labelRed;

    public GameScreen(Game game, Stage stage) {
        GameScreen gameScreen = this;
        this.stage = stage;
        Tank tank = null;
        userControl = new Control(tank, stage);
        initActor();
    }

    private void initActor() {

        grid = new Grid(stage);

        BitmapFont font = new BitmapFont(Gdx.files.internal("font/24.fnt"),
                Gdx.files.internal("font/24.png"), false);


        BitmapFont fontBig = new BitmapFont(Gdx.files.internal("font/24.fnt"),
                Gdx.files.internal("font/24.png"), false);

        labelStyleBlueBig = new Label.LabelStyle(fontBig, GameScreen.COLORBLUE);
        labelStyleRedBig = new Label.LabelStyle(fontBig, GameScreen.COLORRED);
        labelStyleGoldBig = new Label.LabelStyle(fontBig, Color.GOLD);

        labelStyleBlue = new Label.LabelStyle(font, GameScreen.COLORBLUE);
        labelStyleRed = new Label.LabelStyle(font, GameScreen.COLORRED);
        labelStyleGold = new Label.LabelStyle(font, Color.GOLD);

        creatWorld();
    }

    private void creatWorld() {

        Texture scoreLayoutTexture = new Texture("score_layout.png");
        Image scoreLayout = new Image(scoreLayoutTexture);
        scoreLayout.setPosition(651, 342);
        stage.addActor(scoreLayout);

        labelBlueVictory = new Label("victory", labelStyleBlueBig);
        labelBlueVictory.setName("label");
        labelBlueVictory.setPosition(355, 230);
        labelBlueVictory.setVisible(false);
        stage.addActor(labelBlueVictory);

        labelRedVictory = new Label("victory", labelStyleRedBig);
        labelRedVictory.setName("label");
        labelRedVictory.setPosition(355, 230);
        labelRedVictory.setVisible(false);
        stage.addActor(labelRedVictory);

        labelTie = new Label("   Tie", labelStyleGoldBig);
        labelTie.setName("label");
        labelTie.setPosition(360, 230);
        labelTie.setVisible(false);
        stage.addActor(labelTie);

        labelBlue = new Label("", labelStyleBlue);
        labelBlue.setName("label");
        labelBlue.setPosition(185, 468);
        stage.addActor(labelBlue);

        labelRed = new Label("", labelStyleRed);
        labelRed.setName("label");
        labelRed.setPosition(603, 468);
        stage.addActor(labelRed);

        labelGold = new Label("", labelStyleGold);
        labelGold.setName("label");
        labelGold.setPosition(302, 468);
        stage.addActor(labelGold);

        //----------------------One------------------------------

        Label roundOneLabel = new Label("1", labelStyleGold);
        roundOneLabel.setName("label");
        roundOneLabel.setPosition(660, 405);

        roundOneBlueLabel = new Label("--", labelStyleBlue);
        roundOneBlueLabel.setName("label");
        roundOneBlueLabel.setPosition(685, 405);

        Label roundOneLabelDiff = new Label("/", labelStyleGold);
        roundOneLabelDiff.setName("label");
        roundOneLabelDiff.setPosition(720, 405);

        roundOneRedLabel = new Label("--", labelStyleRed);
        roundOneRedLabel.setName("label");
        roundOneRedLabel.setPosition(735, 405);

        stage.addActor(roundOneLabel);
        stage.addActor(roundOneRedLabel);
        stage.addActor(roundOneBlueLabel);
        stage.addActor(roundOneLabelDiff);

        //----------------------Two------------------------------

        Label roundTwoLabel = new Label("2", labelStyleGold);
        roundTwoLabel.setName("label");
        roundTwoLabel.setPosition(660, 375);

        roundTwoBlueLabel = new Label("--", labelStyleBlue);
        roundTwoBlueLabel.setName("label");
        roundTwoBlueLabel.setPosition(685, 375);

        Label roundTwoLabelDiff = new Label("/", labelStyleGold);
        roundTwoLabelDiff.setName("label");
        roundTwoLabelDiff.setPosition(720, 375);

        roundTwoRedLabel = new Label("--", labelStyleRed);
        roundTwoRedLabel.setName("label");
        roundTwoRedLabel.setPosition(735, 375);

        stage.addActor(roundTwoLabel);
        stage.addActor(roundTwoRedLabel);
        stage.addActor(roundTwoBlueLabel);
        stage.addActor(roundTwoLabelDiff);

        //----------------------Three------------------------------

        Label roundThreeLabel = new Label("3", labelStyleGold);
        roundThreeLabel.setName("label");
        roundThreeLabel.setPosition(660, 345);

        roundThreeBlueLabel = new Label("--", labelStyleBlue);
        roundThreeBlueLabel.setName("label");
        roundThreeBlueLabel.setPosition(685, 345);

        Label roundThreeLabelDiff = new Label("/", labelStyleGold);
        roundThreeLabelDiff.setName("label");
        roundThreeLabelDiff.setPosition(720, 345);

        roundThreeRedLabel = new Label("--", labelStyleRed);
        roundThreeRedLabel.setName("label");
        roundThreeRedLabel.setPosition(735, 345);

        stage.addActor(roundThreeLabel);
        stage.addActor(roundThreeRedLabel);
        stage.addActor(roundThreeBlueLabel);
        stage.addActor(roundThreeLabelDiff);

        int horizontalPointCount = 0;

        for (int i = 0; i < ARRAY_SIZE; i++) {

            horizontalPointCount++;

            if (i <= TABLE_ROW_SIZE) {
                borderArray.add(i);
                border.add(i);
            }

            if (i <= TABLE_ROW_SIZE * 3) {
                lockElement.add(i);
            }

            if (i > ARRAY_SIZE - TABLE_ROW_SIZE) {
                border.add(i);
            }

            if (i > ARRAY_SIZE - TABLE_ROW_SIZE * 3) {
                lockElement.add(i);
            }

            if (horizontalPointCount == TABLE_ROW_SIZE) {

                lockElement.add(i);
                lockElement.add(i - 1);
                lockElement.add(i - 2);
                lockElement.add(i - 111);
                lockElement.add(i - 110);
                lockElement.add(i - 109);
                border.add(i);
                border.add(i - 111);
                horizontalPointCount = 0;
            }
        }

        start();

        menu = new Menu(this);
        stage.addActor(menu);

    }

    private void initColorBorderLock() {
        for (int i = 0; i < border.size(); i++) {
            grid.table[border.get(i)].setColor(GameScreen.BRICK_COLOR);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        t += v;

        if (t < 0.1) {
            RESPAWN_USER_RED_COLOR = new Color(230 / 255f, 100 / 255f, 45 / 255f, 0.5f);
            RESPAWN_USER_BLUE_COLOR = new Color(130 / 255f, 130 / 255f, 230 / 255f, 0.5f);

        }
        if (t > 0.1) {
            RESPAWN_USER_RED_COLOR = new Color(230 / 255f, 100 / 255f, 45 / 255f, 1f);
            RESPAWN_USER_BLUE_COLOR = new Color(130 / 255f, 130 / 255f, 230 / 255f, 1f);
        }

        if (t > 0.2) {
            t = 0;
        }

        Gdx.gl.glClearColor(67 / 255f, 163 / 255f, 104 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        userControl.render();

        menu.render(v);

        initColorBorderLock();

        if (parser != null) {
            if (!GAMEOVER && !OPENMENU) {
                parser.mapRender(v);
                menu.setVisible(false);
                victory_t = 0;

                if (!GameScreen.GAMEOVER)
                    if (!ROUND_ONE && !ROUND_TWO && !ROUND_THREE) {
                        labelGold.setText("      Round 1");
                    } else if (ROUND_ONE && !ROUND_TWO && !ROUND_THREE) {
                        labelGold.setText("      Round 2");
                    } else if (ROUND_ONE && ROUND_TWO && !ROUND_THREE) {
                        labelGold.setText("      Round 3");
                    }

            } else {

                if (!round && red >= WIN_SCORE || !round && blue >= WIN_SCORE) {

                    if (!ROUND_ONE && !ROUND_TWO && !ROUND_THREE) {
                        ROUND_ONE = true;
                    } else if (ROUND_ONE && !ROUND_TWO && !ROUND_THREE) {
                        ROUND_TWO = true;
                    } else if (ROUND_ONE && ROUND_TWO && !ROUND_THREE) {
                        ROUND_THREE = true;
                    }

                    round = true;
                }


                if (victory_t < 2) {
                    victory_t += v;
                    if (red >= WIN_SCORE && red > blue) {
                        labelRedVictory.setVisible(true);
                        labelRedVictory.setColor(new Color(labelRedVictory.getColor().r, labelRedVictory.getColor().g, labelRedVictory.getColor().b, 1 - t * 1.5f));
                    } else {
                        labelRedVictory.setVisible(false);
                    }
                    if (blue >= WIN_SCORE && red < blue) {
                        labelBlueVictory.setVisible(true);
                        labelBlueVictory.setColor(new Color(labelBlueVictory.getColor().r, labelBlueVictory.getColor().g, labelBlueVictory.getColor().b, 1 - t * 1.5f));
                    } else {
                        labelBlueVictory.setVisible(false);
                    }

                    if (blue >= WIN_SCORE && red >= WIN_SCORE) {
                        if (blue == red) {
                            labelRedVictory.setVisible(false);
                            labelBlueVictory.setVisible(false);
                            labelTie.setVisible(true);
                            labelTie.setColor(new Color(labelTie.getColor().r, labelTie.getColor().g, labelTie.getColor().b, 1 - t * 1.5f));
                        }
                    } else {
                        labelTie.setVisible(false);
                    }

                } else {
                    labelRedVictory.setVisible(false);
                    labelBlueVictory.setVisible(false);
                    labelTie.setVisible(false);
                }

                if (!OPENMENU) {
                    if (!(labelBlueVictory.isVisible() || labelRedVictory.isVisible() || labelTie.isVisible())) {
                        menu.setVisible(true);
                        menu.setVisibleButton(true);
                        if (GameScreen.ROUND_ONE && !GameScreen.ROUND_TWO && !GameScreen.ROUND_THREE) {
                            @SuppressWarnings("DefaultLocale") String formattedRed = String.format("%02d", red);
                            @SuppressWarnings("DefaultLocale") String formattedBlue = String.format("%02d", blue);
                            roundOneRedLabel.setText(formattedRed);
                            roundOneBlueLabel.setText(formattedBlue);
                            BLUE_SCORE = BLUE_SCORE + blue;
                            RED_SCORE = RED_SCORE + red;

                            start();
                        }
                        if (GameScreen.ROUND_ONE && GameScreen.ROUND_TWO && !GameScreen.ROUND_THREE) {
                            @SuppressWarnings("DefaultLocale") String formattedRed = String.format("%02d", red);
                            @SuppressWarnings("DefaultLocale") String formattedBlue = String.format("%02d", blue);
                            roundTwoRedLabel.setText(formattedRed);
                            roundTwoBlueLabel.setText(formattedBlue);
                            BLUE_SCORE = BLUE_SCORE + blue;
                            RED_SCORE = RED_SCORE + red;
                            start();
                        }
                        if (GameScreen.ROUND_ONE && GameScreen.ROUND_TWO && GameScreen.ROUND_THREE) {
                            @SuppressWarnings("DefaultLocale") String formattedRed = String.format("%02d", red);
                            @SuppressWarnings("DefaultLocale") String formattedBlue = String.format("%02d", blue);
                            roundThreeRedLabel.setText(formattedRed);
                            roundThreeBlueLabel.setText(formattedBlue);


                            menu.setVisible(true);
                            menu.setVisibleButton(true);
                            labelGold.setText("");
                            GameScreen.GAMEOVER = true;
                            menu.initVictory();

                            if (tvictory > 20) {
                                GameScreen.ROUND_ONE = false;
                                GameScreen.ROUND_TWO = false;
                                GameScreen.ROUND_THREE = false;

                                GameScreen.roundOneBlueLabel.setText("--");
                                GameScreen.roundOneRedLabel.setText("--");
                                GameScreen.roundTwoBlueLabel.setText("--");
                                GameScreen.roundTwoRedLabel.setText("--");
                                GameScreen.roundThreeBlueLabel.setText("--");
                                GameScreen.roundThreeRedLabel.setText("--");

                                red = 0;
                                blue = 0;
                                menu.disposeVictory();
                            } else {
                                menu.setVisibleButton(false);

                                if (tvictory < 20)
                                    tvictory += v;
                            }

                        }
                    }
                } else {
                    menu.setVisibleButton(true);
                    menu.setVisible(true);
                }
            }
        } else {
            if (GameScreen.TEAM_TYPE != -1 && !GameScreen.SELECT_MAP) {
                menu.setVisibleButton(false);
                loadingAnimation_t += v;
                if (loadingAnimation_t < 0.05)
                    labelGold.setText("     Loading");
                if (loadingAnimation_t > 0.1)
                    labelGold.setText("     Loading...");
                if (loadingAnimation_t > 0.15)
                    loadingAnimation_t = 0;
            }
        }
        if (GameScreen.TEAM_TYPE != -1 && FIRST_START) {

            start();
            Gdx.app.log("START", "!");
            FIRST_START = false;
        }
        grid.render(v);

        labelBlue.setText("" + blue);
        labelRed.setText("" + red);

        if (red > 9) {
            labelRed.setPosition(603 - 12, 468);
        }

        if (CLOSE_VICTORY) {
            if (GameScreen.ROUND_ONE && GameScreen.ROUND_TWO && GameScreen.ROUND_THREE) {
                tvictory = 21;

                GameScreen.ROUND_ONE = false;
                GameScreen.ROUND_TWO = false;
                GameScreen.ROUND_THREE = false;

                GameScreen.roundOneBlueLabel.setText("--");
                GameScreen.roundOneRedLabel.setText("--");
                GameScreen.roundTwoBlueLabel.setText("--");
                GameScreen.roundTwoRedLabel.setText("--");
                GameScreen.roundThreeBlueLabel.setText("--");
                GameScreen.roundThreeRedLabel.setText("--");
                Gdx.app.log("ADS", "SHOW+");

                red = 0;
                blue = 0;

                menu.disposeVictory();

            }
            CLOSE_VICTORY = false;
        }

        if (GameScreen.GAMEOVER || GameScreen.MAP == null) {
            labelRed.setVisible(false);
            labelBlue.setVisible(false);
        } else {
            labelRed.setVisible(true);
            labelBlue.setVisible(true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            menu.disposeVictory();
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void start() {

        round = false;

        for (int i = 0; i < grid.table.length; i++) {
            grid.table[i].setColor(Color.CLEAR);
        }
        if (GameScreen.TEAM_TYPE != -1) {
            new Thread(() -> {
                final Parser[] parser = {new Parser(grid, GameScreen.MAP)};
                Gdx.app.postRunnable(() -> {
                    GameScreen.parser = parser[0];
                    parser[0] = null;
                });
            }).start();
        }

        GameScreen.GAMEOVER = false;
        GameScreen.OPENMENU = false;
        GameScreen.blue = 0;
        GameScreen.red = 0;
        GameScreen.parser = null;

        tvictory = 0;
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {
        Gdx.app.log("PAUSE", "+");

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.app.log("HIDE", "+");
    }

    @Override
    public void dispose() {

    }
}
