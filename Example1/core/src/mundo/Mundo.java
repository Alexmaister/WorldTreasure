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
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.ietf.jgss.GSSException;

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
                                        .createBox(50f,50f,50f,new Material(ColorAttribute.createDiffuse(Color.RED)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal),0f,50f,0f)));

        motor.addEntity(
                new Entity().add(
                        new ModeloComponente(
                                new ModelBuilder()
                                        .createBox(20000f,1f,20000f,new Material(ColorAttribute.createDiffuse(Color.BLUE)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal),0f,0f,0f)));


        motor.addSystem(new SistemaRenderizado(renderizador,entorno));
    }


    private void iniciarCamara(){

        camara=new OrthographicCamera();

        camara.position.set(40f ,200f, 40f);
        camara.up.set(0,1,0);
        camara.lookAt(0f,0f,0f);

        //camara.rotate(Vector3.,10);
        camara.near=0.3f;
        camara.far=10000f;
        //camara.setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //camara.zoom= (float) 2;
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

       //
        if(Gdx.input.isTouched())
        if(Gdx.input.getX()>0){

            ModeloComponente entity = motor.getEntities().first().getComponent(ModeloComponente.class);
            Vector3 posicion=new Vector3();

            entity.instancia.transform.getTranslation(posicion);
            posicion.x+=10;
            posicion.z-=10;

            //posicion.z-=10;
            camara.position.x+=10;
            camara.position.z-=10;
            camara.update();
            entity.instancia.transform.setTranslation(posicion.x,posicion.y,posicion.z);

        }
        motor.getEntities().first().getComponent(ModeloComponente.class).instancia.transform.rotate(Vector3.Y,10);
        motor.update(delta);
        renderizador.end();
    }
}
