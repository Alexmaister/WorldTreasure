package fisicas;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btAxisSweep3;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btGhostPairCallback;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;

/**
 * Created by aortiz on 29/01/18.
 */
/*Interfaz SistemaFisicas

Esta clase se encargara de recoger todos los cuerpos y detectar sus colisiones
registrara todos los objetos que interactuaran en el sistema
* */
public class SistemaFisicas extends EntitySystem implements EntityListener {


    //con esto establecemos la configuracion
    public final btCollisionConfiguration configuracionColisiones;
    //esto nos da soporte para detectar las colisiones
    public final btCollisionDispatcher  pasador;
    public final btBroadphaseInterface broadphase;
    public final btConstraintSolver solucionadorresticciones;

    public final btDiscreteDynamicsWorld mundoColisionado;

    private btGhostPairCallback parLlamadasFantasma;

    public int maxSubSteps=5;
    public float fixedTimeStep=1f/60f;

    public SistemaFisicas(){

        configuracionColisiones=new btDefaultCollisionConfiguration();
        pasador=new btCollisionDispatcher(configuracionColisiones);
        broadphase=new btAxisSweep3(new Vector3(-1000,-1000,-1000),new Vector3(1000,1000,1000));
        solucionadorresticciones=new btSequentialImpulseConstraintSolver();
        //este sera el sistema que lo calculara todo se creeara un mundo con los objectos y se calculara todo lo relacionado a la fisica
        mundoColisionado=new btDiscreteDynamicsWorld(pasador,broadphase,solucionadorresticciones,configuracionColisiones);
        parLlamadasFantasma=new btGhostPairCallback();
        broadphase.getOverlappingPairCache().setInternalGhostPairCallback(parLlamadasFantasma);
        this.mundoColisionado.setGravity(new Vector3(0,-0.5f,0));

    }


    @Override
    public void entityAdded(Entity entity) {
        ComponenteFisica componente=
                entity.getComponent(ComponenteFisica.class);
        if(componente.cuerpo!=null){

            mundoColisionado.addRigidBody((btRigidBody)componente.cuerpo);
        }
    }

    public void eliminarCuerpo(Entity entity){

        ComponenteFisica componente=entity.getComponent(ComponenteFisica.class);

        if(componente!=null) mundoColisionado.removeCollisionObject(componente.cuerpo);

        ComponenteMovilidad movilidad=entity.getComponent(ComponenteMovilidad.class);
        if(movilidad!=null) {

            mundoColisionado.removeAction(movilidad.controlador);

            mundoColisionado.removeCollisionObject(movilidad.objetofantasma);
        }

    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    /*Sobreescribiremos addedToEngine para poder filtrar solo los componentes que tengas fisicas
    * */
    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener((EntityListener) Family.all(ComponenteFisica.class).get());
    }



    @Override
    public void update(float deltaTime){

        mundoColisionado.stepSimulation(deltaTime,maxSubSteps,fixedTimeStep);
    }

    public void dispose(){

        mundoColisionado.dispose();
        if(solucionadorresticciones!=null)solucionadorresticciones.dispose();
        if(broadphase!=null)broadphase.dispose();
        if(pasador!=null)pasador.dispose();
        if(configuracionColisiones!=null)configuracionColisiones.dispose();
        parLlamadasFantasma.dispose();

    }
}
