// Table: tb_employees
// id emp_id emp_name emp_address emp_tel emp_Job_title emp_w_time emp_type_id emp_p_id_number emp_dat_hiring beg_date end_date status user_name

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.openide.util.Exceptions;

/**
 *
 * @author A7meD3H
 */
public class Employees extends javax.swing.JFrame {

    DBConn db = new DBConn();
    // NewClass tdb = new NewClass();

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
    String c_e_time = "";
    String dat = "";
    public String tablec;
    int xx, xy;

    /**
     * Creates new form ADU
     */
    public Employees() {
        initComponents();

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //تعديل
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        if (combo_Emp_W_Time.getSelectedIndex() == 0) {
            c_e_time = "صباحي";
        }

        bt_new.setBackground(new java.awt.Color(240, 240, 240));
        bt_save.setBackground(new java.awt.Color(240, 240, 240));
        bt_update.setBackground(new java.awt.Color(240, 240, 240));
        bt_delete.setBackground(new java.awt.Color(240, 240, 240));

        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);

        // تعديل
        Update_ta_emp();
        EmbteTex_IGHT_TO_LEFT();
        table.setShowGrid(true);
    }

    public void EmbteTex() {

        txt_Emp_Number.setText("");
        txt_Emp_Name.setText("");
        txt_Emp_Address.setText("");
        txt_Emp_Tel.setText("");
        txt_Emp_Job_title.setText("");
        txt_Emp_Pers_id_number.setText("");
        txt_Emp_Type_id.setText("");
        txt_search.setText("");
        combo_Emp_W_Time.setSelectedIndex(0);
        dat_Emp_hiring.setDate(null);
        dat = "";

    }

    public void EmbteTex_IGHT_TO_LEFT() {
        //.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Number.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_Emp_Name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_Emp_Address.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_Emp_Tel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_Emp_Job_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_Emp_Pers_id_number.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_Emp_Type_id.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_search.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    //Update tabel Employees
    private void Update_ta_emp() {

        ResultSet rs = db.selecte("tb_employees", new String[]{"id as \"الرقم\"", "emp_id as \" رقم الموظف\"", 
            "emp_name as  \"إسم الموظف \"", "emp_tel as  \" الهاتف\"", "emp_Job_title as  \"  الوظيفي\"", 
            "emp_w_time as  \" فترة الدوام\"", "emp_dat_hiring as  \" تاريخ التعين\""}, 
            new String[]{"id"}, new String[]{"9999"}, "!=", "and");
        // ResultSet rs= tdb.selecte("tb_employees", new String[]{"id as \"الرقم\"","emp_id as \" رقم الموظف\"","emp_name as  \"إسم الموظف \"","emp_tel as  \" الهاتف\"","emp_Job_title as  \"  الوظيفي\"","emp_w_time as  \" فترة الدوام\"","emp_dat_hiring as  \" تاريخ التعين\""}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");

        table.setModel(DbUtils.resultSetToTableModel(rs));

        db.tab(table);

       

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    
    public void getText(){
        txt_Emp_Tel.getText();
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
        txt_Emp_Job_title = new javax.swing.JTextField();
        txt_Emp_Type_id = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_Emp_Number = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_Emp_Name = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_Emp_Address = new javax.swing.JTextField();
        txt_Emp_Tel = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_Emp_Pers_id_number = new javax.swing.JTextField();
        combo_Emp_W_Time = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        dat_Emp_hiring = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        bt_exit = new javax.swing.JButton();
        bt_minimize = new javax.swing.JButton();
        bt_secure = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("إدارة الموظفين");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setUndecorated(true);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(93, 156, 236));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N
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
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bt_delete, bt_save, bt_update});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
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

        txt_Emp_Job_title.setBackground(new java.awt.Color(240, 240, 240));
        txt_Emp_Job_title.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Emp_Job_title.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Job_title.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        txt_Emp_Type_id.setBackground(new java.awt.Color(240, 240, 240));
        txt_Emp_Type_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Emp_Type_id.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Type_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("فترة الدوام");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("نوع اثبات الشخصية");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("المسمي الوظيفي");

        txt_Emp_Number.setBackground(new java.awt.Color(240, 240, 240));
        txt_Emp_Number.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Emp_Number.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_Emp_Number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Emp_NumberKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Emp_NumberKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Emp_NumberKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("رقم الموظف");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("إسم الموظف");

        txt_Emp_Name.setBackground(new java.awt.Color(240, 240, 240));
        txt_Emp_Name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Emp_Name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("السكن");

        txt_Emp_Address.setBackground(new java.awt.Color(240, 240, 240));
        txt_Emp_Address.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Emp_Address.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        txt_Emp_Tel.setBackground(new java.awt.Color(240, 240, 240));
        txt_Emp_Tel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Emp_Tel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Tel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_Emp_Tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Emp_TelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Emp_TelKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Emp_TelKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("رقم الهاتف");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("رقم اثبات الشخصية");

        txt_Emp_Pers_id_number.setBackground(new java.awt.Color(240, 240, 240));
        txt_Emp_Pers_id_number.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Emp_Pers_id_number.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Emp_Pers_id_number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        combo_Emp_W_Time.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combo_Emp_W_Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "صباحي", "مسائي" }));
        combo_Emp_W_Time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        combo_Emp_W_Time.setPreferredSize(new java.awt.Dimension(6, 28));
        combo_Emp_W_Time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_Emp_W_TimeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                combo_Emp_W_TimeMouseEntered(evt);
            }
        });
        combo_Emp_W_Time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_Emp_W_TimeActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("تاريخ التعيين");

        dat_Emp_hiring.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        dat_Emp_hiring.setDateFormatString("yyyy-MM-dd");
        dat_Emp_hiring.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dat_Emp_hiring.setMaxSelectableDate(new java.util.Date(2208985313000L));
        dat_Emp_hiring.setMinSelectableDate(new java.util.Date(946681313000L));
        dat_Emp_hiring.setMinimumSize(new java.awt.Dimension(35, 35));
        dat_Emp_hiring.setOpaque(false);
        dat_Emp_hiring.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dat_Emp_hiringMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("رقم الموظف / فترة الدوام / اسم الموظف");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_Emp_Job_title, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_Emp_W_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Emp_Type_id, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Emp_Pers_id_number, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dat_Emp_hiring, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(txt_Emp_Number, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_Emp_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_Emp_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_Emp_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel19)))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {combo_Emp_W_Time, dat_Emp_hiring, txt_Emp_Address, txt_Emp_Job_title, txt_Emp_Name, txt_Emp_Number, txt_Emp_Pers_id_number, txt_Emp_Tel, txt_Emp_Type_id});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(txt_Emp_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_Emp_Job_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(txt_Emp_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(combo_Emp_W_Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(txt_Emp_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_Emp_Type_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(txt_Emp_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txt_Emp_Pers_id_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel21)
                        .addComponent(dat_Emp_hiring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {combo_Emp_W_Time, dat_Emp_hiring, txt_Emp_Job_title, txt_Emp_Name, txt_Emp_Number, txt_Emp_Pers_id_number, txt_Emp_Tel, txt_Emp_Type_id});

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));
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

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("إدارة الموظفين");
        jPanel7.add(jLabel18);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bt_secure, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(bt_secure, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bt_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bt_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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

        EmbteTex();

        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);
        bt_save.setEnabled(true);
        
        
   



    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed

        dat = ((JTextField) dat_Emp_hiring.getDateEditor().getUiComponent()).getText();
        String mDate = "2015-12-31";
        String maDate = "2035-12-31";

        if (dat.compareTo(mDate) < 0 || maDate.compareTo(dat) < 0) {
            JOptionPane.showMessageDialog(null, "قد ادخلة تاريخ تعين خطاء", "خطاء", JOptionPane.ERROR_MESSAGE);

            dat_Emp_hiring.setDate(null);
            dat = "";

        } else if (txt_Emp_Number.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل رقم الوظف", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Number.requestFocus();
        } else if (txt_Emp_Name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل اسم الموظف", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Name.requestFocus();
        } else if (txt_Emp_Address.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل السكن", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Address.requestFocus();
        } else if (txt_Emp_Tel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  رقم الهاتف ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Tel.requestFocus();
        } else if (txt_Emp_Type_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  نوع اثبات الشخصية ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Type_id.requestFocus();
        } else if (txt_Emp_Pers_id_number.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  رقم اثبات الشخصية ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Pers_id_number.requestFocus();
        } else if (dat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل تاريخ  التعيين ", "خطاء", JOptionPane.ERROR_MESSAGE);

        } else if (txt_Emp_Job_title.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  المسمي الوظيفي ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Job_title.requestFocus();
        } else {
            try {

                ResultSet rs = db.selecte("tb_employees", new String[]{"emp_id"}, new String[]{"emp_id"}, new String[]{txt_Emp_Number.getText()}, "=", "and");
                // ResultSet rs= tdb.selecte("tb_employees", new String[]{"emp_id"}, new String[]{"emp_id"}, new String[]{txt_Emp_Number.getText()}, "=", "and");

                if (rs.next()) {

                    JOptionPane.showMessageDialog(null, "رقم الموظف موجود", "خطاء", JOptionPane.ERROR_MESSAGE);
                    txt_Emp_Number.requestFocus();

                } else {

                    //String[] values ={null,txt_Emp_Number.getText(),txt_Emp_Name.getText(),txt_Emp_Address.getText(),
                    //   txt_Emp_Tel.getText(),txt_Emp_Job_title.getText(),c_e_time,txt_Emp_Type_id.getText(),
                    //    txt_Emp_Pers_id_number.getText(),dat,null,null,"0","gad"};
                    int r = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ البيانات", "حفظ بيانات", JOptionPane.YES_NO_OPTION);

                    if (r == JOptionPane.YES_OPTION) {

                        int res = db.add("tb_employees", new String[]{null, txt_Emp_Number.getText(), txt_Emp_Name.getText(), txt_Emp_Address.getText(),
                            txt_Emp_Tel.getText(), txt_Emp_Job_title.getText(), combo_Emp_W_Time.getSelectedItem().toString(),
                            txt_Emp_Type_id.getText(), txt_Emp_Pers_id_number.getText(), dat, null, null, "0", "gad"}, null);
                        // int res= tdb.add("tb_employees", values,null);

                        if (res == -1) {
                            JOptionPane.showMessageDialog(null, "تم حفظ البيانات بنجاح");

                            //تعديل
                            Update_ta_emp();
                            EmbteTex();

                        } else {
                            JOptionPane.showMessageDialog(null, "لم تتة الاضافه", "خطاء", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }

            try {
                db.conn().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

    }//GEN-LAST:event_bt_saveActionPerformed

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed

        int row = table.getSelectedRow();
        String tableClick = (table.getValueAt(row, 0).toString());

        dat = ((JTextField) dat_Emp_hiring.getDateEditor().getUiComponent()).getText();

        String mDate = "2015-12-31";
        String maDate = "2035-12-31";

        if (dat.compareTo(mDate) < 0 || maDate.compareTo(dat) < 0) {
            JOptionPane.showMessageDialog(null, "قد ادخلة تاريخ تعين خطاء", "خطاء", JOptionPane.ERROR_MESSAGE);

            dat_Emp_hiring.setDate(null);
            dat = "";

        } else if (txt_Emp_Number.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل رقم الوظف", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Number.requestFocus();
        } else if (txt_Emp_Name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل اسم الموظف", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Name.requestFocus();
        } else if (txt_Emp_Address.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل السكن", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Address.requestFocus();
        } else if (txt_Emp_Tel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  رقم الهاتف ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Tel.requestFocus();
        } else if (txt_Emp_Type_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  نوع اثبات الشخصية ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Type_id.requestFocus();
        } else if (txt_Emp_Pers_id_number.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  رقم اثبات الشخصية ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Pers_id_number.requestFocus();
        } else if (dat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل تاريخ  التعيين ", "خطاء", JOptionPane.ERROR_MESSAGE);

        } else if (txt_Emp_Job_title.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل  المسمي الوظيفي ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_Emp_Job_title.requestFocus();
        } else {

            int r = JOptionPane.showConfirmDialog(null, "هل تريد التعديل", "تنبيه", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                String[] updateFail = {"emp_id", "emp_name", "emp_address", "emp_tel", "emp_Job_title", "emp_w_time", "emp_type_id", "emp_p_id_number", "emp_dat_hiring", "end_date"};

                String[] updatevalus = {txt_Emp_Number.getText(), txt_Emp_Name.getText(), txt_Emp_Address.getText(),
                txt_Emp_Tel.getText(), txt_Emp_Job_title.getText(), combo_Emp_W_Time.getSelectedItem().toString(),
                txt_Emp_Type_id.getText(), txt_Emp_Pers_id_number.getText(), dat, null};

                int res = db.update("tb_employees", updateFail, updatevalus, new String[]{"id"}, new String[]{tableClick}, null, "=", "and");

                if (res == -1) {

                    JOptionPane.showMessageDialog(null, "تم التعديل");
                    Update_ta_emp();
                    EmbteTex();

                    bt_delete.setEnabled(false);
                    bt_update.setEnabled(false);
                    txt_Emp_Number.setEnabled(true);
                    bt_save.setEnabled(true);
                    bt_new.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "  لم يتم التعديل" + res);
                }

            }
            bt_new.setEnabled(true);
        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed

        int row = table.getSelectedRow();
        String tableClick = (table.getValueAt(row, 0).toString());
        int res = JOptionPane.showConfirmDialog(null, "هل تريد المسح", "تنبيه", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            int r = db.delete("tb_employees", new String[]{"id"}, new String[]{tableClick}, "=", "and");
            if (r == -1) {

                JOptionPane.showMessageDialog(null, " تم المسح بنجاح");

                Update_ta_emp();
                EmbteTex();
                bt_delete.setEnabled(false);
                bt_update.setEnabled(false);
                bt_save.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "no");
            }

        }
        bt_new.setEnabled(true);

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_bt_deleteActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        int row = table.getSelectedRow();
        String tableClick = (table.getValueAt(row, 0).toString());
        ResultSet rs = db.selecte("tb_employees", new String[]{"*"}, new String[]{"id"}, new String[]{tableClick}, "=", "and");

        try {
            if (rs.next()) {

                txt_Emp_Number.setText(String.valueOf(rs.getInt("emp_id")));
                txt_Emp_Name.setText(rs.getString("emp_name"));
                txt_Emp_Address.setText(rs.getString("emp_address"));
                txt_Emp_Tel.setText(rs.getString("emp_tel"));
                txt_Emp_Job_title.setText(rs.getString("emp_Job_title"));
                txt_Emp_Type_id.setText(rs.getString("emp_type_id"));
                txt_Emp_Pers_id_number.setText(rs.getString("emp_p_id_number"));
                dat_Emp_hiring.setDate(rs.getDate("emp_dat_hiring"));

                if (rs.getString("emp_w_time").equals("صباحي")) {
                    combo_Emp_W_Time.setSelectedIndex(0);
                } else {
                    combo_Emp_W_Time.setSelectedIndex(1);
                }

                bt_delete.setEnabled(true);
                bt_update.setEnabled(true);
                txt_Emp_Number.setEnabled(false);
                bt_save.setEnabled(false);
                bt_new.setEnabled(false);
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e, "خطاء", JOptionPane.ERROR_MESSAGE);
        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_tableMouseClicked

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        String[] choosenFail = {"id as \"الرقم\"", "emp_id as \" رقم الموظف\"", "emp_name as  \"إسم الموظف \"", "emp_tel as  \" الهاتف\"", "emp_Job_title as  \"  الوظيفي\"", "emp_w_time as  \" فترة الدوام\"", "emp_dat_hiring as  \" تاريخ التعين\""};

        ResultSet rs = db.selecte("tb_employees", choosenFail, new String[]{"emp_id", "emp_w_time", "emp_name"}, new String[]{txt_search.getText() + "%", txt_search.getText() + "%", txt_search.getText() + "%"}, "like", "or");
        table.setModel(DbUtils.resultSetToTableModel(rs));

        //  DBConn.setTableR(table);
        db.tab(table);

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_txt_searchKeyReleased

    private void bt_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exitActionPerformed

        this.dispose();

    }//GEN-LAST:event_bt_exitActionPerformed

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
        this.setState(1);
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

    private void combo_Emp_W_TimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_Emp_W_TimeMouseClicked

    }//GEN-LAST:event_combo_Emp_W_TimeMouseClicked

    private void combo_Emp_W_TimeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_Emp_W_TimeMouseEntered

    }//GEN-LAST:event_combo_Emp_W_TimeMouseEntered

    private void combo_Emp_W_TimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_Emp_W_TimeActionPerformed
        // TODO add your handling code here:

      

    }//GEN-LAST:event_combo_Emp_W_TimeActionPerformed

    private void txt_Emp_NumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Emp_NumberKeyReleased

    }//GEN-LAST:event_txt_Emp_NumberKeyReleased

    private void txt_Emp_TelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Emp_TelKeyReleased
        // TODO add your handling code here: 
    }//GEN-LAST:event_txt_Emp_TelKeyReleased

    private void txt_Emp_NumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Emp_NumberKeyPressed
        // TODO add your handling code here:      
    }//GEN-LAST:event_txt_Emp_NumberKeyPressed

    private void txt_Emp_TelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Emp_TelKeyPressed
        // TODO add your handling code here:     
    }//GEN-LAST:event_txt_Emp_TelKeyPressed

    private void dat_Emp_hiringMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dat_Emp_hiringMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dat_Emp_hiringMouseClicked

    private void txt_Emp_NumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Emp_NumberKeyTyped
        class_number cc = new class_number();
        cc.Just_Number(txt_Emp_Number, evt);
    }//GEN-LAST:event_txt_Emp_NumberKeyTyped

    private void txt_Emp_TelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Emp_TelKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Just_Number(txt_Emp_Tel, evt);


    }//GEN-LAST:event_txt_Emp_TelKeyTyped

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
            java.util.logging.Logger.getLogger(Employees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employees().setVisible(true);
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
    private javax.swing.JComboBox<String> combo_Emp_W_Time;
    private com.toedter.calendar.JDateChooser dat_Emp_hiring;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_Emp_Address;
    private javax.swing.JTextField txt_Emp_Job_title;
    private javax.swing.JTextField txt_Emp_Name;
    private javax.swing.JTextField txt_Emp_Number;
    private javax.swing.JTextField txt_Emp_Pers_id_number;
    private javax.swing.JTextField txt_Emp_Tel;
    private javax.swing.JTextField txt_Emp_Type_id;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
//combo_Emp_W_Time

}
