/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import net.proteanit.sql.DbUtils;
import org.openide.util.Exceptions;

/**
 *
 * @author Ahmedfg
 */
public class T_Users extends javax.swing.JFrame {

    DBConn db = new DBConn();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    /**
     * Creates new form T_Users
     */
    int c_d = 0;
    int c_r = 0;
    int c_e = 0;
    int c_u = 0;
    int c_dam = 0;
    int c_p = 0;
    int c_exp = 0;
    int c_rep = 0;
    int c_Acc = 0;
    int c_m_p = 0;
    int check_w_sa = 0;
    int c_In = 0;
    int c_b_pr = 0;
    int c_Units = 0;
    int c_ca_de_gr = 0;
    int c_bar = 0;
    String ch = "";
    String ch_job = "";
    String username = "";
    int xx, xy;
    int id_em = 0;

    public T_Users() {
        initComponents();

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //تعديل
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        bt_new.setBackground(new java.awt.Color(240, 240, 240));
        bt_save.setBackground(new java.awt.Color(240, 240, 240));
        bt_update.setBackground(new java.awt.Color(240, 240, 240));
        bt_delete.setBackground(new java.awt.Color(240, 240, 240));

        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);
        tex_titel.setEnabled(false);

        combo_Emp();
        Update_ta_user();
        Update_Section();
        check_false();

    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    public void combo_Emp() {

        ResultSet rs = db.selecte("tb_employees", new String[]{"emp_name", "emp_Job_title"}, new String[]{"id"},
                new String[]{"999999999999999999991000000000"}, "!=", "and");
        try {
            while (rs.next()) {
                combo_Emp.addItem(rs.getString("emp_name"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            DBConn.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public int id_Emp(String chs) {
        int id_Em = 0;
        ResultSet rs = db.selecte("tb_employees", new String[]{"id"}, new String[]{"emp_name"}, new String[]{chs}, "=", "and");
        try {
            if (rs.next()) {

                id_Em = rs.getInt("id");
            }
            return id_Em;

        } catch (SQLException ex) {
            try {
                DBConn.conn().close();
            } catch (SQLException ex1) {
                Exceptions.printStackTrace(ex1);
            }
            return 0;
        }

    }

    private void Update_Section() {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from tb_section";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("section_name");
                comb_job.addItem(name);

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

    public int id_Section(String chs) {
        int id_Em = 0;
        ResultSet rs = db.selecte("tb_section", new String[]{"id"}, new String[]{"section_name"},
                new String[]{chs}, "=", "and");
        try {
            if (rs.next()) {

                id_Em = rs.getInt("id");
            }
            return id_Em;

        } catch (SQLException ex) {
            try {
                DBConn.conn().close();
            } catch (SQLException ex1) {
                Exceptions.printStackTrace(ex1);
            }
            return 0;
        }

    }

    public String emp_Job(int id_emp) {

        ResultSet rs = db.selecte("tb_employees", new String[]{"emp_Job_title"}, new String[]{"id"},
                new String[]{String.valueOf(id_emp)}, "=", "and");
        String emp_Job = "";
        try {
            if (rs.next()) {
                emp_Job = rs.getString("emp_Job_title");
            }
            return emp_Job;
        } catch (SQLException ex) {
            try {
                DBConn.conn().close();
            } catch (SQLException ex1) {
                Exceptions.printStackTrace(ex1);
            }
            return null;
        }

    }

    private void Update_ta_user() {

        ResultSet rs = db.selecte("tb_user", new String[]{"id as \"الرقم\"", "name_emp as \" أسم الموظف\"",
            "job as  \" الموظف\"", "section_name as  \"القسم\"", "username as  \"أسم المستخدم\"",
            "beg_dat as  \"تاريخ الحفظ\"", "usernames as  \"أسم المدخل\""}, new String[]{"id"},
                new String[]{"999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"},
                "!=", "and");

        table.setModel(DbUtils.resultSetToTableModel(rs));

        db.tab(table);

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void EmbteTex() {
        combo_Emp.setSelectedIndex(0);
        tex_titel.setText("");
        comb_job.setSelectedIndex(0);
        tex_user.setText("");
        txt_pass.setText("");
        check_Discount.setSelected(false);
        check_Retriever.setSelected(false);
        check_Emp.setSelected(false);
        check_user.setSelected(false);
        check_Damaged.setSelected(false);
        check_Payouts.setSelected(false);
        check_Expenses.setSelected(false);
        check_Rep.setSelected(false);
        check_Accounts.setSelected(false);
        check_Add_product_manage_products.setSelected(false);
        check_Wholesale_sales.setSelected(false);
        check_Inventory.setSelected(false);
        check_buying_process.setSelected(false);
        check_Units.setSelected(false);
        check_Cat_Dep_Gro.setSelected(false);
        check_Baecod.setSelected(false);
        c_d = 0;
        c_r = 0;
        c_e = 0;
        c_u = 0;
        c_dam = 0;
        c_p = 0;
        c_exp = 0;
        c_rep = 0;
        c_Acc = 0;
        c_m_p = 0;
        check_w_sa = 0;
        c_In = 0;
        c_b_pr = 0;
        c_Units = 0;
        c_ca_de_gr = 0;
        c_bar = 0;

    }

    public void check_false() {

        check_Discount.setEnabled(false);
        check_Retriever.setEnabled(false);
        check_Emp.setEnabled(false);
        check_user.setEnabled(false);
        check_Damaged.setEnabled(false);
        check_Payouts.setEnabled(false);
        check_Expenses.setEnabled(false);
        check_Rep.setEnabled(false);
        check_Accounts.setEnabled(false);
        check_Add_product_manage_products.setEnabled(false);
        check_Wholesale_sales.setEnabled(false);
        check_Inventory.setEnabled(false);
        check_buying_process.setEnabled(false);
        check_Units.setEnabled(false);
        check_Cat_Dep_Gro.setEnabled(false);
        check_Baecod.setEnabled(false);

    }

    public void check_true() {

        check_Discount.setEnabled(true);
        check_Retriever.setEnabled(true);
        check_Emp.setEnabled(true);
        check_user.setEnabled(true);
        check_Damaged.setEnabled(true);
        check_Payouts.setEnabled(true);
        check_Expenses.setEnabled(true);
        check_Rep.setEnabled(true);
        check_Accounts.setEnabled(true);
        check_Add_product_manage_products.setEnabled(true);
        check_Wholesale_sales.setEnabled(true);
        check_Inventory.setEnabled(true);
        check_buying_process.setEnabled(true);
        check_Units.setEnabled(true);
        check_Cat_Dep_Gro.setEnabled(true);
        check_Baecod.setEnabled(true);

    }
    public void falsetex(){
        comb_job.setEnabled(false);
        tex_user.setEnabled(false);
       txt_pass.setEnabled(false);
    }
    
     public void truetex(){
        comb_job.setEnabled(true);
        tex_user.setEnabled(true);
       txt_pass.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        bt_exit1 = new javax.swing.JButton();
        bt_minimize = new javax.swing.JButton();
        bt_secure = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combo_Emp = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comb_job = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tex_user = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_pass = new javax.swing.JPasswordField();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        check_Discount = new javax.swing.JCheckBox();
        check_Rep = new javax.swing.JCheckBox();
        check_Damaged = new javax.swing.JCheckBox();
        check_Payouts = new javax.swing.JCheckBox();
        check_user = new javax.swing.JCheckBox();
        check_Emp = new javax.swing.JCheckBox();
        check_Retriever = new javax.swing.JCheckBox();
        check_Expenses = new javax.swing.JCheckBox();
        check_Cat_Dep_Gro = new javax.swing.JCheckBox();
        check_Add_product_manage_products = new javax.swing.JCheckBox();
        check_Inventory = new javax.swing.JCheckBox();
        check_Baecod = new javax.swing.JCheckBox();
        check_Accounts = new javax.swing.JCheckBox();
        check_Wholesale_sales = new javax.swing.JCheckBox();
        check_Units = new javax.swing.JCheckBox();
        check_buying_process = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        bt_new = new javax.swing.JButton();
        bt_save = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tex_titel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel10.setBackground(new java.awt.Color(75, 137, 220));
        jPanel10.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        jPanel10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel10MouseDragged(evt);
            }
        });
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel10MousePressed(evt);
            }
        });

        bt_exit1.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_exit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Cancel_24px.png"))); // NOI18N
        bt_exit1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        bt_exit1.setBorderPainted(false);
        bt_exit1.setContentAreaFilled(false);
        bt_exit1.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_exit1.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_exit1.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_exit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exit1ActionPerformed(evt);
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

        jPanel8.setBackground(new java.awt.Color(75, 137, 220));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("أدارة المستخدمين");
        jPanel8.add(jLabel19);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bt_secure, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_exit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_exit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_secure, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel1.setBackground(new java.awt.Color(75, 137, 220));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("أسم الموظف");

        combo_Emp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        combo_Emp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------------------" }));
        combo_Emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_EmpActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("القسم");

        comb_job.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        comb_job.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------------", "لايوجد" }));
        comb_job.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_jobActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("أسم المستخدم");

        tex_user.setBackground(new java.awt.Color(240, 240, 240));
        tex_user.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_user.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("كلمة المرور");

        txt_pass.setBackground(new java.awt.Color(240, 240, 240));
        txt_pass.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_pass.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(75, 137, 220));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "الصلاحيات", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        check_Discount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Discount.setText("الخصم");

        check_Rep.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Rep.setText("التقترير اليومية");
        check_Rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_RepActionPerformed(evt);
            }
        });

        check_Damaged.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Damaged.setText("التالف");

        check_Payouts.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Payouts.setText("المردودات");

        check_user.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_user.setText("المستخدمين");

        check_Emp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Emp.setText("الموظفين");

        check_Retriever.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Retriever.setText("المسترجع");

        check_Expenses.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Expenses.setText("المصروفات");

        check_Cat_Dep_Gro.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Cat_Dep_Gro.setText("الاصناف و الاقسام و المجموعات");

        check_Add_product_manage_products.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Add_product_manage_products.setText("اضافة منتج و ادارة المنتجات");

        check_Inventory.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Inventory.setText("الجرد");

        check_Baecod.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Baecod.setText("طباعة باركود");

        check_Accounts.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Accounts.setText("الحسابات");

        check_Wholesale_sales.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Wholesale_sales.setText("مبعات بجملة");

        check_Units.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_Units.setText("أدارة الوحدات");

        check_buying_process.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        check_buying_process.setText("أضافة عملية شراء و أدارة المشتريات");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(check_Damaged)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(check_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(check_Emp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(check_Retriever)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(check_Discount))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(check_Units)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(check_buying_process))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(check_Baecod)
                                .addGap(64, 64, 64)
                                .addComponent(check_Cat_Dep_Gro))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(check_Accounts)
                                    .addComponent(check_Inventory))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(check_Wholesale_sales)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(check_Add_product_manage_products))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(check_Payouts)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(check_Rep)
                                            .addGap(7, 7, 7)
                                            .addComponent(check_Expenses)
                                            .addGap(88, 88, 88))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_Discount)
                    .addComponent(check_Retriever)
                    .addComponent(check_Emp)
                    .addComponent(check_user)
                    .addComponent(check_Damaged))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_Expenses)
                    .addComponent(check_Payouts, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(check_Rep)
                    .addComponent(check_Accounts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_Add_product_manage_products)
                    .addComponent(check_Inventory)
                    .addComponent(check_Wholesale_sales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_buying_process)
                    .addComponent(check_Units))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_Cat_Dep_Gro)
                    .addComponent(check_Baecod))
                .addContainerGap(76, Short.MAX_VALUE))
        );

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

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("الوظيفه");

        tex_titel.setBackground(new java.awt.Color(240, 240, 240));
        tex_titel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_titel.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("أسم الموظف او الوظيفة او القسم او أسم المستخدم");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(combo_Emp, 0, 289, Short.MAX_VALUE)
                                    .addComponent(comb_job, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tex_user)
                                    .addComponent(txt_pass)
                                    .addComponent(tex_titel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(combo_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tex_titel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(comb_job, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(tex_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exit1ActionPerformed
         this.dispose();
    }//GEN-LAST:event_bt_exit1ActionPerformed

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

    private void jPanel10MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel10MouseDragged

    private void jPanel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel10MousePressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        try {
            int row = table.getSelectedRow();
            String tableClick = (table.getValueAt(row, 0).toString());
            ResultSet rs = db.selecte("tb_user", new String[]{"*"}, new String[]{"id"},
                    new String[]{tableClick}, "=", "and");

            try {
                if (rs.next()) {

                    combo_Emp.setSelectedItem(rs.getString("name_emp"));
                    tex_titel.setText(rs.getString("job"));
                    comb_job.setSelectedItem(rs.getString("section_name"));
                    tex_user.setText(rs.getString("username"));
                    txt_pass.setText(rs.getString("pass"));
                    c_d = rs.getInt("check_Discount");
                    if (c_d == 1) {
                        check_Discount.setSelected(true);
                    } else {
                        check_Discount.setSelected(false);
                    }
                    c_r = rs.getInt("check_Retriever");
                    if (c_r == 1) {
                        check_Retriever.setSelected(true);
                    } else {
                        check_Retriever.setSelected(false);
                    }
                    c_e = rs.getInt("check_Emp");
                    if (c_e == 1) {
                        check_Emp.setSelected(true);

                    } else {
                        check_Emp.setSelected(false);
                    }
                    c_u = rs.getInt("check_user");
                    if (c_u == 1) {
                        check_user.setSelected(true);
                    } else {
                        check_user.setSelected(false);
                    }
                    c_dam = rs.getInt("check_Damaged");
                    if (c_dam == 1) {
                        check_Damaged.setSelected(true);
                    } else {
                        check_Damaged.setSelected(false);
                    }
                    c_p = rs.getInt("check_Payouts");
                    if (c_p == 1) {
                        check_Payouts.setSelected(true);
                    } else {
                        check_Payouts.setSelected(false);
                    }
                    c_exp = rs.getInt("check_Expenses");
                    if (c_exp == 1) {
                        check_Expenses.setSelected(true);
                    } else {
                        check_Expenses.setSelected(false);
                    }
                    c_rep = rs.getInt("check_Rep");
                    if (c_rep == 1) {
                        check_Rep.setSelected(true);
                    } else {
                        check_Rep.setSelected(false);
                    }
                    c_Acc = rs.getInt("check_Accounts");
                    if (c_Acc == 1) {
                        check_Accounts.setSelected(true);
                    } else {
                        check_Accounts.setSelected(false);
                    }
                    c_m_p = rs.getInt("check_Ad_pr_ma_pr");
                    if (c_m_p == 1) {
                        check_Add_product_manage_products.setSelected(true);
                    } else {
                        check_Add_product_manage_products.setSelected(false);
                    }
                    check_w_sa = rs.getInt("check_Wh_sales");
                    if (check_w_sa == 1) {
                        check_Wholesale_sales.setSelected(true);
                    } else {
                        check_Wholesale_sales.setSelected(false);
                    }
                    c_In = rs.getInt("check_Inventory");
                    if (c_In == 1) {
                        check_Inventory.setSelected(true);
                    } else {
                        check_Inventory.setSelected(false);
                    }
                    c_b_pr = rs.getInt("check_bu_pro");
                    if (c_b_pr == 1) {
                        check_buying_process.setSelected(true);
                    } else {
                        check_buying_process.setSelected(false);
                    }
                    c_Units = rs.getInt("check_Units");
                    if (c_Units == 1) {
                        check_Units.setSelected(true);
                    } else {
                        check_Units.setSelected(false);
                    }
                    c_ca_de_gr = rs.getInt("check_Cat_Dep_Gro");
                    if (c_ca_de_gr == 1) {
                        check_Cat_Dep_Gro.setSelected(true);
                    } else {
                        check_Cat_Dep_Gro.setSelected(false);
                    }
                    c_bar = rs.getInt("check_Baecod");
                    if (c_bar == 1) {
                        check_Baecod.setSelected(true);
                    } else {
                        check_Baecod.setSelected(false);
                    }

                    bt_delete.setEnabled(true);
                    bt_update.setEnabled(true);

                    bt_save.setEnabled(false);

                }

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, e, "خطاء", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {

        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_tableMouseClicked

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        ResultSet rs = db.selecte("tb_user", new String[]{"id as \"الرقم\"", "name_emp as \" أسم الموظف\"",
            "job as  \" الموظف\"", "section_name as  \"القسم\"", "username as  \"أسم المستخدم\"",
            "beg_dat as  \"تاريخ الحفظ\"", "usernames as  \"أسم المدخل\""}, new String[]{"name_emp", "job",
            "section_name", "username"},
                new String[]{txt_search.getText() + "%", txt_search.getText() + "%", txt_search.getText() + "%", txt_search.getText() + "%"},
                "like", "or");

        table.setModel(DbUtils.resultSetToTableModel(rs));

        db.tab(table);


    }//GEN-LAST:event_txt_searchKeyReleased

    private void bt_newMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseEntered
        setColor(bt_new);
    }//GEN-LAST:event_bt_newMouseEntered

    private void bt_newMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseExited
        resetColor(bt_new);
    }//GEN-LAST:event_bt_newMouseExited

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed

        EmbteTex();
        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);
        bt_save.setEnabled(true);

    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseEntered
        setColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseEntered

    private void bt_saveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseExited
        resetColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseExited

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed

        if (combo_Emp.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "من فضلك اختار أسم الموظف", "خطاء", JOptionPane.ERROR_MESSAGE);

        } else if (comb_job.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(null, "من فضلك اختار القسم", "خطاء", JOptionPane.ERROR_MESSAGE);

        } else if (tex_user.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل اسم المستخدم", "خطاء", JOptionPane.ERROR_MESSAGE);
            tex_user.requestFocus();

        } else if (txt_pass.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "من فضلك ادخل كلمة المرور  ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_pass.requestFocus();

        } else {

            try {
                ResultSet rs = db.selecte("tb_user", new String[]{"username"}, new String[]{"username"},
                        new String[]{tex_user.getText()}, "=", "and");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "أسم المستخدم موجود", "خطاء", JOptionPane.ERROR_MESSAGE);
                    tex_user.requestFocus();
                } else {
                    int r = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ البيانات", "حفظ بيانات", JOptionPane.YES_NO_OPTION);

                    if (r == JOptionPane.YES_OPTION) {

                        if (check_Discount.isSelected()) {
                            c_d = 1;
                        }
                        if (check_Retriever.isSelected()) {
                            c_r = 1;
                        }
                        if (check_Emp.isSelected()) {
                            c_e = 1;
                        }
                        if (check_user.isSelected()) {
                            c_u = 1;
                        }
                        if (check_Damaged.isSelected()) {
                            c_dam = 1;
                        }
                        if (check_Payouts.isSelected()) {
                            c_p = 1;
                        }
                        if (check_Expenses.isSelected()) {
                            c_exp = 1;
                        }
                        if (check_Rep.isSelected()) {
                            c_rep = 1;
                        }
                        if (check_Accounts.isSelected()) {
                            c_Acc = 1;
                        }
                        if (check_Add_product_manage_products.isSelected()) {
                            c_m_p = 1;
                        }
                        if (check_Wholesale_sales.isSelected()) {
                            check_w_sa = 1;
                        }
                        if (check_Inventory.isSelected()) {
                            c_In = 1;
                        }
                        if (check_buying_process.isSelected()) {
                            c_b_pr = 1;
                        }
                        if (check_Units.isSelected()) {
                            c_Units = 1;
                        }
                        if (check_Cat_Dep_Gro.isSelected()) {
                            c_ca_de_gr = 1;
                        }
                        if (check_Baecod.isSelected()) {
                            c_bar = 1;
                        }

                        if (!check_Discount.isSelected()) {
                            c_d = 0;
                        }
                        if (!check_Retriever.isSelected()) {
                            c_r = 0;
                        }
                        if (!check_Emp.isSelected()) {
                            c_e = 0;
                        }
                        if (!check_user.isSelected()) {
                            c_u = 0;
                        }
                        if (!check_Damaged.isSelected()) {
                            c_dam = 0;
                        }
                        if (!check_Payouts.isSelected()) {
                            c_p = 0;
                        }
                        if (!check_Expenses.isSelected()) {
                            c_exp = 0;
                        }
                        if (!check_Rep.isSelected()) {
                            c_rep = 0;
                        }
                        if (!check_Accounts.isSelected()) {
                            c_Acc = 0;
                        }
                        if (!check_Add_product_manage_products.isSelected()) {
                            c_m_p = 0;
                        }
                        if (!check_Wholesale_sales.isSelected()) {
                            check_w_sa = 0;
                        }
                        if (!check_Inventory.isSelected()) {
                            c_In = 0;
                        }
                        if (!check_buying_process.isSelected()) {
                            c_b_pr = 0;
                        }
                        if (!check_Units.isSelected()) {
                            c_Units = 0;
                        }
                        if (!check_Cat_Dep_Gro.isSelected()) {
                            c_ca_de_gr = 0;
                        }
                        if (!check_Baecod.isSelected()) {
                            c_bar = 0;
                        }

                        id_em = id_Emp(ch);
                        int i = id_Section(ch_job);
                        String[] values = {null, ch, String.valueOf(id_em), tex_titel.getText(), ch_job,
                            String.valueOf(i), tex_user.getText(), txt_pass.getText(),
                            String.valueOf(c_d), String.valueOf(c_r), String.valueOf(c_e), String.valueOf(c_u), String.valueOf(c_dam),
                            String.valueOf(c_p), String.valueOf(c_exp), String.valueOf(c_rep), String.valueOf(c_Acc), String.valueOf(c_m_p),
                            String.valueOf(check_w_sa), String.valueOf(c_In), String.valueOf(c_b_pr), String.valueOf(c_Units),
                            String.valueOf(c_ca_de_gr),
                            String.valueOf(c_bar), null, null, username};

                        int res = db.add("tb_user", values, null);

                        if (res == -1) {
                            JOptionPane.showMessageDialog(null, "تم حفظ البيانات بنجاح");
                            Update_ta_user();
                            EmbteTex();
                        } else {
                            JOptionPane.showMessageDialog(null, "لم تتة الاضافه", "خطاء", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                }

            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);

            }

        }

        try {
            DBConn.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_bt_saveActionPerformed

    private void bt_updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseEntered
        setColor(bt_update);
    }//GEN-LAST:event_bt_updateMouseEntered

    private void bt_updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_updateMouseExited
        resetColor(bt_update);
    }//GEN-LAST:event_bt_updateMouseExited

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed

        int row = table.getSelectedRow();
        String tableClick = (table.getValueAt(row, 0).toString());

        if (combo_Emp.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "من فضلك اختار أسم الموظف", "خطاء", JOptionPane.ERROR_MESSAGE);

        } else if (comb_job.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(null, "من فضلك اختار القسم", "خطاء", JOptionPane.ERROR_MESSAGE);

        } else if (tex_user.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "من فضلك ادخل اسم المستخدم", "خطاء", JOptionPane.ERROR_MESSAGE);
            tex_user.requestFocus();

        } else if (txt_pass.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "من فضلك ادخل كلمة المرور  ", "خطاء", JOptionPane.ERROR_MESSAGE);
            txt_pass.requestFocus();
        } else {

            try {
               
                    int r = JOptionPane.showConfirmDialog(null, "هل تريد حقا تعديل البيانات", "تعديل بيانات", JOptionPane.YES_NO_OPTION);

                    if (r == JOptionPane.YES_OPTION) {
                        String[] updateFail = {"name_emp", "id_emp", "job", "section_name", "id_section",
                            "pass", "check_Discount", "check_Retriever", "check_Emp", "check_user", "check_Damaged", "check_Payouts",
                            "check_Expenses", "check_Rep", "check_Accounts", "check_Ad_pr_ma_pr", "check_Wh_sales", "check_Inventory",
                            "check_bu_pro", "check_Units", "check_Cat_Dep_Gro", "check_Baecod", "end_dat"};

                        if (check_Discount.isSelected()) {
                            c_d = 1;
                        }
                        if (check_Retriever.isSelected()) {
                            c_r = 1;
                        }
                        if (check_Emp.isSelected()) {
                            c_e = 1;
                        }
                        if (check_user.isSelected()) {
                            c_u = 1;
                        }
                        if (check_Damaged.isSelected()) {
                            c_dam = 1;
                        }
                        if (check_Payouts.isSelected()) {
                            c_p = 1;
                        }
                        if (check_Expenses.isSelected()) {
                            c_exp = 1;
                        }
                        if (check_Rep.isSelected()) {
                            c_rep = 1;
                        }
                        if (check_Accounts.isSelected()) {
                            c_Acc = 1;
                        }
                        if (check_Add_product_manage_products.isSelected()) {
                            c_m_p = 1;
                        }
                        if (check_Wholesale_sales.isSelected()) {
                            check_w_sa = 1;
                        }
                        if (check_Inventory.isSelected()) {
                            c_In = 1;
                        }
                        if (check_buying_process.isSelected()) {
                            c_b_pr = 1;
                        }
                        if (check_Units.isSelected()) {
                            c_Units = 1;
                        }
                        if (check_Cat_Dep_Gro.isSelected()) {
                            c_ca_de_gr = 1;
                        }
                        if (check_Baecod.isSelected()) {
                            c_bar = 1;
                        }

                        if (!check_Discount.isSelected()) {
                            c_d = 0;
                        }
                        if (!check_Retriever.isSelected()) {
                            c_r = 0;
                        }
                        if (!check_Emp.isSelected()) {
                            c_e = 0;
                        }
                        if (!check_user.isSelected()) {
                            c_u = 0;
                        }
                        if (!check_Damaged.isSelected()) {
                            c_dam = 0;
                        }
                        if (!check_Payouts.isSelected()) {
                            c_p = 0;
                        }
                        if (!check_Expenses.isSelected()) {
                            c_exp = 0;
                        }
                        if (!check_Rep.isSelected()) {
                            c_rep = 0;
                        }
                        if (!check_Accounts.isSelected()) {
                            c_Acc = 0;
                        }
                        if (!check_Add_product_manage_products.isSelected()) {
                            c_m_p = 0;
                        }
                        if (!check_Wholesale_sales.isSelected()) {
                            check_w_sa = 0;
                        }
                        if (!check_Inventory.isSelected()) {
                            c_In = 0;
                        }
                        if (!check_buying_process.isSelected()) {
                            c_b_pr = 0;
                        }
                        if (!check_Units.isSelected()) {
                            c_Units = 0;
                        }
                        if (!check_Cat_Dep_Gro.isSelected()) {
                            c_ca_de_gr = 0;
                        }
                        if (!check_Baecod.isSelected()) {
                            c_bar = 0;
                        }

                        id_em = id_Emp(ch);
                        int i = id_Section(ch_job);
                        String[] updatevalus = {ch, String.valueOf(id_em), tex_titel.getText(), ch_job,
                            String.valueOf(i), txt_pass.getText(),
                            String.valueOf(c_d), String.valueOf(c_r), String.valueOf(c_e), String.valueOf(c_u), String.valueOf(c_dam),
                            String.valueOf(c_p), String.valueOf(c_exp), String.valueOf(c_rep), String.valueOf(c_Acc), String.valueOf(c_m_p),
                            String.valueOf(check_w_sa), String.valueOf(c_In), String.valueOf(c_b_pr), String.valueOf(c_Units),
                            String.valueOf(c_ca_de_gr),
                            String.valueOf(c_bar), null,};

                        int res = db.update("tb_user", updateFail, updatevalus, new String[]{"id"}, new String[]{tableClick}, null, "=", "and");

                        if (res == -1) {
                            JOptionPane.showMessageDialog(null, "تم التعديل");
                            Update_ta_user();
                            EmbteTex();
                        } else {
                            JOptionPane.showMessageDialog(null, "  لم يتم التعديل" + res, "خطاء", JOptionPane.ERROR_MESSAGE);

                        }

                    }

            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);

            }

        }

        try {
            DBConn.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseEntered
        setColor(bt_delete);
    }//GEN-LAST:event_bt_deleteMouseEntered

    private void bt_deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deleteMouseExited
        resetColor(bt_delete);
    }//GEN-LAST:event_bt_deleteMouseExited

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed

        int row = table.getSelectedRow();
        String tableClick = (table.getValueAt(row, 0).toString());
        int res = JOptionPane.showConfirmDialog(null, "هل تريد المسح", "تنبيه", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            int r = db.delete("tb_user", new String[]{"id"}, new String[]{tableClick}, "=", "and");
            if (r == -1) {

                JOptionPane.showMessageDialog(null, " تم المسح بنجاح");

                Update_ta_user();
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

    private void combo_EmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_EmpActionPerformed
        // TODO add your handling code here:
  
        if (combo_Emp.getItemCount() != 0) {
            ch = combo_Emp.getSelectedItem().toString();

            id_em = id_Emp(ch);
            String job = emp_Job(id_em);
            tex_titel.setText(job);

            if (job.equals("مشرف") || job.equals("مدير") ) {
                check_true();
                 truetex();
                
            } else {
                
                if(job.equals("كاشير")){
                    truetex();
                    check_false();
                }else{
                check_false();
                falsetex();
                }

                
            }  

        }
    }//GEN-LAST:event_combo_EmpActionPerformed

    private void comb_jobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_jobActionPerformed
        // TODO add your handling code here:
        if (comb_job.getItemCount() != 0) {
            ch_job = comb_job.getSelectedItem().toString();
        }


    }//GEN-LAST:event_comb_jobActionPerformed

    private void check_RepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_RepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_RepActionPerformed

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
            java.util.logging.Logger.getLogger(T_Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(T_Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(T_Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(T_Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new T_Users().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_exit1;
    private javax.swing.JButton bt_minimize;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_secure;
    private javax.swing.JButton bt_update;
    private javax.swing.JCheckBox check_Accounts;
    private javax.swing.JCheckBox check_Add_product_manage_products;
    private javax.swing.JCheckBox check_Baecod;
    private javax.swing.JCheckBox check_Cat_Dep_Gro;
    private javax.swing.JCheckBox check_Damaged;
    private javax.swing.JCheckBox check_Discount;
    private javax.swing.JCheckBox check_Emp;
    private javax.swing.JCheckBox check_Expenses;
    private javax.swing.JCheckBox check_Inventory;
    private javax.swing.JCheckBox check_Payouts;
    private javax.swing.JCheckBox check_Rep;
    private javax.swing.JCheckBox check_Retriever;
    private javax.swing.JCheckBox check_Units;
    private javax.swing.JCheckBox check_Wholesale_sales;
    private javax.swing.JCheckBox check_buying_process;
    private javax.swing.JCheckBox check_user;
    private javax.swing.JComboBox<String> comb_job;
    private javax.swing.JComboBox<String> combo_Emp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable table;
    private javax.swing.JTextField tex_titel;
    private javax.swing.JTextField tex_user;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
