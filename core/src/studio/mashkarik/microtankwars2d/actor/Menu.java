package studio.mashkarik.microtankwars2d.actor;

import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_EIGHT;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_ELEVEN;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_FIVE;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_FOUR;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_NINE;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_ONE;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_SEVEN;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_SIX;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_TEN;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_THREE;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_TWELVE;
import static studio.mashkarik.microtankwars2d.maps.Maps.MAP_TWO;
import static studio.mashkarik.microtankwars2d.screen.GameScreen.BLUE_SCORE;
import static studio.mashkarik.microtankwars2d.screen.GameScreen.RED_SCORE;
import static studio.mashkarik.microtankwars2d.screen.GameScreen.TEAM_TYPE;
import static studio.mashkarik.microtankwars2d.screen.GameScreen.blue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Random;

import studio.mashkarik.microtankwars2d.MicroTankWarsGame;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Menu extends Table {

    public static Label redScoreLabel;
    public static Label blueScoreLabel;
    private final Image redTeam;
    private final Image blueTeam;
    private final Image map;
    private final Image exit;
    private final Image load;
    private final GameScreen gameScreen;
    private final ScrollPane scrollPane;
    private final Table mapPane = new Table();
    public Label labelNewGame;
    public Label labelMap;
    public Label labelExit;
    public Label labelLoad;
    public Label label;
    ParticleEffect effect;
    ParticleEffect effect1;
    ParticleEffect effect2;
    private Image newGame;
    private Image goldBlue;
    private Image goldRed;
    private Image gold;
    private boolean start = false;


    public Menu(final GameScreen gameScreen) {

        super();

        this.gameScreen = gameScreen;

        Texture menuTexture = new Texture("menu.png");
        Texture redTeamTexture = new Texture("red_team.png");
        Texture blueTeamTexture = new Texture("blue_team.png");
        Texture textButtonTexture = new Texture("textbutton.png");

        BitmapFont font = new BitmapFont(Gdx.files.internal("font/24.fnt"),
                Gdx.files.internal("font/24.png"), false);


        BitmapFont fontHs = new BitmapFont(Gdx.files.internal("font/24.fnt"),
                Gdx.files.internal("font/24.png"), false);

        Label.LabelStyle redScoreLabelStyle = new Label.LabelStyle(fontHs, GameScreen.COLORRED);
        redScoreLabel = new Label("Score: 10", redScoreLabelStyle);
        redScoreLabel.setPosition(35, 380);

        Label.LabelStyle blueScoreLabelStyle = new Label.LabelStyle(fontHs, Color.ROYAL);
        blueScoreLabel = new Label("Score: 00", blueScoreLabelStyle);
        blueScoreLabel.setPosition(297, 380);


        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.GOLD);

        labelNewGame = new Label("New Game", labelStyle);
        labelMap = new Label("Map", labelStyle);
        labelExit = new Label("Exit", labelStyle);
        labelLoad = new Label("Load map", labelStyle);

        label = new Label("      ", labelStyle);

        newGame = new Image(textButtonTexture);
        newGame.setName("NewGame");
        newGame = new Image(textButtonTexture);
        newGame.setName("NewGame");
        map = new Image(textButtonTexture);
        map.setName("Map");
        exit = new Image(textButtonTexture);
        exit.setName("Exit");
        load = new Image(textButtonTexture);
        load.setName("Load");

        this.setName("menu");
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(menuTexture));
        this.setBackground(textureRegionDrawableBg);
        this.addActor(map);

        this.addActor(newGame);

        this.addActor(exit);

        this.addActor(labelMap);

        this.addActor(labelNewGame);

        this.addActor(labelExit);

        this.addActor(redScoreLabel);
        this.addActor(blueScoreLabel);

        blueScoreLabel.setVisible(false);
        redScoreLabel.setVisible(false);

        redTeam = new Image(redTeamTexture);
        blueTeam = new Image(blueTeamTexture);

        redTeam.setSize(70, 70);
        blueTeam.setSize(70, 70);

        redTeam.setPosition(115, 185);
        blueTeam.setPosition(255, 185);

        blueTeam.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                blueTeam.setColor(blueTeam.getColor().r, blueTeam.getColor().g, blueTeam.getColor().b, 1f);
                TEAM_TYPE = 1;

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();
                blueTeam.setColor(blueTeam.getColor().r, blueTeam.getColor().g, blueTeam.getColor().b, 0.5f);

                return true;
            }

        });

        redTeam.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                redTeam.setColor(redTeam.getColor().r, redTeam.getColor().g, redTeam.getColor().b, 1f);
                TEAM_TYPE = 0;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();
                redTeam.setColor(redTeam.getColor().r, redTeam.getColor().g, redTeam.getColor().b, 0.5f);
                return true;
            }

        });


        this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0.8f);

        this.addActor(redTeam);
        this.addActor(blueTeam);

        mapPane.padTop(25);
        mapPane.padBottom(25);


        //======================================= 1 ====================================
        final Image mapOne = new Image(new Texture(new PixelButton(MAP_ONE)));
        mapOne.setName("1");
        InputListener inputListenerOne = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                labelLoad.setVisible(true);
                load.setVisible(true);
                mapOne.setColor(mapOne.getColor().r, mapOne.getColor().g, mapOne.getColor().b, 0.5f);
                return true;
            }
        };
        mapOne.addListener(inputListenerOne);
        mapPane.add(mapOne).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------
        mapPane.add(label);

        final Image mapTwo = new Image(new Texture(new PixelButton(MAP_TWO)));
        mapTwo.setName("2");
        InputListener inputListenerTwo = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                ///   playServices.showScore("");
                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                labelLoad.setVisible(true);
                load.setVisible(true);
                mapTwo.setColor(mapTwo.getColor().r, mapTwo.getColor().g, mapTwo.getColor().b, 0.5f);
                return true;
            }
        };

        mapTwo.addListener(inputListenerTwo);
        mapPane.add(mapTwo).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------

        mapPane.add(label);

        final Image mapThree = new Image(new Texture(new PixelButton(MAP_THREE)));
        mapThree.setName("3");
        InputListener inputListenerThree = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                labelLoad.setVisible(true);
                load.setVisible(true);
                mapThree.setColor(mapThree.getColor().r, mapThree.getColor().g, mapThree.getColor().b, 0.5f);
                return true;
            }
        };

        mapThree.addListener(inputListenerThree);
        mapPane.add(mapThree).size(110, 110);

        mapPane.row();
        mapPane.add(label);
        mapPane.row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        //======================================= 2 ====================================
        final Image mapFour2 = new Image(new Texture(new PixelButton(MAP_FOUR)));
        mapFour2.setName("4");
        InputListener inputListenerFour2 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                labelLoad.setVisible(true);
                load.setVisible(true);
                mapFour2.setColor(mapFour2.getColor().r, mapFour2.getColor().g, mapFour2.getColor().b, 0.5f);
                return true;
            }
        };
        mapFour2.addListener(inputListenerFour2);
        mapPane.add(mapFour2).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------
        mapPane.add(label);

        final Image mapFive2 = new Image(new Texture(new PixelButton(MAP_FIVE)));
        mapFive2.setName("5");
        InputListener inputListenerFive2 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                mapFive2.setColor(mapFive2.getColor().r, mapFive2.getColor().g, mapFive2.getColor().b, 0.5f);
                labelLoad.setVisible(true);
                load.setVisible(true);
                return true;
            }
        };

        mapFive2.addListener(inputListenerFive2);
        mapPane.add(mapFive2).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------

        mapPane.add(label);

        final Image mapSix2 = new Image(new Texture(new PixelButton(MAP_SIX)));
        mapSix2.setName("6");
        InputListener inputListenerSix2 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                mapSix2.setColor(mapSix2.getColor().r, mapSix2.getColor().g, mapSix2.getColor().b, 0.5f);
                labelLoad.setVisible(true);
                load.setVisible(true);
                return true;
            }
        };

        mapSix2.addListener(inputListenerSix2);
        mapPane.add(mapSix2).size(110, 110);

        mapPane.row();
        mapPane.add(label);
        mapPane.row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        //======================================= 3 ====================================
        final Image mapSeven3 = new Image(new Texture(new PixelButton(MAP_SEVEN)));
        mapSeven3.setName("7");
        InputListener inputListenerSeven3 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                labelLoad.setVisible(true);
                load.setVisible(true);
                mapSeven3.setColor(mapSeven3.getColor().r, mapSeven3.getColor().g, mapSeven3.getColor().b, 0.5f);
                return true;
            }
        };
        mapSeven3.addListener(inputListenerSeven3);
        mapPane.add(mapSeven3).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------
        mapPane.add(label);

        final Image mapEight3 = new Image(new Texture(new PixelButton(MAP_EIGHT)));
        mapEight3.setName("8");
        InputListener inputListenerEight3 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                mapEight3.setColor(mapEight3.getColor().r, mapEight3.getColor().g, mapEight3.getColor().b, 0.5f);
                labelLoad.setVisible(true);
                load.setVisible(true);
                return true;
            }
        };

        mapEight3.addListener(inputListenerEight3);
        mapPane.add(mapEight3).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------

        mapPane.add(label);

        final Image mapNine3 = new Image(new Texture(new PixelButton(MAP_NINE)));
        mapNine3.setName("9");
        InputListener inputListenerNine3 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                mapNine3.setColor(mapNine3.getColor().r, mapNine3.getColor().g, mapNine3.getColor().b, 0.5f);
                labelLoad.setVisible(true);
                load.setVisible(true);
                return true;
            }
        };

        mapNine3.addListener(inputListenerNine3);
        mapPane.add(mapNine3).size(110, 110);

        mapPane.row();
        mapPane.add(label);
        mapPane.row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        //======================================= 3 ====================================
        final Image mapTen4 = new Image(new Texture(new PixelButton(MAP_TEN)));
        mapTen4.setName("10");
        InputListener inputListenerTen4 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                labelLoad.setVisible(true);
                load.setVisible(true);
                mapTen4.setColor(mapTen4.getColor().r, mapTen4.getColor().g, mapTen4.getColor().b, 0.5f);
                return true;
            }
        };
        mapTen4.addListener(inputListenerTen4);
        mapPane.add(mapTen4).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------
        mapPane.add(label);

        final Image mapEleven4 = new Image(new Texture(new PixelButton(MAP_ELEVEN)));
        mapEleven4.setName("11");
        InputListener inputListenerEleven4 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                mapEleven4.setColor(mapEleven4.getColor().r, mapEleven4.getColor().g, mapEleven4.getColor().b, 0.5f);
                labelLoad.setVisible(true);
                load.setVisible(true);
                return true;
            }
        };

        mapEleven4.addListener(inputListenerEleven4);
        mapPane.add(mapEleven4).size(110, 110);
        //---------------------------------------------------------------------------------------------------------------------------------------------

        mapPane.add(label);

        final Image mapTwelve4 = new Image(new Texture(new PixelButton(MAP_TWELVE)));
        mapTwelve4.setName("12");
        InputListener inputListenerTwelve4 = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        mapPane.getChild(i).setColor(mapPane.getChild(i).getColor().r, mapPane.getChild(i).getColor().g, mapPane.getChild(i).getColor().b, 1f);
                    }
                }
                mapTwelve4.setColor(mapTwelve4.getColor().r, mapTwelve4.getColor().g, mapTwelve4.getColor().b, 0.5f);
                labelLoad.setVisible(true);
                load.setVisible(true);
                return true;
            }
        };

        mapTwelve4.addListener(inputListenerTwelve4);
        mapPane.add(mapTwelve4).size(110, 110);

        mapPane.row();
        mapPane.add(label);
        mapPane.row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        scrollPane = new ScrollPane(mapPane);
        scrollPane.setBounds(0, 0, 440, 440);
        scrollPane.setVisible(false);

        this.addActor(scrollPane);

        load.setColor(label.getColor().r, label.getColor().g, label.getColor().b, 1f);

        this.add(load);
        this.add(labelLoad);

        labelLoad.setVisible(false);
        load.setVisible(false);

        inputListener();
    }

    public void disposeVictory() {

        BLUE_SCORE = 0;
        RED_SCORE = 0;

        blueScoreLabel.setVisible(false);
        redScoreLabel.setVisible(false);

        start = false;
        if (goldBlue != null)
            goldBlue.remove();

        if (goldRed != null)
            goldRed.remove();

        if (gold != null)
            gold.remove();

        goldRed = null;
        goldBlue = null;
        gold = null;

        effect = null;
        effect1 = null;
        effect2 = null;
    }

    public void initVictory() {

        if (!start) {

            BLUE_SCORE = BLUE_SCORE + blue;
            RED_SCORE = RED_SCORE + GameScreen.red;

            Menu.redScoreLabel.setText("Score " + RED_SCORE);
            Menu.blueScoreLabel.setText("Score " + BLUE_SCORE);

            blueScoreLabel.setVisible(true);
            redScoreLabel.setVisible(true);

            if (RED_SCORE == BLUE_SCORE) {

                gold = new Image(new Texture("gold.png"));
                gold.setOrigin(Align.center);
                gold.setScale(2f);
                gold.setPosition(190, 180);

                gold.addAction(Actions.forever(Actions.sequence(
                        (Actions.scaleTo(0f, 2f, 0.6f)),
                        (Actions.scaleTo(2f, 2f, 0.6f)))));

                this.addActor(gold);
            }

            if (RED_SCORE < BLUE_SCORE) {

                goldBlue = new Image(new Texture("gold_b.png"));
                goldBlue.setOrigin(Align.center);
                goldBlue.setSize(300, 291);
                goldBlue.setPosition(70, 70);
                this.addActor(goldBlue);
            }

            if (RED_SCORE > BLUE_SCORE) {
                goldRed = new Image(new Texture("gold_r.png"));
                goldRed.setOrigin(Align.center);
                goldRed.setSize(300, 291);
                goldRed.setPosition(70, 70);
                this.addActor(goldRed);
            }

            effect = new ParticleEffect();
            effect.load(Gdx.files.internal("particle"), Gdx.files.internal(""));
            Random random1 = new Random();
            Random random2 = new Random();
            int x = random1.nextInt(400) + 280;
            int y = random2.nextInt(180) + 200;
            effect.setPosition(x, y);
            effect.start();

            effect1 = new ParticleEffect();
            effect1.load(Gdx.files.internal("particle"), Gdx.files.internal(""));
            Random random3 = new Random();
            Random random4 = new Random();
            int x1 = random3.nextInt(400) + 280;
            int y1 = random4.nextInt(180) + 200;
            effect1.setPosition(x1, y1);
            effect1.start();

            effect2 = new ParticleEffect();
            effect2.load(Gdx.files.internal("particle"), Gdx.files.internal(""));
            Random random5 = new Random();
            Random random6 = new Random();
            int x2 = random3.nextInt(400) + 280;
            int y2 = random4.nextInt(180) + 200;
            effect2.setPosition(x2, y2);
            effect2.start();
            start = true;
        }
    }

    private void inputListener() {

        InputListener loadListener = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                load.setColor(label.getColor().r, label.getColor().g, label.getColor().b, 1f);
                for (int i = 0; i < mapPane.getChildren().size; i++) {
                    if (mapPane.getChild(i).getColor().a == 0.5f) {
                        if (mapPane.getChild(i).getName().equals("1")) {
                            GameScreen.MAP = MAP_ONE;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }

                        if (mapPane.getChild(i).getName().equals("2")) {
                            GameScreen.MAP = MAP_TWO;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("3")) {
                            GameScreen.MAP = MAP_THREE;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }

                        if (mapPane.getChild(i).getName().equals("4")) {
                            GameScreen.MAP = MAP_FOUR;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }

                        if (mapPane.getChild(i).getName().equals("5")) {
                            GameScreen.MAP = MAP_FIVE;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("6")) {
                            GameScreen.MAP = MAP_SIX;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("7")) {
                            GameScreen.MAP = MAP_SEVEN;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("8")) {
                            GameScreen.MAP = MAP_EIGHT;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("9")) {
                            GameScreen.MAP = MAP_NINE;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("10")) {
                            GameScreen.MAP = MAP_TEN;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("11")) {
                            GameScreen.MAP = MAP_ELEVEN;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }
                        if (mapPane.getChild(i).getName().equals("12")) {
                            GameScreen.MAP = MAP_TWELVE;
                            TEAM_TYPE = -1;
                            GameScreen.FIRST_START = true;
                            gameScreen.start();
                            GameScreen.SELECT_MAP = false;
                        }

                    }
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();

                load.setColor(load.getColor().r, load.getColor().g, load.getColor().b, 0.5f);

                return true;
            }
        };

        load.addListener(loadListener);
        labelLoad.addListener(loadListener);

        InputListener startGame = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);


                newGame.setColor(newGame.getColor().r, newGame.getColor().g, newGame.getColor().b, 1);
                TEAM_TYPE = -1;
                GameScreen.FIRST_START = true;

                GameScreen.ROUND_ONE = false;
                GameScreen.ROUND_TWO = false;
                GameScreen.ROUND_THREE = false;

                GameScreen.roundOneBlueLabel.setText("--");
                GameScreen.roundOneRedLabel.setText("--");
                GameScreen.roundTwoBlueLabel.setText("--");
                GameScreen.roundTwoRedLabel.setText("--");
                GameScreen.roundThreeBlueLabel.setText("--");
                GameScreen.roundThreeRedLabel.setText("--");

                if (GameScreen.MAP != null)
                    gameScreen.start();
                else GameScreen.SELECT_MAP = true;

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();

                newGame.setColor(newGame.getColor().r, newGame.getColor().g, newGame.getColor().b, 0.5f);
                return true;
            }
        };

        InputListener mapPlane = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                map.setColor(map.getColor().r, map.getColor().g, map.getColor().b, 1);
                GameScreen.SELECT_MAP = true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();
                map.setColor(map.getColor().r, map.getColor().g, map.getColor().b, 0.5f);
                return true;
            }
        };

        InputListener inputListenerExit = new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                exit.setColor(exit.getColor().r, exit.getColor().g, exit.getColor().b, 1);
                System.exit(0);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (MicroTankWarsGame.preferences.getBoolean("sound"))
                    MicroTankWarsGame.click.play();

                exit.setColor(exit.getColor().r, exit.getColor().g, exit.getColor().b, 0.5f);
                return true;
            }
        };


        exit.addListener(inputListenerExit);
        labelExit.addListener(inputListenerExit);

        newGame.addListener(startGame);

        labelNewGame.addListener(startGame);

        map.addListener(mapPlane);

        labelMap.addListener(mapPlane);

    }

    public void render(float v) {
        if (TEAM_TYPE == -1 && GameScreen.MAP != null) {
            redTeam.setVisible(true);
            blueTeam.setVisible(true);
            GameScreen.labelGold.setText("   Select Team");
            setVisibleButton(false);
        } else {
            setVisibleButton(true);
            redTeam.setVisible(false);
            blueTeam.setVisible(false);
        }
        if (GameScreen.SELECT_MAP) {
            mapPane.setVisible(true);
            scrollPane.setVisible(true);
            GameScreen.labelGold.setText("    Select Map");
        } else {
            mapPane.setVisible(false);
            scrollPane.setVisible(false);
            labelLoad.setVisible(false);
            load.setVisible(false);
        }
        resize();
    }

    public void setVisibleButton(boolean visible) {

        if (!GameScreen.SELECT_MAP) {
            map.setVisible(visible);
            labelMap.setVisible(visible);
            newGame.setVisible(visible);
            labelNewGame.setVisible(visible);
            exit.setVisible(visible);
            labelExit.setVisible(visible);
        } else {
            map.setVisible(false);
            labelMap.setVisible(false);
            newGame.setVisible(false);
            labelNewGame.setVisible(false);
            exit.setVisible(false);
            labelExit.setVisible(false);
            scrollPane.setVisible(true);

        }
    }

    private void resize() {
        this.setSize(Grid.GRID_W, Grid.GRID_H);
        this.setPosition(Grid.GRID_X, Grid.GRID_Y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (effect != null && effect1 != null && effect2 != null) {
            actParticleEffect(effect);
            actParticleEffect(effect1);
            actParticleEffect(effect2);
            effect.update(delta);
            effect1.update(delta);
            effect2.update(delta);
        }

        float BUTTON_Y = (Grid.GRID_H * 163) / 440;
        float BUTTON_FONT_Y = (Grid.GRID_H * 165) / 440;
        float GAP = (Grid.GRID_H * 70) / 440;

        labelNewGame.setPosition(170, BUTTON_FONT_Y + GAP * 2);
        labelMap.setPosition(200, BUTTON_FONT_Y + GAP);
        labelExit.setPosition(205, BUTTON_FONT_Y);
        //labelExit.setPosition(205, BUTTON_FONT_Y-GAP);
        labelLoad.setPosition(175, (Grid.GRID_H * 130) / 440 - GAP * 1.5f);

        newGame.setBounds(125, BUTTON_Y + GAP * 2, 200, 35);
        map.setBounds(125, BUTTON_Y + GAP, 200, 35);
        exit.setBounds(125, BUTTON_Y, 200, 35);
        // exit.setBounds(125, BUTTON_Y-GAP, 200, 35);
        load.setBounds(125, (Grid.GRID_H * 128) / 440 - GAP * 1.5f, 200, 35);

    }

    private void actParticleEffect(ParticleEffect effect) {
        if (effect != null && effect1 != null && effect2 != null) {
            if (effect.isComplete()) {
                Random random1 = new Random();
                Random random2 = new Random();
                int x = random1.nextInt(400) + 280;
                int y = random2.nextInt(180) + 200;
                effect.setPosition(x, y);
                effect.start();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (effect != null && effect1 != null && effect2 != null) {
            effect.draw(batch);
            effect1.draw(batch);
            effect2.draw(batch);
        }
    }
}
