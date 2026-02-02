package AccesoDatos;

import java.io.BufferedReader; //Se utiliza para leer texto de una entrada (como un archivo)
import java.io.File; //Proporciona una clase para trabajar con archivosPDF y directorios
//permitiendo realizar acciones como crear, borrar o verificar la existencia de un archivo.
import java.io.FileReader;//Es una clase que permite leer el contenido de un archivo de texto carácter por carácter.
import java.io.IOException;
import javax.mail.*;//Proporciona clases y métodos para enviar y recibir correos electrónicos utilizando el protocolo SMTP.
import javax.mail.internet.*;//Incluye clases específicas para trabajar con correos electrónicos de Internet
import java.util.Properties; //Se utiliza para almacenar y gestionar un conjunto de valores clave, como configuraciones de aplicaciones.
import java.util.ArrayList;
import javax.activation.DataHandler;//Proporciona una forma de manejar los datos de diferentes tipos en mensajes MIME, como adjuntar archivosPDF a correos electrónicos.
import javax.activation.FileDataSource;//Permite manejar archivosPDF como fuentes de datos para adjuntarlos en un correo electrónico o procesarlos en aplicaciones que usan MIME.

public class EnviarCorreo {

    private Properties propertiesSMTP = null;
    private Session seccionSMTP = null;
    private ArrayList<BodyPart> adjuntos = new ArrayList<>();
    private ArrayList<File> archivos = new ArrayList<>();
    private String nombrePDF;

    /**
     * Obtiene el nombre del archivo PDF.
     *
     * @return El nombre del archivo PDF.
     */
    public String getNombrePDF() {
        return nombrePDF;
    }

    /**
     * Establece el nombre del archivo PDF.
     *
     * @param nombrePDF El nombre del archivo PDF.
     */
    public void setNombrePDF(String nombrePDF) {
        this.nombrePDF = nombrePDF;
    }

    private String datos;

    /**
     * Obtiene la lista de archivos.
     *
     * @return La lista de archivos.
     */
    public ArrayList<File> getArchivos() {
        return archivos;
    }

    /**
     * Añade un archivo a la lista de archivos.
     *
     * @param archivos El archivo a añadir a la lista.
     */
    public void setArchivos(File archivos) {
        this.archivos.add(archivos);
    }

    /**
     * Obtiene los datos.
     *
     * @return Los datos.
     */
    public String getDatos() {
        return datos;
    }

    /**
     * Establece los datos.
     *
     * @param datos Los nuevos datos.
     */
    public void setDatos(String datos) {
        this.datos = datos;
    }

    /**
     * Carga la configuración del servidor SMTP desde un archivo de texto.
     *
     * Lee el archivo "config.txt" y almacena las propiedades del servidor SMTP
     * en un objeto Properties.
     *
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private void cargarConfiguracionSMTP() throws IOException {

        propertiesSMTP = new Properties();
        String ArchivoDeConfiguracion = "config.txt";
        String linea = null;
        String[] atributos = null;

        try (BufferedReader objBufferredReader = new BufferedReader(new FileReader(ArchivoDeConfiguracion))) {

            while ((linea = objBufferredReader.readLine()) != null) {

                atributos = linea.split(",");
                propertiesSMTP.put(atributos[0], atributos[1]);
            }
        }
    }

    /**
     * Crea una sesión SMTP para enviar correos electrónicos.
     *
     * Utiliza las propiedades cargadas previamente para crear una sesión SMTP y
     * autenticarse en el servidor.
     */
    public void crearSeccion() {
        seccionSMTP = Session.getInstance(propertiesSMTP, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("empresaurio50@gmail.com", "wjon ltpj mxdb exom");
            }
        });

    }

    /**
     * Crea partes MIME para adjuntar archivos al correo electrónico.
     *
     * Itera sobre los archivos proporcionados y crea partes MIME para cada uno,
     * agregándolas a una lista.
     *
     * @throws MessagingException Si ocurre un error al crear las partes MIME.
     */
    public void crearArchivos() throws MessagingException {

        try {

            for (File archivos : getArchivos()) {
                BodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(archivos.getAbsolutePath())));
                adjunto.setFileName(archivos.getName());
                adjuntos.add(adjunto);
            }
        } finally {

        }
    }

    /**
     * Envía un correo electrónico con archivos adjuntos.
     *
     * Carga la configuración SMTP, crea una sesión, crea el mensaje con los
     * datos del usuario y los adjuntos, y envía el correo.
     *
     * @throws AddressException Si ocurre un error con las direcciones de
     * correo.
     * @throws SendFailedException Si falla el envío del correo.
     * @throws MessagingException Si ocurre una excepción relacionada con el
     * correo.
     * @throws IOException Si ocurre un error al leer o escribir archivosPDF.
     */
    public void EnviarCorreos() throws AddressException, SendFailedException, MessagingException, IOException {

        cargarConfiguracionSMTP();

        crearSeccion();

        String[] datosU = this.datos.split(",");

        setArchivos(new File(nombrePDF));
        try {
            // Crear objeto
            Message objCorreo = new MimeMessage(seccionSMTP);
            objCorreo.setFrom(new InternetAddress("empresaurio50@gmail.com"));// user del servidor
            objCorreo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(datosU[1]));//datosU 1 es el correo
            objCorreo.setSubject(datosU[3]);//datosU 3 es el asunto

            // Crear mensaje
            BodyPart objBodyPart = new MimeBodyPart();
            objBodyPart.setText(datosU[4]); //mensaje

            // Crear multipart 
            Multipart objMultipart = new MimeMultipart();

            // Configurar el contenido del mensaje con el multipart
            objMultipart.addBodyPart(objBodyPart);
            if (getArchivos() != null) {
                crearArchivos();
                for (BodyPart adjunto : adjuntos) {
                    objMultipart.addBodyPart(adjunto);
                }
            }

            objCorreo.setContent(objMultipart);

            // Enviar el correo
            Transport.send(objCorreo);

        } finally {
        }
    }

}
