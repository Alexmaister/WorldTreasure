package componentes;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;

/**
 * Created by maister on 28/02/18.
 *
 * Esta clase sera usada para añadir animaciones a las entidades
 */

public class AnimacionComponente implements Component {

    //Controladora de animaciones del motor Ashley
    private AnimationController animationController;

    public AnimacionComponente (ModelInstance instance){

        animationController = new AnimationController( instance );
        animationController.allowSameAnimation = true;

    }

    /*  animar (id, repeticiones velocidad)
    procedimietno que hara comenzar la animacion del modelo
    * */
    public void animar(final String id,final int repeticiones,final int velocidad_reproduccion){

        animationController.animate(id,repeticiones,null,velocidad_reproduccion);

    }

    //Actualizar
    public void update (float delta) {

        animationController.update(delta);
    }

}
