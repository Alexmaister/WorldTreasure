package fisicas;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;

/**
 * Created by aortiz on 29/01/18.
 */
/*Interfaz EstadoMovimiento

 Esta clase contendra informacion sobre el movimiento de un onjeto
 posicion angulo, si esta estatico o en movimiento
* */
public class EstadoMovimiento extends btMotionState{
    private final Matrix4 transform;


    public EstadoMovimiento(final Matrix4 transform){

        this.transform=transform;
    }

    @Override
    public void setWorldTransform(Matrix4 worldTrans) {
        transform.set(worldTrans);
    }

    @Override
    public void getWorldTransform(Matrix4 worldTrans) {
        worldTrans.set(transform);
    }

}
