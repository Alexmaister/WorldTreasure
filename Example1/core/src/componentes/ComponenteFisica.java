package componentes;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

import fisicas.EstadoMovimiento;

/**
 * Created by aortiz on 29/01/18.
 */
/*Interfaz Componente fisica
Esta clase se usara para añadirle los
componentes fisicos a las entidades
este contendra un EstadoMovimiento
contiene informacion requeridas para el Sistema de Fisicas
*
* */
public class ComponenteFisica implements Component {

    //informacion sobre posicion, angulo
    public EstadoMovimiento movimiento;
    //informacion para crear un cuerpo rigido , mass=0 estatico,
    public btRigidBody.btRigidBodyConstructionInfo info;
    //este es el cuerpo que tomara el sistema de fiicas
    public btCollisionObject cuerpo;
}
