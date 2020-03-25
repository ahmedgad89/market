/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import org.openide.util.Exceptions;

/**
 *
 * @author A7meD3H
 */
public class Store_File extends javax.swing.JDialog {

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
   
    int xx, xy;
    String filename = null;
    byte[] s_image = null;
    byte[] add6 = null;
    ByteArrayOutputStream bos;

    /**
     * Creates new form ADU
     */
    public Store_File() {
        initComponents();   setModal(true);

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        bt_new.setBackground(new java.awt.Color(240, 240, 240));
        bt_save.setBackground(new java.awt.Color(240, 240, 240));
        bt_update.setBackground(new java.awt.Color(240, 240, 240));
        bt_delete.setBackground(new java.awt.Color(240, 240, 240));

        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);

        Update_table();
        table.setShowGrid(true);
    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    private void Update_table() {
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select id,file_name,file_note,file_begin_data,file_end_data,user_save,date_save from tb_store_file";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            String[] tableColumnsName = {"*", "إسم الملف", "ملاحظات الملف", "تاريخ الأصدار", "تاريخ  الأنتهاء", "حفظ بواسطة", "تاريخ الحفظ"};
            DefaultTableModel aModel = (DefaultTableModel) table.getModel();
            aModel.setColumnIdentifiers(tableColumnsName);
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
            }
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            table.setRowHeight(30);
            table.setSize(50, 50);
            table.setEditingRow(0);
            JTableHeader header = table.getTableHeader();
            header.setPreferredSize(new Dimension(30, 30));
            header.setBackground(new java.awt.Color(75, 137, 220));
            header.setForeground(new java.awt.Color(240, 240, 240));
            header.setFont(new java.awt.Font("Times New Roman", 1, 18));
            header.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
            int colNo = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] objects = new Object[colNo];
                for (int i = 0; i < colNo; i++) {
                    objects[i] = rs.getObject(i + 1);
                    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    table.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));

                    //setTableAlignmentValue(table, i);
                }

                aModel.addRow(objects);

            }

            table.setModel(aModel);

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bt_new = new javax.swing.JButton();
        bt_save = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_file_name = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_file_note = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_file_end_alarm = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_file_end_data = new com.toedter.calendar.JDateChooser();
        txt_file_begin_data = new com.toedter.calendar.JDateChooser();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        txt_file_picture = new javax.swing.JLabel();
        bt_upload_logo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        bt_secure = new javax.swing.JButton();
        bt_minimize = new javax.swing.JButton();
        bt_exit = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ملفات المحل");
        setFocusCycleRoot(false);
        setUndecorated(true);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(93, 156, 236));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(93, 156, 236));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)), "العمليات المتاحة", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(240, 240, 240))); // NOI18N

        bt_new.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_new.setForeground(new java.awt.Color(75, 137, 220));
        bt_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Add_New_24px_1.png"))); // NOI18N
        bt_new.setText("جديد");
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
        bt_save.setText("حفظ");
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

        bt_update.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_update.setForeground(new java.awt.Color(75, 137, 220));
        bt_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Available_Updates_24px.png"))); // NOI18N
        bt_update.setText("تعديل");
        bt_update.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_update.setContentAreaFilled(false);
        bt_update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_update.setOpaque(true);
        bt_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_updateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_updateMouseExited(evt);
            }
        });
        bt_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_updateActionPerformed(evt);
            }
        });

        bt_delete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_delete.setForeground(new java.awt.Color(75, 137, 220));
        bt_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Delete_24px_1.png"))); // NOI18N
        bt_delete.setText("حذف");
        bt_delete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_delete.setContentAreaFilled(false);
        bt_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_delete.setOpaque(true);
        bt_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_deleteMouseExited(evt);
            }
        });
        bt_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_save, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bt_delete, bt_save, bt_update});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_save, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_delete, bt_new, bt_save, bt_update});

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75,137,220)));

        table.setBackground(new java.awt.Color(240, 240, 240));
        table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        table.setForeground(new java.awt.Color(75,137,220));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setGridColor(new java.awt.Color(75, 137, 220));
        table.setSelectionBackground(new java.awt.Color(75, 137, 220));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table);

        txt_search.setBackground(new java.awt.Color(240, 240, 240));
        txt_search.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_search.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Google_Web_Search_24px_1.png"))); // NOI18N
        jLabel6.setFocusable(false);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel6.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("إسم الملف");

        txt_file_name.setBackground(new java.awt.Color(240, 240, 240));
        txt_file_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_file_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_file_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ملاحظات عن الملف");

        txt_file_note.setBackground(new java.awt.Color(240, 240, 240));
        txt_file_note.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_file_note.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_file_note.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("تاريخ الأصدار");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("تاريخ الأنتهاء");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("إنذار الإنتهاء");

        txt_file_end_alarm.setBackground(new java.awt.Color(240, 240, 240));
        txt_file_end_alarm.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_file_end_alarm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_file_end_alarm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("صورة من الملف");

        txt_file_end_data.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_file_end_data.setDateFormatString("yyyy-MM-dd");
        txt_file_end_data.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_file_end_data.setMaxSelectableDate(new java.util.Date(2208985313000L));
        txt_file_end_data.setMinSelectableDate(new java.util.Date(946681313000L));
        txt_file_end_data.setMinimumSize(new java.awt.Dimension(35, 35));
        txt_file_end_data.setOpaque(false);

        txt_file_begin_data.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_file_begin_data.setDateFormatString("yyyy-MM-dd");
        txt_file_begin_data.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_file_begin_data.setMaxSelectableDate(new java.util.Date(2208985313000L));
        txt_file_begin_data.setMinSelectableDate(new java.util.Date(946681313000L));
        txt_file_begin_data.setMinimumSize(new java.awt.Dimension(35, 35));
        txt_file_begin_data.setOpaque(false);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        bt_upload_logo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_upload_logo.setForeground(new java.awt.Color(75, 137, 220));
        bt_upload_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Image_File_24px.png"))); // NOI18N
        bt_upload_logo.setText("إختيار الصورة");
        bt_upload_logo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        bt_upload_logo.setContentAreaFilled(false);
        bt_upload_logo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_upload_logo.setOpaque(true);
        bt_upload_logo.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_upload_logo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_upload_logoActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(txt_file_picture, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(bt_upload_logo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_file_picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_upload_logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(txt_file_picture, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bt_upload_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_file_note, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_file_begin_data, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel13)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txt_file_end_alarm, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_file_end_data, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)))))))
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_file_begin_data, txt_file_end_alarm, txt_file_end_data, txt_file_name, txt_file_note});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txt_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel13)
                            .addComponent(txt_file_note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel17)
                            .addComponent(txt_file_begin_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel14)
                            .addComponent(txt_file_end_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel15)
                            .addComponent(txt_file_end_alarm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_file_begin_data, txt_file_end_alarm, txt_file_end_data, txt_file_name, txt_file_note});

        jPanel6.setBackground(new java.awt.Color(75, 137, 220));
        jPanel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        jPanel6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel6MouseDragged(evt);
            }
        });
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });

        bt_secure.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_secure.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Secure_28px.png"))); // NOI18N
        bt_secure.setBorder(null);
        bt_secure.setBorderPainted(false);
        bt_secure.setContentAreaFilled(false);
        bt_secure.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_secure.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_secure.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_secure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_secureActionPerformed(evt);
            }
        });

        bt_minimize.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Minus_30px.png"))); // NOI18N
        bt_minimize.setBorderPainted(false);
        bt_minimize.setContentAreaFilled(false);
        bt_minimize.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_minimize.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_minimize.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_minimizeActionPerformed(evt);
            }
        });

        bt_exit.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Cancel_24px.png"))); // NOI18N
        bt_exit.setBorder(null);
        bt_exit.setBorderPainted(false);
        bt_exit.setContentAreaFilled(false);
        bt_exit.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_exit.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_exit.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exitActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(75, 137, 220));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ملفات المحل");
        jPanel4.add(jLabel11);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bt_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_secure, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(bt_minimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_secure, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(bt_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed

        txt_file_name.setText("");
        txt_file_note.setText("");

        txt_file_end_alarm.setText("-");
  txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));
        txt_search.setText("");
        Update_table();
        bt_save.setEnabled(true);
        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);

    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql22 = " select * from tb_store_file where file_name= '" + txt_file_name.getText() + "'";
            pst = conn.prepareStatement(sql22);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "إسم الملف " + txt_file_name.getText() + " مسجل مسبقاً", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
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

        if ((txt_file_name.getText().matches("")) || (txt_file_name.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "قم بإدخال إسم الملف للحفظ !");
            return;
        } else if ((txt_file_end_alarm.getText().matches("")) || (txt_file_end_alarm.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "قم بإدخال إنذار الأنتهاء للحفظ !");
            return;
        } else {

            int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ " + txt_file_name.getText(), "حفظ بيانات", JOptionPane.YES_NO_OPTION);
            if (Ahmed == 0) {

                try {
                    if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                        conn = DB.jave_db();
                    }
                    String sql = "insert into tb_store_file "
                            + "(file_name,file_note,file_begin_data,file_end_data,file_end_alarm,file_picture,user_save,date_save)" + " values (?,?,?,?,?,?,?,?)";

                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txt_file_name.getText());
                    pst.setString(2, txt_file_note.getText());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String txt_file_begin_data1 = (df.format(txt_file_begin_data.getDate()));
                    pst.setString(3, txt_file_begin_data1);
                    String txt_file_end_data1 = (df.format(txt_file_end_data.getDate()));
                    pst.setString(4, txt_file_end_data1);
                    pst.setString(5, txt_file_end_alarm.getText());
                     pst.setBytes(6, s_image);
                 
                    pst.setString(7, "Ahmed");
                    Date date = GregorianCalendar.getInstance().getTime();
                    String datestring = df.format(date);
                    pst.setString(8, datestring);
                    pst.execute();
                    Update_table();
                    String add2 = txt_file_name.getText();
                    txt_file_name.setText("");
                    txt_file_note.setText("");

                    txt_file_end_alarm.setText("-");
                   txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));
                    JOptionPane.showMessageDialog(null, "تم حفظ " + " " + add2 + " " + "بنجاح");
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

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql1 = "select * from tb_store_file where file_name ='" + txt_file_name.getText() + "' AND id !='" + AA5 + "'";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, " تم تسجيل " + txt_file_name.getText() + " من قبل ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                return;
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
        if ((txt_file_name.getText().matches("")) || (txt_file_name.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "قم بإدخال إسم العميل للتعديل !");
            return;

        }
        if ((txt_file_note.getText().matches("")) || (txt_file_note.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "قم بإدخال هاتف الملف للتعديل !");
            return;

        }
        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد تعديل " + A4, "تعديل بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {
                String add1 = txt_file_name.getText();
                String add2 = txt_file_note.getText();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                String txt_file_begin_data1 = (df.format(txt_file_begin_data.getDate()));
                String add3 = txt_file_begin_data1;

                String txt_file_end_data1 = (df.format(txt_file_end_data.getDate()));
                String add4 = txt_file_end_data1;
                String add5 = txt_file_end_alarm.getText();
                
                
                   if (s_image != null) {
                        add6 = s_image;
                        add6 = bos.toByteArray();
                        s_image = null;
                    }
                
                String add7 = "Ahmed";

                Date date = GregorianCalendar.getInstance().getTime();
                String datestring = df.format(date);
                String add8 = datestring;

                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = "update  tb_store_file set  file_name = '" + add1 + "',file_note = '" + add2 + "',file_begin_data = '" + add3 + "',file_end_data = '" + add4 + "',file_end_alarm = '" + add5 + "',file_picture = ?,user_save = '" + add7 + "',date_save='" + add8 + "'where id ='" + AA4 + "'";
                pst = conn.prepareStatement(sql);
                 pst.setBytes(1, add6);
                pst.execute();
                Update_table();
                bt_save.setEnabled(true);
                bt_delete.setEnabled(false);
                bt_update.setEnabled(false);

                txt_file_name.setText("");
                txt_file_note.setText("");

                txt_file_end_alarm.setText("-");
               txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));

                JOptionPane.showMessageDialog(null, "تم تعديل " + " " + add1 + " " + "بنجاح");

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

        } else {

            bt_save.setEnabled(true);
            bt_delete.setEnabled(false);
            bt_update.setEnabled(false);
            txt_file_name.setText("");
            txt_file_note.setText("");

            txt_file_end_alarm.setText("-");
           txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));
        }
    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حذف  " + txt_file_name.getText(), "حذف بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = "delete from tb_store_file where id= ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, AA4);
                pst.execute();
                Update_table();

                bt_save.setEnabled(true);
                bt_delete.setEnabled(false);
                bt_update.setEnabled(false);

                String add2 = txt_file_name.getText();

                txt_file_name.setText("");
                txt_file_note.setText("");

                txt_file_end_alarm.setText("-");
               txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));

                JOptionPane.showMessageDialog(null, "تم حذف بيانات" + " " + add2 + " " + "بنجاح");

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

    }//GEN-LAST:event_bt_deleteActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        try {

            bt_save.setEnabled(false);
            bt_delete.setEnabled(true);
            bt_update.setEnabled(true);

            txt_file_name.setText("");
            txt_file_note.setText("");

            txt_file_end_alarm.setText("-");
           txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));

            int row = table.getSelectedRow();

            tablec = (table.getModel().getValueAt(row, 0).toString());

            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from tb_store_file where id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tablec);
            rs = pst.executeQuery();
            if (rs.next()) {

                String add1 = rs.getString("file_name");
                txt_file_name.setText(add1);
                String add2 = rs.getString("file_note");
                txt_file_note.setText(add2);

                String add3 = rs.getString("file_begin_data");
                java.util.Date aaa = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
                txt_file_begin_data.setDate(aaa);

                String add4 = rs.getString("file_end_data");
                java.util.Date aaa1 = new SimpleDateFormat("yyyy-MM-dd").parse(add4);
                txt_file_end_data.setDate(aaa1);

                String add5 = rs.getString("file_end_alarm");
                txt_file_end_alarm.setText(add5);
              
                try {
                        add6 = rs.getBytes("file_picture");
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon(add6).getImage().getScaledInstance(txt_file_picture.getWidth(), txt_file_picture.getHeight(), Image.SCALE_DEFAULT));
                        txt_file_picture.setIcon(imageIcon);

                    } catch (Exception e) {
                    }
                
                
                
                AA4 = rs.getString("id");
                AA5 = rs.getString("id");

                A4 = txt_file_name.getText();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Store_File.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();

            } catch (Exception e) {

            }
        }

    }//GEN-LAST:event_tableMouseClicked

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        if (txt_search.getText().isEmpty()) {
            Update_table();
            bt_save.setEnabled(true);
            bt_delete.setEnabled(false);
            bt_update.setEnabled(false);
            txt_file_name.setText("");
            txt_file_note.setText("");

            txt_file_end_alarm.setText("-");
           txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));

        } else {

            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = " select * from tb_store_file where file_name like '" + txt_search.getText() + "%' or file_note like '" + txt_search.getText() + "%' or file_end_data like '" + txt_search.getText() + "%' or file_end_alarm like '" + txt_search.getText() + "%'";
                table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                bt_save.setEnabled(false);
                bt_delete.setEnabled(true);
                bt_update.setEnabled(true);

                txt_file_name.setText("");
                txt_file_note.setText("");

                txt_file_end_alarm.setText("-");
               txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));

                if (rs.next()) {
                    String add1 = rs.getString("file_name");
                    txt_file_name.setText(add1);
                    String add2 = rs.getString("file_note");
                    txt_file_note.setText(add2);

                    String add3 = rs.getString("file_begin_data");
                    java.util.Date aaa = new SimpleDateFormat("yyyy-MM-dd").parse(add3);
                    txt_file_begin_data.setDate(aaa);

                    String add4 = rs.getString("file_end_data");
                    java.util.Date aaa1 = new SimpleDateFormat("yyyy-MM-dd").parse(add4);
                    txt_file_end_data.setDate(aaa1);

                    String add5 = rs.getString("file_end_alarm");
                    txt_file_end_alarm.setText(add5);
                    try {
                        add6 = rs.getBytes("file_picture");
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon(add6).getImage().getScaledInstance(txt_file_picture.getWidth(), txt_file_picture.getHeight(), Image.SCALE_DEFAULT));
                        txt_file_picture.setIcon(imageIcon);

                    } catch (Exception e) {
                    }
                      
                    AA5 = rs.getString("id");
                    AA4 = rs.getString("id");

                } else {
                    bt_save.setEnabled(false);
                    bt_delete.setEnabled(false);
                    bt_update.setEnabled(false);

                    txt_file_name.setText("");
                    txt_file_note.setText("");

                    txt_file_end_alarm.setText("-");
                   txt_file_picture.setIcon(new ImageIcon(getClass().getResource("/image/icons8_Picture_128px.png")));
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
            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql11 = " select id,file_name,file_note,file_end_data,file_end_alarm,user_save,date_save from tb_store_file where file_name like '" + txt_search.getText() + "%' or file_note like '" + txt_search.getText() + "%' or file_end_data like '" + txt_search.getText() + "%' or file_end_alarm like '" + txt_search.getText() + "%'";
                pst = conn.prepareStatement(sql11);
                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                String[] tableColumnsName = {"*", "إسم الملف", "هاتف الملف", "هاتف الملف", "حفظ بواسطة", "تاريخ الحفظ"};
                DefaultTableModel aModel = (DefaultTableModel) table.getModel();
                aModel.setColumnIdentifiers(tableColumnsName);
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
                }
                java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                table.setRowHeight(30);
                table.setSize(50, 50);
                table.setEditingRow(0);
                JTableHeader header = table.getTableHeader();
                header.setPreferredSize(new Dimension(30, 30));
                header.setBackground(new java.awt.Color(75, 137, 220));
                header.setForeground(new java.awt.Color(240, 240, 240));
                header.setFont(new java.awt.Font("Times New Roman", 1, 18));
                int colNo = rsmd.getColumnCount();
                while (rs.next()) {
                    Object[] objects = new Object[colNo];
                    for (int i = 0; i < colNo; i++) {
                        objects[i] = rs.getObject(i + 1);
                        table.getColumnModel().getColumn(i).setCellRenderer(renderer);
                        table.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));
                    }
                    aModel.addRow(objects);
                }
                table.setModel(aModel);
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
    }//GEN-LAST:event_txt_searchKeyReleased

    private void bt_newMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseEntered
        setColor(bt_new);
    }//GEN-LAST:event_bt_newMouseEntered

    private void bt_newMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseExited
        resetColor(bt_new);
    }//GEN-LAST:event_bt_newMouseExited

    private void bt_saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseEntered
        setColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseEntered

    private void bt_saveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseExited
        resetColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseExited

    private void bt_updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseEntered
        setColor(bt_update);
    }//GEN-LAST:event_bt_updateMouseEntered

    private void bt_updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseExited
        resetColor(bt_update);
    }//GEN-LAST:event_bt_updateMouseExited

    private void bt_deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseEntered
        setColor(bt_delete);
    }//GEN-LAST:event_bt_deleteMouseEntered

    private void bt_deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseExited
        resetColor(bt_delete);
    }//GEN-LAST:event_bt_deleteMouseExited

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed

    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged

    }//GEN-LAST:event_jPanel3MouseDragged

    private void bt_minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_minimizeActionPerformed
        //this.setState(1);
    }//GEN-LAST:event_bt_minimizeActionPerformed

    private void bt_secureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_secureActionPerformed
        System.gc();
        java.awt.Window window[] = java.awt.Window.getWindows();
        for (int i = 0; i < window.length; i++) {
            window[i].setVisible(false);
            window[i] = null;
        }
        new login_S().setVisible(true);
    }//GEN-LAST:event_bt_secureActionPerformed

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel6MousePressed

    private void jPanel6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel6MouseDragged

    private void bt_upload_logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_upload_logoActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        if (f == null) {
            JOptionPane.showMessageDialog(null, "لم يتم إختيار اي صورة !", "تنبية", JOptionPane.OK_OPTION);
            return;
        }
        filename = f.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(txt_file_picture.getWidth(), txt_file_picture.getHeight(), Image.SCALE_DEFAULT));
        txt_file_picture.setIcon(imageIcon);
        try {
            File image = new File(filename);
            FileInputStream fix = new FileInputStream(image);
            bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int number; (number = fix.read(buf)) != -1;) {
                bos.write(buf, 0, number);
            }
            s_image = bos.toByteArray();
        } catch (IOException e) {
        }
    }//GEN-LAST:event_bt_upload_logoActionPerformed

    private void bt_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exitActionPerformed
        this.dispose();
    }//GEN-LAST:event_bt_exitActionPerformed

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
            java.util.logging.Logger.getLogger(Store_File.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Store_File().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_exit;
    private javax.swing.JButton bt_minimize;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_secure;
    private javax.swing.JButton bt_update;
    private javax.swing.JButton bt_upload_logo;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable table;
    private com.toedter.calendar.JDateChooser txt_file_begin_data;
    private javax.swing.JTextField txt_file_end_alarm;
    private com.toedter.calendar.JDateChooser txt_file_end_data;
    private javax.swing.JTextField txt_file_name;
    private javax.swing.JTextField txt_file_note;
    private javax.swing.JLabel txt_file_picture;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
