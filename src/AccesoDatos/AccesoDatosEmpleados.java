/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Empresaurio50
 */
public class AccesoDatosEmpleados {

    private String nombreArchivo, registro, buscarCorreo, buscarPassword,
            administrador = "kendallcorralessanchez@gmail.com", contraAdmin = "123";

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getContraAdmin() {
        return contraAdmin;
    }

    public void setContraAdmin(String contraAdmin) {
        this.contraAdmin = contraAdmin;
    }
     private ArrayList<String[]> listaEmpleados;
    private int verificacion;

    ;

    /**
 * Obtiene el administrador.
 * 
 * @return El administrador.
 */


   

    /**
     * Obtiene el valor de verificación.
     *
     * @return El valor de verificación.
     */
    public int getVerificacion() {
        return verificacion;
    }

    /**
     * Establece el valor de verificación.
     *
     * @param verificacion El nuevo valor de verificación.
     */
    public void setVerificacion(int verificacion) {
        this.verificacion = verificacion;
    }

    /**
     * Obtiene el correo a buscar.
     *
     * @return El correo a buscar.
     */
    public String getBuscarCorreo() {
        return buscarCorreo;
    }

    /**
     * Establece el correo a buscar.
     *
     * @param buscarCorreo El nuevo correo a buscar.
     */
    public void setBuscarCorreo(String buscarCorreo) {
        this.buscarCorreo = buscarCorreo;
    }

    /**
     * Obtiene la contraseña a buscar.
     *
     * @return La contraseña a buscar.
     */
    public String getBuscarPassword() {
        return buscarPassword;
    }

    /**
     * Establece la contraseña a buscar.
     *
     * @param buscarPassword La nueva contraseña a buscar.
     */
    public void setBuscarPassword(String buscarPassword) {
        this.buscarPassword = buscarPassword;
    }

    /**
     * Constructor de la clase AccesoDatosEmpleados.
     */
    public AccesoDatosEmpleados() {

    }

    /**
     * Obtiene el nombre del archivo.
     *
     * @return El nombre del archivo.
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Establece el nombre del archivo.
     *
     * @param nombreArchivo El nuevo nombre del archivo.
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene la lista de empleados.
     *
     * @return La lista de empleados.
     */
    public ArrayList<String[]> getListaEmpleados() {
        return listaEmpleados;
    }

    /**
     * Establece la lista de empleados.
     *
     * @param listaEmpleados La nueva lista de empleados.
     */
    public void setListaEmpleados(ArrayList<String[]> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    /**
     * Obtiene el registro.
     *
     * @return El registro.
     */
    public String getRegistro() {
        return registro;
    }

    /**
     * Establece el registro.
     *
     * @param registro El nuevo registro.
     */
    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public void agregarEmpleadoRegistro() throws IOException {

        try (BufferedWriter objAgregarRegistro = new BufferedWriter(new FileWriter(this.nombreArchivo, true))) {

            objAgregarRegistro.append(registro);
            objAgregarRegistro.newLine();
        }
    }

    /**
     * Lee los registros de empleados del archivo de texto y los almacena en una
     * lista.
     *
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void leerEmpleadoRegistro() throws IOException {

        listaEmpleados = new ArrayList();
        try (BufferedReader objLeerRegistro = new BufferedReader(new FileReader(this.nombreArchivo))) {

            String lineaEmpleado = "";
            while ((lineaEmpleado = objLeerRegistro.readLine()) != null) {
                String[] datosEmpleados = lineaEmpleado.split(",");
                this.listaEmpleados.add(datosEmpleados);
            }
        }
    }

    /**
     * Actualiza un registro de empleado existente en el archivo de texto.
     *
     * @param id El ID del empleado a actualizar.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo.
     */
    public void actualizarEmpleadoRegistro(int id) throws IOException {

        File archivoActual = new File(this.nombreArchivo);
        File archivoTemporal = new File("temp_" + this.nombreArchivo);

        try (BufferedReader bR = new BufferedReader(new FileReader(archivoActual)); BufferedWriter bW = new BufferedWriter(new FileWriter(archivoTemporal, true))) {

            String registroActual;
            while ((registroActual = bR.readLine()) != null) {
                String[] datosActuales = registroActual.split(",");

                if (datosActuales[0].equals(String.valueOf(id))) {

                    bW.append(this.registro);
                    bW.newLine();
                } else {
                    bW.append(registroActual);
                    bW.newLine();
                }
            }
        }

        if (!archivoActual.delete()) {
            throw new IOException("No se puede borrar el archivo actual");
        }
        if (!archivoTemporal.renameTo(archivoActual)) {
            throw new IOException("No se puede renombrear el archivo actual");
        }
    }

    /**
     * Elimina un registro de empleado del archivo de texto.
     *
     * @param id El ID del empleado a eliminar.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo.
     */
    public void eliminarEmpleadoRegistro(int id) throws IOException {

        File archivoActual = new File(this.nombreArchivo);
        File archivoTemporal = new File("temp_" + this.nombreArchivo);

        try (BufferedReader bR = new BufferedReader(new FileReader(archivoActual)); BufferedWriter bW = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String registroActual;
            while ((registroActual = bR.readLine()) != null) {
                String[] datos = registroActual.split(",");

                if (datos[0].equals(String.valueOf(id))) {
                    continue;
                } else {
                    bW.append(registroActual);
                    bW.newLine();
                }
            }
        }
        if (!archivoActual.delete()) {
            throw new IOException("No se puede borrar el archivo actual");
        }
        if (!archivoTemporal.renameTo(archivoActual)) {
            throw new IOException("No se puede renombrear el archivo actual");
        }
    }

    /**
     * * Verifica la existencia de un empleado en el archivo. * * Lee el
     * archivo línea por línea y compara los datos de cada línea * con los
     * correos y contraseñas buscados. Asigna un valor de verificación según el
     * resultado de la búsqueda: - 1 si se encuentra el administrador. - 2 si se
     * encuentra un empleado. * - 3 si no se encuentra ninguno.
     *
     *
     * @throws IOException Si ocurre un error durante la lectura del archivo.
     */
    public void verificarEmpleado() throws IOException {

        try (BufferedReader readEmpleado = new BufferedReader(new FileReader(this.nombreArchivo))) {

            String line;

            while ((line = readEmpleado.readLine()) != null) {

                String[] datos = line.split(",");

                if ((buscarCorreo.equals(administrador)) && (buscarPassword.equals(contraAdmin))) {

                    verificacion = 1;
                    break;
                }

                if (datos[3].equals(buscarCorreo) && datos[2].equals(buscarPassword)) {

                    verificacion = 2;
                    break;
                } else {
                    verificacion = 3;
                }
            }
        }
    }
}
