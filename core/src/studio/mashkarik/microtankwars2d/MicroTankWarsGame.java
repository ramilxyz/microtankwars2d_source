package studio.mashkarik.microtankwars2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class MicroTankWarsGame extends Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 480;
    public static Preferences preferences;
    public static Sound click;

    //camera & stage init
    private Stage stage;
    private SpriteBatch batch;
    private StretchViewport viewp;


    @Override
    public void create() {
        preferences = Gdx.app.getPreferences("microtankwars");

        if (!preferences.contains("sound")) {
            preferences.putBoolean("sound", true);
            preferences.flush();
        }

        click = Gdx.audio.newSound(Gdx.files.internal("sound/select.wav"));

        initializeView();
        setScreen(new GameScreen(this, stage));

    }

    private void initializeView() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        batch = new SpriteBatch();
        viewp = new StretchViewport(WIDTH, HEIGHT, camera);
        batch = new SpriteBatch();
        stage = new Stage(viewp, batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewp.update(width, height);
        stage.getViewport().update(width, height, true);

    }
}
