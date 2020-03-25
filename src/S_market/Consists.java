// Table: tb_consi
// id con_barcode con_p_name con_pur_price con_sel_price con_exi_quantity con_prod_date con_exp_date con_qua_dam con_unit dat_Dam_date beg_date end_date status user_name

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
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
public class Consists extends javax.swing.JFrame {

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
    int xx, xy;
    
    private Date nw1 = new Date();
    private Date nw2 = new Date();
private SimpleDateFormat dm = new SimpleDateFormat("yyyy-MM-dd");
private SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form ADU
     */
    public Consists() {
        initComponents();

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        bt_new.setBackground(new java.awt.Color(240, 240, 240));
        bt_save.setBackground(new java.awt.Color(240, 240, 240));
        bt_update.setBackground(new java.awt.Color(240, 240, 240));
        bt_delete.setBackground(new java.awt.Color(240, 240, 240));

        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);
        dat_Dam_date.setEnabled(false);

        Update_ta_consi();
       // Update_table();
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
            String sql = "select id,resource_name,resource_phone,resource_comany,resource_company_phone,user_save,date_save from resource";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            String[] tableColumnsName = {"*", "إسم المورد", "هاتف المورد", "إسم الشركة", "هاتف الشركة", "حفظ بواسطة", "تاريخ الحفظ"};
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
    
    private void Update_ta_consi(){
        
        ResultSet rs= db.selecte("tb_consi", new String[]{"id as \"الرقم\"","con_barcode as \"الباركود\"","con_p_name as  \"أسم المنتج\"","con_pur_price as  \"سعر الشراء\"","con_sel_price as  \"سعر البيع\"","con_exi_quantity as  \"الكمية الموجوده\"","con_prod_date as  \"تاريخ الأنتاج\"","con_exp_date as  \"تاريخ الأنتهاء\"","con_qua_dam as  \"كمية التالف\"","con_unit as  \"الوحده\"","dat_Dam_date as  \"تاريخ التالف\""}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");
          
          table.setModel(DbUtils.resultSetToTableModel(rs));
          DBConn.setTableR(table);
          
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
    
    public void EmbteTex(){
      
                txt_Barcode.setText("");
                txt_P_name.setText("");
                txt_pur_price.setText("");
                txt_sel_price.setText("");
                txt_Exi_quantity.setText("");
                txt_Prod_Date.setText("");
                txt_Exp_date.setText("");
                txt_Qua_Dam.setText("");
                txt_Unit.setText("");
                dat_Dam_date.setDate(null);  
    }
    
    public void getText(){
        //to = dm.format(nw);
        //br="", pn = "" , pup = "",sp="",P_D = "" ,E_D="",q_d="",u ="";
        br = txt_Barcode.getText();
        pn = txt_P_name.getText();
        pup = txt_pur_price.getText();
        sp = txt_sel_price.getText();
        P_D = txt_Prod_Date.getText();
        E_D = txt_Exp_date.getText();
         try {
             dm.parse(P_D);
             dd.parse(E_D);
         } catch (ParseException ex) {
             Exceptions.printStackTrace(ex);
         }
        
        q_d = txt_Qua_Dam.getText();
        u = txt_Unit.getText();
        
         String iro = String.valueOf(row);
          int ro = Integer.parseInt(iro);
        table.getRowCount();
          su = Integer.parseInt(table.getValueAt(ro, 0).toString());
          String r = String.valueOf(su);
          
          JOptionPane.showMessageDialog(null, r+pn);
          
          
        
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
        txt_Prod_Date = new javax.swing.JTextField();
        txt_Qua_Dam = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_P_name = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_pur_price = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_sel_price = new javax.swing.JTextField();
        txt_Exi_quantity = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_Unit = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        dat_Dam_date = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        txt_Barcode = new javax.swing.JTextField();
        txt_Exp_date = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        bt_exit = new javax.swing.JButton();
        bt_minimize = new javax.swing.JButton();
        bt_secure = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("التالف");
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
        table.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableMouseEntered(evt);
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
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        txt_Prod_Date.setBackground(new java.awt.Color(240, 240, 240));
        txt_Prod_Date.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Prod_Date.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Prod_Date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        txt_Qua_Dam.setBackground(new java.awt.Color(240, 240, 240));
        txt_Qua_Dam.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Qua_Dam.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Qua_Dam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("تاريخ الأنتهاء");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("كمية التالف");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("تاريخ الأنتاج");

        txt_P_name.setBackground(new java.awt.Color(240, 240, 240));
        txt_P_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_P_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_P_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("أسم المنتج");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("سعر الشراء");

        txt_pur_price.setBackground(new java.awt.Color(240, 240, 240));
        txt_pur_price.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_pur_price.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_pur_price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_pur_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_pur_priceKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("سعر البيع");

        txt_sel_price.setBackground(new java.awt.Color(240, 240, 240));
        txt_sel_price.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_sel_price.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sel_price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_sel_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_sel_priceKeyTyped(evt);
            }
        });

        txt_Exi_quantity.setBackground(new java.awt.Color(240, 240, 240));
        txt_Exi_quantity.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Exi_quantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Exi_quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("الكمية الموجوده");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("الوحده");

        txt_Unit.setBackground(new java.awt.Color(240, 240, 240));
        txt_Unit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Unit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Unit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_Unit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_UnitActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("تاريخ التالف");

        dat_Dam_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        dat_Dam_date.setDateFormatString("yyyy-MM-dd");
        dat_Dam_date.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dat_Dam_date.setMaxSelectableDate(new java.util.Date(2208985313000L));
        dat_Dam_date.setMinSelectableDate(new java.util.Date(946681313000L));
        dat_Dam_date.setMinimumSize(new java.awt.Dimension(35, 35));
        dat_Dam_date.setOpaque(false);

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("الباركود");

        txt_Barcode.setBackground(new java.awt.Color(240, 240, 240));
        txt_Barcode.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Barcode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Barcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        txt_Barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BarcodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BarcodeKeyReleased(evt);
            }
        });

        txt_Exp_date.setBackground(new java.awt.Color(240, 240, 240));
        txt_Exp_date.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_Exp_date.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_Exp_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Unit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Qua_Dam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Prod_Date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dat_Dam_date, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Exp_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_P_name, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txt_Exi_quantity)
                                                .addComponent(txt_pur_price, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                                .addComponent(txt_sel_price)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_Barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel22))))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dat_Dam_date, txt_Barcode, txt_Exi_quantity, txt_Exp_date, txt_P_name, txt_Prod_Date, txt_Qua_Dam, txt_Unit, txt_pur_price, txt_sel_price});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel22)
                    .addComponent(txt_Barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_Prod_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(txt_P_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_Exp_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(txt_pur_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_Qua_Dam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(txt_sel_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txt_Unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(txt_Exi_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(dat_Dam_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dat_Dam_date, txt_Barcode, txt_Exi_quantity, txt_Exp_date, txt_P_name, txt_Prod_Date, txt_Qua_Dam, txt_Unit, txt_pur_price, txt_sel_price});

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
        jLabel18.setText("التالف");
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
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newActionPerformed

        txt_P_name.setText("");
        txt_pur_price.setText("");
        txt_sel_price.setText("-");
        txt_Prod_Date.setText("-");
        txt_Unit.setText("-");
        txt_Qua_Dam.setText("-");

        txt_search.setText("");
        Update_table();
        bt_save.setEnabled(true);
        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);

    }//GEN-LAST:event_bt_newActionPerformed

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed
       
    }//GEN-LAST:event_bt_saveActionPerformed

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed
       
         int row = table.getSelectedRow();
        String tableClick =(table.getValueAt(row, 0).toString());
        
              String[] updateFail={"con_qua_dam","con_unit","dat_Dam_date","end_date"};
             dat =    ((JTextField)dat_Dam_date.getDateEditor().getUiComponent()).getText();  
      String[] updatevalus={txt_Qua_Dam.getText(),txt_Unit.getText(),dat,null};
      
     if(dat.isEmpty()){
     JOptionPane.showMessageDialog(null, "من فظلك حدد تاريخ التلف","خطاء",JOptionPane.ERROR_MESSAGE);
             } 
     else{
     int res=   db.update("tb_consi", updateFail, updatevalus, new String[]{"id"}, new String[]{tableClick}, null,"=", "and");
        
        if(res== -1){
            
            JOptionPane.showMessageDialog(null, "تم التعديل");
            Update_ta_consi();
            EmbteTex();
           
            
        bt_delete.setEnabled(false);
        bt_update.setEnabled(false);
        dat_Dam_date.setEnabled(false);
            
             }
        else
           JOptionPane.showMessageDialog(null, "  لم يتم التعديل"+res); 
        
     }
         
          try {
        db.conn().close();
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex);
    }
        
        
    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        int Ahmed = JOptionPane.showConfirmDialog(null, "هل تريد حذف  " + txt_P_name.getText(), "حذف بيانات", JOptionPane.YES_NO_OPTION);
        if (Ahmed == 0) {

            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = "delete from resource where id= ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, AA4);
                pst.execute();
                Update_table();

                bt_save.setEnabled(true);
                bt_delete.setEnabled(false);
                bt_update.setEnabled(false);

                String add2 = txt_P_name.getText();

                txt_P_name.setText("");
                txt_pur_price.setText("");
                txt_sel_price.setText("-");
                txt_Prod_Date.setText("-");
                txt_Unit.setText("-");
                txt_Qua_Dam.setText("-");

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
try{       
                 row = table.getSelectedRow();
                    String tableClick =(table.getValueAt(row, 0).toString());
        ResultSet rs= db.selecte("tb_consi", new String[]{"*"}, new String[]{"id"}, new String[]{tableClick}, "=", "and");
        
        try {
            if(rs.next()){
               
                txt_Barcode.setText(rs.getString("con_barcode"));
                txt_P_name.setText(rs.getString("con_p_name"));
                txt_pur_price.setText(String.valueOf(rs.getDouble("con_pur_price")));
                txt_sel_price.setText(String.valueOf(rs.getDouble("con_sel_price")));
                txt_Exi_quantity.setText(String.valueOf(rs.getInt("con_exi_quantity")));
                txt_Prod_Date.setText(String.valueOf(rs.getDate("con_prod_date")));
                txt_Exp_date.setText(String.valueOf(rs.getDate("con_exp_date")));
                txt_Qua_Dam.setText(String.valueOf(rs.getInt("con_qua_dam")));
                txt_Unit.setText(String.valueOf(rs.getInt("con_unit")));
                
                getText();

                
            }
        }catch(SQLException e){
        
             JOptionPane.showMessageDialog(null, e,"خطاء",JOptionPane.ERROR_MESSAGE);
        }
        
         bt_delete.setEnabled(true);
         bt_update.setEnabled(true);
         dat_Dam_date.setEnabled(true);
         
}catch(Exception e){
    JOptionPane.showMessageDialog(null, e,"خطاء",JOptionPane.ERROR_MESSAGE);
}
         
               try {
        db.conn().close();
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex);
    }
    }//GEN-LAST:event_tableMouseClicked

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        String ts = txt_search.getText();
                // String[] choosenFail ={"id as \"الرقم\"","con_barcode as \"الباركود\"","con_p_name as  \"أسم المنتج\"","con_pur_price as  \"سعر الشراء\"","con_sel_price as  \"سعر البيع\"","con_exi_quantity as  \"الكمية الموجوده\"","con_prod_date as  \"تاريخ الأنتاج\"","con_exp_date as  \"تاريخ الأنتهاء\"","con_qua_dam as  \"كمية التالف\"","con_unit as  \"الوحده\"","dat_Dam_date as  \"تاريخ التالف\""};

        if(ts.isEmpty()){
            EmbteTex();
            Update_ta_consi();
            //table.setModel(new DefaultTableModel(null,new String[]{""}));
             //table.setModel(new DefaultTableModel(null,new String[]{"Id","First Name","Last Name","Age"}));
        }
        else {
        
            ResultSet rs= db.selecte("tb_consi", new String[]{"*"}, new String[]{"id"}, new String[]{ts}, "=", "and");
        
        try {
            if(rs.next()){
               
                txt_Barcode.setText(rs.getString("con_barcode"));
                txt_P_name.setText(rs.getString("con_p_name"));
                txt_pur_price.setText(String.valueOf(rs.getDouble("con_pur_price")));
                txt_sel_price.setText(String.valueOf(rs.getDouble("con_sel_price")));
                txt_Exi_quantity.setText(String.valueOf(rs.getInt("con_exi_quantity")));
                txt_Prod_Date.setText(String.valueOf(rs.getDate("con_prod_date")));
                txt_Exp_date.setText(String.valueOf(rs.getDate("con_exp_date")));
                txt_Qua_Dam.setText(String.valueOf(rs.getInt("con_qua_dam")));
                txt_Unit.setText(String.valueOf(rs.getInt("con_unit")));
                
               // getText();
 String[] choosenFail ={"id as \"الرقم\"","con_barcode as \"الباركود\"","con_p_name as  \"أسم المنتج\"","con_pur_price as  \"سعر الشراء\"","con_sel_price as  \"سعر البيع\"","con_exi_quantity as  \"الكمية الموجوده\"","con_prod_date as  \"تاريخ الأنتاج\"","con_exp_date as  \"تاريخ الأنتهاء\"","con_qua_dam as  \"كمية التالف\"","con_unit as  \"الوحده\"","dat_Dam_date as  \"تاريخ التالف\""};
ResultSet rss = db.selecte("tb_consi", choosenFail, new String[]{"id"}, new String[]{ts}, "=", "and");
 table.setModel(DbUtils.resultSetToTableModel(rss));

                
            }
        }catch(SQLException e){
        
             JOptionPane.showMessageDialog(null, e,"خطاء",JOptionPane.ERROR_MESSAGE);
        }
        
         bt_delete.setEnabled(true);
         bt_update.setEnabled(true);
         dat_Dam_date.setEnabled(true);
         
               try {
        db.conn().close();
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex);
    }
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

    private void txt_pur_priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pur_priceKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(Character.isLetter(c)&&!evt.isAltDown()){
            evt.consume();
        }   
    }//GEN-LAST:event_txt_pur_priceKeyTyped

    private void txt_sel_priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sel_priceKeyTyped
        // TODO add your handling code here:      
        char c=evt.getKeyChar();
        if(Character.isLetter(c)&&!evt.isAltDown()){
            evt.consume();
        }       
    }//GEN-LAST:event_txt_sel_priceKeyTyped

    private void txt_UnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_UnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_UnitActionPerformed

    private void txt_BarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BarcodeKeyPressed
        // TODO add your handling code here:
        
         
        
        ResultSet rs= db.selecte("producer", new String[]{"*"}, new String[]{"producer_id"},
        new String[]{txt_Barcode.getText()}, "=", "and");
        
        try {
            if(rs.next()){
               
                txt_P_name.setText(rs.getString("producer_name"));
                txt_pur_price.setText(rs.getString("min_buy"));
                txt_sel_price.setText(rs.getString("max_buy")); //max_buy
               // txt_sel_price.setText(String.valueOf(rs.getDouble("con_sel_price")));
               // txt_Exi_quantity.setText(String.valueOf(rs.getInt("con_exi_quantity")));
               // txt_Prod_Date.setText(String.valueOf(rs.getDate("con_prod_date")));
               // txt_Exp_date.setText(String.valueOf(rs.getDate("con_exp_date")));
              //  txt_Qua_Dam.setText(String.valueOf(rs.getInt("con_qua_dam")));
              //  txt_Unit.setText(String.valueOf(rs.getInt("con_unit")));
                
              
                
            }
        }catch(SQLException e){
        
             JOptionPane.showMessageDialog(null, e,"خطاء",JOptionPane.ERROR_MESSAGE);
        }
        
         bt_delete.setEnabled(true);
         bt_update.setEnabled(true);
         dat_Dam_date.setEnabled(true);
         

         
               try {
        db.conn().close();
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex);
    }
        
       
        
        
    }//GEN-LAST:event_txt_BarcodeKeyPressed

    private void tableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseEntered

    private void txt_BarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BarcodeKeyReleased
        // TODO add your handling code here:
        
        if(txt_Barcode.getText().isEmpty()){
            txt_Barcode.setText("");
            txt_P_name.setText("");
            txt_pur_price.setText("");
            txt_sel_price.setText("");
        }
        
        
    }//GEN-LAST:event_txt_BarcodeKeyReleased

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
            java.util.logging.Logger.getLogger(Consists.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consists().setVisible(true);
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
    private com.toedter.calendar.JDateChooser dat_Dam_date;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_Barcode;
    private javax.swing.JTextField txt_Exi_quantity;
    private javax.swing.JTextField txt_Exp_date;
    private javax.swing.JTextField txt_P_name;
    private javax.swing.JTextField txt_Prod_Date;
    private javax.swing.JTextField txt_Qua_Dam;
    private javax.swing.JTextField txt_Unit;
    private javax.swing.JTextField txt_pur_price;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_sel_price;
    // End of variables declaration//GEN-END:variables

    String dat = "";
    
    String br="", pn = "" , pup = "",sp="",P_D = "" ,E_D="",q_d="",u ="";  int su =0; int row ;
}
