/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ahmedfg
 */
public class Payments extends javax.swing.JFrame {

    DBConn db = new DBConn();
    /**
     * Creates new form Payments
     */
    int xx, xy;
    String dat = "";
    String username = "";
    int id_exp = 0;
    List<String> id = new ArrayList<String>();

    public Payments() {
        initComponents();

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table_exp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_value_exp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_from.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_Pay_cash.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_pay_noticeable.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txt_exp_noticeable.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //icons8_Biotech_48px.png
        setIconImage(Toolkit.getDefaultToolkit().createImage("icons8_Checkout_48px.png"));

        // comp_Expenses();
        Updat_Tabel_Sales_Returns();
        bt_update.setEnabled(false);
        bt_delete.setEnabled(false);

        txt_value_exp.setEnabled(false);
        tex_exp_name.setEnabled(false);
        txt_from.setEnabled(false);
        txt_to.setEnabled(false);
        txt_pay_noticeable.setEnabled(false);

        Updat_Expenses();

    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    public void Updat_Expenses() {

        String[] choosenFail = {"id as \"الرقم\"",
            "exp_name as \"  أسم المصروفات\"", "exp_values as  \"قيمة المنصرف\"",
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
    public void Up_status(){
        
        String[] updateFail = {"status"};
        String[] updatevalus = {String.valueOf(1)};
        
         int res = db.update("tb_expenses", updateFail, updatevalus, new String[]{"id"}, 
         new String[]{String.valueOf(id_exp)}, null, "=", "and");
        
        
        
    }            

    /*
    public void comp_Expenses() {

        ResultSet rs = db.selecte("tb_expenses", new String[]{"exp_name", "id"}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");
        try {
            comb_name.removeAll();
            id.clear();
            while (rs.next()) {
                //  String [] name_exp = null;
               

                id.add(rs.getString("id"));
                comb_name.addItem(rs.getString("exp_name"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void comb_All_Expenses(int all) {

        try {
       int x =     comb_name.getSelectedIndex();
            String xx = id.get(all);

            ResultSet rs = db.selecte(" tb_expenses", new String[]{"exp_values", "exp_noticeable",
                "exp_from_account", "exp_to"},
                    new String[]{"id"},
                    new String[]{xx}, "=", "and");

            if (rs.next()) {

                txt_value_exp.setText(rs.getString("exp_values"));
                txt_pay_noticeable.setText(rs.getString("exp_noticeable"));
                txt_from.setText(rs.getString("exp_from_account"));
                txt_to.setText(rs.getString("exp_to"));

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "خطاء", JOptionPane.ERROR_MESSAGE);

        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
     */
    public void Updat_Tabel_Sales_Returns() {

        String[] choosenFail = {"id as \"الرقم\"",
            "exp_name as \"  أسم المصروفات\"", "value_of_outgoing as  \"قيمة المنصرف\"",
            "from_account as  \"من/ حساب\"", "to_account as  \"الي /حساب\"",
            "amount_paid as  \"المبلغ المدفوع\"", "dat_return as  \"التاريخ\""};

        ResultSet rs = db.selecte("tb_sales_returns", choosenFail,
                new String[]{"id"}, new String[]{"9999"}, "!=", "and");

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel14 = new javax.swing.JPanel();
        bt_exit1 = new javax.swing.JButton();
        bt_minimize1 = new javax.swing.JButton();
        bt_secure1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_value_exp = new javax.swing.JTextField();
        txt_from = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        bt_new = new javax.swing.JButton();
        bt_save = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_Pay_cash = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_exp_noticeable = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_pay_noticeable = new javax.swing.JTextArea();
        dat_expenses = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_to = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_to_account = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_from_account = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_exp = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txt_search_exp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tex_exp_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_stay = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel14.setBackground(new java.awt.Color(0, 204, 204));
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
        jLabel20.setText("إدارة المردودات");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(566, 566, 566)
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

        jPanel8.setBackground(new java.awt.Color(93, 156, 236));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("اسم المنصرفات");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("قيمة المنصرف");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("من/ حساب");

        txt_value_exp.setBackground(new java.awt.Color(240, 240, 240));
        txt_value_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_value_exp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_value_exp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_value_exp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_value_expMouseClicked(evt);
            }
        });
        txt_value_exp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_value_expKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_value_expKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_value_expKeyTyped(evt);
            }
        });

        txt_from.setBackground(new java.awt.Color(240, 240, 240));
        txt_from.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_from.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_from.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_from.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_fromMouseClicked(evt);
            }
        });
        txt_from.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fromActionPerformed(evt);
            }
        });
        txt_from.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fromKeyTyped(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75,137,220)));

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
        jScrollPane6.setViewportView(table);

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

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("المبلغ المدفوع");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ملحوظة");

        txt_Pay_cash.setBackground(new java.awt.Color(240, 240, 240));
        txt_Pay_cash.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Pay_cash.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Pay_cash.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_Pay_cash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Pay_cashMouseClicked(evt);
            }
        });
        txt_Pay_cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Pay_cashActionPerformed(evt);
            }
        });
        txt_Pay_cash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Pay_cashKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Pay_cashKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Pay_cashKeyTyped(evt);
            }
        });

        txt_exp_noticeable.setColumns(20);
        txt_exp_noticeable.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_exp_noticeable.setLineWrap(true);
        txt_exp_noticeable.setRows(5);
        txt_exp_noticeable.setText("-");
        jScrollPane1.setViewportView(txt_exp_noticeable);

        txt_pay_noticeable.setColumns(20);
        txt_pay_noticeable.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_pay_noticeable.setLineWrap(true);
        txt_pay_noticeable.setRows(5);
        jScrollPane2.setViewportView(txt_pay_noticeable);

        dat_expenses.setDateFormatString("yyyy-MM-dd");
        dat_expenses.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("التاريخ");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("ملحوظة");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("الي /حساب");

        txt_to.setBackground(new java.awt.Color(240, 240, 240));
        txt_to.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_to.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_to.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_to.setVerifyInputWhenFocusTarget(false);
        txt_to.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_toMouseClicked(evt);
            }
        });
        txt_to.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_toKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("الي/ حساب");

        txt_to_account.setBackground(new java.awt.Color(240, 240, 240));
        txt_to_account.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_to_account.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_to_account.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_to_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_to_accountMouseClicked(evt);
            }
        });
        txt_to_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_to_accountActionPerformed(evt);
            }
        });
        txt_to_account.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_to_accountKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("من/ حساب");

        txt_from_account.setBackground(new java.awt.Color(240, 240, 240));
        txt_from_account.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_from_account.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_from_account.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_from_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_from_accountMouseClicked(evt);
            }
        });
        txt_from_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_from_accountActionPerformed(evt);
            }
        });
        txt_from_account.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_from_accountKeyTyped(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(93, 156, 236));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "حساب المنصرفات", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

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
        table_exp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_expKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(table_exp);

        txt_search_exp.setBackground(new java.awt.Color(240, 240, 240));
        txt_search_exp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_search_exp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_search_exp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_search_exp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_expKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Google_Web_Search_24px_1.png"))); // NOI18N
        jLabel7.setFocusable(false);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel7.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_search_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel7)
                .addComponent(txt_search_exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" بحث / اسم المنصرفات - قيمة المنصرف - من/ حساب -الي/حساب ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5)
                .addGap(15, 15, 15))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tex_exp_name.setBackground(new java.awt.Color(240, 240, 240));
        tex_exp_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_exp_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_exp_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_exp_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tex_exp_nameMouseClicked(evt);
            }
        });
        tex_exp_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_exp_nameActionPerformed(evt);
            }
        });
        tex_exp_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_exp_nameKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" بحث / اسم المنصرفات - من/ حساب - الي/حساب ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("متبقي");

        txt_stay.setBackground(new java.awt.Color(240, 240, 240));
        txt_stay.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_stay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_stay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_stay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_stayMouseClicked(evt);
            }
        });
        txt_stay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_stayKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_stayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stayKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(dat_expenses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_to_account, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_from_account)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txt_stay, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_Pay_cash, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel23)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_value_exp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_from, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tex_exp_name, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_to, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15)
                                .addComponent(jLabel19)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(14, 14, 14)))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tex_exp_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel14)
                                    .addComponent(txt_value_exp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel15)
                                    .addComponent(txt_from, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel19)
                                    .addComponent(txt_to, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel18))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txt_Pay_cash, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                .addComponent(txt_stay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)))
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel22)
                                            .addComponent(txt_from_account, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel21)
                                            .addComponent(txt_to_account, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel23)
                                            .addComponent(dat_expenses, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(36, 36, 36)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void txt_value_expMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_value_expMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_value_expMouseClicked

    private void txt_value_expKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_value_expKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_value_expKeyPressed

    private void txt_value_expKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_value_expKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_value_expKeyReleased

    private void txt_value_expKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_value_expKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_value_expKeyTyped

    private void txt_fromMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_fromMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_fromMouseClicked

    private void txt_fromKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fromKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Just_Number(txt_from, evt);
    }//GEN-LAST:event_txt_fromKeyTyped

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        String[] choosenFail = {"id as \"الرقم\"",
            "exp_name as \"  أسم المصروفات\"", "value_of_outgoing as  \"قيمة المنصرف\"",
            "from_account as  \"من/ حساب\"", "to_account as  \"الي /حساب\"",
            "amount_paid as  \"المبلغ المدفوع\"", "dat_return as  \"التاريخ\""};

        ResultSet rs = db.selecte("tb_sales_returns", choosenFail, new String[]{"exp_name",
            "from_account", "to_account"},
                new String[]{txt_search.getText() + "%", txt_search.getText() + "%",
                    txt_search.getText() + "%"}, "like", "or");
        table.setModel(DbUtils.resultSetToTableModel(rs));

        //  DBConn.setTableR(table);
        db.tab(table);

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_txt_searchKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:

        /*
         String[] choosenFail = {"id as \"الرقم\"",
            "exp_name as \"  أسم المصروفات\"", "value_of_outgoing as  \"قيمة المنصرف\"",
        "from_account as  \"من/ حساب\"","to_account as  \"الي /حساب\"",
        "amount_paid as  \"المبلغ المدفوع\"","dat_return as  \"التاريخ\""};
        
        "from_account_asl_re",
                        "to_account_asl_re"
        
         */
        try {
            int row = table.getSelectedRow();
            String tableClick = (table.getValueAt(row, 0).toString());
            ResultSet rs = db.selecte("tb_sales_returns", new String[]{"*"}, new String[]{"id"}, new String[]{tableClick}, "=", "and");

            if (rs.next()) {

                tex_exp_name.setText(rs.getString("exp_name"));
                txt_value_exp.setText(rs.getString("value_of_outgoing"));
                txt_from.setText(rs.getString("from_account"));
                txt_to.setText(rs.getString("to_account"));
                txt_pay_noticeable.setText(rs.getString("exp_noticeable"));
                txt_Pay_cash.setText(rs.getString("amount_paid"));
                txt_from_account.setText(rs.getString("from_account_asl_re"));
                txt_to_account.setText(rs.getString("to_account_asl_re"));
                dat_expenses.setDate(rs.getDate("dat_return"));
                txt_exp_noticeable.setText(rs.getString("return_noticeable"));

                bt_save.setEnabled(false);

                bt_update.setEnabled(true);
                bt_delete.setEnabled(true);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "خطاء", JOptionPane.ERROR_MESSAGE);

        }
        bt_new.setEnabled(true);

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_tableMouseClicked

    private void bt_newMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseEntered
        setColor(bt_new);
    }//GEN-LAST:event_bt_newMouseEntered

    private void bt_newMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseExited
        resetColor(bt_new);
    }//GEN-LAST:event_bt_newMouseExited

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed

        tex_exp_name.setText("");
        txt_value_exp.setText("");
        txt_from.setText("");
        txt_to.setText("");
        txt_pay_noticeable.setText("-");
        txt_Pay_cash.setText("");
        txt_from_account.setText("");
        txt_to_account.setText("");
        txt_exp_noticeable.setText("-");
        txt_from_account.setText("");
        txt_to_account.setText("");
         txt_stay.setText("");
        dat_expenses.setDate(null);
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

        dat = ((JTextField) dat_expenses.getDateEditor().getUiComponent()).getText();
      int p1 = Integer.parseInt(txt_stay.getText())   ;
       int p2 = Integer.parseInt(txt_Pay_cash.getText())  ;

        if (tex_exp_name.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "  أسم المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            tex_exp_name.requestFocus();

        } else if (txt_Pay_cash.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "   المبلغ المدفوع فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_Pay_cash.requestFocus();

        } else if (dat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "   التاريخ  فارغ", "خطأ", JOptionPane.OK_OPTION);

        } else if (txt_exp_noticeable.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "    الملحوظة فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_exp_noticeable.requestFocus();
        } else if (txt_from_account.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "    من حساب فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_from_account.requestFocus();

        } else if (txt_to_account.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "    الي حساب فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_to_account.requestFocus();

        }else if(p1 < p2){
            
             JOptionPane.showMessageDialog(null, "المتبقي اكبر من المبلغ ", "خطأ", JOptionPane.OK_OPTION);
            txt_Pay_cash.requestFocus();
        } 
        
        else {

          
            int r = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ البيانات", "حفظ بيانات", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {
                
               

                String[] values = {null,String.valueOf(id_exp), tex_exp_name.getText(),
                    txt_value_exp.getText(), txt_from.getText(), txt_to.getText(), txt_pay_noticeable.getText(),
                    txt_Pay_cash.getText(), txt_stay.getText(),txt_from_account.getText(), 
                    txt_to_account.getText(), dat, txt_exp_noticeable.getText(), null, null, String.valueOf(1), 
                    username};

                int res = db.add("tb_sales_returns", values, null);

                if (res == -1) {
                    JOptionPane.showMessageDialog(null, "تم حفظ البيانات بنجاح");

                    Updat_Tabel_Sales_Returns();
                    
                    Up_status();
                    tex_exp_name.setText("");
                    txt_value_exp.setText("");
                    txt_from.setText("");
                    txt_to.setText("");
                    txt_pay_noticeable.setText("");
                    txt_Pay_cash.setText("");
                    txt_exp_noticeable.setText("");
                    txt_from_account.setText("");
                    txt_to_account.setText("");
                    txt_stay.setText("");
                    dat_expenses.setDate(null);
                    // EmbteTex();

                } else {
                    JOptionPane.showMessageDialog(null, "لم يتم إضافة البيانات", "خطاء", JOptionPane.ERROR_MESSAGE);
                }

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

        //JOptionPane.showMessageDialog(null, "غير مسموح بتعديل", "تنبيه", JOptionPane.ERROR_MESSAGE);
        int row = table.getSelectedRow();
        String tableClick = (table.getValueAt(row, 0).toString());

        dat = ((JTextField) dat_expenses.getDateEditor().getUiComponent()).getText();

        if (tex_exp_name.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "  أسم المصروفات فارغ", "خطأ", JOptionPane.OK_OPTION);
            tex_exp_name.requestFocus();

        } else if (txt_Pay_cash.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "   المبلغ المدفوع فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_Pay_cash.requestFocus();

        } else if (dat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "   التاريخ  فارغ", "خطأ", JOptionPane.OK_OPTION);

        } else if (txt_exp_noticeable.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "    الملحوظة فارغ", "خطأ", JOptionPane.OK_OPTION);
            txt_exp_noticeable.requestFocus();
        } else {
            try {
                int r = JOptionPane.showConfirmDialog(null, "هل تريد التعديل", "تنبيه", JOptionPane.YES_NO_OPTION);

                if (r == JOptionPane.YES_OPTION) {

                    String[] updateFail = {"exp_name", "value_of_outgoing", "from_account",
                        "to_account", "exp_noticeable", "amount_paid", "from_account_asl_re",
                        "to_account_asl_re", "dat_return", "return_noticeable","status" , "end_date"};

                    String[] updatevalus = {tex_exp_name.getText(),
                        txt_value_exp.getText(), txt_from.getText(), txt_to.getText(),
                        txt_pay_noticeable.getText(), txt_Pay_cash.getText(), txt_from_account.getText(), 
                        txt_to_account.getText(),dat, txt_exp_noticeable.getText(),String.valueOf("1"), null};

                    int res = db.update("tb_sales_returns", updateFail, updatevalus, new String[]{"id"}, new String[]{tableClick}, null, "=", "and");

                    if (res == -1) {

                        JOptionPane.showMessageDialog(null, "تم التعديل");
                        
                       
                             Updat_Tabel_Sales_Returns();
                       

                       

                        tex_exp_name.setText("");
                        txt_value_exp.setText("");
                        txt_from.setText("");
                        txt_to.setText("");
                        txt_pay_noticeable.setText("");
                        txt_Pay_cash.setText("");
                        txt_from_account.setText("");
                        txt_to_account.setText("");
                        txt_exp_noticeable.setText("");
                         txt_stay.setText("");
                        dat_expenses.setDate(null);

                        bt_update.setEnabled(false);
                        bt_delete.setEnabled(false);
                        bt_save.setEnabled(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "  لم يتم التعديل" + res);
                    }

                }
                bt_new.setEnabled(true);

            } catch (Exception ex) {

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

        int row = table.getSelectedRow();
        String tableClick = (table.getValueAt(row, 0).toString());
        int res = JOptionPane.showConfirmDialog(null, "هل تريد المسح", "تنبيه", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            int r = db.delete("tb_sales_returns", new String[]{"id"}, new String[]{tableClick}, "=", "and");
            if (r == -1) {

                JOptionPane.showMessageDialog(null, " تم المسح بنجاح");
                Updat_Tabel_Sales_Returns();

                tex_exp_name.setText("");
                txt_value_exp.setText("");
                txt_from.setText("");
                txt_to.setText("");
                txt_pay_noticeable.setText("-");
                txt_Pay_cash.setText("");
                txt_exp_noticeable.setText("-");
                txt_from_account.setText("");
                txt_to_account.setText("");
                 txt_stay.setText("");
                dat_expenses.setDate(null);

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

    private void txt_Pay_cashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Pay_cashMouseClicked
        // TODO add your handling code here:
         if(txt_value_exp.getText().isEmpty() ){
             JOptionPane.showMessageDialog(null, "  قيمة المصرف  فارغ", "خطأ", JOptionPane.OK_OPTION);
          //  txt_Pay_cash.requestFocus();
            txt_stay.setText("") ;
        }
    }//GEN-LAST:event_txt_Pay_cashMouseClicked

    private void txt_Pay_cashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pay_cashKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Pay_cashKeyPressed

    private void txt_Pay_cashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pay_cashKeyReleased
        // TODO add your handling code here:
        if(txt_Pay_cash.getText().isEmpty() ){
             JOptionPane.showMessageDialog(null, "  قيمة المصرف  فارغ", "خطأ", JOptionPane.OK_OPTION);
          //  txt_Pay_cash.requestFocus();
            txt_stay.setText("") ;
        }
        else if(txt_Pay_cash.getText().isEmpty()){
              txt_stay.setText("0") ;
        }
        
        else{
        
        int p1=0 , p2=0 , sum=0;
        p1 = Integer.valueOf(txt_value_exp.getText()+0) ; p2 = Integer.valueOf(txt_Pay_cash.getText()+0) ; 
        sum = p1  -  p2;
        
        txt_stay.setText(String.valueOf(sum));
        }
        
        
    }//GEN-LAST:event_txt_Pay_cashKeyReleased

    private void txt_Pay_cashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Pay_cashKeyTyped
        // TODO add your handling code here:
        class_number cc = new class_number();
        cc.Just_Number(txt_Pay_cash, evt);
    }//GEN-LAST:event_txt_Pay_cashKeyTyped

    private void txt_toMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_toMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_toMouseClicked

    private void txt_toKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_toKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_toKeyTyped

    private void txt_fromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fromActionPerformed

    private void txt_to_accountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_to_accountMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_to_accountMouseClicked

    private void txt_to_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_to_accountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_to_accountActionPerformed

    private void txt_to_accountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_to_accountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_to_accountKeyTyped

    private void txt_from_accountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_from_accountMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_from_accountMouseClicked

    private void txt_from_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_from_accountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_from_accountActionPerformed

    private void txt_from_accountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_from_accountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_from_accountKeyTyped

    private void txt_search_expKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_expKeyReleased

        String[] choosenFail = {"id as \"الرقم\"",
            "exp_name as \"  أسم المصروفات\"", "exp_values as  \"قيمة المنصرف\"",
            "exp_from_account as  \"حساب /من\"", "exp_to as  \"حساب /الي\""};

        ResultSet rs = db.selecte("tb_expenses", choosenFail, new String[]{"exp_name", "exp_values",
            "exp_from_account", "exp_to"},
                new String[]{txt_search_exp.getText() + "%", txt_search_exp.getText() + "%",
                    txt_search_exp.getText() + "%",
                    txt_search_exp.getText() + "%"}, "like", "or");
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


    }//GEN-LAST:event_txt_search_expKeyReleased

    private void table_expMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_expMouseClicked
        // TODO add your handling code here:

        try {
            int row = table_exp.getSelectedRow();
            String tableClick = (table_exp.getValueAt(row, 0).toString());
            ResultSet rs = db.selecte("tb_expenses", new String[]{"*"}, new String[]{"id"}, new String[]{tableClick}, "=", "and");

            if (rs.next()) {

           
                id_exp = rs.getInt("id");
                tex_exp_name.setText(rs.getString("exp_name"));
              
                txt_value_exp.setText(rs.getString("exp_values"));
                txt_pay_noticeable.setText(rs.getString("exp_noticeable"));
                
                txt_from.setText(rs.getString("exp_from_account"));
                txt_to.setText(rs.getString("exp_to"));

                txt_Pay_cash.setText("");
                txt_from_account.setText("");
                txt_to_account.setText("");
                txt_exp_noticeable.setText("-");
                dat_expenses.setDate(null);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "خطاء", JOptionPane.ERROR_MESSAGE);

        }

        try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_table_expMouseClicked

    private void tex_exp_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tex_exp_nameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_exp_nameMouseClicked

    private void tex_exp_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_exp_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_exp_nameActionPerformed

    private void tex_exp_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_exp_nameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_exp_nameKeyTyped

    private void table_expKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_expKeyReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_table_expKeyReleased

    private void txt_stayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_stayMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stayMouseClicked

    private void txt_stayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stayKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stayKeyPressed

    private void txt_stayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stayKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stayKeyReleased

    private void txt_stayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stayKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stayKeyTyped

    private void txt_Pay_cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Pay_cashActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_Pay_cashActionPerformed

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
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_exit1;
    private javax.swing.JButton bt_minimize1;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_secure1;
    private javax.swing.JButton bt_update;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dat_expenses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable table;
    private javax.swing.JTable table_exp;
    private javax.swing.JTextField tex_exp_name;
    private javax.swing.JTextField txt_Pay_cash;
    private javax.swing.JTextArea txt_exp_noticeable;
    private javax.swing.JTextField txt_from;
    private javax.swing.JTextField txt_from_account;
    private javax.swing.JTextArea txt_pay_noticeable;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_search_exp;
    private javax.swing.JTextField txt_stay;
    private javax.swing.JTextField txt_to;
    private javax.swing.JTextField txt_to_account;
    private javax.swing.JTextField txt_value_exp;
    // End of variables declaration//GEN-END:variables
}
