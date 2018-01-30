package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import controles.Controles;

/**
 * Created by maister on 29/01/18.
 */
/*Esta clase proporcionara la interfaz del juego,
por ahora solo joistick
* */
public class JuegoInterfaz {

    private Juego juego;
    public Stage stage;
    private Controles controles;


    public JuegoInterfaz(Juego juego){
        this.juego=juego;
        this.controles=new Controles();
        this.stage=new Stage(new FitViewport(Juego.VIRTUAL_WIDTH,Juego.VIRTUAL_HEIGHT));

    }

    public void render(){

        stage.draw();

    }
    public void update(float delta){

        stage.act(delta);
    }

    public void resize(int w, int h){

        stage.getViewport().update(w,h);

    }

    public void dispose(){

        stage.dispose();
    }
}
