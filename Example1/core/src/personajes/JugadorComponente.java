package personajes;

import com.badlogic.ashley.core.Component;

/**
 * Created by aortiz on 12/02/18.
 */

public class JugadorComponente implements Component {

    public float puntuacion;
    public int vida;
    public int nivel;
    public float experiencia;

    public JugadorComponente(){

        puntuacion=0;
        vida=100;
        nivel=0;
        experiencia=0f;

    }
}
