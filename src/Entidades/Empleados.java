package Entidades;

import java.util.ArrayList;
/**
 *
 * @author Empresaurio50
 */
public class Empleados {

    private String nombre, correo, password;
    private int id, verificacion;
    private double salarioBruto;
    private ArrayList empleadosLista = new ArrayList();

    /**
     * Obtiene el salario bruto.
     *
     * @return El salario bruto.
     */
    public double getSalarioBruto() {
        return salarioBruto;
    }

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
     * Obtiene el salario bruto (sin descuentos) de un empleado.
     *
     * @return El salario bruto como un valor double.
     */
    public double getTotalPagar() {
        return salarioBruto;
    }

    /**
     * Establece el salario bruto (sin descuentos) de un empleado.
     *
     * @param salarioBruto El nuevo salario bruto del empleado como un valor
     * double.
     */
    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    /**
     * Obtiene una lista de empleados.
     *
     * Esta función devuelve una lista que contiene información sobre los
     * empleados. El formato exacto de la información en la lista puede variar
     * dependiendo de cómo esté implementada la clase.
     *
     * @return Una lista de empleados.
     */
    public ArrayList<String[]> getEmpleadosLista() {    
        return empleadosLista;
    }

    /**
     * Establece la lista de empleados en un formato específico (posiblemente
     * una lista de arreglos de cadenas de caracteres).
     *
     * @param empleadosLista La nueva lista de empleados como un objeto
     * `ArrayList<String[]>`.
     */
    public void setEmpleadosLista(ArrayList empleadosLista) {
        this.empleadosLista = empleadosLista;
    }

    /**
     * Obtiene el nombre de un empleado.
     *
     * @return El nombre del empleado como una cadena de caracteres.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de un empleado.
     *
     * @param nombre El nuevo nombre del empleado como una cadena de caracteres.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico de un empleado.
     *
     * @return El correo electrónico del empleado como una cadena de caracteres.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico de un empleado.
     *
     * @param correo El nuevo correo electrónico del empleado como una cadena de
     * caracteres.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña de un empleado.
     *
     * @return La contraseña del empleado como una cadena de caracteres.
     *
     * @aviso: Almacenar contraseñas en texto plano es una mala práctica de
     * seguridad. Se deben utilizar técnicas como el hash y el salazón para
     * proteger las contraseñas.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña de un empleado.
     *
     * @param password La nueva contraseña del empleado como una cadena de
     * caracteres.
     *
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de este objeto.
     *
     * @param id El nuevo identificador único.
     */
    public void setId(int id) {
        this.id = id;
    }

}
