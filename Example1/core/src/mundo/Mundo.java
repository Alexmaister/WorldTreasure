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

import com.badlogic.gdx.graphics.g3d.ModelBatch;

import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import com.badlogic.gdx.math.Vector3;


import componentes.ModeloComponente;
import componentes.SistemaRenderizado;
import controles.Controles;
import manejadores.EntidadFactoria;

/**
 * Created by maister on 18/01/18.
 */

public class Mundo {

    //Camara ortografica para el juego en perspectiva de tercera persona
    private OrthographicCamera camara;
    private PerspectiveCamera camarap;
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

      /*  motor.addEntity(
                new Entity().add(
                        new ModeloComponente(
                                new ModelBuilder()
                                        .createBox(50f,50f,50f,new Material(ColorAttribute.createDiffuse(Color.RED)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal),0f,25f,0f)));
*/

        motor.addEntity(EntidadFactoria.crearSmaug(0,-10,0));
        motor.addEntity(
                new Entity().add(
                        new ModeloComponente(
                                new ModelBuilder()
                                        .createBox(2000f,1f,2000f,new Material(ColorAttribute.createDiffuse(Color.BLUE)),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal),0f,0f,0f)));


        motor.addSystem(new SistemaRenderizado(renderizador,entorno));
    }


    private void iniciarCamara(){

       camara=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
       // camara=new OrthographicCamera();
       // camara.position.set(70f ,200f, -10f);
        camara.position.set(8000f ,10000f, -100f);
        //camara.up.set(0,1,0);
        camara.lookAt(0f,0f,0f);

        camara.near=0.3f;
        camara.far=60000f;
        //camara.setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //camara.zoom= (float) 2;
        camara.update();



        camarap=new PerspectiveCamera(100,Gdx.graphics.getWidth()*100,Gdx.graphics.getHeight()*100);
        camarap.position.set(0,800,1000);
        camarap.lookAt(0,800,900);
        camarap.near=0.3f;
        camarap.far=60000f;
        camarap.update();
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
        if(Controles.getmovimiento().x>0.01 ) {
            camara.position.x += 1;

        }else if(Controles.getmovimiento().x<-0.01)

            camara.position.x-=1;

        if (Controles.getmovimiento().y > 0.01) {

            camara.position.z += 1;
        } else if(Controles.getmovimiento().y < -0.01){

            camara.position.z-=1;
        }
        camara.update();
       // motor.getEntities().first().getComponent(ModeloComponente.class).instancia.transform.rotate(Vector3.Y,10);
        motor.update(delta);
        renderizador.end();
    }
}
