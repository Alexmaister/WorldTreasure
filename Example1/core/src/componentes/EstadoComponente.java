package componentes;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;

/**
 * Created by maister on 28/02/18.
 */

public class EstadoComponente implements Component {

    private AnimacionComponente animacion;
    public boolean vivo,movimiento,atacando;
    public float tiempo_estado_vivo;

    public EstadoComponente(AnimacionComponente animation){

        this.animacion = animation;
        vivo = true ;
        movimiento = false;

    }

    public void update (float delta){

        if(!vivo) tiempo_estado_vivo +=delta;
        empezarAnimacionMuerte();
    }

    private void empezarAnimacionMuerte(){

        //
        //animacion.animar(EnemigoAnimaciones.id,EnemigoAnimaciones.offsetDeath, EnemigoAnimaciones.duracionMuerte,1,3);
    }
}
