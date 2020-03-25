//producer
// ID , producer_id , producer_name , war_expiration , type_name , company_make , s_name , a_name t_name
//ref , min , min_buy , min_sale , min_number , max , max_buy , max_sale , max_number , producer_note , user_save
//date_save , in_country , unit_number , beg_date , end_date
//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author A7meD3H
 */
public class Producer extends javax.swing.JDialog {

    DBConn db = new DBConn();
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

    String co_t_n = "";
    String co_s_n = "";
    String co_a_n = "";

    String username = "";

    /**
     * Creates new form ADU
     */
    public Producer() {
        initComponents();   setModal(true);

        StyledDocument sd = tex_producer_note.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        sd.setParagraphAttributes(0, sd.getLength(), center, false);

        Update_Class();
        Update_Section();
        Update_group();
        UpdateType();
        UpdateType1();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();

        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        //  tex_min_buy.setText("0.000");
        // tex_max_buy.setText("0.000");
        // tex_min_sale.setText("0.000");
        // tex_max_sale.setText("0.000");
        // comp_Name_section();
        //    comp_Group_name();
        //  Update_Section();
        //  Update_group();
        //   EmbteTex_IGHT_TO_LEFT();
        // comp_Class_Mang();
        //   Update_Section();
    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    private void Update_Section() {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from tb_section";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            section_name.removeAllItems();
            section_name.addItem("إختيار");
            while (rs.next()) {
                String name = rs.getString("section_name");
                section_name.addItem(name);
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

    private void Update_Class() {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from tb_class";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            type_name1.removeAllItems();
            type_name1.addItem("إختيار");
            while (rs.next()) {
                String name = rs.getString("class_name");
                type_name1.addItem(name);
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

    private void Update_group() {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from tb_group";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            group_name.removeAllItems();
            group_name.addItem("إختيار");
            while (rs.next()) {
                String name = rs.getString("group_name");
                group_name.addItem(name);
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

    private void UpdateType() {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from add_type";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            co_max.removeAllItems();
           

            while (rs.next()) {
                String name = rs.getString("name");
                co_max.addItem(name);
             

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
 private void UpdateType1() {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from add_type";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            co_min.removeAllItems();

            while (rs.next()) {
                String name = rs.getString("name");
                
                co_min.addItem(name);

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
    //get Name Section
    public void comp_Name_section() {

        ResultSet rs = db.selecte("tb_section", new String[]{"section_name"}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");
        try {
            while (rs.next()) {
                section_name.addItem(rs.getString("section_name"));
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

    //get Name Group
    public final void comp_Group_name() {

        ResultSet rs = db.selecte("tb_group", new String[]{"group_name"}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");
        try {
            while (rs.next()) {
                group_name.removeAllItems();
                group_name.addItem("إختيار");
                group_name.addItem(rs.getString("group_name"));
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

    //get Name Class_Mang
    public void comp_Class_Mang() {

        ResultSet rs = db.selecte("tb_class", new String[]{"class_name"}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");
        try {
            while (rs.next()) {
                type_name1.addItem(rs.getString("class_name"));
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

    private void Update_Section(String group_name) {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from tb_section where group_name = '" + group_name + "'";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            section_name.removeAllItems();
            while (rs.next()) {
                String name = rs.getString("section_name");
                section_name.addItem(name);

            }
            if (!rs.next()) {
                section_name.addItem("إختيار");
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

    private void Update_Calss(String group_name) {

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select * from tb_class where section_name = '" + group_name + "'";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            type_name1.removeAllItems();

            while (rs.next()) {
                String name = rs.getString("class_name");

                type_name1.addItem(name);

                //setTableAlignmentValue(tabl_stu, i);
            }
            if (!rs.next()) {
                type_name1.addItem("إختيار");
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

    public void EmbteTex() {

        tex_producer_id.setText("");
        tex_producer_name.setText("");
        tex_type_name.setText("");
        tex_company_make.setText("");
        section_name.setSelectedIndex(0);
        type_name1.setSelectedIndex(0);
        group_name.setSelectedIndex(0);
        tex_ref.setText("");
        co_min.setSelectedIndex(0);
        tex_min_buy.setText("");
        tex_min_sale.setText("");
        tex_min_number.setText("");
        co_max.setSelectedIndex(0);

        tex_max_buy.setText("");
        tex_max_sale.setText("");
        tex_max_number.setText("");
        tex_min_buy.setText("");
        tex_max_buy.setText("");
        tex_min_sale.setText("");
        tex_max_sale.setText("");
        tex_alarm_date.setText("");
        tex_country_make.setText("");
        tex_alarm_number.setText("");

        group_name.removeAllItems();
        group_name.addItem("إختيار");
        comp_Group_name();

        section_name.removeAllItems();
        section_name.addItem("إختيار");

        type_name1.removeAllItems();
        type_name1.addItem("إختيار");

        tex_producer_note.setText("");

    }

    public void EmbteTex_IGHT_TO_LEFT() {

        tex_producer_id.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_producer_name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_type_name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_company_make.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        section_name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        type_name1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        group_name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_ref.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_min_buy.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_min_sale.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_min_number.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_max_buy.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_max_sale.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_max_number.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_min_buy.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_max_buy.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_min_sale.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_max_sale.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_alarm_date.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_country_make.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_alarm_number.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tex_producer_note.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

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
        jLabel9 = new javax.swing.JLabel();
        tex_producer_id = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tex_company_make = new javax.swing.JTextField();
        group_name = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tex_producer_name = new javax.swing.JTextField();
        tex_ref = new javax.swing.JTextField();
        section_name = new javax.swing.JComboBox<>();
        type_name1 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        co_max = new javax.swing.JComboBox<>();
        co_min = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tex_max_buy = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        tex_min_number = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        tex_min_sale = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tex_producer_note = new javax.swing.JTextPane();
        jLabel16 = new javax.swing.JLabel();
        tex_type_name = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        bt_new = new javax.swing.JButton();
        bt_save = new javax.swing.JButton();
        bt_new1 = new javax.swing.JButton();
        bt_add_s = new javax.swing.JButton();
        bt_add_a = new javax.swing.JButton();
        bt_add_t = new javax.swing.JButton();
        tex_alarm_number = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tex_alarm_date = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tex_country_make = new javax.swing.JTextField();
        bt_add_t1 = new javax.swing.JButton();
        bt_add_t2 = new javax.swing.JButton();
        tex_max_number = new javax.swing.JTextField();
        tex_max_sale = new javax.swing.JTextField();
        tex_min_buy = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        bt_exit3 = new javax.swing.JButton();
        bt_minimize2 = new javax.swing.JButton();
        bt_secure3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

        myChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("إضافة منتج");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(93, 156, 236));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
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

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("رقم المنتج");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("نوع الصنف ");

        tex_producer_id.setBackground(new java.awt.Color(240, 240, 240));
        tex_producer_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_producer_id.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_producer_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_producer_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_producer_idActionPerformed(evt);
            }
        });
        tex_producer_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_producer_idKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("إسم المنتج");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("الشركة المصنعة");

        tex_company_make.setBackground(new java.awt.Color(240, 240, 240));
        tex_company_make.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_company_make.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_company_make.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        group_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        group_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "إختيار" }));
        group_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        group_name.setPreferredSize(new java.awt.Dimension(6, 28));
        group_name.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                group_namePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        group_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                group_nameMouseClicked(evt);
            }
        });
        group_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                group_nameActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("الرف");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("اسم المجموعه ");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("الإسم القسم");

        tex_producer_name.setBackground(new java.awt.Color(240, 240, 240));
        tex_producer_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_producer_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_producer_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_producer_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_producer_nameActionPerformed(evt);
            }
        });

        tex_ref.setBackground(new java.awt.Color(240, 240, 240));
        tex_ref.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_ref.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_ref.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        section_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        section_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "إختيار" }));
        section_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        section_name.setPreferredSize(new java.awt.Dimension(6, 28));
        section_name.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                section_namePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        section_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                section_nameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                section_nameMouseEntered(evt);
            }
        });
        section_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                section_nameActionPerformed(evt);
            }
        });

        type_name1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        type_name1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "إختيار" }));
        type_name1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        type_name1.setPreferredSize(new java.awt.Dimension(6, 28));
        type_name1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                type_name1MouseClicked(evt);
            }
        });
        type_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_name1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("الحد الأدني");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("الحد الأعلي");

        co_max.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        co_max.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "شريط", "علبة", "كرتونة صغيرة", "كرتونة كبيرة", "وحدة", " " }));
        co_max.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        co_max.setPreferredSize(new java.awt.Dimension(6, 28));
        co_max.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                co_maxMouseClicked(evt);
            }
        });
        co_max.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                co_maxActionPerformed(evt);
            }
        });

        co_min.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        co_min.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "شريط", "علبة", "كرتونة صغيرة", "كرتونة كبيرة", "وحدة", " " }));
        co_min.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        co_min.setPreferredSize(new java.awt.Dimension(6, 28));
        co_min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                co_minMouseClicked(evt);
            }
        });
        co_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                co_minActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("أعلي سعر الشراء");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("اقل سعر الشراء");

        tex_max_buy.setBackground(new java.awt.Color(240, 240, 240));
        tex_max_buy.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_max_buy.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_max_buy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_max_buy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_max_buyKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("أعلي سعر البيع");

        tex_min_number.setBackground(new java.awt.Color(240, 240, 240));
        tex_min_number.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_min_number.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_min_number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_min_number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_min_numberKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("اقل سعر البيع");

        tex_min_sale.setBackground(new java.awt.Color(240, 240, 240));
        tex_min_sale.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_min_sale.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_min_sale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_min_sale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_min_saleKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("وصف المنتج");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("عدد الوحدة");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("عدد الوحدة");

        tex_producer_note.setBackground(new java.awt.Color(240, 240, 240));
        tex_producer_note.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_producer_note.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_producer_note.setText("-");
        jScrollPane1.setViewportView(tex_producer_note);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("إسم الصنف");

        tex_type_name.setBackground(new java.awt.Color(240, 240, 240));
        tex_type_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_type_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_type_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_type_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_type_nameActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(93, 156, 236));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "العمليات المتاحة", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(240, 240, 240))); // NOI18N

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

        bt_new1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_new1.setForeground(new java.awt.Color(93, 156, 236));
        bt_new1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Administrative_Tools_24px_1.png"))); // NOI18N
        bt_new1.setText("إدارة المنتجات");
        bt_new1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        bt_new1.setContentAreaFilled(false);
        bt_new1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_new1.setOpaque(true);
        bt_new1.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_new1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_new1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_new1MouseExited(evt);
            }
        });
        bt_new1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_new1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 10, Short.MAX_VALUE)
                .addComponent(bt_new1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_save, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_new, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_save, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_new1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bt_add_s.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_add_s.setForeground(new java.awt.Color(46, 204, 113));
        bt_add_s.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Plus_24px.png"))); // NOI18N
        bt_add_s.setBorder(null);
        bt_add_s.setContentAreaFilled(false);
        bt_add_s.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_add_s.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_add_s.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_add_sMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_add_sMouseExited(evt);
            }
        });
        bt_add_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_sActionPerformed(evt);
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

        bt_add_t.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_add_t.setForeground(new java.awt.Color(46, 204, 113));
        bt_add_t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Plus_24px.png"))); // NOI18N
        bt_add_t.setBorder(null);
        bt_add_t.setContentAreaFilled(false);
        bt_add_t.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_add_t.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_add_t.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_add_tMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_add_tMouseExited(evt);
            }
        });
        bt_add_t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_tActionPerformed(evt);
            }
        });

        tex_alarm_number.setBackground(new java.awt.Color(240, 240, 240));
        tex_alarm_number.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_alarm_number.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_alarm_number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_alarm_number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_alarm_numberKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("انذار الكميه");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("انذر الصلاحية");

        tex_alarm_date.setBackground(new java.awt.Color(240, 240, 240));
        tex_alarm_date.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_alarm_date.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_alarm_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_alarm_date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_alarm_dateKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("الدولة المصنعة");

        tex_country_make.setBackground(new java.awt.Color(240, 240, 240));
        tex_country_make.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_country_make.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_country_make.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        bt_add_t1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_add_t1.setForeground(new java.awt.Color(46, 204, 113));
        bt_add_t1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Plus_24px.png"))); // NOI18N
        bt_add_t1.setBorder(null);
        bt_add_t1.setContentAreaFilled(false);
        bt_add_t1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_add_t1.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_add_t1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_add_t1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_add_t1MouseExited(evt);
            }
        });
        bt_add_t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_t1ActionPerformed(evt);
            }
        });

        bt_add_t2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        bt_add_t2.setForeground(new java.awt.Color(46, 204, 113));
        bt_add_t2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Plus_24px.png"))); // NOI18N
        bt_add_t2.setBorder(null);
        bt_add_t2.setContentAreaFilled(false);
        bt_add_t2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bt_add_t2.setPreferredSize(new java.awt.Dimension(59, 34));
        bt_add_t2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_add_t2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_add_t2MouseExited(evt);
            }
        });
        bt_add_t2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_t2ActionPerformed(evt);
            }
        });

        tex_max_number.setBackground(new java.awt.Color(240, 240, 240));
        tex_max_number.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_max_number.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_max_number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_max_number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_max_numberKeyTyped(evt);
            }
        });

        tex_max_sale.setBackground(new java.awt.Color(240, 240, 240));
        tex_max_sale.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_max_sale.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_max_sale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_max_sale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tex_max_saleActionPerformed(evt);
            }
        });
        tex_max_sale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_max_saleKeyTyped(evt);
            }
        });

        tex_min_buy.setBackground(new java.awt.Color(240, 240, 240));
        tex_min_buy.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_min_buy.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_min_buy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_min_buy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_min_buyKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tex_ref, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(tex_country_make, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(tex_min_number, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(tex_alarm_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(tex_max_number, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_add_a, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tex_min_sale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(tex_alarm_number, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(tex_max_sale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(type_name1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 130, Short.MAX_VALUE)
                            .addComponent(tex_company_make, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tex_producer_name, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_add_s, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(section_name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tex_max_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tex_min_buy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_add_t, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_add_t1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_add_t2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(co_min, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(group_name, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(co_max, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tex_type_name)
                    .addComponent(tex_producer_id)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26)
                    .addComponent(jLabel33))
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel16, jLabel26, jLabel27, jLabel33});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel17, jLabel20, jLabel28});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel14, jLabel15, jLabel34, jLabel35});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {co_max, co_min, group_name, section_name, tex_alarm_date, tex_alarm_number, tex_company_make, tex_country_make, tex_max_buy, tex_max_number, tex_max_sale, tex_min_buy, tex_min_number, tex_min_sale, tex_ref, type_name1});

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
                    .addComponent(tex_company_make, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addComponent(tex_type_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(tex_country_make, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(group_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tex_ref, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(section_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(type_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(bt_add_s, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add_a, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add_t, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel34)
                    .addComponent(jLabel27)
                    .addComponent(co_max, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add_t1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tex_max_number, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(tex_max_sale, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(tex_max_buy, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel35)
                    .addComponent(jLabel26)
                    .addComponent(co_min, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_add_t2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tex_min_number, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(tex_min_sale, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(tex_min_buy, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tex_alarm_number, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addComponent(tex_alarm_date, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel16, jLabel26, jLabel27, jLabel33, jLabel8, jLabel9});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {co_max, co_min, group_name, section_name, tex_max_buy, tex_min_buy});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, jLabel14, jLabel15, jLabel34, jLabel35});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tex_alarm_date, tex_alarm_number, tex_company_make, tex_country_make, tex_max_number, tex_max_sale, tex_min_number, tex_min_sale, tex_ref, type_name1});

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
        jLabel21.setText("إضافة منتج جديد");
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
                .addGap(0, 0, 0)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void group_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_group_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_group_nameActionPerformed

    private void group_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_group_nameMouseClicked

        Update_group();

        // comp_Group_name();
//        String t = group_name.getSelectedItem().toString();
//        // Update_group();
//        group_name.removeAllItems();
//        group_name.addItem("إختيار");
//
//        comp_Group_name();
//        int x = 0;
//        for (int i = 0; i < group_name.getItemCount(); i++) {
//
//            if (group_name.getItemAt(i).equals(t)) {
//                x = 1;
//            }
//        }
//        if (x == 1) {
//            group_name.setSelectedItem(t);
//        } else {
//            group_name.setSelectedIndex(0);
//        }

    }//GEN-LAST:event_group_nameMouseClicked

    private void tex_producer_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_producer_idActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tex_producer_idActionPerformed

    private void tex_producer_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_producer_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_producer_nameActionPerformed

    private void section_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_section_nameMouseClicked
//comp_Name_section();
        //comp_Group_name();

        if (group_name.getSelectedItem() == "إختيار" || group_name.getSelectedItem() == "" || group_name.getSelectedItem() == " ") {
            JOptionPane.showMessageDialog(null, "من فضلك حدد اسم المجموعه");
        } else {

            Update_Section(group_name.getSelectedItem().toString());
        }

    }//GEN-LAST:event_section_nameMouseClicked

    private void section_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_section_nameActionPerformed
        // TODO add your handling code here:    

    }//GEN-LAST:event_section_nameActionPerformed

    private void type_name1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_type_name1MouseClicked

        if (group_name.getSelectedItem() == "إختيار" || group_name.getSelectedItem() == "" || group_name.getSelectedItem() == " "
                || section_name.getSelectedItem() == "إختيار" || section_name.getSelectedItem() == "" || section_name.getSelectedItem() == " ") {

            JOptionPane.showMessageDialog(null, "من فضلك حدد المجموعة و القسم");
        } else {
            Update_Calss(section_name.getSelectedItem().toString());
        }
    }//GEN-LAST:event_type_name1MouseClicked

    private void type_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type_name1ActionPerformed

    private void co_maxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_co_maxMouseClicked
      UpdateType();
    }//GEN-LAST:event_co_maxMouseClicked

    private void co_maxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_co_maxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_co_maxActionPerformed

    private void co_minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_co_minMouseClicked
         UpdateType1();
    }//GEN-LAST:event_co_minMouseClicked

    private void co_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_co_minActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_co_minActionPerformed

    private void tex_type_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_type_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_type_nameActionPerformed

    private void bt_newMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseEntered
        setColor(bt_new);
    }//GEN-LAST:event_bt_newMouseEntered

    private void bt_newMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_newMouseExited
        resetColor(bt_new);
    }//GEN-LAST:event_bt_newMouseExited

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed
        // كود زر جديد
        bt_save.setEnabled(true);

        EmbteTex();


    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseEntered
        setColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseEntered

    private void bt_saveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_saveMouseExited
        resetColor(bt_save);
    }//GEN-LAST:event_bt_saveMouseExited

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed

        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql22 = " select * from producer where producer_id= '" + tex_producer_id.getText() + "'";
            pst = conn.prepareStatement(sql22);
            //  pst.setString(1, txt_idsh.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(null, "رقم المنتج " + tex_producer_id.getText() + " مسجل مسبقاً", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                return;
            }
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            sql22 = " select * from producer where producer_name= '" + tex_producer_name.getText() + "'";
            pst = conn.prepareStatement(sql22);
            //  pst.setString(1, txt_idsh.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(null, "إسم المنتج " + tex_producer_name.getText() + " مسجل مسبقاً", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                return;
            }

            if (tex_producer_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "رقم المنتج فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_producer_id.requestFocus();
            } else if (tex_producer_name.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "إسم المنتج فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_producer_name.requestFocus();

            } else if (tex_type_name.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, " إسم الصنف فارغ ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_type_name.requestFocus();

            } else if (tex_company_make.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "الشركة المصنعة فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_company_make.requestFocus();
            } else if (tex_min_buy.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "سعر الشراء فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_min_buy.requestFocus();
            } else if (tex_max_buy.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "سعر البيع فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_max_buy.requestFocus();

            } else if (tex_min_sale.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "اقل سعر البيع", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_min_sale.requestFocus();

            } else if (tex_max_sale.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "سعر البيع", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_max_sale.requestFocus();

            } else if (tex_ref.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "رقم الرف فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_ref.requestFocus();

            } else if (tex_min_number.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "اقل الوحداة", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_min_number.requestFocus();

            } else if (tex_max_number.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "عدد الواحداة", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_max_number.requestFocus();

            } else if (tex_producer_note.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "وصف المنتج فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_producer_note.requestFocus();

            } else if (tex_alarm_number.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "إنذار الكمية فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_alarm_number.requestFocus();

            } else if (tex_alarm_date.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "انذر الصلاحية فارغ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_alarm_date.requestFocus();

            } else if (tex_country_make.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "الدولة المصتعة", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
                tex_country_make.requestFocus();

            } else if (section_name.getSelectedItem() == "إختيار") {
                JOptionPane.showMessageDialog(null, "الإسم القسم", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
            } else if (type_name1.getSelectedItem() == "إختيار") {

                JOptionPane.showMessageDialog(null, "نوع الصنف ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
            } else if (group_name.getSelectedItem() == "إختيار") {
                JOptionPane.showMessageDialog(null, "اسم المجموعه ", "لا يمكن الحفظ", JOptionPane.OK_OPTION);
            } else {

                int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حقا حفظ البيانات", "حفظ بيانات", JOptionPane.YES_NO_OPTION);
                if (Ahmed == 0) {

                    String[] values = {null, tex_producer_id.getText(), tex_producer_name.getText(), tex_type_name.getText(),
                        tex_company_make.getText(), tex_country_make.getText(), group_name.getSelectedItem().toString(),
                        section_name.getSelectedItem().toString(), type_name1.getSelectedItem().toString(), tex_ref.getText(),
                        co_max.getSelectedItem().toString(), tex_max_buy.getText(), tex_max_sale.getText(), tex_max_number.getText(),
                        co_min.getSelectedItem().toString(), tex_min_buy.getText(), tex_min_sale.getText(),
                        tex_min_number.getText(), tex_producer_note.getText(), tex_alarm_number.getText(),
                        tex_alarm_date.getText(), username, null, null};

                    int res = db.add("producer", values, null);

                    if (res == -1) {
                        JOptionPane.showMessageDialog(null, "تم حفظ البيانات بنجاح");

                        EmbteTex();

                    } else {
                        JOptionPane.showMessageDialog(null, "لم يتم إضافة البيانات", "خطاء", JOptionPane.ERROR_MESSAGE);
                    }

                }
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

            try {
                db.conn().close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }//GEN-LAST:event_bt_saveActionPerformed

    private void bt_add_sMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_sMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_sMouseEntered

    private void bt_add_sMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_sMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_sMouseExited

    private void bt_add_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_sActionPerformed
        new Section_Mang().setVisible(true);
    }//GEN-LAST:event_bt_add_sActionPerformed

    private void bt_add_aMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_aMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_aMouseEntered

    private void bt_add_aMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_aMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_aMouseExited

    private void bt_add_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_aActionPerformed
        new Class_Mang().setVisible(true);
    }//GEN-LAST:event_bt_add_aActionPerformed

    private void bt_add_tMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_tMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_tMouseEntered

    private void bt_add_tMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_tMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_tMouseExited

    private void bt_add_tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_tActionPerformed
        new Group_Mang().setVisible(true);
    }//GEN-LAST:event_bt_add_tActionPerformed

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

    private void section_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_section_nameMouseEntered

    }//GEN-LAST:event_section_nameMouseEntered

    private void bt_new1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_new1MouseEntered
        setColor(bt_new1);
    }//GEN-LAST:event_bt_new1MouseEntered

    private void bt_new1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_new1MouseExited
        resetColor(bt_new1);
    }//GEN-LAST:event_bt_new1MouseExited

    private void bt_new1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_new1ActionPerformed
        new Producer_Setting().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bt_new1ActionPerformed

    private void tex_producer_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_producer_idKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Just_Number(tex_producer_id, evt);


    }//GEN-LAST:event_tex_producer_idKeyTyped

    private void tex_min_numberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_min_numberKeyTyped
        // TODO add your handling code here:
        class_number cc = new class_number();
        cc.Just_Number(tex_min_number, evt);
    }//GEN-LAST:event_tex_min_numberKeyTyped

    private void tex_alarm_dateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_alarm_dateKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Just_Number(tex_alarm_date, evt);

    }//GEN-LAST:event_tex_alarm_dateKeyTyped

    private void tex_max_numberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_max_numberKeyTyped
        // TODO add your handling code here:
        class_number cc = new class_number();
        cc.Just_Number(tex_max_number, evt);

    }//GEN-LAST:event_tex_max_numberKeyTyped

    private void tex_min_buyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_min_buyKeyTyped
        // TODO add your handling code here:
        class_number cc = new class_number();
        cc.Number_dot(tex_min_buy, evt);

    }//GEN-LAST:event_tex_min_buyKeyTyped

    private void tex_max_buyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_max_buyKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Number_dot(tex_max_buy, evt);

    }//GEN-LAST:event_tex_max_buyKeyTyped

    private void tex_min_saleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_min_saleKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Number_dot(tex_min_sale, evt);
    }//GEN-LAST:event_tex_min_saleKeyTyped

    private void tex_max_saleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_max_saleKeyTyped
        // TODO add your handling code here:

        class_number cc = new class_number();
        cc.Number_dot(tex_max_sale, evt);

    }//GEN-LAST:event_tex_max_saleKeyTyped

    private void tex_alarm_numberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_alarm_numberKeyTyped
        // TODO add your handling code here:
        class_number cc = new class_number();
        cc.Just_Number(tex_alarm_number, evt);
    }//GEN-LAST:event_tex_alarm_numberKeyTyped

    private void group_namePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_group_namePopupMenuWillBecomeInvisible
        // TODO add your handling code here:

        Update_Section(group_name.getSelectedItem().toString());
        //  Update_Section(co_t_name.getSelectedItem().toString());


    }//GEN-LAST:event_group_namePopupMenuWillBecomeInvisible

    private void section_namePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_section_namePopupMenuWillBecomeInvisible
        // TODO add your handling code here:

        //  Update_Class(co_a_name.getSelectedItem().toString());
        // Update_Section(co_a_name.getSelectedItem().toString());
        Update_Calss(section_name.getSelectedItem().toString());

    }//GEN-LAST:event_section_namePopupMenuWillBecomeInvisible

    private void bt_exit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exit3ActionPerformed
        Producer.this.dispose();
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

    private void bt_add_t1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_t1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_t1MouseEntered

    private void bt_add_t1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_t1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_t1MouseExited

    private void bt_add_t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_t1ActionPerformed
        new Add_type().setVisible(true);
    }//GEN-LAST:event_bt_add_t1ActionPerformed

    private void bt_add_t2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_t2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_t2MouseEntered

    private void bt_add_t2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_add_t2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_add_t2MouseExited

    private void bt_add_t2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_t2ActionPerformed
        new Add_type().setVisible(true);
    }//GEN-LAST:event_bt_add_t2ActionPerformed

    private void tex_max_saleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tex_max_saleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tex_max_saleActionPerformed

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
            java.util.logging.Logger.getLogger(Producer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Producer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_a;
    private javax.swing.JButton bt_add_s;
    private javax.swing.JButton bt_add_t;
    private javax.swing.JButton bt_add_t1;
    private javax.swing.JButton bt_add_t2;
    private javax.swing.JButton bt_exit3;
    private javax.swing.JButton bt_minimize2;
    private javax.swing.JButton bt_new;
    private javax.swing.JButton bt_new1;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_secure3;
    private javax.swing.JComboBox<String> co_max;
    private javax.swing.JComboBox<String> co_min;
    private javax.swing.JComboBox<String> group_name;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFileChooser myChooser;
    private javax.swing.JComboBox<String> section_name;
    private javax.swing.JTextField tex_alarm_date;
    private javax.swing.JTextField tex_alarm_number;
    private javax.swing.JTextField tex_company_make;
    private javax.swing.JTextField tex_country_make;
    private javax.swing.JTextField tex_max_buy;
    private javax.swing.JTextField tex_max_number;
    private javax.swing.JTextField tex_max_sale;
    private javax.swing.JTextField tex_min_buy;
    private javax.swing.JTextField tex_min_number;
    private javax.swing.JTextField tex_min_sale;
    private javax.swing.JTextField tex_producer_id;
    private javax.swing.JTextField tex_producer_name;
    private javax.swing.JTextPane tex_producer_note;
    private javax.swing.JTextField tex_ref;
    private javax.swing.JTextField tex_type_name;
    private javax.swing.JComboBox<String> type_name1;
    // End of variables declaration//GEN-END:variables
}
