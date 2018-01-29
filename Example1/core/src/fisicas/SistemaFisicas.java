package fisicas;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btGhostPairCallback;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;

/**
 * Created by aortiz on 29/01/18.
 */

public class SistemaFisicas extends EntitySystem implements EntityListener {


    public final btCollisionConfiguration configuracionColisiones;
    public final btCollisionDispatcher  pasador;
    public final btBroadphaseInterface broadphaseInterface;
    public final btConstraintSolver solucionadorresticciones;

    public final btDiscreteDynamicsWorld mundoColisionado;

    private btGhostPairCallback parLlamadasFantasma;

    public int maxSubSteps=5;
    public float fixedTimeStep=1f/60f;

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener((EntityListener) Family.all(ComponenteFisica.class).get());
    }
}
