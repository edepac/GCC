
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class GenerarPdf {
	public static void hazPDF(int mes, int año,int pistas, int entrenadores,int cascos, int sliders, int escobas) throws DocumentException, MalformedURLException, IOException{
		 Document document = new Document(PageSize.A4, 35, 30, 50, 50);
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\AFM-ASUS-i7\\Desktop\\PrevisionIngresos.pdf");
		 PdfWriter.getInstance(document, fileOutputStream);
        document.open();
        Image imagen = Image.getInstance("C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\Fotos Inso\\Logo GCC.png");        
       
        imagen.scaleToFit(200,200);
        imagen.setAbsolutePosition(320f, 600);
        
        document.add(imagen);
        
        
        
        Font fuente5=new Font();
        fuente5.setSize(13);
        fuente5.setStyle(1);
        String cero="0";
        if(mes<=9){
        Paragraph fecha=new Paragraph("0"+mes+"/"+año+"               ",fuente5);
        fecha.setAlignment(Element.ALIGN_RIGHT);
        document.add((fecha));
        }else{
        Paragraph fecha=new Paragraph(mes+"/"+año+"               ",fuente5); 
        fecha.setAlignment(Element.ALIGN_RIGHT);
        document.add((fecha));
        }
        
        
        
        
       
        
        
        
        Paragraph parrafo = new Paragraph();
        DecimalFormat df = new DecimalFormat("0.00");
        
        Font fuente=new Font();
        fuente.setSize(32);
        fuente.setStyle(1);
        fuente.setColor(21, 40, 207);
        Paragraph titulo=new Paragraph("    Gotham"+"\n"+"     Curling"+"\n"+"       Club",fuente);
        titulo.setAlignment(Element.ALIGN_LEFT);
        document.add((titulo));
      
        document.add(new Chunk("_______________________________________________________________________________"));
       
        
        Font fuente2=new Font();
        fuente2.setSize(15);
        fuente2.setStyle(1);
        Paragraph titulo2=new Paragraph("PREVISIÓN DE SOLICITUDES ",fuente2);
        titulo.setAlignment(Element.ALIGN_LEFT);
        document.add((titulo2));
        document.add(new Chunk(" "));
        document.add(parrafo);
        
        document.add(new Chunk("Número de pistas alquiladas:                                                                                            "+ (pistas)));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Número de sesiones de entrenador alquiladas:                                                                "+ (entrenadores)));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Número de cascos alquilados:                                                                                          "+ (cascos)));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Número de sliders alquilados:                                                                                           "+ (sliders)));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Número de escobas alquiladas:                                                                                        "+ (escobas)));
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(new Chunk("_______________________________________________________________________________"));
        

        
        
        Font fuente3=new Font();
        fuente3.setSize(15);
        fuente3.setStyle(1);
        Paragraph titulo3=new Paragraph("PREVISIÓN DE INGRESOS ",fuente3);
        titulo3.setAlignment(Element.ALIGN_LEFT);
        document.add((titulo3));
        document.add(new Chunk(" "));
        document.add(parrafo);
        
        document.add(new Chunk("Ingresos por pistas alquiladas:                                                                                       "+ df.format(pistas*150)+" €"));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Ingresos por sesiones de entrenador alquiladas:                                                           "+ df.format(entrenadores*70)+" €"));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Ingresos por cascos alquilados:                                                                                      "+ df.format(cascos*12)+" €"));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Ingresos por sliders alquilados:                                                                                       "+ df.format(sliders*18)+" €"));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Ingresos por escobas alquiladas:                                                                                    "+ df.format(escobas*20)+" €"));
        document.add(parrafo);
        document.add(new Chunk(" "));
        document.add(parrafo);
      
       
        document.add(new Chunk("_______________________________________________________________________________"));
        
      
        
        int total=pistas*100+ entrenadores*50+cascos*10+ sliders*14+ escobas*15;
        
        Font fuente4=new Font();
        fuente4.setSize(15);
        fuente4.setStyle(1);
        Paragraph titulo4=new Paragraph("PREVISIÓN DE INGRESOS TOTALES ",fuente4);
        titulo4.setAlignment(Element.ALIGN_LEFT);
        document.add((titulo4));
        document.add(new Chunk(" "));
        document.add(parrafo);
        document.add(new Chunk("Ingresos totales previstos:                                                                                               "+ df.format(total)+" €"));
        
        
        document.close();              
	
	 }
	
	
}
