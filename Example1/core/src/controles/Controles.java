package controles;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by maister on 29/01/18.
 */
/*
* Clase que contendra los controles de los joistick y botones de la interfaz grafica*/
public class Controles {

    private static Touchpad jmovimiento;
    private static Touchpad jdireccion;
    private static Vector2 vectormovimiento;
    private static Vector2 vectordireccion;



    //inicializamos los controles // hay que cambuiar las imagenes
    public Controles() {

        Touchpad.TouchpadStyle stilo = new Touchpad.TouchpadStyle();
        stilo.knob = new TextureRegionDrawable(new TextureRegion(new Texture("/android/assets/analog.png")));
        stilo.knob.setMinHeight(64);
        stilo.knob.setMinWidth(64);
        stilo.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("/assets/analog.png"))));
        stilo.background.setMinHeight(64);
        stilo.background.setMinWidth(64);

        jmovimiento = new Touchpad(10, stilo);
        jdireccion = new Touchpad(10, stilo);

        vectordireccion = new Vector2(0, 0);
        vectormovimiento = new Vector2(0, 0);
    }



    //añadimos los controles a la pantalla
    public void addToStage(Stage stage){

        jmovimiento.setBounds(15,15,300,300);
        jdireccion.setBounds(stage.getWidth()-315,15,300,300);
        stage.addActor(jmovimiento);
        stage.addActor(jdireccion);
    }



    public static Vector2 getmovimiento() {

        vectormovimiento.set(jmovimiento.getKnobPercentX(),jmovimiento.getKnobPercentY());

        return vectormovimiento;
    }

    public static Vector2 getdireccion() {

        vectordireccion.set(jdireccion.getKnobPercentX(),jdireccion.getKnobPercentY());

        return vectordireccion;
    }
}
