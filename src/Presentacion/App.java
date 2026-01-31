package Presentacion;

import Entidades.Deducciones;
import Entidades.Empleados;
import Entidades.Nominas;
import LogicaNegocio.LogicaEmpleados;
import LogicaNegocio.LogicaNominas;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Empresaurio50
 */
public class App {

    public static void main(String[] args) {

        /** 
         * Crea una nueva instancia de la ventana de inicio de sesión y la muestra. 
         * Este código crea una nueva instancia de `VentanaLogin`, la hace visible 
         * y la centra en la pantalla. 
         */
        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.setVisible(true);
        ventanaLogin.setLocationRelativeTo(null);

    }

}
