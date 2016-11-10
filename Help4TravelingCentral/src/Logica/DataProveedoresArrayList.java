
package Logica;

import java.util.ArrayList;

/**
 *
 * @author yaman
 */
public class DataProveedoresArrayList {
    private ArrayList<DtUsuario> proveedores;
    


public  DataProveedoresArrayList(){    
}

public  DataProveedoresArrayList(ArrayList<DtUsuario> proveedores){    
    this.proveedores=proveedores;
}

public ArrayList<DtUsuario> getProveedores(){
    return proveedores;
}

public void setProveedores(ArrayList<DtUsuario> proveedores){
    this.proveedores = proveedores;
}
}