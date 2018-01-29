package componentes;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;


/**
 * Created by maister on 18/01/18.
 */

/*Interfaz ModeloComponente

    Esta clase es un componente del motor Ashley, los cuales pueden ser añadidos
    a las entidades , para asi poder modularizar y encapsular el calculo de los diferentes sistemas unos de otros
     ModeloComponente es el componente para crear la forma del objeto (Entidad)

     Propiedades :

            Model
            ModelInstance
*
* */

public class ModeloComponente implements Component {


    //
    public Model modelo;
    public ModelInstance instancia;
    public ModeloComponente(Model modelo,float x,float y, float z){

        this.modelo=modelo;
        this.instancia=new ModelInstance(this.modelo,new Matrix4().setToTranslation(x,y,z));
    }

}
