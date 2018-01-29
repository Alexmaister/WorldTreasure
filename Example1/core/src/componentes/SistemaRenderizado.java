package componentes;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

/**
 * Created by maister on 18/01/18.
 */

/*Interfaz SistemaRenderizado

clase que iterara por todos los elementos para dibujarlos en pantalla
InmutableArray<Entity> referencia a las entidades del sistema
referencia a todas las entidades filtradas
Cojera solo el modelocomponente necesario para dibujar :)
* */
public class SistemaRenderizado extends EntitySystem {

    private ImmutableArray<Entity> entidades;
    private ModelBatch renderizador;
    private Environment entorno;

    public SistemaRenderizado(ModelBatch renderizador, Environment entorno){

        this.renderizador=renderizador;
        this.entorno=entorno;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entidades= engine.getEntitiesFor(Family.all(ModeloComponente.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(Entity e:entidades){

            renderizador.render(e.getComponent(ModeloComponente.class).instancia,entorno);

        }
    }


}
