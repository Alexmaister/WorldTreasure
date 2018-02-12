package personajes;

import componentes.ModeloComponente;
import controles.Controles;
import fisicas.ComponenteMovilidad;
import mundo.Mundo;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by aortiz on 12/02/18.
 */

public class SistemaJugador extends EntitySystem implements EntityListener {

   private Entity jugador;
   private JugadorComponente jugadorComponente;
   private ComponenteMovilidad movilidad;
   private ModeloComponente modeloComponente;
   private final Vector3 tmp=new Vector3();
   private final Camera camara;
   private final Mundo mundo;

    public SistemaJugador(Camera camara,Mundo mundo) {
        this.camara = camara;
        this.mundo=mundo;
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(Family.all
                (JugadorComponente.class).get(), this);
    }

    @Override
    public void entityAdded(Entity entity) {

        jugador=entity;
        jugadorComponente=entity.getComponent(JugadorComponente.class);
        movilidad=entity.getComponent(ComponenteMovilidad.class);
        modeloComponente = entity.getComponent(ModeloComponente.class);


    }


    public void update(float delta){


        if(jugador!=null){
            actualizarMovimiento(delta);
        }
    }

    private void actualizarMovimiento(float delta){

        float deltaX= -Gdx.input.getDeltaX()+0.5f;

        float deltaY= -Gdx.input.getDeltaY()+0.5f;

        Matrix4 ghost = new Matrix4();

        Vector3 traslacion = new Vector3();

        movilidad.objetofantasma.getWorldTransform(ghost);

        ghost.getTranslation(traslacion);

        modeloComponente.instancia.transform.set();

    }
    @Override
    public void entityRemoved(Entity entity) {

    }
}
