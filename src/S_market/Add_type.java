/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
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
public class Add_type extends javax.swing.JDialog {

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

    /**
     * Creates new form ADU
     */
    public Add_type() {
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
            if (conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select id,name,user_save,date_save from add_type";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            String[] tableColumnsName = {"*", "إسم الوحدة", "حفظ بواسطة", "تاريخ الحفظ"};

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

                //setTableAlignmentValue(table, i);
            }

            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            table.setRowHeight(30);

            table.setSize(50, 50);
            table.setEditingRow(0);
            JTableHeader header = table.getTableHeader();
            header.setPreferredSize(new Dimension(30, 30));
            header.setBackground(new java.awt.Color(75,137,220));
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
        txt_fname = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        bt_exit3 = new javax.swing.JButton();
        bt_minimize2 = new javax.swing.JButton();
        bt_secure3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("إدارة الوحدات");
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
        bt_new.setForeground(new java.awt.Color(93, 156, 236));
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
        bt_save.setForeground(new java.awt.Color(93, 156, 236));
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
        bt_update.setForeground(new java.awt.Color(93, 156, 236));
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
        bt_delete.setForeground(new java.awt.Color(93, 156, 236));
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
                .addContainerGap()
                .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_save, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_delete, bt_new, bt_save, bt_update});

        txt_fname.setBackground(new java.awt.Color(240, 240, 240));
        txt_fname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_fname.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_fname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75, 137, 220)));

        table.setBackground(new java.awt.Color(240, 240, 240));
        table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        table.setForeground(new java.awt.Color(75, 137, 220));
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
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Search_24px_1.png"))); // NOI18N
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
        jLabel12.setText("إسم الوحدة");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel11.setBackground(new java.awt.Color(216, 112, 173));
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

        jPanel12.setBackground(new java.awt.Color(216, 112, 173));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("إدارة الوحدات");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed
        txt_fname.setText("");
        txt_search.setText("");

        Update_table();
        bt_save.setEnabled(true);
        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);

    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed
        try {
            if (conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql22 = " select * from add_type where name= '" + txt_fname.getText() + "'";
            pst = conn.prepareStatement(sql22);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "إسم الوحدة " + txt_fname.getText() + " مسجل مسبقاً", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
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

        if ((txt_fname.getText().matches("")) || (txt_fname.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "قم بإدخال إسم الوحدة للحفظ !");
        } else {

            int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ " + txt_fname.getText(), "حفظ بيانات", JOptionPane.YES_NO_OPTION);
            if (Ahmed == 0) {

                try {
                    if (conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                        conn = DB.jave_db();
                    }
                    String sql = "insert into add_type "
                            + "(name,user_save,date_save)" + " values (?,?,?)";

                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txt_fname.getText());
                    pst.setString(2, "Ahmed");
                    Date date = GregorianCalendar.getInstance().getTime();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String datestring = df.format(date);
                    pst.setString(3, datestring);
                    pst.execute();
                    Update_table();
                    String add2 = txt_fname.getText();
                    txt_fname.setText("");
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
            if (conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql1 = "select * from add_type where name ='" + txt_fname.getText() + "' AND id !='" + AA5 + "'";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, " تم تسجيل " + txt_fname.getText() + " من قبل ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
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
        if ((txt_fname.getText().matches("")) || (txt_fname.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "قم بإدخال إسم الوحدة للتعديل !");
            return;

        }

        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد تعديل " + A4, "تعديل بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {
                String add1 = txt_fname.getText();
                String add2 = "Ahmed";

                Date date = GregorianCalendar.getInstance().getTime();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String datestring = df.format(date);
                String add3 = datestring;
                if(conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()){conn = DB.jave_db();}
                String sql = "update  add_type set  name= '" + add1 + "',user_save='" + add2 + "',date_save='" + add3 + "'where id ='" + AA4 + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                Update_table();

                bt_save.setEnabled(true);
                bt_delete.setEnabled(false);
                bt_update.setEnabled(false);

                txt_fname.setText("");

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
            txt_fname.setText("");
        }
    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حذف  " + txt_fname.getText(), "حذف بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {if(conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()){conn = DB.jave_db();}
                String sql = "delete from add_type where id= ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, AA4);
                pst.execute();
                Update_table();

                bt_save.setEnabled(true);
                bt_delete.setEnabled(false);
                bt_update.setEnabled(false);

                String add2 = txt_fname.getText();

                txt_fname.setText("");

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

            txt_fname.setText("");

            int row = table.getSelectedRow();

            tablec = (table.getModel().getValueAt(row, 0).toString());

            if (conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from add_type where id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tablec);
            rs = pst.executeQuery();
            if (rs.next()) {
                //  JOptionPane.showMessageDialog(null, "الرجاء");
                // }else{
                String add1 = rs.getString("name");
                txt_fname.setText(add1);

                AA4 = rs.getString("id");
                AA5 = rs.getString("id");

                A4 = txt_fname.getText();

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

    }//GEN-LAST:event_tableMouseClicked

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        if (txt_search.getText().isEmpty()) {

            Update_table();
            bt_save.setEnabled(true);
            bt_delete.setEnabled(false);
            bt_update.setEnabled(false);
            txt_fname.setText("");

        } else {

            try {
                if (conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = " select * from add_type where name like '" + txt_search.getText() + "%'";
                table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                bt_save.setEnabled(false);
                bt_delete.setEnabled(true);
                bt_update.setEnabled(true);
                if (rs.next()) {

                    String add1 = rs.getString("name");
                    txt_fname.setText(add1);
                    AA5 = rs.getString("id");
                    AA4 = rs.getString("id");

                } else {
                    bt_save.setEnabled(false);
                    bt_delete.setEnabled(false);
                    bt_update.setEnabled(false);

                    txt_fname.setText("");
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
                if (conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql11 = " select id,name,user_save,date_save from add_type where name like '" + txt_search.getText() + "%'";
                pst = conn.prepareStatement(sql11);
                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                String[] tableColumnsName = {"*", "إسم الوحدة", "حفظ بواسطة", "تاريخ الحفظ"};
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
                header.setBackground(new java.awt.Color(75,137,220));
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
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void bt_exit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exit3ActionPerformed
        Add_type.this.dispose();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        this.dispose();
        this.dispose();
    }//GEN-LAST:event_bt_exit3ActionPerformed

    private void bt_minimize2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_minimize2ActionPerformed
        //this.setState(1);
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
            java.util.logging.Logger.getLogger(Add_type.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_type().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_exit3;
    private javax.swing.JButton bt_minimize2;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_secure3;
    private javax.swing.JButton bt_update;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
