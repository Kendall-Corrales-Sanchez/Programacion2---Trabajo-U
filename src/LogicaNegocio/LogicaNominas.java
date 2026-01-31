package LogicaNegocio;

import AccesoDatos.CrearPatronoPDF;
import AccesoDatos.EnviarCorreo;
import Entidades.Nominas;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import Servicios.ServicioPatrono;
import AccesoDatos.AccesoDatosEmpleados;
import Entidades.Deducciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Empresaurio50
 */
public class LogicaNominas implements ServicioPatrono {

    private String datosEnviar;
    private CrearPatronoPDF objCrearPatronoPDF = new CrearPatronoPDF();
    private AccesoDatosEmpleados objAccesoDatosEmpleados = new AccesoDatosEmpleados();
    private LogicaEmpleados logicaEmpleados;
    

    /**
     * Calcula las deducciones del patrono y establece el total a pagar.
     *
     * Este método aplica las deducciones de la CCSS, aportes institucionales y
     * aportes LPT (Ley de Protección al Trabajador) según el total a pagar, y
     * luego establece el total de pagos del patrono en el objeto Nominas.
     *
     * @param objNominas El objeto Nominas que contiene la información del
     * salario.
     */
    public void rebajaPatrono(Nominas objNominas) {
        objNominas.setCcssPatrono(objNominas.getTotalPagar() * 0.15);
        objNominas.setAportesInstitucionales(objNominas.getTotalPagar() * 0.07);
        objNominas.setAportesLPT(objNominas.getTotalPagar() * 0.05);
        objNominas.setPagoPatronoTotal(objNominas.getCcssPatrono() + objNominas.getAportesInstitucionales() + objNominas.getAportesLPT());
    }

    /**
     * Prepara los datos necesarios para enviar un correo del patrono.
     *
     * Este método calcula las deducciones del patrono y prepara una cadena con
     * los datos necesarios para el envío del correo.
     *
     * @param objNominas El objeto Nominas que contiene la información del
     * salario.
     */
    public void datosCorreoPatrono(Nominas objNominas) {
        rebajaPatrono(objNominas);

        datosEnviar = objNominas.getTotalPagar() + "," // 0 contiene el total a pagar
                + objAccesoDatosEmpleados.getAdministrador() + "," // 1 contiene el correo del patrono
                + objNominas.getCcssPatrono() + "," // 2 contiene la CCSS del patrono
                + objNominas.getAportesInstitucionales() + "," // 3 aportes institucionales
                + objNominas.getAportesLPT() + "," // 4 contiene los aportes LPT
                + objNominas.getPagoPatronoTotal();
        

    }

    /**
     * Envía correos electrónicos con un archivo PDF adjunto.
     *
     * Este método prepara los datos del correo y utiliza el objeto EnviarCorreo
     * para enviar el correo electrónico con el archivo PDF adjunto.
     *
     * @param objNominas El objeto Nominas que contiene la información del
     * salario.
     * @throws AddressException Si la dirección del correo es incorrecta.
     * @throws SendFailedException Si ocurre un error al enviar el correo.
     * @throws MessagingException Si ocurre un error con el sistema de
     * mensajería.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void enviarCorreos(Nominas objNominas) throws AddressException, SendFailedException, MessagingException, IOException {
        EnviarCorreo objEnviarCorreo = new EnviarCorreo();
        objEnviarCorreo.setNombrePDF(objCrearPatronoPDF.getNombreArchivo());
        datosCorreoPatrono(objNominas);
        objEnviarCorreo.setDatos(this.datosEnviar);
        objEnviarCorreo.EnviarCorreos();
    }

    /**
     * Crea un archivo PDF con los datos de las deducciones del salario.
     *
     * Este método prepara los datos necesarios y utiliza el objeto
     * CrearPatronoPDF para generar un archivo PDF con el reporte de nómina.
     *
     * @param objNominas El objeto Nominas que contiene la información del
     * salario.
     * @throws DocumentException Si ocurre un error al crear el documento.
     * @throws FileNotFoundException Si no se encuentra el archivo especificado.
     */
    @Override
    public void crearPDF(Nominas objNominas) throws DocumentException, FileNotFoundException, IOException {
        objCrearPatronoPDF = new CrearPatronoPDF();
        LogicaDeducciones logicaDeducciones = new LogicaDeducciones();
        objCrearPatronoPDF.setNombreArchivo("Reporte Nomina.pdf");
        datosCorreoPatrono(objNominas);
        
        Deducciones deducciones = new Deducciones();
        logicaEmpleados = new LogicaEmpleados();
        logicaEmpleados.leerEmpleado(deducciones);
        
        List<Deducciones> listaDeducciones = new ArrayList<>();
        
        for (String[] strings : deducciones.getEmpleadosLista()) {
            
            Deducciones deduccionesNew = new Deducciones();
            deduccionesNew.setNombre(strings[1]);
            deduccionesNew.setSalarioBruto(Double.parseDouble(strings[4]));
            logicaDeducciones.rebajaSalario(deduccionesNew);
            listaDeducciones.add(deduccionesNew);
        }
        
        objCrearPatronoPDF.setRegistro(datosEnviar);
        objCrearPatronoPDF.createPDF(listaDeducciones);
    }

}
