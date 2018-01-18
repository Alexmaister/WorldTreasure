package pantallas;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Juego;
import mundo.Mundo;

/**
 * Created by maister on 18/01/18.
 */

public class PantallaJuego implements Screen {

    //Juego al que esta relacionada la pantalla
    private Juego juego;

    //Mundo del juego contiene todo el entorno del juego, camara , luces , objetos , etc
    private Mundo mundo;


    public PantallaJuego(Juego juego){

        this.juego=juego;
        mundo=new Mundo();
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        mundo.render(delta);
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
