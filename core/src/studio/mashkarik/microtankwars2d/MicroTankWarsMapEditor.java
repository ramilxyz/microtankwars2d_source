package studio.mashkarik.microtankwars2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import studio.mashkarik.microtankwars2d.screen.EditorScreen;

public class MicroTankWarsMapEditor extends Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 480;

    //camera & stage init
    private Stage stage;
    private SpriteBatch batch;
    private StretchViewport viewp;

    @Override
    public void create() {
        initializeView();
        setScreen(new EditorScreen(this, stage));
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
