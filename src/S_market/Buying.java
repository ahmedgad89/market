/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.openide.util.Exceptions;

/**
 *
 * @author A7meD3H
 */
public class Buying extends javax.swing.JDialog {

    public static Connection conn = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;
    public static String AA;
    public static String A;
    public static String A1;
    public static String AA2;
    public static String A2;
    public static String AA3;
    public static String A3;
    public static String A4;
    public static String AA4;
    public static String A5;
    public static String AA5;
    public static String tablec;
    public static int x;
    public int y;
    public static Border red = new LineBorder(Color.red);
    public static Border black = new LineBorder(Color.black);
    public static String saveLoc = null;
    public static Boolean isPathChosen = false;
    public static ImageIcon format = null;
    public static String filename = null;
    public static byte[] s_image = null;
    public static byte[] add2 = null;
    public static ByteArrayOutputStream bos;
    public static int xx, xy;
    public static JButton button = new JButton("");
    public static JTextField field = new JTextField();
    public static JComboBox ComboBox = new JComboBox();
    public static JComboBox type_name = new JComboBox();
    public static JComboBox discount = new JComboBox();
    public static JComboBox limit = new JComboBox();
    public static DefaultTableModel aModel;
    public static JTableHeader header;
    public static DefaultTableCellRenderer renderer;
    public static JDateChooser date = new JDateChooser();
    public String patch;
    com.itextpdf.text.Font fontN = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, 18, com.itextpdf.text.Font.BOLD, BaseColor.LIGHT_GRAY);
    com.itextpdf.text.Font fontN1 = FontFactory.getFont("c:/windows/fonts/times.ttf", BaseFont.IDENTITY_H, 14);
    com.itextpdf.text.Font fontB = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 18);
    com.itextpdf.text.Font fontB1 = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 20);
    com.itextpdf.text.Font fonts = FontFactory.getFont("c:/windows/fonts/timesbd.ttf", BaseFont.IDENTITY_H, 5, com.itextpdf.text.Font.BOLD, BaseColor.LIGHT_GRAY);
    public static TableColumnAdjuster adjuster;

    /**
     * Creates new form ADU
     */
    public Buying() {
        initComponents();   
        setModal(true);
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();

        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Update_table();
        Update_resource();

        co_resource.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        ((JLabel) co_resource.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);

        ComboBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        ((JLabel) ComboBox.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);

        date.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        tex_date_buy.setText(Get_Date());
        tex_user_buy.setText("أحمد عامر");
        tex_check_number.setText(Get_Random_Number());
        
        
        button.addActionListener((ActionEvent event) -> {
            DefaultCellEditor dce = (DefaultCellEditor) table.getCellEditor();
            if (dce != null) {
                dce.stopCellEditing();
            }
            int selected = table.getSelectedRow();
            if (table.getSelectedRowCount() > 0) {

                ((DefaultTableModel) table.getModel()).removeRow(selected);
                col_size();
                if (selected > 1) {
                    table.setRowSelectionInterval(Math.max(0, (selected - 1)), (Math.max(0, (selected - 1))));

                }
            }
        });
        field.addActionListener((ActionEvent event) -> {
            Total();
            DefaultCellEditor dce = (DefaultCellEditor) table.getCellEditor();
            if (dce != null) {
                Total();
                dce.stopCellEditing();
            }

        });
        ComboBox.addActionListener((ActionEvent event) -> {

        });

        limit.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {

                //    JOptionPane.showMessageDialog(null, "قمت بإختيار !" + limit.getSelectedItem().toString(), "ليس لديك مشتريات", JOptionPane.OK_OPTION);
                String s_limit = limit.getSelectedItem().toString();

                int row = table.getSelectedRow();
                row = table.convertRowIndexToModel(row);

                int column = table.getSelectedColumn();
                column = table.convertRowIndexToModel(column);
                tablec = (table.getModel().getValueAt(row, column).toString());

                //String s_limit = limit.getSelectedItem().toString();
                if (s_limit.equals("الحد الأعلي")) {

                    try {

                        if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                            conn = DB.jave_db();
                        }
                        //select p.*,b.quantity from producer p LEFT JOIN buying_sub b ON p.producer_id = b.producer_id where p.producer_id=?
                        String sql = "select * from producer  where producer_id=?";
                        pst = conn.prepareStatement(sql);
                        String tablec1 = table.getModel().getValueAt(row, 1).toString();
                        pst.setString(1, tablec1);
                        rs = pst.executeQuery();
                        if (rs.next()) {

                            String max = rs.getString("max");
                            String max_buy = rs.getString("max_buy");
                            String max_sale = rs.getString("max_sale");
                            String max_number = rs.getString("max_number");

                            table.setValueAt(max, row, 5);
                            table.setValueAt(max_buy, row, 6);
                            table.setValueAt(max_sale, row, 7);
                            table.setValueAt(max_number, row, 8);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Add_type.class.getName()).log(Level.SEVERE, null, ex);

                    } finally {
                        try {
                            rs.close();
                            pst.close();
                            conn.close();

                        } catch (Exception e) {

                        }
                    }

                } else if (s_limit.equals("الحد الأدني")) {

                    try {

                        if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                            conn = DB.jave_db();
                        }
                        //select p.*,b.quantity from producer p LEFT JOIN buying_sub b ON p.producer_id = b.producer_id where p.producer_id=?
                        String sql = "select * from producer  where producer_id=?";
                        pst = conn.prepareStatement(sql);
                        String tablec1 = table.getModel().getValueAt(row, 1).toString();
                        pst.setString(1, tablec1);
                        rs = pst.executeQuery();
                        if (rs.next()) {

                            String min = rs.getString("min");
                            String min_buy = rs.getString("min_buy");
                            String min_sale = rs.getString("min_sale");
                            String min_number = rs.getString("min_number");

                            table.setValueAt(min, row, 5);
                            table.setValueAt(min_buy, row, 6);
                            table.setValueAt(min_sale, row, 7);
                            table.setValueAt(min_number, row, 8);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Add_type.class.getName()).log(Level.SEVERE, null, ex);

                    } finally {
                        try {
                            rs.close();
                            pst.close();
                            conn.close();

                        } catch (Exception e) {

                        }
                    }

                }

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent pme) {
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                int colum = table.getSelectedColumn();
                if (row == 0 && colum == 0) {
                    //    JOptionPane.showMessageDialog(null, "قم بإدخال رقم الباتش !", "حقل فارغ", JOptionPane.OK_OPTION);

                }

            }
        });

    }

    public static String Get_Random_Number() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000000);
        String formatted = String.format("%05d", num);
        return formatted;
    }

    public static void Total() {

        double total2 = 0;
        double total3 = 0;
        double total4 = 0;
        double total5 = 0;
        double z = 0;
        double Amount = 0;
        double total = 0;
        double discount_value = 0;

        DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        decimalFormat.setMaximumFractionDigits(340);
        for (int i = 0; i < table.getRowCount(); i++) {
            Amount = Double.parseDouble(table.getValueAt(i, 6) + "");
            total = Double.parseDouble(table.getValueAt(i, 9) + "");

            z = Amount * total;
            if (table.getValueAt(i, 11).equals("نسبة")) {
                discount_value = Double.parseDouble(table.getValueAt(i, 12) + "");
                if (discount_value > 100 || discount_value < 0) {

                    JOptionPane.showMessageDialog(null, "قيمة الخصم لا بدا ان تكون بين 0 و 100 !", "خطأ في قيمة الخصم", JOptionPane.OK_OPTION);
                    table.setValueAt("0", i, 12);
                    table.setCellSelectionEnabled(true);

                    table.editCellAt(i, 12);
                    table.getEditorComponent().requestFocus();

                    return;

                }
                total3 = (z * discount_value) / 100;
                total3 = z - total3;
                table.setValueAt(total3, i, 13);
                total2 = total2 + z;

                // total3 = total2 * Double.parseDouble(discount_value) / 100;
            } else if (table.getValueAt(i, 11).equals("نقدي")) {
                discount_value = Double.parseDouble(table.getValueAt(i, 12) + "");
                if (discount_value > z || discount_value < 0) {

                    JOptionPane.showMessageDialog(null, "قيمة الخصم لا بدا ان تكون بين " + "0" + " و " + z + " !", "خطأ في قيمة الخصم", JOptionPane.OK_OPTION);
                    table.setValueAt("0", i, 12);
                    table.setCellSelectionEnabled(true);

                    table.editCellAt(i, 12);
                    table.getEditorComponent().requestFocus();

                    return;

                }

                total3 = z - discount_value;
                table.setValueAt(total3, i, 13);
                total2 = total2 + z;
            }
            total4 = Double.parseDouble(table.getValueAt(i, 13) + "");

            total5 = total5 + total4;
        }
        //   if(table.getModel() != null   ){
        //        int row = table.getSelectedRow();
        //   row = table.convertRowIndexToModel(row);

        //     int column = table.getSelectedColumn();
        //     column = table.convertRowIndexToModel(column);
        //     tablec = (table.getModel().getValueAt(row, column).toString());
        //     String discount_type = table.getModel().getValueAt(row, 11).toString();
        //   String discount_value = table.getModel().getValueAt(row, 12).toString();
        //  }
        tex_total.setText(decimalFormat.format(total5));

    }

    public static String Get_Date() {
        Date date1 = GregorianCalendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestring = df.format(date1);
        return datestring;
    }

    private void Update_resource() {

        try {

            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from resource";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            co_resource.removeAllItems();
            while (rs.next()) {
                String name = rs.getString("resource_name");
                co_resource.addItem(name);
                //setTableAlignmentValue(tabl_stu, i);

            }

        } catch (SQLException e) {

        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();

            } catch (SQLException e) {

            }

        }
    }

    public static JComboBox Update_Type() {
        type_name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        ((JLabel) type_name.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);

        type_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        type_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        type_name.setPreferredSize(new java.awt.Dimension(6, 28));

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from add_type";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            type_name.removeAllItems();
            while (rs.next()) {
                String name = rs.getString("name");
                type_name.addItem(name);
                //setTableAlignmentValue(tabl_stu, i);

            }
        } catch (SQLException e) {

        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();

            } catch (SQLException e) {

            }

        }
        return type_name;
    }

    public static JComboBox Update_Discount() {
        discount.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        ((JLabel) discount.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        discount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        discount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        discount.setPreferredSize(new java.awt.Dimension(6, 28));
        discount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"نسبة", "نقدي"}));
        return discount;
    }

    public static JComboBox Update_limit() {
        limit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        ((JLabel) limit.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        limit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        limit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        limit.setPreferredSize(new java.awt.Dimension(6, 28));
        limit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"الحد الأعلي", "الحد الأدني"}));
        return limit;
    }

    public static void Update_table() {
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jScrollPane4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        String[] tableColumnsName = {"الباركود", "رقم المنتج", "إسم المنتج", "نوع الصنف",
            "الحد", "الوحده", "سعر الشراء", "سعر البيع", "عدد الوحدات",
            "الكمية", "كمية الزيادة", "نوع الخصم", "كمية الخصم", "الجملة", "تاريخ الصلاحية", "حذف"};
        aModel = (DefaultTableModel) table.getModel();
        aModel.setColumnIdentifiers(tableColumnsName);
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment((int) JLabel.CENTER);
        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
        table.getColumnModel().getColumn(4).setCellRenderer(renderer);
        table.getColumnModel().getColumn(9).setCellRenderer(renderer);
        table.getColumnModel().getColumn(10).setCellRenderer(renderer);
        table.getColumnModel().getColumn(11).setCellRenderer(renderer);
        table.getColumnModel().getColumn(12).setCellRenderer(renderer);
        table.getColumnModel().getColumn(13).setCellRenderer(renderer);
        table.getColumnModel().getColumn(14).setCellRenderer(renderer);
        table.getColumnModel().getColumn(15).setCellRenderer(renderer);

        table.setRowHeight(30);
        table.setSize(50, 50);
        table.setEditingRow(0);
        header = table.getTableHeader();
        jScrollPane4.getColumnHeader().setPreferredSize(new Dimension(30, 30));
        jScrollPane4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //  header.setPreferredSize(new Dimension(30, 30));
        header.setBackground(new java.awt.Color(75, 137, 220));
        header.setForeground(new java.awt.Color(240, 240, 240));
        header.setFont(new java.awt.Font("Times New Roman", 1, 14));
        header.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));

        // tanks to umit ozkan for the bug fix!
        //  aModel.addRow(new Object[]{"", "1", "شاش", "مستهلك", "كرتونة", "1.5", "50", "", ""});
        col_size();

        table.setModel(aModel);

    }

    public static class Mult_Table_Header extends JTextArea implements TableCellRenderer {

        public Mult_Table_Header() {

            setEditable(false);
            setLineWrap(true);
            setOpaque(false);
            setFocusable(false);
            setWrapStyleWord(true);
            setBackground(new java.awt.Color(75, 137, 220));
            setForeground(new java.awt.Color(240, 240, 240));
            setAlignmentY(JLabel.CENTER_ALIGNMENT);
            setAlignmentX(JLabel.CENTER_ALIGNMENT);
            setFont(new java.awt.Font("Times New Roman", 1, 14));
            setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));

            LookAndFeel.installBorder(this, "TableHeader.cellBorder");

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            int width = table.getColumnModel().getColumn(column).getWidth();
            setText((String) value);
            setSize(width, getPreferredSize().height);
            return this;
        }

    }

    public static class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {

            setOpaque(true);

        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            button.setFont(new java.awt.Font("Times New Roman", 1, 0)); // NOI18N
            button.setForeground(new java.awt.Color(231, 77, 60));
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Delete_24px_5.png"))); // NOI18N
            button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
            button.setContentAreaFilled(false);
            button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            button.setOpaque(true);
            button.setPreferredSize(new java.awt.Dimension(59, 34));
            button.setText("حذف");

            return button;

        }

    }

    public static class ButtonEditor extends DefaultCellEditor {

        public ButtonEditor(JCheckBox checkBox) {

            super(checkBox);

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {

            button.setFont(new java.awt.Font("Times New Roman", 1, 0)); // NOI18N
            button.setForeground(new java.awt.Color(231, 77, 60));
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Delete_24px_5.png"))); // NOI18N
            button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
            button.setContentAreaFilled(false);
            button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            button.setOpaque(true);
            button.setPreferredSize(new java.awt.Dimension(59, 34));
            button.setText("حذف");

            return button;

        }

    }

    public static class PositiveIntegerCellEditor extends DefaultCellEditor {

        public static JTextField textField;

        public PositiveIntegerCellEditor(JTextField textField) {
            super(textField);
            textField.setBackground(new java.awt.Color(240, 240, 240));
            textField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
            tex_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            textField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

            PositiveIntegerCellEditor.textField = textField;
            PositiveIntegerCellEditor.textField.setHorizontalAlignment(JTextField.RIGHT);
        }

        @Override
        public boolean stopCellEditing() {
            try {
                BigDecimal v = new BigDecimal(textField.getText());
                if (v.compareTo(v) < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                textField.setBorder(red);
                return false;
            }
            return super.stopCellEditing();

        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            col_size();
            Total();

            return super.isCellEditable(anEvent); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getCellEditorValue() {

            Total();
            return super.getCellEditorValue(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            textField.setBorder(black);
            return super.getTableCellEditorComponent(
                    table, value, isSelected, row, column);
        }
    }

    public static class JDateChooserEditor extends DefaultCellEditor {

        public JDateChooserEditor(JCheckBox checkBox) {
            super(checkBox);

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                Object xx = table.getValueAt(row, column);
                if (xx == "") {
                    date = new JDateChooser();
                    date.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                    date.setDateFormatString("yyyy-MM-dd");
                    return date;
                } else {
                    JDateChooser date1 = new JDateChooser();
                    date1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                    String cc = (String) value;
                    date1.setDateFormatString("yyyy-MM-dd");
                    java.util.Date date2 = null;
                    try {
                        date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cc);
                    } catch (ParseException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    date1.setDate(date2);
                    date = date1;
                    return date;

                }
            } else {
                Object xx = table.getValueAt(row, column);
                if (xx == "") {
                    date = new JDateChooser();
                    date.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                    date.setDateFormatString("yyyy-MM-dd");
                    return date;
                } else {
                    JDateChooser date1 = new JDateChooser();
                    date1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                    String cc = (String) value;
                    date1.setDateFormatString("yyyy-MM-dd");
                    java.util.Date date2 = null;
                    try {

                        date2 = new SimpleDateFormat("yyyy-MM-dd").parse(cc);
                    } catch (ParseException ex) {
                        date.setBorder(red);
                        return null;
                    }
                    date1.setDate(date2);
                    date = date1;
                    return date;

                }
            }

        }

        @Override
        public Object getCellEditorValue() {
            return ((JTextField) date.getDateEditor().getUiComponent()).getText();
        }

        @Override
        public boolean stopCellEditing() {
            try {

                //  Calendar v = (date.getDateFormatCalendar());
                if (date.getDate() == null) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                date.setBorder(red);
                return false;
            }
            try {
                DateFormat day = new SimpleDateFormat("d");
                DateFormat month = new SimpleDateFormat("MM");
                DateFormat year = new SimpleDateFormat("yyyy");
                int day2 = Integer.valueOf(day.format(date.getDate()));
                int month2 = Integer.valueOf(month.format(date.getDate()));
                int year2 = Integer.valueOf(year.format(date.getDate()));
                Calendar datenow = Calendar.getInstance();
                datenow.add(Calendar.DATE, 2);
                if (day2 > 31 || day2 < 1 || month2 > 12 || month2 < 1 || year2 > 3300) {
                    throw new NumberFormatException();
                }
                if (date.getDate().before(datenow.getTime())) {
                    JOptionPane.showMessageDialog(null, "قم بمراجعة تاريخ الصلاحية لا يمكن ان يكون أقل من تاريخ اليوم او أكثر من تاريخ اليوم بـ 2 أيام فقط !", "خطأ في تاريخ الصلاحية", JOptionPane.OK_OPTION);
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                date.setBorder(red);
                return false;
            }
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    public static void col_size() {

        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();
            if (column != 0 && column != 4
                    && column != 9
                    && column != 10
                    && column != 11
                    && column != 12
                    && column != 13
                    && column != 14
                    && column != 15) {
                table.getColumnModel().getColumn(column).setCellRenderer(renderer);

                table.getColumnModel().getColumn(column).setCellEditor(new stop_editable(new JCheckBox()));
            } else {
                table.getColumnModel().getColumn(0).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(4).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(9).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(10).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(11).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(12).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(13).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(14).setCellEditor(new PositiveIntegerCellEditor(field));
                table.getColumnModel().getColumn(15).setCellEditor(new PositiveIntegerCellEditor(field));
            }
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth(preferredWidth + 70);
        }
        // table.getColumn("الوحده").setCellEditor(new DefaultCellEditor(Update_Type()));
        table.getColumn("الحد").setCellEditor(new DefaultCellEditor(Update_limit()));
        table.getColumn("نوع الخصم").setCellEditor(new DefaultCellEditor(Update_Discount()));
        table.getColumn("حذف").setCellRenderer(new ButtonRenderer());
        table.getColumn("حذف").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumn("تاريخ الصلاحية").setCellEditor(new JDateChooserEditor(new JCheckBox()));

    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myChooser = new javax.swing.JFileChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tex_user_buy = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        bt_new = new javax.swing.JButton();
        bt_save = new javax.swing.JButton();
        bt_print = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        tex_date_buy = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tex_total = new javax.swing.JTextField();
        bt_add_resource = new javax.swing.JButton();
        co_resource = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tex_check_number = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        bt_add_producer = new javax.swing.JButton();
        bt_chose = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        bt_exit3 = new javax.swing.JButton();
        bt_minimize2 = new javax.swing.JButton();
        bt_secure3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

        myChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("إضافة عملية شراء");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(93, 156, 236));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel3FocusLost(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });
        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentShown(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("إسم المندوب");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("إسم المستخدم");

        tex_user_buy.setBackground(new java.awt.Color(240, 240, 240));
        tex_user_buy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tex_user_buy.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_user_buy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_user_buy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_user_buy.setEnabled(false);
        tex_user_buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_user_buyActionPerformed(evt);
            }
        });
        tex_user_buy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tex_user_buyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_user_buyKeyTyped(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(93, 156, 236));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "العمليات المتاحة", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(240, 240, 240))); // NOI18N

        bt_new.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_new.setForeground(new java.awt.Color(75, 137, 220));
        bt_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Add_New_24px_1.png"))); // NOI18N
        bt_new.setText("عملية شراء جديدة");
        bt_new.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_new.setContentAreaFilled(false);
        bt_new.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_new.setOpaque(true);
        bt_new.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_new.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_newMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_newMouseExited(evt);
            }
        });
        bt_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_newActionPerformed(evt);
            }
        });

        bt_save.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_save.setForeground(new java.awt.Color(75, 137, 220));
        bt_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Save_as_24px.png"))); // NOI18N
        bt_save.setText("حفظ عملية الشراء");
        bt_save.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_save.setContentAreaFilled(false);
        bt_save.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_save.setOpaque(true);
        bt_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_saveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_saveMouseExited(evt);
            }
        });
        bt_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_saveActionPerformed(evt);
            }
        });

        bt_print.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_print.setForeground(new java.awt.Color(75, 137, 220));
        bt_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Print_24px.png"))); // NOI18N
        bt_print.setText("طباعة");
        bt_print.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_print.setContentAreaFilled(false);
        bt_print.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_print.setOpaque(true);
        bt_print.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_printMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_printMouseExited(evt);
            }
        });
        bt_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(bt_print, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_save, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bt_new, bt_save});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_save, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_print, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75, 137, 220)));

        table.setBackground(new java.awt.Color(240, 240, 240));
        table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        table.setForeground(new java.awt.Color(75, 137, 220));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.setGridColor(new java.awt.Color(75, 137, 220));
        table.setSelectionBackground(new java.awt.Color(75, 137, 220));
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tableMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(table);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("تاريخ الشراء");

        tex_date_buy.setBackground(new java.awt.Color(240, 240, 240));
        tex_date_buy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tex_date_buy.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_date_buy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_date_buy.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_date_buy.setEnabled(false);
        tex_date_buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_date_buyActionPerformed(evt);
            }
        });
        tex_date_buy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_date_buyKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("إجمالي الفاتورة");

        tex_total.setBackground(new java.awt.Color(240, 240, 240));
        tex_total.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_total.setText("0");
        tex_total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_total.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_total.setEnabled(false);
        tex_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_totalActionPerformed(evt);
            }
        });
        tex_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tex_totalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_totalKeyTyped(evt);
            }
        });

        bt_add_resource.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_add_resource.setForeground(new java.awt.Color(46, 204, 113));
        bt_add_resource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Plus_24px.png"))); // NOI18N
        bt_add_resource.setBorder(null);
        bt_add_resource.setContentAreaFilled(false);
        bt_add_resource.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_add_resource.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_add_resource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_resourceActionPerformed(evt);
            }
        });

        co_resource.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        co_resource.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "إختيار" }));
        co_resource.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        co_resource.setPreferredSize(new java.awt.Dimension(6, 28));
        co_resource.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                co_resourceMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("رقم الفاتورة");

        tex_check_number.setBackground(new java.awt.Color(240, 240, 240));
        tex_check_number.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tex_check_number.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_check_number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_check_number.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_check_number.setEnabled(false);
        tex_check_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_check_numberActionPerformed(evt);
            }
        });
        tex_check_number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tex_check_numberKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_check_numberKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Reset_24px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tex_check_number, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(tex_check_number, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bt_add_producer.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_add_producer.setForeground(new java.awt.Color(75, 137, 220));
        bt_add_producer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Positive_24px.png"))); // NOI18N
        bt_add_producer.setText("إضافة منتج");
        bt_add_producer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_add_producer.setContentAreaFilled(false);
        bt_add_producer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_add_producer.setOpaque(true);
        bt_add_producer.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_add_producer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_add_producerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_add_producerMouseExited(evt);
            }
        });
        bt_add_producer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_producerActionPerformed(evt);
            }
        });

        bt_chose.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_chose.setForeground(new java.awt.Color(75, 137, 220));
        bt_chose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Multiple_Choice_24px_3.png"))); // NOI18N
        bt_chose.setText("إختيار منتج");
        bt_chose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_chose.setContentAreaFilled(false);
        bt_chose.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_chose.setOpaque(true);
        bt_chose.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_chose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_choseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_choseMouseExited(evt);
            }
        });
        bt_chose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_choseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(bt_add_resource, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(co_resource, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tex_user_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tex_date_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tex_total, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addGap(31, 31, 31)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(bt_add_producer, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_chose, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {co_resource, tex_user_buy});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, tex_date_buy});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel12, jLabel14});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bt_add_producer, bt_chose});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10)
                    .addComponent(tex_user_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(tex_date_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(co_resource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add_resource, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_add_producer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_chose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tex_total, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11});

        jPanel11.setBackground(new java.awt.Color(75, 137, 220));
        jPanel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        jPanel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel11MouseDragged(evt);
            }
        });
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel11MousePressed(evt);
            }
        });

        bt_exit3.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_exit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Cancel_24px.png"))); // NOI18N
        bt_exit3.setBorder(null);
        bt_exit3.setBorderPainted(false);
        bt_exit3.setContentAreaFilled(false);
        bt_exit3.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_exit3.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_exit3.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_exit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exit3ActionPerformed(evt);
            }
        });

        bt_minimize2.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_minimize2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Minus_30px.png"))); // NOI18N
        bt_minimize2.setBorderPainted(false);
        bt_minimize2.setContentAreaFilled(false);
        bt_minimize2.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_minimize2.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_minimize2.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_minimize2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_minimize2ActionPerformed(evt);
            }
        });

        bt_secure3.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_secure3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Secure_28px.png"))); // NOI18N
        bt_secure3.setBorder(null);
        bt_secure3.setBorderPainted(false);
        bt_secure3.setContentAreaFilled(false);
        bt_secure3.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_secure3.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_secure3.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_secure3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_secure3ActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(75, 137, 220));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("إضافة عملية شراء");
        jPanel12.add(jLabel21);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bt_secure3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_minimize2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_exit3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(bt_secure3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bt_minimize2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bt_exit3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tex_user_buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_user_buyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_user_buyActionPerformed

    private void bt_newMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseEntered
        setColor(bt_new);
    }//GEN-LAST:event_bt_newMouseEntered

    private void bt_newMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseExited
        resetColor(bt_new);
    }//GEN-LAST:event_bt_newMouseExited

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed
        tex_date_buy.setText(Get_Date());
        Update_table();
        tex_check_number.setText(Get_Random_Number());
        Total();
    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseEntered
        setColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseEntered

    private void bt_saveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseExited
        resetColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseExited

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed
        int rows = table.getRowCount();
        if (rows <= 0) {
            JOptionPane.showMessageDialog(null, "قم بإختيار المشتريات !", "ليس لديك مشتريات", JOptionPane.OK_OPTION);
            return;
        }

        for (int row = 0; row < rows; row++) {

            try {
                if (table.getValueAt(row, 0).toString().equals("") || table.getValueAt(row, 0).toString().matches("")) {
                    JOptionPane.showMessageDialog(null, "قم بإدخال رقم الباركود !", "حقل فارغ", JOptionPane.OK_OPTION);
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 0, false, false);
                    table.editCellAt(row, 0);
                    table.getEditorComponent().requestFocus();
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 0, false, false);
                    table.editCellAt(row, 0);
                    table.getEditorComponent().requestFocus();
                    return;
                }
                if (Integer.valueOf(table.getValueAt(row, 9).toString()) <= 0 || table.getValueAt(row, 9).toString().equals("") || table.getValueAt(row, 9).toString().matches("")) {
                    JOptionPane.showMessageDialog(null, "قم بإدخال الكمية !", "حقل فارغ", JOptionPane.OK_OPTION);
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 9, false, false);
                    table.editCellAt(row, 9);
                    table.getEditorComponent().requestFocus();
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 9, false, false);
                    table.editCellAt(row, 9);
                    table.getEditorComponent().requestFocus();

                    return;
                }

                if (table.getValueAt(row, 14).toString().equals("") || table.getValueAt(row, 14).toString().matches("")) {
                    JOptionPane.showMessageDialog(null, "قم بإدخال تاريخ الصلاحية !", "حقل فارغ", JOptionPane.OK_OPTION);
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 14, false, false);
                    table.editCellAt(row, 14);
                    table.getEditorComponent().requestFocus();
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 14, false, false);
                    table.editCellAt(row, 14);
                    table.getEditorComponent().requestFocus();

                    return;
                }
                Calendar datenow = Calendar.getInstance();
                datenow.add(Calendar.DATE, 2);

                String d = table.getValueAt(row, 14).toString();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                Date d1 = format.parse(d);
                if (d1.before(datenow.getTime())) {
                    JOptionPane.showMessageDialog(null, "قم بمراجعة تاريخ الصلاحية لا يمكن ان يكون أقل من تاريخ اليوم او أكثر من تاريخ اليوم بـ 2 أيام فقط !", "خطأ في تاريخ الصلاحية", JOptionPane.OK_OPTION);
                    return;
                }
            } catch (ParseException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql22 = " select * from buying where check_number= '" + tex_check_number.getText() + "'";
            pst = conn.prepareStatement(sql22);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "رقم الفاتورة مسجل مسبقاً !", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                return;
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();

            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        if ((tex_user_buy.getText().matches("")) || (tex_user_buy.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "إسم المستخدم فارغ !");
            return;
        } else {

            int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ عملية الشراء ", "حفظ بيانات", JOptionPane.YES_NO_OPTION);
            if (Ahmed == 0) {

                try {
                    if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                        conn = DB.jave_db();
                    }
                    Total();
                    String date11 = Get_Date();
                    DBConn db = new DBConn();
                    //   String[] values = null;

                    int res = 0;
                    for (int row = 0; row < rows; row++) {
                        int x = Integer.valueOf(table.getValueAt(row, 8).toString());
                        int x1 = Integer.valueOf(table.getValueAt(row, 10).toString());
                        int x2 = Integer.valueOf(table.getValueAt(row, 9).toString());

                        int quantity_total = (x2 * x) + x1;
                        //  quantity_total = x1 + x2;

                        res = db.add("buying", new String[]{null,
                            tex_check_number.getText(), tex_user_buy.getText(),
                            tex_date_buy.getText(),
                            co_resource.getSelectedItem().toString(),
                            table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(), table.getValueAt(row, 2).toString(),
                            table.getValueAt(row, 3).toString(), table.getValueAt(row, 4).toString(), table.getValueAt(row, 5).toString(),
                            table.getValueAt(row, 6).toString(), table.getValueAt(row, 7).toString(), table.getValueAt(row, 8).toString(),
                            table.getValueAt(row, 9).toString(), table.getValueAt(row, 10).toString(), quantity_total + "", table.getValueAt(row, 11).toString(),
                            table.getValueAt(row, 12).toString(), table.getValueAt(row, 13).toString(), tex_total.getText(), table.getValueAt(row, 14).toString(),
                            null, null, "0", "Ahmed"
                        }, null);
                        res = db.add("buying_sub", new String[]{null,
                            tex_check_number.getText(), tex_user_buy.getText(),
                            tex_date_buy.getText(),
                            co_resource.getSelectedItem().toString(),
                            table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(), table.getValueAt(row, 2).toString(),
                            table.getValueAt(row, 3).toString(), table.getValueAt(row, 4).toString(), table.getValueAt(row, 5).toString(),
                            table.getValueAt(row, 6).toString(), table.getValueAt(row, 7).toString(), table.getValueAt(row, 8).toString(),
                            table.getValueAt(row, 9).toString(), table.getValueAt(row, 10).toString(), quantity_total + "", table.getValueAt(row, 11).toString(),
                            table.getValueAt(row, 12).toString(), table.getValueAt(row, 13).toString(), tex_total.getText(), table.getValueAt(row, 14).toString(),
                            null, null, "0", "Ahmed"
                        }, null);
                    }

                    if (res == -1) {
                        bt_printActionPerformed(evt);
                        JOptionPane.showMessageDialog(null, "تم حفظ عملية الشراء بنجاح");
                        table.removeAll();
                    }

                    /* check_number,producer_qr,producer_id,producer_name,
type_name,the_limit,unit,sale,buy,number,quantity,
quantity_plus,discount_type,discount_value,total,
complete_total,expiry_date,user_save,date_save,beg_date,end_date,status,usernam */
                    //  bt_printActionPerformed(evt);
                    //     JOptionPane.showMessageDialog(null, "تم حفظ عملية الشراء بنجاح");
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);

                } finally {
                    try {
                        rs.close();
                        pst.close();
                        conn.close();

                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }

            }

        }

    }//GEN-LAST:event_bt_saveActionPerformed

    private void bt_printMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_printMouseEntered
        setColor(bt_print);
    }//GEN-LAST:event_bt_printMouseEntered

    private void bt_printMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_printMouseExited
        resetColor(bt_print);
    }//GEN-LAST:event_bt_printMouseExited

    private void bt_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_printActionPerformed
        int rows = table.getRowCount();
        if (rows <= 0) {
            JOptionPane.showMessageDialog(null, "قم بإختيار المشتريات !", "ليس لديك مشتريات", JOptionPane.OK_OPTION);
            return;
        }

        for (int row = 0; row < rows; row++) {

            try {
                if (table.getValueAt(row, 0).toString().equals("") || table.getValueAt(row, 0).toString().matches("")) {
                    JOptionPane.showMessageDialog(null, "قم بإدخال رقم الباركود !", "حقل فارغ", JOptionPane.OK_OPTION);
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 0, false, false);
                    table.editCellAt(row, 0);
                    table.getEditorComponent().requestFocus();
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 0, false, false);
                    table.editCellAt(row, 0);
                    table.getEditorComponent().requestFocus();
                    return;
                }
                if (Integer.valueOf(table.getValueAt(row, 9).toString()) <= 0 || table.getValueAt(row, 9).toString().equals("") || table.getValueAt(row, 9).toString().matches("")) {
                    JOptionPane.showMessageDialog(null, "قم بإدخال الكمية !", "حقل فارغ", JOptionPane.OK_OPTION);
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 9, false, false);
                    table.editCellAt(row, 9);
                    table.getEditorComponent().requestFocus();
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 9, false, false);
                    table.editCellAt(row, 9);
                    table.getEditorComponent().requestFocus();

                    return;
                }

                if (table.getValueAt(row, 14).toString().equals("") || table.getValueAt(row, 14).toString().matches("")) {
                    JOptionPane.showMessageDialog(null, "قم بإدخال تاريخ الصلاحية !", "حقل فارغ", JOptionPane.OK_OPTION);
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 14, false, false);
                    table.editCellAt(row, 14);
                    table.getEditorComponent().requestFocus();
                    table.setCellSelectionEnabled(true);
                    table.changeSelection(row, 14, false, false);
                    table.editCellAt(row, 14);
                    table.getEditorComponent().requestFocus();

                    return;
                }
                Calendar datenow = Calendar.getInstance();
                datenow.add(Calendar.DATE, 2);

                String d = table.getValueAt(row, 14).toString();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                Date d1 = format.parse(d);
                if (d1.before(datenow.getTime())) {
                    JOptionPane.showMessageDialog(null, "قم بمراجعة تاريخ الصلاحية لا يمكن ان يكون أقل من تاريخ اليوم او أكثر من تاريخ اليوم بـ 2 أيام فقط !", "خطأ في تاريخ الصلاحية", JOptionPane.OK_OPTION);
                    return;
                }
            } catch (ParseException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql22 = " select * from buying where check_number= '" + tex_check_number.getText() + "'";
            pst = conn.prepareStatement(sql22);
            rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "قم بحفظ عملية الشراء أولاَ !", "لا يمكن الطباعة", JOptionPane.OK_OPTION);
                return;
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();

            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        if ((tex_user_buy.getText().matches("")) || (tex_user_buy.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "إسم المستخدم فارغ !");
            return;
        }

        PdfWriter pdfWriter = null;
        try {

            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql22 = " select * from tb_store_info where id = 1";
            pst = conn.prepareStatement(sql22);
            rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "قم بإدخال بيانات المحل لطباعة الفاتورة !", "لا يمكن الطباعة", JOptionPane.OK_OPTION);
                return;
            }
            String store_name = rs.getString("store_name");
            String store_phone = rs.getString("store_phone");
            String store_address = rs.getString("store_address");
            String store_v_g = rs.getString("store_v_g");
            String store_note = rs.getString("store_note");
            String store_logo = rs.getString("store_logo");

            Date datenow = GregorianCalendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String datestring = df.format(datenow);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
            String timestring = sdf.format(d);
            String value0 = timestring;
            String values = datestring;
            File Dir = new File(System.getProperty("user.home"), "Desktop\\تقارير '" + store_name + "'");
            Dir.mkdir();

            File Dir1 = new File(System.getProperty("user.dir"), "\\تقارير '" + store_name + "'");
            Dir1.mkdir();
//new Rectangle(581.1f, 396.85f));
            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A3.rotate());
            document.setMargins(10, 10, 10, 10);
            patch = "فاتورة مشتريات" + " " + store_name + " " + values + " " + value0 + ".pdf";

            // PdfWriter.getInstance(document, new FileOutputStream(patch));
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(Dir1 + "\\" + patch));
            PdfWriter pdfWriter1 = PdfWriter.getInstance(document, new FileOutputStream(Dir + "\\" + patch));

            document.open();
            //************************************************************************************************************************************
            String check_number = tex_check_number.getText();
            String dat_buy = tex_date_buy.getText();
            String user_buy = tex_user_buy.getText();
            String total = tex_total.getText();
            String resource = co_resource.getSelectedItem().toString();

            DefaultListModel listModel1 = new DefaultListModel();
            DefaultListModel listModel2 = new DefaultListModel();
            DefaultListModel listModel3 = new DefaultListModel();
            DefaultListModel listModel4 = new DefaultListModel();
            DefaultListModel listModel5 = new DefaultListModel();
            DefaultListModel listModel6 = new DefaultListModel();
            DefaultListModel listModel7 = new DefaultListModel();
            DefaultListModel listModel8 = new DefaultListModel();
            DefaultListModel listModel9 = new DefaultListModel();
            DefaultListModel listModel10 = new DefaultListModel();
            DefaultListModel listModel11 = new DefaultListModel();

            for (int row = 0; row < rows; row++) {
                listModel1.addElement(table.getValueAt(row, 1));
                listModel2.addElement(table.getValueAt(row, 2));
                listModel3.addElement(table.getValueAt(row, 3));
                listModel4.addElement(table.getValueAt(row, 5));
                listModel5.addElement(table.getValueAt(row, 6));
                listModel6.addElement(table.getValueAt(row, 9));
                listModel7.addElement(table.getValueAt(row, 10));
                listModel8.addElement(table.getValueAt(row, 11));
                listModel9.addElement(table.getValueAt(row, 12));
                listModel10.addElement(table.getValueAt(row, 13));
                listModel11.addElement(table.getValueAt(row, 14));
            }
            Print_PDF print_PDF = new Print_PDF();
            document.add(print_PDF.sub1());
            document.add(print_PDF.header1(check_number, dat_buy, user_buy, resource));

            document.add(print_PDF.sub2());
            document.add(print_PDF.t1());
            document.add(print_PDF.t2(total, listModel1, listModel2, listModel3, listModel4, listModel5, listModel6, listModel7, listModel8, listModel9, listModel10, listModel11));

            document.close();
            File file = new File(Dir1 + "\\" + patch);
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {

            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
        } catch (DocumentException ex) {
            Exceptions.printStackTrace(ex);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                pdfWriter.close();
                rs.close();
                pst.close();
                conn.close();

            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }


    }//GEN-LAST:event_bt_printActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        Total();

    }//GEN-LAST:event_tableMouseClicked

    private void tex_date_buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_date_buyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_date_buyActionPerformed

    private void tex_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_totalActionPerformed

    private void tex_user_buyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_user_buyKeyReleased

    }//GEN-LAST:event_tex_user_buyKeyReleased

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged


    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

    }//GEN-LAST:event_formWindowGainedFocus

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange

    }//GEN-LAST:event_formPropertyChange

    private void bt_add_resourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_resourceActionPerformed
        new Resource().setVisible(true);
    }//GEN-LAST:event_bt_add_resourceActionPerformed

    private void co_resourceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_co_resourceMouseClicked
        String a = co_resource.getSelectedItem().toString();
        Update_resource();
        int ii = 0;
        for (int i = 0; i < co_resource.getItemCount(); i++) {

            if (co_resource.getItemAt(i).equals(a)) {
                ii = 1;
            }
        }
        if (ii == 1) {
            co_resource.setSelectedItem(a);
        } else {
            co_resource.setSelectedIndex(0);
        }
    }//GEN-LAST:event_co_resourceMouseClicked

    private void tableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseEntered
        //   Total();
    }//GEN-LAST:event_tableMouseEntered

    private void tableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseExited
//        Total();
    }//GEN-LAST:event_tableMouseExited

    private void tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyReleased

        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            Total();
        }
    }//GEN-LAST:event_tableKeyReleased

    private void tex_totalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_totalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_totalKeyReleased

    private void tex_totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_totalKeyTyped
        class_number cc = new class_number();
        cc.None_None(tex_total, evt);
    }//GEN-LAST:event_tex_totalKeyTyped

    private void tex_check_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_check_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_check_numberActionPerformed

    private void tex_check_numberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_check_numberKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_check_numberKeyReleased

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased

    }//GEN-LAST:event_tableMouseReleased

    private void tex_check_numberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_check_numberKeyTyped
        class_number cc = new class_number();
        cc.None_None(tex_total, evt);
    }//GEN-LAST:event_tex_check_numberKeyTyped

    private void tex_date_buyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_date_buyKeyTyped
        class_number cc = new class_number();
        cc.None_None(tex_total, evt);
    }//GEN-LAST:event_tex_date_buyKeyTyped

    private void tex_user_buyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_user_buyKeyTyped
        class_number cc = new class_number();
        cc.None_None(tex_total, evt);
    }//GEN-LAST:event_tex_user_buyKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tex_check_number.setText(Get_Random_Number());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_exit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exit3ActionPerformed

        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
        this.dispose();
    }//GEN-LAST:event_bt_exit3ActionPerformed

    private void bt_minimize2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_minimize2ActionPerformed
       // //this.setState(1);
    }//GEN-LAST:event_bt_minimize2ActionPerformed

    private void bt_secure3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_secure3ActionPerformed
        System.gc();
        java.awt.Window window[] = java.awt.Window.getWindows();
        for (int i = 0; i < window.length; i++) {
            window[i].setVisible(false);
            window[i] = null;
        }
        new login_S().setVisible(true);
    }//GEN-LAST:event_bt_secure3ActionPerformed

    private void jPanel11MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel11MouseDragged

    private void jPanel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel11MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged

    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered

    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed

    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusLost

    }//GEN-LAST:event_jPanel3FocusLost

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained

    }//GEN-LAST:event_jPanel3FocusGained

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown

    }//GEN-LAST:event_jPanel3ComponentShown

    private void bt_add_producerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_producerMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_producerMouseEntered

    private void bt_add_producerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_producerMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_producerMouseExited

    private void bt_add_producerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_producerActionPerformed
         new Producer().setVisible(true);
    }//GEN-LAST:event_bt_add_producerActionPerformed

    private void bt_choseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_choseMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_choseMouseEntered

    private void bt_choseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_choseMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_choseMouseExited

    private void bt_choseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_choseActionPerformed
         new Select_Producer().setVisible(true);
    }//GEN-LAST:event_bt_choseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buying.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buying().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_producer;
    private javax.swing.JButton bt_add_resource;
    private javax.swing.JButton bt_chose;
    private javax.swing.JButton bt_exit3;
    private javax.swing.JButton bt_minimize2;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_print;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_secure3;
    public static javax.swing.JComboBox<String> co_resource;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    public static javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JFileChooser myChooser;
    public static javax.swing.JTable table;
    private javax.swing.JTextField tex_check_number;
    private javax.swing.JTextField tex_date_buy;
    public static javax.swing.JTextField tex_total;
    private javax.swing.JTextField tex_user_buy;
    // End of variables declaration//GEN-END:variables
}
