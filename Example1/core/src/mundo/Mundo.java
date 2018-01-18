package mundo;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
                                        .createBox(50f,50f,50f,new Material(ColorAttribute.createDiffuse(Color.BLUE)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal),0f,0f,0f)));

        motor.addSystem(new SistemaRenderizado(renderizador,entorno));
    }


    private void iniciarCamara(){

        camara=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camara.position.set(30f, 30f, 30f);
        camara.lookAt(0f,0f,0f);
        camara.near=0.3f;
        camara.far=100f;
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
    public void resize(int w,int h){

        camara.viewportWidth=w;
        camara.viewportHeight=h;
        camara.update();

    }

    /*Metodo para renderizar el mundo
    * */
    public void render(float delta){


        renderizador.begin(camara);
        motor.update(delta);
        renderizador.end();
    }
}
