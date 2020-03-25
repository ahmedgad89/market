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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ahmedfg
 */
public class Expenses extends javax.swing.JFrame {

    DBConn db = new DBConn();
    /**
     * Creates new form Expenses
     */
    int xx, xy;
    String username = "";
    String dat = "";
    String up_ex = "";
    String up_name_exp_pay = "";

    public Expenses() {
        initComponents();

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        table_name_exp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table_exp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        txt_value_exp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_noticeable.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_name_exp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_from_account.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);

        bt_delete_exp.setEnabled(false);
        bt_update_exp.setEnabled(false);
        comp_Category_expenses();
        Updat_name_Cat();
        Updat_Expenses();

    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    public void comp_Category_expenses() {

        ResultSet rs = db.selecte("tb_category_expenses", new String[]{"cat_name"}, new String[]{"id"},
                new String[]{"999999999999999999991000000000"}, "!=", "and");
        try {
            while (rs.next()) {
                comb_name_exp.addItem(rs.getString("cat_name"));
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

    public void Updat_name_Cat() {

        String[] choosenFail = {"id as \"الرقم\"",
            "cat_name as \"  أسم المصروفات\"", "beg_date as  \"تاريخ الحفظ\""};

        ResultSet rs = db.selecte("tb_category_expenses", choosenFail,
                new String[]{"id"}, new String[]{"9999"}, "!=", "and");
        // ResultSet rs= tdb.selecte("tb_employees", new String[]{"id as \"الرقم\"","emp_id as \" رقم الموظف\"","emp_name as  \"إسم الموظف \"","emp_tel as  \" الهاتف\"","emp_Job_title as  \"  الوظيفي\"","emp_w_time as  \" فترة الدوام\"","emp_dat_hiring as  \" تاريخ التعين\""}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");

        table_name_exp.setModel(DbUtils.resultSetToTableModel(rs));

        db.tab(table_name_exp);

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

    public void Updat_Expenses() {

        String[] choosenFail = {"id as \"الرقم\"",
            "exp_name as \"  أسم المصروفات\"", "exp_values as  \"قيمة المنصرف\"", "exp_date as  \"التاريخ\"",
            "exp_from_account as  \"حساب /من\"", "exp_to as  \"حساب /الي\""};

        ResultSet rs = db.selecte("tb_expenses", choosenFail,
                new String[]{"id"}, new String[]{"9999"}, "!=", "and");
        // ResultSet rs= tdb.selecte("tb_employees", new String[]{"id as \"الرقم\"","emp_id as \" رقم الموظف\"","emp_name as  \"إسم الموظف \"","emp_tel as  \" الهاتف\"","emp_Job_title as  \"  الوظيفي\"","emp_w_time as  \" فترة الدوام\"","emp_dat_hiring as  \" تاريخ التعين\""}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");

        table_exp.setModel(DbUtils.resultSetToTableModel(rs));

        db.tab(table_exp);

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        bt_exit1 = new javax.swing.JButton();
        bt_minimize1 = new javax.swing.JButton();
        bt_secure1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_name_exp = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        bt_new = new javax.swing.JButton();
        bt_save = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        txt_name_exp = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        txt_search2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        comb_name_exp = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_exp = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        bt_new_exp = new javax.swing.JButton();
        bt_save_exp = new javax.swing.JButton();
        bt_update_exp = new javax.swing.JButton();
        bt_delete_exp = new javax.swing.JButton();
        txt_value_exp = new javax.swing.JTextField();
        bt_add_a = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_exp_to = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        dat_expenses = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_noticeable = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        txt_from_account = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comp_cloud_type = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel14.setBackground(new java.awt.Color(93, 156, 236));
        jPanel14.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
        jPanel14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel14jPanel6MouseDragged(evt);
            }
        });
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel14jPanel6MousePressed(evt);
            }
        });

        bt_exit1.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_exit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Cancel_24px.png"))); // NOI18N
        bt_exit1.setBorder(null);
        bt_exit1.setBorderPainted(false);
        bt_exit1.setContentAreaFilled(false);
        bt_exit1.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_exit1.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_exit1.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_exit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exit1bt_exitActionPerformed(evt);
            }
        });

        bt_minimize1.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_minimize1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Minus_30px.png"))); // NOI18N
        bt_minimize1.setBorderPainted(false);
        bt_minimize1.setContentAreaFilled(false);
        bt_minimize1.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_minimize1.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_minimize1.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_minimize1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_minimize1bt_minimizeActionPerformed(evt);
            }
        });

        bt_secure1.setFont(new java.awt.Font("Times New Roman", 1, 5)); // NOI18N
        bt_secure1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Secure_28px.png"))); // NOI18N
        bt_secure1.setBorder(null);
        bt_secure1.setBorderPainted(false);
        bt_secure1.setContentAreaFilled(false);
        bt_secure1.setMaximumSize(new java.awt.Dimension(31, 31));
        bt_secure1.setMinimumSize(new java.awt.Dimension(31, 31));
        bt_secure1.setPreferredSize(new java.awt.Dimension(0, 0));
        bt_secure1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_secure1bt_secureActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(75, 137, 220));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("إدارة الخصم");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(341, 341, 341)
                .addComponent(bt_secure1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_minimize1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_exit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(bt_secure1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bt_minimize1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel20))
            .addComponent(bt_exit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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

        jPanel4.setBackground(new java.awt.Color(93, 156, 236));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "أسم المصروفات", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("  أسم المصروفات");

        jScrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75,137,220)));

        table_name_exp.setBackground(new java.awt.Color(240, 240, 240));
        table_name_exp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table_name_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        table_name_exp.setForeground(new java.awt.Color(75,137,220));
        table_name_exp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_name_exp.setGridColor(new java.awt.Color(75, 137, 220));
        table_name_exp.setSelectionBackground(new java.awt.Color(75, 137, 220));
        table_name_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_name_expMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table_name_exp);

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

        txt_name_exp.setBackground(new java.awt.Color(240, 240, 240));
        txt_name_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_name_exp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txt_search2.setBackground(new java.awt.Color(240, 240, 240));
        txt_search2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_search2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_search2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_search2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search2KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Google_Web_Search_24px_1.png"))); // NOI18N
        jLabel8.setFocusable(false);
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel8.setOpaque(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_search2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(txt_search2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("  أسم المصروفات");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_name_exp)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(txt_name_exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(93, 156, 236));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "المنصرفات", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("  أسم المصروفات");

        comb_name_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        comb_name_exp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ـــــــــ إختار ـــــــــ" }));
        comb_name_exp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        comb_name_exp.setPreferredSize(new java.awt.Dimension(6, 28));
        comb_name_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comb_name_expMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comb_name_expMouseEntered(evt);
            }
        });
        comb_name_exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_name_expActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("من /حساب");

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75,137,220)));

        table_exp.setBackground(new java.awt.Color(240, 240, 240));
        table_exp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        table_exp.setForeground(new java.awt.Color(75,137,220));
        table_exp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_exp.setGridColor(new java.awt.Color(75, 137, 220));
        table_exp.setSelectionBackground(new java.awt.Color(75, 137, 220));
        table_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_expMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_exp);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("قيمة المنصرف");

        jPanel6.setBackground(new java.awt.Color(93, 156, 236));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)), "العمليات المتاحة", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(240, 240, 240))); // NOI18N

        bt_new_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_new_exp.setForeground(new java.awt.Color(75, 137, 220));
        bt_new_exp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Add_New_24px_1.png"))); // NOI18N
        bt_new_exp.setText("جديد");
        bt_new_exp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_new_exp.setContentAreaFilled(false);
        bt_new_exp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_new_exp.setOpaque(true);
        bt_new_exp.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_new_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_new_expMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_new_expMouseExited(evt);
            }
        });
        bt_new_exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_new_expActionPerformed(evt);
            }
        });

        bt_save_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_save_exp.setForeground(new java.awt.Color(75, 137, 220));
        bt_save_exp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Save_as_24px.png"))); // NOI18N
        bt_save_exp.setText("حفظ");
        bt_save_exp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_save_exp.setContentAreaFilled(false);
        bt_save_exp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_save_exp.setOpaque(true);
        bt_save_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_save_expMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_save_expMouseExited(evt);
            }
        });
        bt_save_exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_save_expActionPerformed(evt);
            }
        });

        bt_update_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_update_exp.setForeground(new java.awt.Color(75, 137, 220));
        bt_update_exp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Available_Updates_24px.png"))); // NOI18N
        bt_update_exp.setText("تعديل");
        bt_update_exp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_update_exp.setContentAreaFilled(false);
        bt_update_exp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_update_exp.setOpaque(true);
        bt_update_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_update_expMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_update_expMouseExited(evt);
            }
        });
        bt_update_exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_update_expActionPerformed(evt);
            }
        });

        bt_delete_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_delete_exp.setForeground(new java.awt.Color(75, 137, 220));
        bt_delete_exp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Delete_24px_1.png"))); // NOI18N
        bt_delete_exp.setText("حذف");
        bt_delete_exp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_delete_exp.setContentAreaFilled(false);
        bt_delete_exp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_delete_exp.setOpaque(true);
        bt_delete_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_delete_expMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_delete_expMouseExited(evt);
            }
        });
        bt_delete_exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_delete_expActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(bt_delete_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_update_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_save_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_new_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_new_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_save_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_delete_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_update_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        txt_value_exp.setBackground(new java.awt.Color(240, 240, 240));
        txt_value_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_value_exp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_value_exp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_value_exp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_value_expKeyTyped(evt);
            }
        });

        bt_add_a.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_add_a.setForeground(new java.awt.Color(46, 204, 113));
        bt_add_a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Plus_24px.png"))); // NOI18N
        bt_add_a.setBorder(null);
        bt_add_a.setContentAreaFilled(false);
        bt_add_a.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_add_a.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_add_a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_add_aMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_add_aMouseExited(evt);
            }
        });
        bt_add_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_aActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel6)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("الي /حساب");

        txt_exp_to.setBackground(new java.awt.Color(240, 240, 240));
        txt_exp_to.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_exp_to.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("التاريخ");

        dat_expenses.setDateFormatString("yyyy-MM-dd");
        dat_expenses.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txt_noticeable.setColumns(20);
        txt_noticeable.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_noticeable.setLineWrap(true);
        txt_noticeable.setRows(5);
        jScrollPane1.setViewportView(txt_noticeable);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ملحوظة");

        txt_from_account.setBackground(new java.awt.Color(240, 240, 240));
        txt_from_account.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_from_account.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("نوع السحب");

        comp_cloud_type.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        comp_cloud_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "من المحل", "شخصي" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_add_a, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(comb_name_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_value_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_from_account, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comp_cloud_type, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_exp_to, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dat_expenses, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel21)
                    .addComponent(jLabel19)
                    .addComponent(jLabel1)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {comb_name_exp, dat_expenses, jScrollPane1, txt_exp_to, txt_from_account, txt_value_exp});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(comb_name_exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add_a, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(txt_value_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(txt_from_account, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(comp_cloud_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel22)
                    .addComponent(txt_exp_to, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel23)
                    .addComponent(dat_expenses, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel24)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_exit1bt_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exit1bt_exitActionPerformed
        this.dispose();
    }//GEN-LAST:event_bt_exit1bt_exitActionPerformed

    private void bt_minimize1bt_minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_minimize1bt_minimizeActionPerformed
        this.setState(1);
    }//GEN-LAST:event_bt_minimize1bt_minimizeActionPerformed

    private void bt_secure1bt_secureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_secure1bt_secureActionPerformed
        System.gc();
        java.awt.Window window[] = java.awt.Window.getWindows();
        for (int i = 0; i < window.length; i++) {
            window[i].setVisible(false);
            window[i] = null;
        }
        new login_S().setVisible(true);
    }//GEN-LAST:event_bt_secure1bt_secureActionPerformed

    private void jPanel14jPanel6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14jPanel6MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel14jPanel6MouseDragged

    private void jPanel14jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14jPanel6MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel14jPanel6MousePressed

    private void table_name_expMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_name_expMouseClicked
        // TODO add your handling code here:

        try {
            int row = table_name_exp.getSelectedRow();
            String tableClick = (table_name_exp.getValueAt(row, 0).toString());
            ResultSet rs = db.selecte("tb_category_expenses", new String[]{"*"}, new String[]{"id"}, new String[]{tableClick}, "=", "and");

            if (rs.next()) {

                txt_name_exp.setText(rs.getString("cat_name"));

                up_ex = rs.getString("cat_name");

                bt_save.setEnabled(false);

                bt_update.setEnabled(true);
                bt_delete.setEnabled(true);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "خطاء", JOptionPane.ERROR_MESSAGE);

        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_table_name_expMouseClicked

    private void comb_name_expMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comb_name_expMouseClicked

        comb_name_exp.removeAllItems();
        comb_name_exp.addItem("ــــ إختار ــــ");
        comp_Category_expenses();

    }//GEN-LAST:event_comb_name_expMouseClicked

    private void comb_name_expMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comb_name_expMouseEntered

    }//GEN-LAST:event_comb_name_expMouseEntered

    private void comb_name_expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_name_expActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comb_name_expActionPerformed

    private void table_expMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_expMouseClicked
        // TODO add your handling code here:

        try {
            int row = table_exp.getSelectedRow();
            String tableClick = (table_exp.getValueAt(row, 0).toString());
            ResultSet rs = db.selecte("tb_expenses", new String[]{"*"}, new String[]{"id"}, new String[]{tableClick}, "=", "and");

            if (rs.next()) {

                comb_name_exp.setSelectedItem(rs.getString("exp_name"));
                up_name_exp_pay = rs.getString("exp_name");
                txt_value_exp.setText(rs.getString("exp_values"));
                txt_noticeable.setText(rs.getString("exp_noticeable"));
                dat_expenses.setDate(rs.getDate("exp_date"));
                txt_from_account.setText(rs.getString("exp_from_account"));
                String cl_type = rs.getString("cloud_type");

                if (cl_type.equals("من المحل")) {
                    comp_cloud_type.setSelectedIndex(0);
                } else {
                    comp_cloud_type.setSelectedIndex(1);
                }

                txt_exp_to.setText(rs.getString("exp_to"));

                bt_save_exp.setEnabled(false);
                bt_update_exp.setEnabled(true);
                bt_delete_exp.setEnabled(true);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "خطاء", JOptionPane.ERROR_MESSAGE);

        }
        bt_new_exp.setEnabled(true);

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_table_expMouseClicked

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged

    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed

    }//GEN-LAST:event_jPanel3MousePressed

    private void bt_newMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseEntered
        setColor(bt_new);
    }//GEN-LAST:event_bt_newMouseEntered

    private void bt_newMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseExited
        resetColor(bt_new);
    }//GEN-LAST:event_bt_newMouseExited

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed

        txt_name_exp.setText("");
        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);

    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseEntered
        setColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseEntered

    private void bt_saveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseExited
        resetColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseExited

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed

        if (txt_name_exp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "  أسم المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_name_exp.requestFocus();
        } else {
            try {

                ResultSet rs = db.selecte("tb_category_expenses", new String[]{"cat_name"}, new String[]{"cat_name"},
                        new String[]{txt_name_exp.getText()}, "=", "and");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, " أسم المصروفات موجود", "خطاء", JOptionPane.ERROR_MESSAGE);
                    txt_name_exp.requestFocus();

                } else {

                    String[] values = {null, txt_name_exp.getText(), null, null, "0", username};

                    int res = db.add("tb_category_expenses", values, null);

                    if (res == -1) {
                        JOptionPane.showMessageDialog(null, "تم حفظ البيانات بنجاح");
                        txt_name_exp.setText("");

                        comb_name_exp.removeAllItems();
                        comb_name_exp.addItem("ـــــــــ إختار ـــــــــ");
                        Updat_name_Cat();
                        comp_Category_expenses();

                        //تعديل
                        //  Update_ta_emp();
                        // EmbteTex();
                    } else {
                        JOptionPane.showMessageDialog(null, "لم تتة الاضافه", "خطاء", JOptionPane.ERROR_MESSAGE);
                    }

                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, ex, "خطاء", JOptionPane.ERROR_MESSAGE);

            }

        }

        try {
            db.conn().close();
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

        //  JOptionPane.showMessageDialog(null, "غير مسموح بتعديل", "تنبيه", JOptionPane.ERROR_MESSAGE);
        int row = table_name_exp.getSelectedRow();
        String tableClick = (table_name_exp.getValueAt(row, 0).toString());

        if (txt_name_exp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "  أسم المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_name_exp.requestFocus();
        } else {
            try {

                ResultSet rs = db.selecte("tb_category_expenses", new String[]{"cat_name"}, new String[]{"cat_name"},
                        new String[]{txt_name_exp.getText()}, "=", "and");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, " أسم المصروفات موجود", "خطاء", JOptionPane.ERROR_MESSAGE);
                    txt_name_exp.requestFocus();

                } else {

                    int r = JOptionPane.showConfirmDialog(null, "هل تريد التعديل", "تنبيه", JOptionPane.YES_NO_OPTION);

                    if (r == JOptionPane.YES_OPTION) {

                        String[] updateFail = {"cat_name", "end_date"};

                        String[] updatevalus = {txt_name_exp.getText(), null};

                        int res = db.update("tb_category_expenses", updateFail, updatevalus, new String[]{"id"}, new String[]{tableClick}, null, "=", "and");

                        if (res == -1) {

                            JOptionPane.showMessageDialog(null, "تم التعديل");

                            comb_name_exp.removeAllItems();
                            comb_name_exp.addItem("ـــــــــ إختار ـــــــــ");
                            Updat_name_Cat();

                            Updat_Expenses();

                            txt_name_exp.setText("");

                            bt_update.setEnabled(false);
                            bt_delete.setEnabled(false);
                            bt_save.setEnabled(true);

                        } else {
                            JOptionPane.showMessageDialog(null, "  لم يتم التعديل" + res);
                        }

                    }
                    bt_new.setEnabled(true);

                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "ex", "خطاء", JOptionPane.ERROR_MESSAGE);

            }

        }

        try {
            db.conn().close();
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

        int row = table_name_exp.getSelectedRow();
        String tableClick = (table_name_exp.getValueAt(row, 0).toString());
        int res = JOptionPane.showConfirmDialog(null, "هل تريد المسح", "تنبيه", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            int r = db.delete("tb_category_expenses", new String[]{"id"}, new String[]{tableClick}, "=", "and");
            if (r == -1) {

                JOptionPane.showMessageDialog(null, " تم المسح بنجاح");
                Updat_name_Cat();

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

    private void bt_new_expMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_new_expMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_new_expMouseEntered

    private void bt_new_expMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_new_expMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_new_expMouseExited

    private void bt_new_expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_new_expActionPerformed
        // TODO add your handling code here:

        comb_name_exp.setSelectedIndex(0);
        txt_value_exp.setText("");
        txt_noticeable.setText("");
        dat_expenses.setDate(null);
        dat = "";
        txt_exp_to.setText("");
        txt_from_account.setText("");
        comp_cloud_type.setSelectedIndex(0);

        bt_update_exp.setEnabled(false);
        bt_delete_exp.setEnabled(false);
        bt_save_exp.setEnabled(true);

    }//GEN-LAST:event_bt_new_expActionPerformed

    private void bt_save_expMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_save_expMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_save_expMouseEntered

    private void bt_save_expMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_save_expMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_save_expMouseExited

    private void bt_save_expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_save_expActionPerformed
        // TODO add your handling code here:

        dat = ((JTextField) dat_expenses.getDateEditor().getUiComponent()).getText();

        if (comb_name_exp.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "  أسم المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            comb_name_exp.requestFocus();
        } else if (txt_value_exp.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "  قيمة المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_value_exp.requestFocus();

        } else if (txt_noticeable.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "ملحوظة فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_noticeable.requestFocus();

        } else if (dat.isEmpty()) {

            JOptionPane.showMessageDialog(null, "التاريخ فارف", "خطأ", JOptionPane.OK_OPTION);
            dat_expenses.requestFocus();
        } else if (txt_exp_to.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, " حساب فارف/الي", "خطأ", JOptionPane.OK_OPTION);
            txt_exp_to.requestFocus();

        } else if (txt_from_account.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "حساب فارغ /من", "خطأ", JOptionPane.OK_OPTION);
            txt_from_account.requestFocus();

        } else {

            String[] values = {null, comb_name_exp.getSelectedItem().toString(),
                txt_value_exp.getText(), txt_noticeable.getText(), dat, txt_from_account.getText(),
                comp_cloud_type.getSelectedItem().toString(), txt_exp_to.getText(),
                null, null, "0", username};

            int res = db.add("tb_expenses", values, null);

            if (res == -1) {
                JOptionPane.showMessageDialog(null, "تم حفظ البيانات بنجاح");
                Updat_Expenses();
                comb_name_exp.setSelectedIndex(0);
                txt_value_exp.setText("");
                txt_noticeable.setText("");
                dat_expenses.setDate(null);
                dat = "";
                txt_exp_to.setText("");
                txt_from_account.setText("");
                comp_cloud_type.setSelectedIndex(0);

            } else {
                JOptionPane.showMessageDialog(null, "لم تتة الاضافه", "خطاء", JOptionPane.ERROR_MESSAGE);
            }
        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_bt_save_expActionPerformed

    private void bt_update_expMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_update_expMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_update_expMouseEntered

    private void bt_update_expMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_update_expMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_update_expMouseExited

    private void bt_update_expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_update_expActionPerformed
        // TODO add your handling code here:

        // JOptionPane.showMessageDialog(null, "غير مسموح بتعديل", "تنبيه", JOptionPane.ERROR_MESSAGE);
        int row = table_exp.getSelectedRow();
        String tableClick = (table_exp.getValueAt(row, 0).toString());

        dat = ((JTextField) dat_expenses.getDateEditor().getUiComponent()).getText();

        if (comb_name_exp.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "  أسم المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            comb_name_exp.requestFocus();
        } else if (txt_value_exp.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "  قيمة المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_value_exp.requestFocus();

        } else if (txt_noticeable.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "ملحوظة فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_noticeable.requestFocus();

        } else if (dat.isEmpty()) {

            JOptionPane.showMessageDialog(null, "التاريخ فارف", "خطأ", JOptionPane.OK_OPTION);
            dat_expenses.requestFocus();
        } else if (txt_from_account.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, " حساب  فارغ/الي ", "خطأ", JOptionPane.OK_OPTION);
            txt_from_account.requestFocus();
        } else if (txt_exp_to.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, " حساب  فارغ/من ", "خطأ", JOptionPane.OK_OPTION);
            txt_exp_to.requestFocus();

        } else {

            int r = JOptionPane.showConfirmDialog(null, "هل تريد التعديل", "تنبيه", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                String[] updateFail = {"exp_name", "exp_values", "exp_noticeable", "exp_date", "exp_from_account",
                    "cloud_type", "exp_to", "end_dat"};

                String[] updatevalus = {
                    comb_name_exp.getSelectedItem().toString(), txt_value_exp.getText(), txt_noticeable.getText(),
                    dat, txt_from_account.getText(), comp_cloud_type.getSelectedItem().toString(),
                    txt_exp_to.getText(), null};

                int res = db.update("tb_expenses", updateFail, updatevalus, new String[]{"id"}, new String[]{tableClick}, null, "=", "and");

                if (res == -1) {

                    JOptionPane.showMessageDialog(null, "تم التعديل");

                    Updat_Expenses();

                    comb_name_exp.setSelectedIndex(0);
                    txt_value_exp.setText("");
                    txt_noticeable.setText("");
                    dat_expenses.setDate(null);
                    dat = "";
                    txt_exp_to.setText("");
                    txt_from_account.setText("");
                    comp_cloud_type.setSelectedIndex(0);

                    bt_update_exp.setEnabled(false);
                    bt_delete_exp.setEnabled(false);
                    bt_save_exp.setEnabled(true);

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

    }//GEN-LAST:event_bt_update_expActionPerformed

    private void bt_delete_expMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_delete_expMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_delete_expMouseEntered

    private void bt_delete_expMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_delete_expMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_delete_expMouseExited

    private void bt_delete_expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_delete_expActionPerformed
        // TODO add your handling code here:

        int row = table_exp.getSelectedRow();
        String tableClick = (table_exp.getValueAt(row, 0).toString());
        int res = JOptionPane.showConfirmDialog(null, "هل تريد المسح", "تنبيه", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            int r = db.delete("tb_expenses", new String[]{"id"}, new String[]{tableClick}, "=", "and");
            if (r == -1) {

                JOptionPane.showMessageDialog(null, " تم المسح بنجاح");
                Updat_Expenses();
                comb_name_exp.setSelectedIndex(0);
                txt_value_exp.setText("");
                txt_noticeable.setText("");
                dat_expenses.setDate(null);
                dat = "";
                txt_exp_to.setText("");
                txt_from_account.setText("");
                comp_cloud_type.setSelectedIndex(0);

                bt_update_exp.setEnabled(false);
                bt_delete_exp.setEnabled(false);
                bt_save_exp.setEnabled(true);

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


    }//GEN-LAST:event_bt_delete_expActionPerformed

    private void bt_add_aMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_aMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_aMouseEntered

    private void bt_add_aMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_aMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_aMouseExited

    private void bt_add_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_aActionPerformed
        new Payments().setVisible(true);
    }//GEN-LAST:event_bt_add_aActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        String[] choosenFail = {"id as \"الرقم\"",
            "exp_name as \"  أسم المصروفات\"", "exp_values as  \"قيمة المنصرف\"", "exp_date as  \"التاريخ\"",
            "exp_from_account as  \"حساب /من\"", "exp_to as  \"حساب /الي\""};

        ResultSet rs = db.selecte("tb_expenses", choosenFail, new String[]{"exp_name", "exp_values", "exp_to"},
                new String[]{txt_search.getText() + "%", txt_search.getText() + "%", txt_search.getText() + "%"}, "like", "or");
        table_exp.setModel(DbUtils.resultSetToTableModel(rs));

        db.tab(table_exp);

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


    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search2KeyReleased
        // TODO add your handling code here:

        String[] choosenFail = {"id as \"الرقم\"",
            "cat_name as \"  أسم المصروفات\"", "beg_date as  \"تاريخ الحفظ\""};

        ResultSet rs = db.selecte("tb_category_expenses", choosenFail, new String[]{"cat_name"},
                new String[]{txt_search2.getText() + "%"}, "like", "and");
        table_name_exp.setModel(DbUtils.resultSetToTableModel(rs));

        //  DBConn.setTableR(table);
        db.tab(table_name_exp);

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_txt_search2KeyReleased

    private void txt_value_expKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_value_expKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Just_Number(txt_value_exp, evt);


    }//GEN-LAST:event_txt_value_expKeyTyped

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
            java.util.logging.Logger.getLogger(Expenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Expenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Expenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Expenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Expenses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_a;
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_delete_exp;
    private javax.swing.JButton bt_exit1;
    private javax.swing.JButton bt_minimize1;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_new_exp;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_save_exp;
    private javax.swing.JButton bt_secure1;
    private javax.swing.JButton bt_update;
    private javax.swing.JButton bt_update_exp;
    private javax.swing.JComboBox<String> comb_name_exp;
    private javax.swing.JComboBox<String> comp_cloud_type;
    private com.toedter.calendar.JDateChooser dat_expenses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable table_exp;
    private javax.swing.JTable table_name_exp;
    private javax.swing.JTextField txt_exp_to;
    private javax.swing.JTextField txt_from_account;
    private javax.swing.JTextField txt_name_exp;
    private javax.swing.JTextArea txt_noticeable;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_search2;
    private javax.swing.JTextField txt_value_exp;
    // End of variables declaration//GEN-END:variables
}
