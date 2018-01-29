package controles;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
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
    private static Vector2 movementVector;
    private static Vector2 watchVector;

    public Controles(){

        Touchpad.TouchpadStyle stilo = new Touchpad.TouchpadStyle();
        stilo.knob  =   new TextureRegionDrawable(new TextureRegion(new Texture("/android/assets/joistick.png")));
    }


}
