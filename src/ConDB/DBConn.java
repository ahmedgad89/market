/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConDB;

import S_market.DB;
import S_market.stop_editable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ahmedfg
 */
public class DBConn {
    
    PreparedStatement prs = null;
    ResultSet rs = null;

    public static Connection conn() {
        
        
        try {
            String Ip_Port = null;
            String db_name = null;
            String db_user = null;
            String db_pass = null;
            Properties prop = new Properties();
            File f = new File(System.getProperty("user.dir"), "\\server.properties.txt");
         
            InputStream input = null;
            if (f.canExecute()) {
                input = new FileInputStream(f);
                prop.load(input);
                Ip_Port = prop.getProperty("Ip_Port");
                db_name = prop.getProperty("db_name");
                db_user = prop.getProperty("db_user");
                db_pass = prop.getProperty("db_pass");
                if (Ip_Port == null || db_name==null || db_user == null || db_pass == null) {
                    OutputStream output = null;

                    output = new FileOutputStream(f);
                    prop.setProperty("Ip_Port", "localhost:3306");
                    prop.setProperty("db_name", "s_market_db");
                    prop.setProperty("db_user", "root");
                    prop.setProperty("db_pass", "");
                    prop.store(output, null);
                    Ip_Port = prop.getProperty("Ip_Port");
                    db_name = prop.getProperty("db_name");
                    db_user = prop.getProperty("db_user");
                    db_pass = prop.getProperty("db_pass");

                }
            } else {
                OutputStream output = null;

                output = new FileOutputStream(f);
                prop.setProperty("Ip_Port", "localhost:3306");
                prop.setProperty("db_name", "s_market_db");
                prop.setProperty("db_user", "root");
                prop.setProperty("db_pass", "");
                prop.store(output, null);
            }

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;

            conn = DriverManager.getConnection("jdbc:mysql://" + Ip_Port + "/"+db_name+"?useUnicode=yes&characterEncoding=UTF-8", db_user, db_pass);

             //JOptionPane.showMessageDialog(null, "ok ");
            
            return conn;

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "خطأ في جلب البيانات من قاعدة البيانات ..");
            return null;

       
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "تأكد من أيبي السيرفر الموجود في server.properties "+"\n"+ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
    }

    public int add(String tablename, String[] values, byte[] imageByte) {
        String insert = "insert  into " + tablename + " values(";
        for (int i = 0; i < values.length; i++) {
            insert += "?,";
        }
        insert = insert.substring(0, insert.length() - 1) + ")";

        if (imageByte != null) {

            try {
                prs = conn().prepareStatement(insert);
                for (int i = 1; i < values.length; i++) {
                    prs.setString(i, values[i - 1]);
                }
                prs.setBytes(values.length, imageByte);
                prs.executeUpdate();
                return -1;

            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "\n"+e, "خطأ 1", JOptionPane.ERROR_MESSAGE);
                return 0;

            }
        } else {
            try {
                prs = conn().prepareStatement(insert);
                for (int i = 1; i <= values.length; i++) {
                    prs.setString(i, values[i - 1]);
                }

                prs.executeUpdate();
                return -1;

            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, "\n"+e, "خطأ 2", JOptionPane.ERROR_MESSAGE);
                return e.getErrorCode();

            }
        }

    }

    public int getMaxPK(String ColumnName, String tableame) {
        String sql = "select max(" + ColumnName + ") from " + tableame + "";
        int max = 0;
        try {
            prs = conn().prepareStatement(sql);
            rs = prs.executeQuery();
            if (rs.next()) {
                max = rs.getInt(1);
            }
            return max + 1;

        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "\n"+e, "خطأ 3", JOptionPane.ERROR_MESSAGE);
            return e.getErrorCode();
        }
    }

    public ResultSet selecte(String tabelName, String[] choosenFail, String[] whereFields, String[] valus, String op, String sep) {

        String sql = "select ";
        for (int i = 0; i < choosenFail.length; i++) {
            sql += choosenFail[i] + ",";
        }

        sql = sql.substring(0, sql.length() - 1);
        sql += " from " + tabelName + " where";
        String select = makwher(sql, whereFields, op, sep);
        try {
            prs = conn().prepareStatement(select);
            for (int i = 1; i <= valus.length; i++) {
                prs.setString(i, valus[i - 1]);

            }
            rs = prs.executeQuery();
            return rs;

        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "\n"+e, "خطأ 4", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public String makwher(String sql, String[] fields, String op, String sep) {

        String sql1 = sql;
        for (int i = 0; i < fields.length; i++) {

            sql1 += " " + fields[i] + " " + op + "? " + sep;

        }

        sql1 = sql1.substring(0, sql1.length() - sep.length());

        return sql1;
    }

    public void print(String message, JTable table) {

        MessageFormat header = new MessageFormat(message);

        MessageFormat footer = new MessageFormat("0.(number,integer)");

        try {
            boolean rs = table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            if (rs == true) {
                JOptionPane.showMessageDialog(null, "تمة الطباعه");
            }
        } catch (PrinterException ex) {
             JOptionPane.showMessageDialog(null, "\n"+ex, "خطأ 5", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int delete(String tabelName, String[] whereFields, String[] whereValues, String op, String sep) {

        String delete = "delete from " + tabelName + " where";
        delete = makwher(delete, whereFields, op, sep);
        try {
            prs = conn().prepareStatement(delete);
            for (int i = 1; i <= whereValues.length; i++) {
                prs.setString(i, whereValues[i - 1]);

            }
            prs.executeUpdate();
            return -1;

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "\n"+ex, "خطأ 6", JOptionPane.ERROR_MESSAGE);
            return ex.getErrorCode();
        }

    }

    public int update(String tabelName, String[] updateFail, String[] updatevalus, String[] whereFields, String[] wherevalus, byte[] imageByte, String op, String sep) {

        String update = "update " + tabelName + " set ";
        update = makwher(update, updateFail, op, ",");
        update += makwher(" where ", whereFields, op, sep);
        if (imageByte != null) {
            try {
                prs = conn().prepareStatement(update);
                int i;
                for (i = 1; i < updatevalus.length; i++) {
                    prs.setString(i, updatevalus[i - 1]);
                }
                prs.setBytes(updatevalus.length, imageByte);
                i = updatevalus.length + 1;
                for (int y = i; y < wherevalus.length + i; y++) {
                    prs.setString(y, wherevalus[y - i]);
                }
                prs.executeUpdate();
                return -1;
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, "\n"+e, "خطأ 7", JOptionPane.ERROR_MESSAGE);
                return e.getErrorCode();
            }
        } else {
            try {
                prs = conn().prepareStatement(update);
                int i;
                for (i = 1; i <= updatevalus.length; i++) {
                    prs.setString(i, updatevalus[i - 1]);
                }
                i = updatevalus.length + 1;
                for (int y = i; y < wherevalus.length + i; y++) {
                    prs.setString(y, wherevalus[y - i]);
                }
                prs.executeUpdate();
                return -1;
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, "\n"+e, "خطأ 8", JOptionPane.ERROR_MESSAGE);
                return e.getErrorCode();
            }
        }

    }

    public static void setTableR(JTable table) {

        DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
        cent.setHorizontalAlignment(JLabel.CENTER);

        table.getTableHeader().setDefaultRenderer(cent);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cent);
        }

        table.setSize(50, 50);
        table.setEditingRow(0);
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(30, 30));
        header.setBackground(new java.awt.Color(93, 156, 236));
        header.setForeground(new java.awt.Color(240, 240, 240));
        header.setFont(new java.awt.Font("Times New Roman", 1, 18));
        header.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));

    }

    public void tab(JTable table) {

        try {

            ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                    .setHorizontalAlignment((int) JLabel.CENTER);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);

            table.getColumnModel().getColumn(0).setCellRenderer(renderer);

            for (int i = 0; i < table.getColumnCount(); i++) {

                table.getColumnModel().getColumn(i).setCellRenderer(renderer);
                table.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));
                table.getColumnModel().getColumn(i).setWidth(500);
                DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();

                TableColumn col = colModel.getColumn(i);
                TableCellRenderer hRenderer = col.getHeaderRenderer();

                int width = 0;
                TableCellRenderer renderer1 = col.getHeaderRenderer();

                JTableHeader header = (JTableHeader) col.getHeaderRenderer();
                for (int r = 0; r < table.getRowCount(); r++) {
                    renderer = (DefaultTableCellRenderer) table.getCellRenderer(r, i);
                    Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i),
                            false, false, r, i);
                    width = Math.max(width, comp.getPreferredSize().width);
                }

                col.setPreferredWidth(width + 2);

                //setTableAlignmentValue(table, i);
            }

            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            table.setRowHeight(30);

            table.setSize(50, 50);
            table.setEditingRow(0);
            JTableHeader header = table.getTableHeader();
            header.setPreferredSize(new Dimension(30, 30));
            header.setBackground(new java.awt.Color(93, 156, 236));
            header.setForeground(new java.awt.Color(240, 240, 240));
            header.setFont(new java.awt.Font("Times New Roman", 1, 18));
            header.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));

            int colNo = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] objects = new Object[colNo];
                // tanks to umit ozkan for the bug fix!
                for (int i = 0; i < colNo; i++) {
                    objects[i] = rs.getObject(i + 1);
                    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    table.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));

                    //setTableAlignmentValue(table, i);
                }

            }

        } catch (Exception e) {
 JOptionPane.showMessageDialog(null, "\n"+e, "خطأ 9", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void printArra(String message, JTextArea jTextArea1) {

        MessageFormat header = new MessageFormat(message);

        MessageFormat footer = new MessageFormat("0.(number,integer)");

        try {
            boolean rs = jTextArea1.print();
            if (rs == true) {
                JOptionPane.showMessageDialog(null, "تمة الطباعه");
            }
        } catch (PrinterException ex) {
             JOptionPane.showMessageDialog(null, "\n"+ex, "خطأ 10", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
     public static void main(String args[]) {
         
          conn();
         
     }
    
     */
}
