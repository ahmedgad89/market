/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author A7meD3H
 */
public class Report_Main {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public String AA;
    public String A;
    public String A4;
    public String AA4;

    public String date;
    public String add0;
    public String add1;
    public String add2;
    public String add3;
    public String add4;
    public String add5;
    public String add6;
    public String add7;
    public String add8;
    public String add9;

    com.itextpdf.text.Font fontN = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, 18);
    com.itextpdf.text.Font fontN1 = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, 14);
    com.itextpdf.text.Font fontB = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 18);
    com.itextpdf.text.Font fontB1 = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 20);
    com.itextpdf.text.Font fontB2 = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 40);

   
    public PdfPTable sub1( ) throws IOException, BadElementException {
       

        File Dirimage = new File(System.getProperty("user.dir"));
        com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(Dirimage + "\\Regset.png");

        image.setRotationDegrees(00.0f);
        image.scaleAbsolute(650, 120);

        PdfPTable T = new PdfPTable(1);
        T.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        PdfPCell c = new PdfPCell();

        image.setAlignment(PdfPCell.ALIGN_CENTER);
        image.setSpacingBefore(10);
        image.setSpacingAfter(10);
        c.addElement(image);
        c.setBorder(Rectangle.NO_BORDER);
        T.addCell(c);

        return T;
    }

}
