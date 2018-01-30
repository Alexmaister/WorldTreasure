package manejadores;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.sun.org.apache.bcel.internal.generic.NEW;

import componentes.ModeloComponente;
import fisicas.ComponenteFisica;
import fisicas.EstadoMovimiento;


/**
 * Created by aortiz on 29/01/18.
 */

public class EntidadFactoria {

    public static Entity crearEntidadEstatica(Model model, float x, float y, float z){

        //cojemos los limites del cuerpo
        final BoundingBox limitedelcuerpo=new BoundingBox();
        //creamos la entidad vacia y le añadiremos todos los componentes
        Entity entidad=new Entity();

        //componenteAshley para los objetos modelo y textura
        ModeloComponente componente = new ModeloComponente(model,x,y,z);

        //compotenteBulletAshley para añadir fisica a la entidad :)
        ComponenteFisica componenteFisica=new ComponenteFisica();

        //calculamos los limites del modelo pasado por parametos con sus dimensiones
        model.calculateBoundingBox(limitedelcuerpo);

        //vector para guardar informacino, no es necesario crearlo aqui es solo para que sea mas claro
        Vector3 tmpV=new Vector3();

        //construimos la forma para la colision aqui usamos ese vector, se puede sustuir tmpV por new Vertor3()
        btCollisionShape collisionShape = new btBoxShape(tmpV.set(limitedelcuerpo.getWidth()*0.5f,limitedelcuerpo.getHeight()*0.5f,limitedelcuerpo.getDepth()*0.5f));

       //ponemos toda la informacion , mass=0 non-dynamic
        componenteFisica.info=new btRigidBody.btRigidBodyConstructionInfo(0,null,collisionShape, Vector3.Zero);
        //construimos el cuerpo con esa informacion proporcionada
        componenteFisica.cuerpo=new btRigidBody(componenteFisica.info);
        //
        componenteFisica.cuerpo.userData=entidad;
        //establecemos el movimiento a partir de la posicion del objeto
        componenteFisica.movimiento=new EstadoMovimiento(componente.instancia.transform);

        //referenciamos al padre, añadimos el movimiento
        ((btRigidBody)componenteFisica.cuerpo).setMotionState(componenteFisica.movimiento);


        //añadimos los componentes a la entidad
        entidad.add(componente);
        entidad.add(componenteFisica);


        //la devolvemos
        return entidad;
    }

}
