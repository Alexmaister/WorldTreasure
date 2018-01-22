package mundo;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import componentes.ModeloComponente;
import componentes.SistemaRenderizado;

/**
 * Created by maister on 18/01/18.
 */

public class Mundo {

    //Camara ortografica para el juego en perspectiva de tercera persona
    private OrthographicCamera camara;
    //Entorno el cual contendra las luces de entorno :)
    private Environment entorno;

    //Renderizador para todos los modelos 3D del juego
    private ModelBatch renderizador;

    //Contendra todas las entidades Ashley
    private Engine motor;

    public Mundo(){

        iniciarCamara();
        iniciarEntorno();
        iniciarRenderizador();

        motor=new Engine();

        motor.addEntity(
                new Entity().add(
                        new ModeloComponente(
                                new ModelBuilder()
                                        .createBox(20f,20f,20f,new Material(ColorAttribute.createDiffuse(Color.BLUE)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal),0f,0f,0f)));

        motor.addSystem(new SistemaRenderizado(renderizador,entorno));
    }


    private void iniciarCamara(){

        camara=new OrthographicCamera(30,30*(Gdx.graphics.getHeight()/Gdx.graphics.getWidth()));

        camara.position.set(camara.viewportWidth/2f, camara.viewportHeight/2f, 0);
        camara.lookAt(0f,0f,0f);
//        camara.rotate(Vector3.X,20);
        //camara.near=1f;
        //camara.far=1000f;
        //camara.setToOrtho(true);
        //camara.zoom=1;
        camara.update();
    }

    private void iniciarEntorno(){

        entorno=new Environment();
       entorno.set(new ColorAttribute(ColorAttribute.AmbientLight,4f,4f,4f,1f));
       // entorno.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f,
      //          1f, 0.8f, 0.2f));
    }

    private void iniciarRenderizador(){

        renderizador=new ModelBatch();
    }

    //El renderizador es una de los objetos que ha de ser eliminado
    /*Metodo que elimina el rendrizador y los modelos
    * */
    public void dispose(){

        renderizador.dispose();
    }

    /*Metodo que cambia el campo de vision de la camara
    * */
    public void resize(float w,float h){

        camara.viewportWidth=w;
        camara.viewportHeight=h;
        camara.update();

    }

    /*Metodo para renderizar el mundo
    * */
    public void render(float delta){

       // camara.rotate(20);
       // camara.update();

        renderizador.begin(camara);
       //ModeloComponente entity = motor.getEntities().first().getComponent(ModeloComponente.class);
      // entity.instancia.transform.setToTranslation(50f,10f,100f);

        motor.update(delta);
        renderizador.end();
    }
}
