package AccesoDatos;

import Entidades.Deducciones;
import com.itextpdf.text.Document; // Importa la clase Document de iText, que se usa para crear y manipular documentos PDF en Java.
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;//Permite trabajar con objetos de tipo Paragraph para añadir texto en los documentos PDF.
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; //permite escribir contenido en un archivo PDF y manejar la creación del archivo.
import java.io.FileNotFoundException;
import java.io.FileOutputStream;// Esta clase se usa para escribir datos a un archivo, en este caso, para guardar el PDF generado.
import java.util.ArrayList;
import java.util.List;

public class CrearPatronoPDF {

    /**
     * El nombre del archivo.
     */
    private String nombreArchivo, registro;

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
     * @param nombreArchivo  
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene el registro de datos asociado al archivo.
     *
     * @return El registro de datos.
     */
    public String getRegistro() {
        return registro;
    }

    /**
     * Establece el registro de datos asociado al archivo.
     *
     * @param registro El nuevo registro de datos.
     */
    public void setRegistro(String registro) {
        this.registro = registro;
    }

    /**
     * Crea un archivo PDF con los datos del usuario.Este método toma los datos del usuario almacenados en el atributo
 `registro` y los utiliza para generar un documento PDF.
     *
     * El nombre del
 archivo PDF se obtiene del atributo `nombreArchivo`.
     *
     * @param listaDeducciones
     * @throws DocumentException Si ocurre un error al crear el documento PDF.
     * @throws FileNotFoundException Si no se encuentra el archivo de salida.
     */
    public void createPDF(List<Deducciones> listaDeducciones) throws DocumentException, FileNotFoundException {
        Document documento = new Document();
        String[] datos = this.registro.split(",");
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            documento.add(new Paragraph("Asunto: Envío de Nómina del Patrono" + "\n"
                    + " Estimado/a Patrono " + "\n"
                    + "Le enviamos la nómina del personal correspondiente en formato PDF, desde la dirección de " + datos[1] + "\n"
                    + "Quedamos a su disposición para cualquier consulta o aclaración que pueda necesitar respecto a este documento. " + "\n"
            ));

            for (Deducciones objDeducciones : listaDeducciones) {

                documento.add(new Paragraph("\n"));
                PdfPTable tabla = new PdfPTable(6);

                tabla.addCell("Nombre");
                tabla.addCell("CCSS");
                tabla.addCell("Impuesto Renta");
                tabla.addCell("Asociacion social");
                tabla.addCell("Salario Bruto");
                tabla.addCell("Salario Neto");

                tabla.addCell(objDeducciones.getNombre());
                tabla.addCell("₡ " + objDeducciones.getCcss());
                tabla.addCell("₡ " + objDeducciones.getImpuestoRenta());
                tabla.addCell("₡ " + objDeducciones.getAsociacionSocial());
                tabla.addCell("₡ " + objDeducciones.getSalarioBruto());
                tabla.addCell("₡ " + objDeducciones.getSalarioNeto());

                documento.add(tabla);
            }

            documento.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(5);

            tabla.addCell("Total Planilla");
            tabla.addCell("CCSS");
            tabla.addCell("Aportes Institucionales");
            tabla.addCell("Aportes LPT");
            tabla.addCell("Total de aportaciones");

            tabla.addCell("₡ " + datos[1]);
            tabla.addCell("₡ " + datos[2]);
            tabla.addCell("₡ " + datos[3]);
            tabla.addCell("₡ " + datos[4]);
            tabla.addCell("₡ " + datos[5]);

            documento.add(tabla);

        } finally {
            documento.close();
        }

    }

}
