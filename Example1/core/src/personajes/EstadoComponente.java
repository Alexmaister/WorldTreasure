package personajes;


import com.badlogic.ashley.core.Component;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by aortiz on 12/02/18.
 */

public class EstadoComponente implements Component {


    public enum ESTADO{

        PARADO,
        MOVIMIENTO,
        ATAQUE,
        MUERTO
    }

    public boolean vivo;

    public ESTADO estado =ESTADO.PARADO;

    public EstadoComponente(ESTADO est){

        vivo=true;
        this.estado=est;
    }

}
