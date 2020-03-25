/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
import static S_market.Buying.conn;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author A7meD3H
 */
public class Producer_Setting extends javax.swing.JDialog {

     DBConn db= new DBConn();
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
    String saveLoc = null;
    Boolean isPathChosen = false;
    private ImageIcon format = null;
    String filename = null;
    byte[] s_image = null;
    byte[] add2 = null;
    ByteArrayOutputStream bos;
    int xx, xy;

    /**
     * Creates new form ADU
     */
    public Producer_Setting() {
        initComponents();   setModal(true);

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);

       

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);
        Update_table();

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
            
            String sql = "select producer_id,producer_name,type_name,company_make,group_name,section_name,type_name1,ref,min_buy,min_sale,user_save,beg_date from producer";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "إسم الصنف", "الشركة المصنعة", "اسم المجموعه", "الإسم القسم", "نوع الصنف", "الرف", "سعر الشراء", "سعر البيع","أسم الحفظ","تاريخ الحفظ"};

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

        myChooser = new javax.swing.JFileChooser();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        bt_delete = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_new = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tex_type_name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tex_company_make = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tex_producer_name = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tex_producer_id = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        bt_exit3 = new javax.swing.JButton();
        bt_minimize2 = new javax.swing.JButton();
        bt_secure3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

        myChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("إدارة المنتجات");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

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

        jPanel6.setBackground(new java.awt.Color(93, 156, 236));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "العمليات المتاحة", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(240, 240, 240))); // NOI18N

        bt_delete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_delete.setForeground(new java.awt.Color(93, 156, 236));
        bt_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Delete_24px_1.png"))); // NOI18N
        bt_delete.setText("حذف");
        bt_delete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_delete.setContentAreaFilled(false);
        bt_delete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_delete.setOpaque(true);
        bt_delete.setPreferredSize(new java.awt.Dimension(59, 34));
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

        bt_update.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_update.setForeground(new java.awt.Color(93, 156, 236));
        bt_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Available_Updates_24px.png"))); // NOI18N
        bt_update.setText("تعديل");
        bt_update.setToolTipText("");
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

        bt_new.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_new.setForeground(new java.awt.Color(93, 156, 236));
        bt_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Add_New_24px_1.png"))); // NOI18N
        bt_new.setText("إضافة منتج");
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Search_24px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        jLabel13.setBackground(new java.awt.Color(46, 204, 113));
        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(240, 240, 240));
        jLabel13.setText("البحث حسب رقم المنتج - إسم المنتج - إسم الصنف - إسم الشركة المصنعة-إسم المجموعة-إسم القسم - نوع الصنف - الرف");

        tex_type_name.setBackground(new java.awt.Color(240, 240, 240));
        tex_type_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_type_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_type_name.setText("-");
        tex_type_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_type_name.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_type_name.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("الشركة المصنعة");

        tex_company_make.setBackground(new java.awt.Color(240, 240, 240));
        tex_company_make.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_company_make.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_company_make.setText("-");
        tex_company_make.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_company_make.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_company_make.setEnabled(false);
        tex_company_make.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_company_makeActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("إسم الصنف");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("إسم المنتج");

        tex_producer_name.setBackground(new java.awt.Color(240, 240, 240));
        tex_producer_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_producer_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_producer_name.setText("-");
        tex_producer_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_producer_name.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_producer_name.setEnabled(false);
        tex_producer_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_producer_nameActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("رقم المنتج");

        tex_producer_id.setBackground(new java.awt.Color(240, 240, 240));
        tex_producer_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_producer_id.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_producer_id.setText("-");
        tex_producer_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_producer_id.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tex_producer_id.setEnabled(false);
        tex_producer_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_producer_idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tex_company_make)
                            .addComponent(tex_producer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tex_type_name, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(tex_producer_id, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel11))
                        .addGap(150, 150, 150))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tex_producer_id, tex_type_name});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel8});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel16});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tex_producer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(tex_producer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tex_type_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tex_company_make, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tex_company_make, tex_producer_id, tex_producer_name, tex_type_name});

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
        jLabel21.setText("إدارة المنتجات");
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void bt_deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseEntered
        setColor(bt_delete);
    }//GEN-LAST:event_bt_deleteMouseEntered

    private void bt_deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseExited
        resetColor(bt_delete);
    }//GEN-LAST:event_bt_deleteMouseExited

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        if (AA4 == null) {
            JOptionPane.showMessageDialog(null, "قم بإختيار المنتج للحذف");
            return;
        }

        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حذف  " + tex_producer_id.getText(), "حذف بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = "delete from producer where producer_id= ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, AA4);
                pst.execute();
                Update_table();

                bt_delete.setEnabled(false);
                bt_update.setEnabled(false);

                String add = tex_producer_name.getText();
            
                tex_producer_name.setText("");
                tex_producer_id.setText("");
                tex_company_make.setText("");
                tex_type_name.setText("");
                txt_search.setText("");

                JOptionPane.showMessageDialog(null, "تم حذف بيانات" + " " + add + " " + "بنجاح");

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

    private void bt_updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseEntered
        setColor(bt_update);
    }//GEN-LAST:event_bt_updateMouseEntered

    private void bt_updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseExited
        resetColor(bt_update);
    }//GEN-LAST:event_bt_updateMouseExited

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed
        if (AA5 == null) {
            JOptionPane.showMessageDialog(null, "قم بإختيار المنتج للتعديل");
            return;
        } else {
            Producer_Edit producer_Edit = new Producer_Edit(AA5);
            producer_Edit.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_bt_updateActionPerformed

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed

    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged

    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered

    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained

    }//GEN-LAST:event_jPanel3FocusGained

    private void jPanel3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusLost

    }//GEN-LAST:event_jPanel3FocusLost

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown

    }//GEN-LAST:event_jPanel3ComponentShown

    private void bt_newMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseEntered
        setColor(bt_new);
    }//GEN-LAST:event_bt_newMouseEntered

    private void bt_newMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseExited
        resetColor(bt_new);
    }//GEN-LAST:event_bt_newMouseExited

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed
        new Producer().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bt_newActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        try {

            bt_delete.setEnabled(true);
            bt_update.setEnabled(true);

          
            tex_producer_name.setText("");
            tex_producer_id.setText("");
            tex_company_make.setText("");
            tex_type_name.setText("");
            int row = table.getSelectedRow();

            tablec = (table.getModel().getValueAt(row, 0).toString());

            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from producer where producer_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tablec);
            rs = pst.executeQuery();
            if (rs.next()) {
                //  JOptionPane.showMessageDialog(null, "الرجاء");
                // }else{
                String add1 = rs.getString("producer_id");
                tex_producer_id.setText(add1);

                String add22 = rs.getString("producer_name");
                tex_producer_name.setText(add22);

                String add3 = rs.getString("type_name");
                tex_type_name.setText(add3);

                String add4 = rs.getString("company_make");
                tex_company_make.setText(add4);

               

                AA4 = rs.getString("producer_id");
                AA5 = rs.getString("producer_id");

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

        try{
        
         String[] choosenFail = {"producer_id as \"رقم المنتج\"","producer_name as \"أسم المنتج\"",
        "type_name as \"إسم الصنف\"","company_make as \"الشركة المصنعة\"","group_name as \"اسم المجموعه\"",
        "section_name as \" الإسم القسم\"","type_name1 as \"نوع الصنف\"","ref as \" الرف\"","min_buy as \"سعرالشراء\"","min_sale as \"سعرالبيع\"",
         "user_save as \" أسم الحفظ\"","beg_date as \"تاريخ الحفظ\""};
        
         ResultSet rs= db.selecte("producer", choosenFail, new String[]{"producer_id","producer_name","type_name",
         "company_make","group_name","section_name","type_name1","ref"},
                 new String[]{txt_search.getText()+"%",txt_search.getText()+"%",txt_search.getText()+"%",
                 txt_search.getText()+"%",txt_search.getText()+"%",txt_search.getText()+"%",txt_search.getText()+"%",
                 txt_search.getText()+"%"}, "like", "or");
         table.setModel(DbUtils.resultSetToTableModel(rs));
 
         db.tab(table);
          
                try {
        db.conn().close();
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex);
    }
        
        }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex, "يوجد خطاء", JOptionPane.OK_OPTION);
        } 
        
        
        /*
        if (txt_search.getText().isEmpty()) {
            Update_table();

            tex_producer_name.setText("");
            tex_producer_id.setText("");
            tex_company_make.setText("");
            tex_type_name.setText("");

            bt_new.setEnabled(true);
            bt_delete.setEnabled(false);
            bt_update.setEnabled(false);

        } else {

            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = " select * from producer where producer_id like '" + txt_search.getText() + "%' or producer_name like '" + txt_search.getText() + "%' or type_name like '" + txt_search.getText() + "%' or company_make like '" + txt_search.getText() + "%' or group_name like '" + txt_search.getText() + "%' or section_name like '" + txt_search.getText() + "%' or type_name1 like '" + txt_search.getText() + "%' or ref like '" + txt_search.getText() + "%' or min_buy like '" + txt_search.getText() + "%' or min_sale  like '" + txt_search.getText() + "%'";
                table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

              

                tex_producer_name.setText("");
                tex_producer_id.setText("");
                tex_company_make.setText("");
                tex_type_name.setText("");
                bt_delete.setEnabled(true);
                bt_update.setEnabled(true);
                if (rs.next()) {

                    String add1 = rs.getString("producer_id");
                    tex_producer_id.setText(add1);

                    String add22 = rs.getString("producer_name");
                    tex_producer_name.setText(add22);

                    String add3 = rs.getString("type_name");
                    tex_type_name.setText(add3);

                    String add4 = rs.getString("company_make");
                    tex_company_make.setText(add4);

                  

                    AA5 = rs.getString("producer_id");
                    AA4 = rs.getString("producer_id");

                } else {
                 

                    tex_producer_name.setText("");
                    tex_producer_id.setText("");
                    tex_company_make.setText("");
                    tex_type_name.setText("");

                    bt_update.setEnabled(false);
                    bt_delete.setEnabled(false);
                    bt_update.setEnabled(false);

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
                String sql11 = " select * from producer where producer_id like '" + txt_search.getText() + "%' or producer_name like '" + txt_search.getText() + "%' or type_name like '" + txt_search.getText() + "%' or company_make like '" + txt_search.getText() + "%' or group_name like '" + txt_search.getText() + "%' or section_name like '" + txt_search.getText() + "%' or type_name1 like '" + txt_search.getText() + "%' or ref like '" + txt_search.getText() + "%' or min_buy like '" + txt_search.getText() + "%' or min_sale  like '" + txt_search.getText() + "%'";
                pst = conn.prepareStatement(sql11);
                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "إسم الصنف", "الشركة المصنعة", "اسم المجموعه", "الإسم القسم", "نوع الصنف", "الرف", "سعر الشراء", "سعر البيع","أسم الحفظ","تاريخ الحفظ"};
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
        */
    }//GEN-LAST:event_txt_searchKeyReleased

    private void tex_company_makeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_company_makeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_company_makeActionPerformed

    private void tex_producer_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_producer_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_producer_nameActionPerformed

    private void tex_producer_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_producer_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_producer_idActionPerformed

    private void bt_exit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exit3ActionPerformed
        Producer_Setting.this.dispose();
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
            java.util.logging.Logger.getLogger(Producer_Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Producer_Setting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_exit3;
    private javax.swing.JButton bt_minimize2;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_secure3;
    private javax.swing.JButton bt_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JFileChooser myChooser;
    private javax.swing.JTable table;
    private javax.swing.JTextField tex_company_make;
    private javax.swing.JTextField tex_producer_id;
    private javax.swing.JTextField tex_producer_name;
    private javax.swing.JTextField tex_type_name;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
