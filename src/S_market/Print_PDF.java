/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Image;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.openide.util.Exceptions;

/**
 *
 * @author A7meD3H
 */
public class Print_PDF {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public String AA;
    public String A;
    public String A4;
    public String AA4;

    public String date;
    public String store_name;
    public String store_address;
    public String store_phone;
    public String store_v_g;
    public String store_note;
    public Blob store_logo;

    public int size = 0;

    public DefaultListModel listModel1 = new DefaultListModel();
    public DefaultListModel listModel2 = new DefaultListModel();
    public DefaultListModel listModel3 = new DefaultListModel();

    com.itextpdf.text.Font fontN = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, 18);
    com.itextpdf.text.Font fontN1 = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, 14);
    com.itextpdf.text.Font fontB = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 15);
    com.itextpdf.text.Font fontB1 = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 17);

    public void sql() {
        try {
            conn = DB.jave_db();
            String sql = " select * from tb_store_info where id = 1";

            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();
            if (rs.next()) {

                store_name = rs.getString("store_name");
                store_phone = rs.getString("store_phone");
                store_address = rs.getString("store_address");
                store_v_g = rs.getString("store_v_g");
                store_note = rs.getString("store_note");
                store_logo = rs.getBlob("store_logo");

            } else {

                date = "-";
                store_address = "-";
                store_phone = "-";

            }
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();

            } catch (Exception e) {

            }

        }

    }

    public PdfPTable sub1() {
        try {
            sql();
            PdfPTable T = new PdfPTable(2);
            T.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);
            PdfPCell ce1 = new PdfPCell();
            PdfPCell ce2 = new PdfPCell(new Paragraph(store_name, fontB));
            PdfPCell ce3 = new PdfPCell(new Paragraph(store_v_g, fontB));
            PdfPCell ce4 = new PdfPCell(new Paragraph("", fontB));

            byte[] imageBytes1 = null;
            try {
                imageBytes1 = store_logo.getBytes(1, (int) store_logo.length());
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
            com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance(imageBytes1);
            image1.scaleToFit(80f, 350f);
            image1.setAbsolutePosition(250, 250);
            image1.scaleAbsoluteWidth(80);
            image1.scaleAbsoluteHeight(80);

//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(store_logo).getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
            //  Image image = Image.getInstance(store_logo);
            //    com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(store_logo);
            ce1.addElement(image1);
            ce2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            ce2.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);
            ce3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            ce3.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);
            ce3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
            ce3.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_BOTTOM);

            ce1.setPaddingBottom(8);
            ce2.setPaddingBottom(8);
            ce3.setPaddingBottom(8);

            ce1.setPaddingTop(8);
            ce2.setPaddingTop(8);

            //   ce2.setColspan(2);
            ce1.setRowspan(3);
            ce1.setBorder(Rectangle.NO_BORDER);
            ce2.setBorder(Rectangle.NO_BORDER);
            ce3.setBorder(Rectangle.NO_BORDER);

            T.addCell(ce1);
            T.addCell(ce2);
            T.addCell(ce3);

            //   T.addCell(ce4);
            return T;
        } catch (BadElementException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public PdfPTable sub2() {

        PdfPTable T = new PdfPTable(1);
        T.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        PdfPCell c = new PdfPCell();
        Paragraph P = new Paragraph("فاتورة مشتريات", fontB1);
        P.setAlignment(PdfPCell.ALIGN_CENTER);
        P.setSpacingAfter(5);
        c.addElement(P);
        c.setBorder(Rectangle.NO_BORDER);
        T.addCell(c);
        return T;
    }

    public PdfPTable header1(String check_number, String date_buy, String user_buy, String resource) {
        sql();
        PdfPTable Table1 = new PdfPTable(9);//العناوين

        Table1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        PdfPCell ce1 = new PdfPCell(new Paragraph("رقم الفاتورة : ", fontB));
        PdfPCell ce2 = new PdfPCell(new Paragraph(check_number, fontB));
        PdfPCell ce3 = new PdfPCell(new Paragraph("إسم المندوب : ", fontB));
        PdfPCell ce4 = new PdfPCell(new Paragraph(resource, fontB));

        PdfPCell ce5 = new PdfPCell(new Paragraph(store_address, fontB));

        PdfPCell ce6 = new PdfPCell(new Paragraph("تاريخ الفاتورة : ", fontB));
        PdfPCell ce7 = new PdfPCell(new Paragraph(date_buy, fontB));
        PdfPCell ce8 = new PdfPCell(new Paragraph("إسم المستخدم :", fontB));

        PdfPCell ce9 = new PdfPCell(new Paragraph(user_buy, fontB));
        PdfPCell ce10 = new PdfPCell(new Paragraph(store_phone, fontB));

        ce1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
        ce2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
        ce4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

        ce5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);

        ce7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
        ce9.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

        ce10.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

        ce1.setPaddingBottom(8);
        ce2.setPaddingBottom(8);
        ce3.setPaddingBottom(8);
        ce4.setPaddingBottom(8);
        ce5.setPaddingBottom(8);
        ce1.setPaddingTop(8);
        ce2.setPaddingTop(8);
        ce3.setPaddingTop(8);
        ce4.setPaddingTop(8);
        ce5.setPaddingTop(8);
        ce6.setPaddingBottom(8);
        ce7.setPaddingBottom(8);
        ce8.setPaddingBottom(8);
        ce9.setPaddingBottom(8);
        ce10.setPaddingBottom(8);

        ce2.setColspan(2);
        ce4.setColspan(2);
        ce5.setColspan(3);

        ce7.setColspan(2);
        ce9.setColspan(2);
        ce10.setColspan(3);

        ce1.setBorder(Rectangle.TOP | Rectangle.RIGHT);
        ce2.setBorder(Rectangle.TOP | Rectangle.LEFT);
        ce3.setBorder(Rectangle.TOP);
        ce4.setBorder(Rectangle.TOP | Rectangle.LEFT);

        ce5.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT);

        ce6.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
        ce7.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
        ce8.setBorder(Rectangle.BOTTOM);
        ce9.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);

        ce10.setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);

        Table1.addCell(ce1);
        Table1.addCell(ce2);
        Table1.addCell(ce3);
        Table1.addCell(ce4);

        Table1.addCell(ce5);
        Table1.addCell(ce6);
        Table1.addCell(ce7);
        Table1.addCell(ce8);

        Table1.addCell(ce9);
        Table1.addCell(ce10);

        return Table1;
    }

    public PdfPTable t1() {

        PdfPTable Table1 = new PdfPTable(11);//العناوين

        Table1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        PdfPCell ce1 = new PdfPCell(new Paragraph("رقم المنتج", fontB1));
        PdfPCell ce2 = new PdfPCell(new Paragraph("إسم المنتج", fontB1));
        PdfPCell ce3 = new PdfPCell(new Paragraph("نوع الصنف", fontB1));//الجدول الثالث 12 
        PdfPCell ce4 = new PdfPCell(new Paragraph("وحده الشراء", fontB1));
        PdfPCell ce5 = new PdfPCell(new Paragraph("سعر الشراء", fontB1));
        PdfPCell ce6 = new PdfPCell(new Paragraph("الكمية", fontB1));
        PdfPCell ce7 = new PdfPCell(new Paragraph("كمية الزيادة", fontB1));
        PdfPCell ce8 = new PdfPCell(new Paragraph("نوع الخصم", fontB1));
        PdfPCell ce9 = new PdfPCell(new Paragraph("كمية الخصم", fontB1));
        PdfPCell ce10 = new PdfPCell(new Paragraph("الجملة", fontB1));
        PdfPCell ce11 = new PdfPCell(new Paragraph("تاريخ الصلاحية", fontB1));

        ce1.setPaddingBottom(7);
        ce1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce9.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce10.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce10.setPaddingBottom(7);
        ce11.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce11.setPaddingBottom(7);

        ce1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce5.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce6.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce7.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce8.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce9.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce10.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce11.setBackgroundColor(BaseColor.LIGHT_GRAY);

        Table1.addCell(ce1);
        Table1.addCell(ce2);
        Table1.addCell(ce3);
        Table1.addCell(ce4);
        Table1.addCell(ce5);
        Table1.addCell(ce6);
        Table1.addCell(ce7);
        Table1.addCell(ce8);
        Table1.addCell(ce9);
        Table1.addCell(ce10);
        Table1.addCell(ce11);
        return Table1;
    }

    public PdfPTable t2(String total, DefaultListModel listModel1, DefaultListModel listModel2, DefaultListModel listModel3, DefaultListModel listModel4, DefaultListModel listModel5, DefaultListModel listModel6, DefaultListModel listModel7, DefaultListModel listModel8, DefaultListModel listModel9, DefaultListModel listModel10, DefaultListModel listModel11) {

        PdfPTable Table2 = new PdfPTable(11);

        int x = listModel1.getSize();
        for (int i = 0; i < x; i++) {

            Table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

            PdfPCell ce1 = new PdfPCell(new Paragraph(listModel1.getElementAt(i).toString(), fontB));
            PdfPCell ce2 = new PdfPCell(new Paragraph(listModel2.getElementAt(i).toString(), fontB));
            PdfPCell ce3 = new PdfPCell(new Paragraph(listModel3.getElementAt(i).toString(), fontB));//الجدول الثالث 12 
            PdfPCell ce4 = new PdfPCell(new Paragraph(listModel4.getElementAt(i).toString(), fontB));
            PdfPCell ce5 = new PdfPCell(new Paragraph(listModel5.getElementAt(i).toString(), fontB));
            PdfPCell ce6 = new PdfPCell(new Paragraph(listModel6.getElementAt(i).toString(), fontB));
            PdfPCell ce7 = new PdfPCell(new Paragraph(listModel7.getElementAt(i).toString(), fontB));
            PdfPCell ce8 = new PdfPCell(new Paragraph(listModel8.getElementAt(i).toString(), fontB));
//
//            DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//            decimalFormat.setMaximumFractionDigits(340);
//
//            double x1 = Double.valueOf(listModel7.getElementAt(i).toString());
//            double x2 = Double.valueOf(listModel8.getElementAt(i).toString());
//            double sum = x1 * x2;

            PdfPCell ce9 = new PdfPCell(new Paragraph(listModel9.getElementAt(i).toString(), fontB));
            PdfPCell ce10 = new PdfPCell(new Paragraph(listModel10.getElementAt(i).toString(), fontB));
            PdfPCell ce11 = new PdfPCell(new Paragraph(listModel11.getElementAt(i).toString(), fontB));

            ce1.setPaddingBottom(7);
            ce1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce9.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce10.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ce11.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

            ce1.setPaddingBottom(10);
            ce2.setPaddingBottom(10);
            ce3.setPaddingBottom(10);
            ce4.setPaddingBottom(10);
            ce5.setPaddingBottom(10);
            ce6.setPaddingBottom(10);
            ce7.setPaddingBottom(10);
            ce8.setPaddingBottom(10);
            ce9.setPaddingBottom(10);
            ce10.setPaddingBottom(10);
            ce11.setPaddingBottom(10);

            ce1.setPaddingTop(10);
            ce2.setPaddingTop(10);
            ce3.setPaddingTop(10);
            ce4.setPaddingTop(10);
            ce5.setPaddingTop(10);
            ce6.setPaddingTop(10);
            ce7.setPaddingTop(10);
            ce8.setPaddingTop(10);
            ce9.setPaddingTop(10);
            ce10.setPaddingTop(10);
            ce11.setPaddingTop(10);

            Table2.addCell(ce1);
            Table2.addCell(ce2);
            Table2.addCell(ce3);
            Table2.addCell(ce4);
            Table2.addCell(ce5);
            Table2.addCell(ce6);
            Table2.addCell(ce7);
            Table2.addCell(ce8);
            Table2.addCell(ce9);
            Table2.addCell(ce10);
            Table2.addCell(ce11);
        }
        Table2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        PdfPCell ce1 = new PdfPCell(new Paragraph("إجمالي الفاتورة  " + " ", fontB1));
        PdfPCell ce2 = new PdfPCell(new Paragraph(total, fontB1));
        PdfPCell ce3 = new PdfPCell(new Paragraph("", fontN1));

        ce1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
        ce2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        ce1.setPaddingBottom(10);
        ce2.setPaddingBottom(10);
        ce1.setPaddingTop(10);
        ce2.setPaddingTop(10);
        ce3.setBorder(Rectangle.NO_BORDER);

        ce1.setColspan(8);
        ce3.setColspan(2);

        ce1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        ce2.setBackgroundColor(BaseColor.LIGHT_GRAY);

        Table2.addCell(ce1);
        Table2.addCell(ce2);
        Table2.addCell(ce3);

        return Table2;
    }

}
