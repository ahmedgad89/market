/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class adduser extends javax.swing.JDialog {

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

    /**
     * Creates new form adduser
     */
    public adduser() {
        initComponents();   setModal(true);

        conn = DB.jave_db();

        Update_table1();

        bt_delete_classof.setEnabled(false);
        bt_update_classof.setEnabled(false);

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();

        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tab_classof.setShowGrid(true);

    }

    private void Update_table1() {
        tab_classof.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        tab_classof.setAutoCreateRowSorter(true);

        try {
            String sql = "select ADid,ADuser,username,ADrole,ADpass from adusers";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tab_classof.setModel(DbUtils.resultSetToTableModel(rs));

            String[] tableColumnsName = {"*", "إسم المستخدم", "الإسم", "الصلاحية "};
            DefaultTableModel aModel = (DefaultTableModel) tab_classof.getModel();
            aModel.setColumnIdentifiers(tableColumnsName);
            Font f = new Font("Times New Roman", 0, 18);
            // FontUIResource f = new FontUIResource("Times New Roman", 0, 18);
            ((DefaultTableCellRenderer) tab_classof.getTableHeader().getDefaultRenderer())
                    .setHorizontalAlignment((int) JLabel.CENTER);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            tab_classof.getColumnModel().getColumn(0).setCellRenderer(renderer);

            for (int i = 0; i < tab_classof.getColumnCount(); i++) {

                tab_classof.getColumnModel().getColumn(i).setCellRenderer(renderer);
                tab_classof.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));
                tab_classof.getColumnModel().getColumn(i).setWidth(500);
                DefaultTableColumnModel colModel = (DefaultTableColumnModel) tab_classof.getColumnModel();

                TableColumn col = colModel.getColumn(i);
                TableCellRenderer hRenderer = col.getHeaderRenderer();

                int width = 0;
                TableCellRenderer renderer1 = col.getHeaderRenderer();
                for (int r = 0; r < tab_classof.getRowCount(); r++) {
                    renderer = (DefaultTableCellRenderer) tab_classof.getCellRenderer(r, i);
                    Component comp = renderer.getTableCellRendererComponent(tab_classof, tab_classof.getValueAt(r, i),
                            false, false, r, i);
                    width = Math.max(width, comp.getPreferredSize().width);
                }
                col.setPreferredWidth(width + 2);

                //setTableAlignmentValue(tab_classof, i);
            }
            // Loop through the ResultSet and transfer in the Model
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            tab_classof.setFont(new java.awt.Font("Times New Roman", 0, 18));

            tab_classof.setRowHeight(30);
            tab_classof.getColumnModel().getColumn(1).setPreferredWidth(50);

            tab_classof.getColumnModel().getColumn(0).setPreferredWidth(5);

            tab_classof.setSize(50, 50);
            tab_classof.setEditingRow(0);
            JTableHeader header = tab_classof.getTableHeader();
            header.setPreferredSize(new Dimension(30, 30));
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            header.setForeground(Color.red);

            int colNo = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] objects = new Object[colNo];
                // tanks to umit ozkan for the bug fix!
                for (int i = 0; i < colNo; i++) {
                    objects[i] = rs.getObject(i + 1);
                    //  setTableAlignmentValue (tabl_stu, i);
                }
                aModel.addRow(objects);

            }
            tab_classof.setModel(aModel);
            //  }

        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        tab_classof = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_uname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        co_class = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_classof_se = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_fname = new javax.swing.JTextField();
        bt_update_classof = new javax.swing.JButton();
        bt_delete_classof = new javax.swing.JButton();
        bt_save_class = new javax.swing.JButton();
        bt_new_classof = new javax.swing.JButton();
        txt_pass = new javax.swing.JPasswordField();
        txt_pass2 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tab_classof.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tab_classof.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tab_classof.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tab_classof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_classofMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tab_classof);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("كلمة المرور");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("إسم المستخدم");

        txt_uname.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_uname.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("الصلاحية");

        co_class.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        co_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "مدير", "وكيل", "مشرف", "أستاذ", "طالب" }));
        co_class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                co_classMouseClicked(evt);
            }
        });
        co_class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                co_classActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("بحث بإسم المستخدم");

        txt_classof_se.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_classof_se.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_classof_se.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_classof_seKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_classof_se, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(8, 8, 8))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_classof_se, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("الإسم رباعي");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("إعادة كلمة المرور");

        txt_fname.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_fname.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fnameActionPerformed(evt);
            }
        });

        bt_update_classof.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bt_update_classof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/schoolsms/images/Update.png"))); // NOI18N
        bt_update_classof.setText("تعديل");
        bt_update_classof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_update_classofActionPerformed(evt);
            }
        });

        bt_delete_classof.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bt_delete_classof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/schoolsms/images/Delete.png"))); // NOI18N
        bt_delete_classof.setText("حذف");
        bt_delete_classof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_delete_classofActionPerformed(evt);
            }
        });

        bt_save_class.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bt_save_class.setIcon(new javax.swing.ImageIcon(getClass().getResource("/schoolsms/images/Save.png"))); // NOI18N
        bt_save_class.setText("حفظ");
        bt_save_class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_save_classActionPerformed(evt);
            }
        });

        bt_new_classof.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bt_new_classof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/schoolsms/images/New document.png"))); // NOI18N
        bt_new_classof.setText("جديد");
        bt_new_classof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_new_classofActionPerformed(evt);
            }
        });

        txt_pass.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_pass.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txt_pass2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txt_pass2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_pass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pass2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/schoolsms/images/back.png"))); // NOI18N
        jButton3.setText("رجوع");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/schoolsms/images/close.png"))); // NOI18N
        jButton4.setText("خروج");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_pass2, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(txt_pass)
                                    .addComponent(txt_uname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_fname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(co_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(315, 315, 315)
                                .addComponent(bt_delete_classof)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bt_update_classof)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_save_class, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_new_classof))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))
                        .addGap(1, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_uname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(co_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_update_classof)
                            .addComponent(bt_delete_classof)
                            .addComponent(bt_save_class)
                            .addComponent(bt_new_classof))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab_classofMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_classofMouseClicked
        // TODO add your handling code here:
        try {

            bt_save_class.setEnabled(false);
            bt_delete_classof.setEnabled(true);
            bt_update_classof.setEnabled(true);

            txt_uname.setText("");
            co_class.setSelectedIndex(0);
            txt_fname.setText("");
            txt_pass.setText("");
            txt_pass2.setText("");

            int row = tab_classof.getSelectedRow();
            String tablec = (tab_classof.getModel().getValueAt(row, 0).toString());

            String sql = "select * from adusers where ADid=?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, tablec);
            rs = pst.executeQuery();
            if (rs.next()) {
                //  JOptionPane.showMessageDialog(null, "الرجاء");
                // }else{

                String add88 = rs.getString("ADuser");
                txt_uname.setText(add88);

                String add10 = rs.getString("ADrole");
                co_class.setSelectedItem(add10);

                String add3 = rs.getString("username");
                txt_fname.setText(add3);

                String add4 = rs.getString("ADpass");
                txt_pass.setText(add4);

                String add5 = rs.getString("ADpass");
                txt_pass2.setText(add5);

                AA4 = rs.getString("ADid");

                A4 = txt_uname.getText();

            }
        } catch (SQLException ex) {
            Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tab_classofMouseClicked

    private void co_classMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_co_classMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_co_classMouseClicked

    private void co_classActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_co_classActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_co_classActionPerformed

    private void txt_classof_seKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_classof_seKeyReleased
        // TODO add your handling code here:

        if (txt_classof_se.getText().isEmpty()) {
            this.Update_table1();
        } else {

            try {
                String sql = " SELECT ADid,ADuser,username,ADrole,ADpass FROM adusers where ADuser like '" + txt_classof_se.getText() + "%'";

                tab_classof.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                tab_classof.setAutoCreateRowSorter(true);
                pst = conn.prepareStatement(sql);
                //  pst.setString(1, txt_idsh.getText());
                rs = pst.executeQuery();

                bt_save_class.setEnabled(false);
                bt_delete_classof.setEnabled(true);
                bt_update_classof.setEnabled(true);

                txt_uname.setText("");

                if (rs.next()) {

                    AA4 = rs.getString("ADid");

                    String add88 = rs.getString("ADuser");
                    txt_uname.setText(add88);

                    String add10 = rs.getString("ADrole");
                    co_class.setSelectedItem(add10);

                    String add3 = rs.getString("username");
                    txt_fname.setText(add3);

                    String add4 = rs.getString("ADpass");
                    txt_pass.setText(add4);

                    String add5 = rs.getString("ADpass");
                    txt_pass2.setText(add5);

                    A4 = txt_uname.getText();
                }

                pst = conn.prepareStatement(sql);

                rs = pst.executeQuery();
                tab_classof.setModel(DbUtils.resultSetToTableModel(rs));

                String[] tableColumnsName = {"*", "إسم المستخدم", "الإسم", "الصلاحية "};
                DefaultTableModel aModel = (DefaultTableModel) tab_classof.getModel();
                aModel.setColumnIdentifiers(tableColumnsName);
                Font f = new Font("Times New Roman", 0, 18);
                // FontUIResource f = new FontUIResource("Times New Roman", 0, 18);
                ((DefaultTableCellRenderer) tab_classof.getTableHeader().getDefaultRenderer())
                        .setHorizontalAlignment((int) JLabel.CENTER);

                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                tab_classof.getColumnModel().getColumn(0).setCellRenderer(renderer);

                for (int i = 0; i < tab_classof.getColumnCount(); i++) {

                    tab_classof.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    tab_classof.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));
                    tab_classof.getColumnModel().getColumn(i).setWidth(500);
                    DefaultTableColumnModel colModel = (DefaultTableColumnModel) tab_classof.getColumnModel();

                    TableColumn col = colModel.getColumn(i);
                    TableCellRenderer hRenderer = col.getHeaderRenderer();

                    int width = 0;
                    TableCellRenderer renderer1 = col.getHeaderRenderer();
                    for (int r = 0; r < tab_classof.getRowCount(); r++) {
                        renderer = (DefaultTableCellRenderer) tab_classof.getCellRenderer(r, i);
                        Component comp = renderer.getTableCellRendererComponent(tab_classof, tab_classof.getValueAt(r, i),
                                false, false, r, i);
                        width = Math.max(width, comp.getPreferredSize().width);
                    }
                    col.setPreferredWidth(width + 2);

                    //setTableAlignmentValue(tab_classof, i);
                }
                // Loop through the ResultSet and transfer in the Model
                java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                tab_classof.setFont(new java.awt.Font("Times New Roman", 0, 18));

                tab_classof.setRowHeight(30);
                tab_classof.getColumnModel().getColumn(1).setPreferredWidth(50);

                tab_classof.getColumnModel().getColumn(0).setPreferredWidth(5);

                tab_classof.setSize(50, 50);
                tab_classof.setEditingRow(0);
                JTableHeader header = tab_classof.getTableHeader();
                header.setPreferredSize(new Dimension(30, 30));
                header.setFont(new java.awt.Font("Times New Roman", 0, 18));
                header.setForeground(Color.red);

                int colNo = rsmd.getColumnCount();

                while (rs.next()) {
                    Object[] objects = new Object[colNo];
                    // tanks to umit ozkan for the bug fix!
                    for (int i = 0; i < colNo; i++) {
                        objects[i] = rs.getObject(i + 1);
                        //  setTableAlignmentValue (tabl_stu, i);
                    }
                    aModel.addRow(objects);

                }
                tab_classof.setModel(aModel);
                //  }

            } catch (SQLException ex) {
                Logger.getLogger(adduser.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_txt_classof_seKeyReleased

    private void txt_fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fnameActionPerformed

    private void bt_update_classofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_update_classofActionPerformed
        // TODO add your handling code here:
        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد تعديل " + A4, "تعديل بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {

                String add1 = txt_uname.getText();
                String add2 = txt_fname.getText();
                String add3 = txt_pass.getText();

                String add4 = co_class.getSelectedItem().toString();

                String sql = "update  adusers set ADuser = '" + add1 + "',username='" + add2 + "',ADpass='" + add3 + "',ADrole='" + add4 + "'where ADid ='" + AA4 + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                Update_table1();

                bt_save_class.setEnabled(true);
                bt_delete_classof.setEnabled(false);
                bt_update_classof.setEnabled(false);

                txt_uname.setText("");
                co_class.setSelectedIndex(0);
                txt_fname.setText("");
                txt_pass.setText("");
                txt_pass2.setText("");

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

            txt_uname.setText("");
            co_class.setSelectedIndex(0);
            txt_fname.setText("");
            txt_pass.setText("");
            txt_pass2.setText("");
        }

    }//GEN-LAST:event_bt_update_classofActionPerformed

    private void bt_delete_classofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_delete_classofActionPerformed
        // TODO add your handling code here:

        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حذف  " + txt_uname.getText(), "حذف بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {
                String sql = "delete from adusers where ADid= ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, AA4);
                pst.execute();
                Update_table1();

                bt_save_class.setEnabled(true);
                bt_delete_classof.setEnabled(false);
                bt_update_classof.setEnabled(false);

                String add2 = txt_uname.getText();

                txt_uname.setText("");
                co_class.setSelectedIndex(0);
                txt_fname.setText("");
                txt_pass.setText("");
                txt_pass2.setText("");

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

    private void bt_save_classActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_save_classActionPerformed
        // TODO add your handling code here:

        if ((txt_uname.getText().matches("")) || (txt_uname.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "الرجاء ادخل اسم المادة");

        } else {
            int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ " + txt_uname.getText(), "حفظ بيانات", JOptionPane.YES_NO_OPTION);
            if (Ahmed == 0) {

                try {
                    String sql = "insert into adusers "
                            + "(ADuser,username,ADpass,ADrole)" + " values (?,?,?,?)";

                    pst = conn.prepareStatement(sql);

                    pst.setString(1, txt_uname.getText());
                    pst.setString(2, txt_fname.getText());
                    pst.setString(3, txt_pass.getText());
                    pst.setString(4, co_class.getSelectedItem().toString());

                    pst.execute();

                    Update_table1();
                    String add2 = txt_uname.getText();
                    txt_uname.setText("");
                    co_class.setSelectedIndex(0);
                    txt_fname.setText("");
                    txt_pass.setText("");
                    txt_pass2.setText("");

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

    private void bt_new_classofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_new_classofActionPerformed
        // TODO add your handling code here:
        bt_save_class.setEnabled(true);
        bt_delete_classof.setEnabled(false);
        bt_update_classof.setEnabled(false);
        Update_table1();
        txt_classof_se.setText("");
        txt_uname.setText("");
        co_class.setSelectedIndex(0);
        txt_fname.setText("");
        txt_pass.setText("");
        txt_pass2.setText("");
    }//GEN-LAST:event_bt_new_classofActionPerformed

    private void txt_pass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pass2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pass2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // TODO add your handling code here:
        this.dispose();
        new main2().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        System.exit(0);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(adduser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adduser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adduser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adduser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adduser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_delete_classof;
    private javax.swing.JButton bt_new_classof;
    private javax.swing.JButton bt_save_class;
    private javax.swing.JButton bt_update_classof;
    private javax.swing.JComboBox<String> co_class;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tab_classof;
    private javax.swing.JTextField txt_classof_se;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JPasswordField txt_pass2;
    private javax.swing.JTextField txt_uname;
    // End of variables declaration//GEN-END:variables
}
