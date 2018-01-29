package fisicas;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btConvexShape;
import com.badlogic.gdx.physics.bullet.collision.btPairCachingGhostObject;
import com.badlogic.gdx.physics.bullet.dynamics.btActionInterface;
import com.badlogic.gdx.physics.bullet.dynamics.btKinematicCharacterController;

/**
 * Created by maister on 29/01/18.
 */
/*Interfaz ComponenteMovilidad

    Esta clase nos permitira añadir Movilidad a cualquier entidad
 implementando la interfaz Component de la libreria Ashley
* */
class ComponenteMovilidad implements Component{

    // con esto se calcularan las colisiones
    public btConvexShape formafantasma;
    public btPairCachingGhostObject objetofantasma;
    public btActionInterface controlador;
    //el controlador necesita referencias al objeto y a la forma
    public btKinematicCharacterController controladormovilidad;

    public Vector3 direccion    =   new Vector3();
    public Vector3 direccionmovimiento  =   new Vector3();
}
