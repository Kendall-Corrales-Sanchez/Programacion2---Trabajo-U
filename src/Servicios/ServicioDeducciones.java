/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servicios;

import Entidades.Deducciones;
import Exepciones.CustomException;
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
public interface ServicioDeducciones {
    
    void enviarCorreos(Deducciones objDeducciones) throws AddressException, SendFailedException, MessagingException, IOException, CustomException ;

    void crearPDF(Deducciones objDeducciones) throws DocumentException, FileNotFoundException, CustomException;
}
