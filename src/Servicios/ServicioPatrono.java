/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicios;

import Entidades.Nominas;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

/**
 *
 * @author Empresaurio50
 */
public interface ServicioPatrono {

    void enviarCorreos(Nominas objNominas) throws AddressException, SendFailedException, MessagingException, IOException;

    void crearPDF(Nominas objNominas) throws DocumentException, FileNotFoundException, IOException;

}
