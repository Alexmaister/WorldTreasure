package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Juego;

import controles.Controles;
import mundo.Mundo;
import sun.font.CoreMetrics;

/**
 * Created by maister on 18/01/18.
 */
/*Interfaz PantallaJuego

    esta clase contendra todo lo que nuestro juego
    necesita para ser dibujado

    Propiedades:

            Referencia al juego

            Mundo  este contendra todo los motores necesarios para dibujar calcular fiicas, crear modelos, sonidos,etc
* */
public class PantallaJuego implements Screen {

    //Juego al que esta relacionada la pantalla
    private Juego juego;

    //Mundo del juego contiene todo el entorno del juego, camara , luces , objetos , etc
    private Mundo mundo;

    private Stage stage;


    public Controles controles;

    //cuando iniciemos el juego empezaremos a ver la pantalla de juego y todo el mundo
    public PantallaJuego(Juego juego){

        this.juego=juego;
        mundo=new Mundo();
        controles=new Controles();
        stage=new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        controles.addToStage(stage);

    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        mundo.render(delta);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {

        mundo.resize(width,height);
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

        mundo.dispose();
    }
}
