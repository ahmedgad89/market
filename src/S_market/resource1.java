/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author A7meD3H
 */
public class resource1 extends javax.swing.JDialog {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public String AA;
    public String A;
    public String A1;
    public String AA2;
    public String A2;
    public String AA3;
    public String A3;
    public String A4;
    public String AA4;
    public String A5;
    public String AA5;
    public String tablec;

    /**
     * Creates new form ADU
     */
    public resource1() {
        initComponents();   setModal(true);

        txt_name.requestFocus();
        txt_name.grabFocus();
        txt_name.setEditable(true);
        txt_name.isEditable();
        txt_name.setFocusable(true);
        txt_name.isFocusable();
        txt_name.setEditable(true);

        JWindow window = new JWindow(new JFrame() {
            @Override
            public boolean isShowing() {
                return true;
            }
        });
        //  JFrame.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/back.png")));
        conn = DB.jave_db();

        Update_table1();

        bt_delete_classof.setEnabled(false);
        bt_update_classof.setEnabled(false);

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();

        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        table1.setShowGrid(true);

    }

    private void Update_table1() {
        table1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        try {
            String sql = "select id,name,phone,co_name,co_phone from resource";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));

            String[] tableColumnsName = {"*", "إسم المورد", "هاتف المورد", "إسم الشركة","هاتف الشركة"};

            DefaultTableModel aModel = (DefaultTableModel) table1.getModel();
            aModel.setColumnIdentifiers(tableColumnsName);

            // FontUIResource f = new FontUIResource("Times New Roman", 0, 18);
// Loop through the ResultSet and transfer in the Model
            ((DefaultTableCellRenderer) table1.getTableHeader().getDefaultRenderer())
                    .setHorizontalAlignment((int) JLabel.CENTER);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);

            table1.getColumnModel().getColumn(0).setCellRenderer(renderer);

            for (int i = 0; i < table1.getColumnCount(); i++) {

                table1.getColumnModel().getColumn(i).setCellRenderer(renderer);
                table1.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));
                table1.getColumnModel().getColumn(i).setWidth(500);
                DefaultTableColumnModel colModel = (DefaultTableColumnModel) table1.getColumnModel();

                TableColumn col = colModel.getColumn(i);
                TableCellRenderer hRenderer = col.getHeaderRenderer();

                int width = 0;
                TableCellRenderer renderer1 = col.getHeaderRenderer();

                JTableHeader header = (JTableHeader) col.getHeaderRenderer();
                for (int r = 0; r < table1.getRowCount(); r++) {
                    renderer = (DefaultTableCellRenderer) table1.getCellRenderer(r, i);
                    Component comp = renderer.getTableCellRendererComponent(table1, table1.getValueAt(r, i),
                            false, false, r, i);
                    width = Math.max(width, comp.getPreferredSize().width);
                }

                col.setPreferredWidth(width + 2);

                //setTableAlignmentValue(table1, i);
            }

            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            table1.setRowHeight(30);

            table1.setSize(50, 50);
            table1.setEditingRow(0);
            JTableHeader header = table1.getTableHeader();
            header.setPreferredSize(new Dimension(30, 30));
            header.setBackground(new java.awt.Color(33, 150, 243));
            header.setForeground(new java.awt.Color(255, 255, 255));
            header.setFont(new java.awt.Font("Times New Roman", 1, 18));

            int colNo = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] objects = new Object[colNo];
                // tanks to umit ozkan for the bug fix!
                for (int i = 0; i < colNo; i++) {
                    objects[i] = rs.getObject(i + 1);
                    table1.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    table1.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));

                    //setTableAlignmentValue(table1, i);
                }

                aModel.addRow(objects);

            }

            table1.setModel(aModel);

        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                pst.close();

            } catch (Exception e) {

            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - unknown
    private void initComponents() {
        jPanel3 = new JPanel();
        jLabel11 = new JLabel();
        txt_name = new JTextField();
        jLabel8 = new JLabel();
        txt_phone = new JTextField();
        jScrollPane4 = new JScrollPane();
        table1 = new JTable();
        txt_classof_se = new JTextField();
        jButton3 = new JButton();
        jLabel5 = new JLabel();
        jPanel2 = new JPanel();
        bt_update_classof = new JButton();
        bt_delete_classof = new JButton();
        bt_save_class = new JButton();
        bt_new_classof = new JButton();
        jButton1 = new JButton();
        jSeparator1 = new JSeparator();
        jButton2 = new JButton();
        jButton4 = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel4 = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jPanel1 = new JPanel();
        txt_co_name = new JTextField();
        jLabel12 = new JLabel();
        txt_co_phone = new JTextField();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        txt_co_addr = new JTextField();
        jScrollPane1 = new JScrollPane();
        jTextPane1 = new JTextPane();
        jLabel15 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u064a\u0646");
        setCursor(null);
        setResizable(false);
        Container contentPane = getContentPane();

        //======== jPanel3 ========
        {

            // JFormDesigner evaluation mark
            jPanel3.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), jPanel3.getBorder())); jPanel3.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


            //---- jLabel11 ----
            jLabel11.setText("\u0627\u0644\u0625\u0633\u0645 \u0627\u0644\u0645\u0648\u0631\u062f");

            //---- txt_name ----
            txt_name.setHorizontalAlignment(JTextField.RIGHT);
            txt_name.setBorder(new LineBorder(new Color(33, 150, 243), 3));
            txt_name.addActionListener(e -> txt_nameActionPerformed(e));

            //---- jLabel8 ----
            jLabel8.setText("\u0631\u0642\u0645 \u0627\u0644\u062a\u0644\u0641\u0648\u0646");

            //---- txt_phone ----
            txt_phone.setHorizontalAlignment(JTextField.RIGHT);
            txt_phone.setBorder(new LineBorder(new Color(33, 150, 243), 3));

            //======== jScrollPane4 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel());
                table1.setGridColor(Color.white);
                table1.setSelectionBackground(new Color(33, 150, 243));
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        table1MouseClicked(e);
                    }
                });
                jScrollPane4.setViewportView(table1);
            }

            //---- txt_classof_se ----
            txt_classof_se.setHorizontalAlignment(JTextField.RIGHT);
            txt_classof_se.setBorder(new LineBorder(new Color(33, 150, 243), 3));
            txt_classof_se.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txt_classof_seKeyReleased(e);
                }
            });

            //---- jButton3 ----
            jButton3.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Go_Back_30px.png")));
            jButton3.setContentAreaFilled(false);
            jButton3.addActionListener(e -> jButton3ActionPerformed(e));

            //---- jLabel5 ----
            jLabel5.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Search_24px.png")));
            jLabel5.setFocusable(false);
            jLabel5.setHorizontalTextPosition(SwingConstants.LEFT);

            //======== jPanel2 ========
            {
                jPanel2.setBorder(new TitledBorder(null, "\u0627\u0644\u0639\u0645\u0644\u064a\u0627\u062a \u0627\u0644\u0645\u062a\u0627\u062d\u0629", TitledBorder.RIGHT, TitledBorder.TOP,
                    new Font("Times New Roman", Font.BOLD, 14), Color.white));

                //---- bt_update_classof ----
                bt_update_classof.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Available_Updates_24px.png")));
                bt_update_classof.setText("\u062a\u0639\u062f\u064a\u0644");
                bt_update_classof.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(144, 202, 249), new Color(33, 150, 243), new Color(144, 202, 249), new Color(33, 150, 243)));
                bt_update_classof.setHorizontalTextPosition(SwingConstants.LEFT);
                bt_update_classof.addActionListener(e -> bt_update_classofActionPerformed(e));

                //---- bt_delete_classof ----
                bt_delete_classof.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Delete_24px.png")));
                bt_delete_classof.setText("\u062d\u0630\u0641");
                bt_delete_classof.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(144, 202, 249), new Color(33, 150, 243), new Color(144, 202, 249), new Color(33, 150, 243)));
                bt_delete_classof.setHorizontalTextPosition(SwingConstants.LEFT);
                bt_delete_classof.addActionListener(e -> bt_delete_classofActionPerformed(e));

                //---- bt_save_class ----
                bt_save_class.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Save_24px.png")));
                bt_save_class.setText("\u062d\u0641\u0638");
                bt_save_class.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(144, 202, 249), new Color(33, 150, 243), new Color(144, 202, 249), new Color(33, 150, 243)));
                bt_save_class.setHorizontalTextPosition(SwingConstants.LEFT);
                bt_save_class.addActionListener(e -> bt_save_classActionPerformed(e));

                //---- bt_new_classof ----
                bt_new_classof.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Add_File_24px.png")));
                bt_new_classof.setText("\u062c\u062f\u064a\u062f");
                bt_new_classof.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(144, 202, 249), new Color(33, 150, 243), new Color(144, 202, 249), new Color(33, 150, 243)));
                bt_new_classof.setHorizontalTextPosition(SwingConstants.LEFT);
                bt_new_classof.addActionListener(e -> bt_new_classofActionPerformed(e));

                //---- jButton1 ----
                jButton1.setText("\u0639\u0631\u0636 \u0627\u0644\u0643\u0644");
                jButton1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(144, 202, 249), new Color(33, 150, 243), new Color(144, 202, 249), new Color(33, 150, 243)));
                jButton1.setHorizontalTextPosition(SwingConstants.LEFT);
                jButton1.addActionListener(e -> jButton1ActionPerformed(e));

                //---- jSeparator1 ----
                jSeparator1.setOrientation(SwingConstants.VERTICAL);
                jSeparator1.setOpaque(true);

                //---- jButton2 ----
                jButton2.setText("\u0637\u0628\u0627\u0639\u0629");
                jButton2.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(144, 202, 249), new Color(33, 150, 243), new Color(144, 202, 249), new Color(33, 150, 243)));
                jButton2.setHorizontalTextPosition(SwingConstants.LEFT);
                jButton2.addActionListener(e -> jButton2ActionPerformed(e));

                //---- jButton4 ----
                jButton4.setText("PDF");
                jButton4.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(144, 202, 249), new Color(33, 150, 243), new Color(144, 202, 249), new Color(33, 150, 243)));
                jButton4.setHorizontalTextPosition(SwingConstants.LEFT);
                jButton4.addActionListener(e -> jButton4ActionPerformed(e));

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bt_delete_classof)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_update_classof)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_save_class, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_new_classof, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                );
                jPanel2Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {bt_delete_classof, bt_new_classof, bt_save_class, bt_update_classof, jButton1, jButton2, jButton4});
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup()
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(bt_delete_classof)
                                .addComponent(bt_update_classof)
                                .addComponent(bt_save_class)
                                .addComponent(bt_new_classof, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator1, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                            .addGap(0, 6, Short.MAX_VALUE))
                );
                jPanel2Layout.linkSize(SwingConstants.VERTICAL, new Component[] {bt_delete_classof, bt_new_classof, bt_save_class, bt_update_classof, jButton1, jButton2, jButton4});
            }

            //---- jLabel1 ----
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_User_Group_Man_Woman_180px.png")));

            //---- jLabel2 ----
            jLabel2.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Male_User_24px.png")));

            //---- jLabel4 ----
            jLabel4.setIcon(new ImageIcon(getClass().getResource("/pharmacy/a7medamer/folder/icons8_Name_24px.png")));

            //---- jLabel13 ----
            jLabel13.setText("\u0628\u064a\u0627\u0646\u0627\u062a");

            //---- jLabel14 ----
            jLabel14.setText("\u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646");

            //======== jPanel1 ========
            {
                jPanel1.setBorder(new TitledBorder(new LineBorder(Color.white, 2), "\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0634\u0631\u0643\u0629 \u0627\u0644\u062a\u0627\u0628\u0639 \u0644\u0647\u0627", TitledBorder.RIGHT, TitledBorder.TOP,
                    new Font("Times New Roman", Font.BOLD, 18), Color.white));
                jPanel1.setOpaque(false);

                //---- txt_co_name ----
                txt_co_name.setHorizontalAlignment(JTextField.RIGHT);
                txt_co_name.setBorder(new LineBorder(new Color(33, 150, 243), 3));
                txt_co_name.addActionListener(e -> txt_co_nameActionPerformed(e));

                //---- jLabel12 ----
                jLabel12.setText("\u0625\u0633\u0645 \u0627\u0644\u0634\u0631\u0643\u0629");

                //---- txt_co_phone ----
                txt_co_phone.setHorizontalAlignment(JTextField.RIGHT);
                txt_co_phone.setBorder(new LineBorder(new Color(33, 150, 243), 3));

                //---- jLabel9 ----
                jLabel9.setText("\u0647\u0627\u062a\u0641 \u0627\u0644\u0634\u0631\u0643\u0629");

                //---- jLabel10 ----
                jLabel10.setText("\u0645\u0644\u0627\u062d\u0638\u0627\u062a");

                //---- txt_co_addr ----
                txt_co_addr.setHorizontalAlignment(JTextField.RIGHT);
                txt_co_addr.setBorder(new LineBorder(new Color(33, 150, 243), 3));

                //======== jScrollPane1 ========
                {

                    //---- jTextPane1 ----
                    jTextPane1.setBorder(new LineBorder(new Color(33, 150, 243), 3));
                    jScrollPane1.setViewportView(jTextPane1);
                }

                //---- jLabel15 ----
                jLabel15.setText("\u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0634\u0631\u0643\u0629");

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_co_addr)
                                .addComponent(txt_co_phone, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txt_co_name, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(jScrollPane1))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(jLabel10)
                                .addComponent(jLabel15)
                                .addComponent(jLabel9)
                                .addComponent(jLabel12))
                            .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel12)
                                .addComponent(txt_co_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(txt_co_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel15)
                                .addComponent(txt_co_addr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(49, 49, 49)))
                            .addGap(0, 0, 0))
                );
            }

            GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup()
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_classof_se, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(jScrollPane4, GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup()
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup()
                                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup()
                                            .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup()
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel8))
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))))
                                .addContainerGap())
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13))
                                .addGap(61, 61, 61))))
            );
            jPanel3Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {txt_name, txt_phone});
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel14)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(txt_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(txt_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(txt_classof_se, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            );
            jPanel3Layout.linkSize(SwingConstants.VERTICAL, new Component[] {jLabel11, jLabel8});
            jPanel3Layout.linkSize(SwingConstants.VERTICAL, new Component[] {txt_classof_se, txt_name, txt_phone});
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void bt_new_classofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_new_classofActionPerformed
        // TODO add your handling code here:
        bt_save_class.setEnabled(true);
        bt_delete_classof.setEnabled(false);
        bt_update_classof.setEnabled(false);
        Update_table1();
        txt_classof_se.setText("");
        txt_name.setText("");
        txt_phone.setText("");
        txt_co_name.setText("");
        txt_co_phone.setText("");
        txt_co_addr.setText("");
        jTextPane1.setText("");
    }//GEN-LAST:event_bt_new_classofActionPerformed

    private void bt_save_classActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_save_classActionPerformed
        // TODO add your handling code here:
        if ((txt_phone.getText().matches("")) || (txt_phone.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "هنالك حقل فارق");

        } else if ((txt_name.getText().matches("")) || (txt_name.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "هنالك حقل فارق");

        } else {

            int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ " + txt_name.getText(), "حفظ بيانات", JOptionPane.YES_NO_OPTION);
            if (Ahmed == 0) {

                try {
                    String sql = "insert into resource "
                            + "(name,phone,co_name,co_phone,co_addr,co_note)" + " values (?,?,?,?,?,?)";

                    pst = conn.prepareStatement(sql);

                    pst.setString(1, txt_name.getText());
                    pst.setString(2, txt_phone.getText());
                    pst.setString(3, txt_co_name.getText());
                    pst.setString(4, txt_co_phone.getText());
                    pst.setString(5, txt_co_addr.getText());
                    pst.setString(6, jTextPane1.getText());

                    pst.execute();

                    Update_table1();
                    String add2 = txt_name.getText();
                    txt_name.setText("");
                    txt_phone.setText("");
                    txt_co_name.setText("");
                    txt_co_phone.setText("");
                    txt_co_addr.setText("");
                    jTextPane1.setText("");

                    JOptionPane.showMessageDialog(null, "تم حفظ " + " " + add2 + " " + "بنجاح");

                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    try {
                        rs.close();
                        pst.close();

                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }

            }

        }
    }//GEN-LAST:event_bt_save_classActionPerformed

    private void bt_delete_classofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_delete_classofActionPerformed
        // TODO add your handling code here:

        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حذف  " + txt_name.getText(), "حذف بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {
                String sql = "delete from resource where id= ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, AA4);
                pst.execute();
                Update_table1();

                bt_save_class.setEnabled(true);
                bt_delete_classof.setEnabled(false);
                bt_update_classof.setEnabled(false);

                String add2 = txt_name.getText();

                txt_name.setText("");
                txt_phone.setText("");
                txt_co_name.setText("");
                txt_co_phone.setText("");
                txt_co_addr.setText("");
                jTextPane1.setText("");

                JOptionPane.showMessageDialog(null, "تم حذف بيانات" + " " + add2 + " " + "بنجاح");

            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    rs.close();
                    pst.close();

                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_bt_delete_classofActionPerformed

    private void bt_update_classofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_update_classofActionPerformed
        // TODO add your handling code here:
        if ((txt_name.getText().matches("")) || (txt_name.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "هنالك حقل فارق");
            return;

        } else if ((txt_phone.getText().matches("")) || (txt_phone.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "هنالك حقل فارق");
            return;
        }

        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد تعديل " + A4, "تعديل بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {

                String add1 = txt_name.getText();
                String add2 = txt_phone.getText();
                String add3 = txt_co_name.getText();
                String add4 = txt_co_phone.getText();
                String add5 = txt_co_addr.getText();
                String add6 = jTextPane1.getText();

                String sql = "update  resource set name = '" + add1 + "',phone='" + add2 + "',co_name='" + add3 + "',co_phone='" + add4 + "',co_addr='" + add4 +"',co_note='" + add4 +"'where id ='" + AA4 + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                Update_table1();

                bt_save_class.setEnabled(true);
                bt_delete_classof.setEnabled(false);
                bt_update_classof.setEnabled(false);

                txt_name.setText("");
                txt_phone.setText("");
                txt_co_name.setText("");
                txt_co_phone.setText("");
                txt_co_addr.setText("");
                jTextPane1.setText("");

                JOptionPane.showMessageDialog(null, "تم تعديل " + " " + add1 + " " + "بنجاح");

            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    rs.close();
                    pst.close();

                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        } else {

            bt_save_class.setEnabled(true);
            bt_delete_classof.setEnabled(false);
            bt_update_classof.setEnabled(false);

            txt_name.setText("");
            txt_phone.setText("");
            txt_co_name.setText("");
            txt_co_phone.setText("");
            txt_co_addr.setText("");
            jTextPane1.setText("");
        }
    }//GEN-LAST:event_bt_update_classofActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        try {

            bt_save_class.setEnabled(false);
            bt_delete_classof.setEnabled(true);
            bt_update_classof.setEnabled(true);

            txt_name.setText("");
            txt_phone.setText("");
            txt_co_name.setText("");
            txt_co_phone.setText("");
            txt_co_addr.setText("");
            jTextPane1.setText("");

            int row = table1.getSelectedRow();

            try {

                tablec = (table1.getModel().getValueAt(row, 0).toString());

            } catch (Exception e) {
            }

            String sql = "select * from resource where id=?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, tablec);
            rs = pst.executeQuery();
            if (rs.next()) {
                //  JOptionPane.showMessageDialog(null, "الرجاء");
                // }else{

                String add1 = rs.getString("name");
                txt_name.setText(add1);

                String add2 = rs.getString("phone");
                txt_phone.setText(add2);

                String add3 = rs.getString("co_name");
                txt_co_name.setText(add3);

                String add4 = rs.getString("co_phone");
                txt_co_phone.setText(add4);

                String add5 = rs.getString("co_addr");
                txt_co_addr.setText(add5);

                String add6 = rs.getString("co_note");
                jTextPane1.setText(add6);

                AA4 = rs.getString("id");

                A4 = txt_phone.getText();

            }
        } catch (SQLException ex) {
            Logger.getLogger(resource1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_table1MouseClicked

    private void txt_classof_seKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_classof_seKeyReleased
        // TODO add your handling code here:

        if (txt_classof_se.getText().isEmpty()) {
            this.Update_table1();
        } else {

            try {
                String sql = " select id,name,phone,co_name,co_phone from resource where name like '" + txt_classof_se.getText() + "%' or co_name like '" + txt_classof_se.getText() + "%'";

                table1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                table1.setAutoCreateRowSorter(true);
                pst = conn.prepareStatement(sql);
                //  pst.setString(1, txt_idsh.getText());
                rs = pst.executeQuery();

                bt_save_class.setEnabled(false);
                bt_delete_classof.setEnabled(true);
                bt_update_classof.setEnabled(true);

                txt_name.setText("");
                txt_phone.setText("");
                txt_co_name.setText("");
                txt_co_phone.setText("");
                txt_co_addr.setText("");
                jTextPane1.setText("");

                if (rs.next()) {

                    AA4 = rs.getString("id");

                    String add1 = rs.getString("name");
                    txt_name.setText(add1);

                    String add2 = rs.getString("phone");
                    txt_phone.setText(add2);

                    String add3 = rs.getString("co_name");
                    txt_co_name.setText(add3);

                    String add4 = rs.getString("co_phone");
                    txt_co_phone.setText(add4);

                    String add5 = rs.getString("co_addr");
                    txt_co_addr.setText(add5);

                    String add6 = rs.getString("co_note");
                    jTextPane1.setText(add6);

                    A4 = txt_name.getText();
                } else {
                    bt_save_class.setEnabled(false);
                    bt_delete_classof.setEnabled(false);
                    bt_update_classof.setEnabled(false);

                    txt_name.setText("");
                    txt_phone.setText("");
                    txt_co_name.setText("");
                    txt_co_phone.setText("");
                    txt_co_addr.setText("");
                    jTextPane1.setText("");
                }

                pst = conn.prepareStatement(sql);

                rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));

                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));

                String[] tableColumnsName = {"*", "الإسم بالكامل", "إسم المستخدم", "الصلاحية"};

                DefaultTableModel aModel = (DefaultTableModel) table1.getModel();
                aModel.setColumnIdentifiers(tableColumnsName);

                // FontUIResource f = new FontUIResource("Times New Roman", 0, 18);
                // Loop through the ResultSet and transfer in the Model
                ((DefaultTableCellRenderer) table1.getTableHeader().getDefaultRenderer())
                        .setHorizontalAlignment((int) JLabel.CENTER);

                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);

                table1.getColumnModel().getColumn(0).setCellRenderer(renderer);

                for (int i = 0; i < table1.getColumnCount(); i++) {

                    table1.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    table1.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));
                    table1.getColumnModel().getColumn(i).setWidth(500);
                    DefaultTableColumnModel colModel = (DefaultTableColumnModel) table1.getColumnModel();

                    TableColumn col = colModel.getColumn(i);
                    TableCellRenderer hRenderer = col.getHeaderRenderer();

                    int width = 0;
                    TableCellRenderer renderer1 = col.getHeaderRenderer();

                    JTableHeader header = (JTableHeader) col.getHeaderRenderer();
                    for (int r = 0; r < table1.getRowCount(); r++) {
                        renderer = (DefaultTableCellRenderer) table1.getCellRenderer(r, i);
                        Component comp = renderer.getTableCellRendererComponent(table1, table1.getValueAt(r, i),
                                false, false, r, i);
                        width = Math.max(width, comp.getPreferredSize().width);
                    }

                    col.setPreferredWidth(width + 2);

                    //setTableAlignmentValue(table1, i);
                }

                java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                table1.setRowHeight(30);

                table1.setSize(50, 50);
                table1.setEditingRow(0);
                JTableHeader header = table1.getTableHeader();
                header.setPreferredSize(new Dimension(30, 30));
                header.setBackground(new java.awt.Color(33, 150, 243));
                header.setForeground(new java.awt.Color(255, 255, 255));
                header.setFont(new java.awt.Font("Times New Roman", 1, 18));

                int colNo = rsmd.getColumnCount();

                while (rs.next()) {
                    Object[] objects = new Object[colNo];
                    // tanks to umit ozkan for the bug fix!
                    for (int i = 0; i < colNo; i++) {
                        objects[i] = rs.getObject(i + 1);
                        table1.getColumnModel().getColumn(i).setCellRenderer(renderer);
                        table1.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));

                        //setTableAlignmentValue(table1, i);
                    }

                    aModel.addRow(objects);

                }

                table1.setModel(aModel);

            } catch (Exception e) {

            } finally {
                try {
                    rs.close();
                    pst.close();

                } catch (Exception e) {

                }
            }
        }
    }//GEN-LAST:event_txt_classof_seKeyReleased

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_co_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_co_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_co_nameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(resource1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new resource1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel jPanel3;
    private JLabel jLabel11;
    private JTextField txt_name;
    private JLabel jLabel8;
    private JTextField txt_phone;
    private JScrollPane jScrollPane4;
    private JTable table1;
    private JTextField txt_classof_se;
    private JButton jButton3;
    private JLabel jLabel5;
    private JPanel jPanel2;
    private JButton bt_update_classof;
    private JButton bt_delete_classof;
    private JButton bt_save_class;
    private JButton bt_new_classof;
    private JButton jButton1;
    private JSeparator jSeparator1;
    private JButton jButton2;
    private JButton jButton4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel4;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JPanel jPanel1;
    private JTextField txt_co_name;
    private JLabel jLabel12;
    private JTextField txt_co_phone;
    private JLabel jLabel9;
    private JLabel jLabel10;
    private JTextField txt_co_addr;
    private JScrollPane jScrollPane1;
    private JTextPane jTextPane1;
    private JLabel jLabel15;
    // End of variables declaration//GEN-END:variables
}
