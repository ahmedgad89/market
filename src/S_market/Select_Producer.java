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
import org.openide.util.Exceptions;
import static S_market.Buying.aModel;

/**
 *
 * @author A7meD3H
 */
public class Select_Producer extends javax.swing.JDialog {
    
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
    public Select_Producer() {
        initComponents();   setModal(true);
        setModal(true);
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Update_table();
        Update_Class();
        Update_Section();
        Update_group();
    }
    
    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }
    
    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }
    
    public static String Get_Date() {
        Date date1 = GregorianCalendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = df.format(date1);
        return datestring;
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
    
    private void Update_table() {
        table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = "select p.producer_id,p.producer_name,p.company_make,p.group_name,p.section_name,p.type_name1,p.min_buy ,p.min_sale,s.quantity_total from producer p LEFT JOIN stock s ON p.producer_id = s.producer_id GROUP BY p.producer_id";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "الشركة المصنعة", "المجموعة", "القسم", "نوع الصنف", "سعر الشراء", "سعر البيع", "الكمية"};
            
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
            header.setBackground(new java.awt.Color(75, 137, 220));
            header.setForeground(new java.awt.Color(240, 240, 240));
            header.setFont(new java.awt.Font("Times New Roman", 1, 18));
            header.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder()));
            
            int colNo = rsmd.getColumnCount();
            
            while (rs.next()) {
                Object[] objects = new Object[colNo];
                // tanks to umit ozkan for the bug fix!
                for (int i = 0; i < colNo - 1; i++) {
                    objects[i] = rs.getObject(i + 1);
                    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
                    table.getColumnModel().getColumn(i).setCellEditor(new stop_editable(new JCheckBox()));

                    //setTableAlignmentValue(table, i);
                }
                
                aModel.addRow(objects);
                
            }
            
            table.setModel(aModel);
            
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
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tex_producer_name = new javax.swing.JTextField();
        tex_producer_id = new javax.swing.JTextField();
        tex_company_make = new javax.swing.JTextField();
        section_name = new javax.swing.JComboBox<>();
        type_name1 = new javax.swing.JComboBox<>();
        group_name = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        bt_exit3 = new javax.swing.JButton();
        bt_minimize2 = new javax.swing.JButton();
        bt_secure3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

        myChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("إختيار المنتجات");
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
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "بحث حسب", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(240, 240, 240))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("إسم المجموعة");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("نوع الصنف");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("إسم المنتج");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("إسم القسم");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("الشركة المصنعة");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("رقم المنتج");

        tex_producer_name.setBackground(new java.awt.Color(240, 240, 240));
        tex_producer_name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_producer_name.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_producer_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_producer_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tex_producer_nameKeyReleased(evt);
            }
        });

        tex_producer_id.setBackground(new java.awt.Color(240, 240, 240));
        tex_producer_id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_producer_id.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_producer_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_producer_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tex_producer_idKeyReleased(evt);
            }
        });

        tex_company_make.setBackground(new java.awt.Color(240, 240, 240));
        tex_company_make.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tex_company_make.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tex_company_make.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2));
        tex_company_make.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tex_company_makeKeyReleased(evt);
            }
        });

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
        type_name1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                type_name1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(type_name1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tex_company_make, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tex_producer_name, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(section_name, javax.swing.GroupLayout.Alignment.TRAILING, 0, 200, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(group_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tex_producer_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {group_name, section_name, tex_company_make, tex_producer_id, tex_producer_name, type_name1});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel15});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel17});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tex_producer_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tex_producer_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(tex_company_make, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(section_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(type_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(group_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {group_name, jLabel11, section_name, tex_company_make, tex_producer_id, tex_producer_name, type_name1});

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75, 137, 220)));

        table.setBackground(new java.awt.Color(240, 240, 240));
        table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        table.setForeground(new java.awt.Color(93, 156, 236));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setGridColor(new java.awt.Color(75, 137, 220));
        table.setSelectionBackground(new java.awt.Color(93, 156, 236));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel11.setBackground(new java.awt.Color(75, 137, 220));
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

        jPanel12.setBackground(new java.awt.Color(75, 137, 220));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("إختيار المنتجات");
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown

    }//GEN-LAST:event_jPanel3ComponentShown

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed

    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered

    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusLost

    }//GEN-LAST:event_jPanel3FocusLost

    private void jPanel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel3FocusGained

    }//GEN-LAST:event_jPanel3FocusGained

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged

    }//GEN-LAST:event_jPanel3MouseDragged

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        try {
            
            tex_producer_name.setText("");
            tex_producer_id.setText("");
            tex_company_make.setText("");
            int row = table.getSelectedRow();
            
            tablec = (table.getModel().getValueAt(row, 0).toString());
            
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            //select p.*,b.quantity from producer p LEFT JOIN buying_sub b ON p.producer_id = b.producer_id where p.producer_id=?
            String sql = "select * from producer  where producer_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, tablec);
            rs = pst.executeQuery();
            if (rs.next()) {
                //  JOptionPane.showMessageDialog(null, "الرجاء");
                // }else{
                Send_Value sv = new Send_Value();
                
                String producer_id = rs.getString("producer_id");
                String producer_name = rs.getString("producer_name");
                String type_name1 = rs.getString("type_name1");
                String max = rs.getString("max");
                String max_buy = rs.getString("max_buy");
                String max_sale = rs.getString("max_sale");
                String max_number = rs.getString("max_number");
                String now_date = Get_Date();
                Buying.aModel.addRow(new Object[]{"", producer_id, producer_name, type_name1, "الحد الأعلي", max, max_buy, max_sale, max_number, "0", "0", "نسبة", "0", "0", now_date, ""});
                Buying.Total();
                AA4 = rs.getString("producer_id");
                AA5 = rs.getString("producer_id");
                this.dispose();
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

    private void tex_producer_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_producer_nameKeyReleased
        if (tex_producer_name.getText().isEmpty()) {
            
            Update_table();
            
        } else {
            
            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = " select * from producer where producer_name like '" + tex_producer_name.getText() + "%'";
                table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    
                    AA5 = rs.getString("producer_id");
                    AA4 = rs.getString("producer_id");
                    
                } else {
                    
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
            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql11 = " select p.producer_id,p.producer_name,p.company_make,p.group_name,p.section_name,p.type_name1,p.min_buy ,p.min_sale,s.quantity_total from producer p LEFT JOIN stock s ON p.producer_id = s.producer_id  where  p.producer_name like '" + tex_producer_name.getText() + "%' GROUP BY p.producer_id";
                pst = conn.prepareStatement(sql11);
                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "الشركة المصنعة", "المجموعة", "القسم", "نوع الصنف", "سعر الشراء", "سعر البيع", "الكمية"};
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
                    }
                    aModel.addRow(objects);
                }
                table.setModel(aModel);
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
    }//GEN-LAST:event_tex_producer_nameKeyReleased

    private void tex_producer_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_producer_idKeyReleased
        if (tex_producer_id.getText().isEmpty()) {
            
            Update_table();
            
        } else {
            
            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = " select * from producer where producer_id like '" + tex_producer_id.getText() + "%'";
                table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    
                    AA5 = rs.getString("producer_id");
                    AA4 = rs.getString("producer_id");
                    
                } else {
                    
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
            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql11 = " select p.producer_id,p.producer_name,p.company_make,p.group_name,p.section_name,p.type_name1,p.min_buy ,p.min_sale,s.quantity_total from producer p LEFT JOIN stock s ON p.producer_id = s.producer_id  where  p.producer_id like '" + tex_producer_id.getText() + "%' GROUP BY p.producer_id";
                pst = conn.prepareStatement(sql11);
                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "الشركة المصنعة", "المجموعة", "القسم", "نوع الصنف", "سعر الشراء", "سعر البيع", "الكمية"};
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
                    }
                    aModel.addRow(objects);
                }
                table.setModel(aModel);
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
    }//GEN-LAST:event_tex_producer_idKeyReleased

    private void tex_company_makeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_company_makeKeyReleased
        if (tex_company_make.getText().isEmpty()) {
            
            Update_table();
            
        } else {
            
            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql = " select * from producer where company_make like '" + tex_company_make.getText() + "%'";
                table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    
                    AA5 = rs.getString("producer_id");
                    AA4 = rs.getString("producer_id");
                    
                } else {
                    
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
            try {
                if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                    conn = DB.jave_db();
                }
                String sql11 = " select p.producer_id,p.producer_name,p.company_make,p.group_name,p.section_name,p.type_name1,p.min_buy ,p.min_sale,s.quantity_total from producer p LEFT JOIN stock s ON p.producer_id = s.producer_id where  p.company_make like '" + tex_company_make.getText() + "%' GROUP BY p.producer_id ";
                pst = conn.prepareStatement(sql11);
                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "الشركة المصنعة", "المجموعة", "القسم", "نوع الصنف", "سعر الشراء", "سعر البيع", "الكمية"};
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
                    }
                    aModel.addRow(objects);
                }
                table.setModel(aModel);
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
    }//GEN-LAST:event_tex_company_makeKeyReleased

    private void section_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_section_nameMouseClicked

    }//GEN-LAST:event_section_nameMouseClicked

    private void section_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_section_nameMouseEntered

    }//GEN-LAST:event_section_nameMouseEntered

    private void section_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_section_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_section_nameActionPerformed

    private void type_name1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_type_name1MouseClicked

    }//GEN-LAST:event_type_name1MouseClicked

    private void type_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type_name1ActionPerformed

    private void section_namePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_section_namePopupMenuWillBecomeInvisible
        
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = " select * from producer where section_name like '" + section_name.getSelectedItem().toString() + "%'";
            table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                
                AA5 = rs.getString("producer_id");
                AA4 = rs.getString("producer_id");
                
            } else {
                
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
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql11 = " select p.producer_id,p.producer_name,p.company_make,p.group_name,p.section_name,p.type_name1,p.min_buy ,p.min_sale,s.quantity_total from producer p LEFT JOIN stock s ON p.producer_id = s.producer_id where  p.section_name like '" + section_name.getSelectedItem().toString() + "%' GROUP BY p.producer_id ";
            pst = conn.prepareStatement(sql11);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "الشركة المصنعة", "المجموعة", "القسم", "نوع الصنف", "سعر الشراء", "سعر البيع", "الكمية"};
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
                }
                aModel.addRow(objects);
            }
            table.setModel(aModel);
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
                
            } catch (SQLException e) {
                
            }
            
        }
    }//GEN-LAST:event_section_namePopupMenuWillBecomeInvisible

    private void type_name1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_type_name1PopupMenuWillBecomeInvisible
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = " select * from producer where type_name1 like '" + type_name1.getSelectedItem().toString() + "%'";
            table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                
                AA5 = rs.getString("producer_id");
                AA4 = rs.getString("producer_id");
                
            } else {
                
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
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql11 = " select p.producer_id,p.producer_name,p.company_make,p.group_name,p.section_name,p.type_name1,p.min_buy ,p.min_sale,s.quantity_total from producer p LEFT JOIN stock s ON p.producer_id = s.producer_id  where  p.type_name1 like '" + type_name1.getSelectedItem().toString() + "%' GROUP BY p.producer_id";
            pst = conn.prepareStatement(sql11);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "الشركة المصنعة", "المجموعة", "القسم", "نوع الصنف", "سعر الشراء", "سعر البيع", "الكمية"};
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
                }
                aModel.addRow(objects);
            }
            table.setModel(aModel);
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
                
            } catch (SQLException e) {
                
            }
            
        }
    }//GEN-LAST:event_type_name1PopupMenuWillBecomeInvisible

    private void bt_exit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exit3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_bt_exit3ActionPerformed

    private void bt_minimize2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_minimize2ActionPerformed
        // //this.setState(1);
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

    private void group_namePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_group_namePopupMenuWillBecomeInvisible
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql = " select * from producer where group_name like '" + group_name.getSelectedItem().toString() + "%'";
            table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                
                AA5 = rs.getString("producer_id");
                AA4 = rs.getString("producer_id");
                
            } else {
                
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
        try {
            if (conn == null || conn.isClosed() || rs.isClosed() || pst.isClosed()) {
                conn = DB.jave_db();
            }
            String sql11 = " select p.producer_id,p.producer_name,p.company_make,p.group_name,p.section_name,p.type_name1,p.min_buy ,p.min_sale,s.quantity_total from producer p LEFT JOIN stock s ON p.producer_id = s.producer_id where  p.group_name like '" + group_name.getSelectedItem().toString() + "%' GROUP BY p.producer_id ";
            pst = conn.prepareStatement(sql11);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            String[] tableColumnsName = {"رقم المنتج", "إسم المنتج", "الشركة المصنعة", "المجموعة", "القسم", "نوع الصنف", "سعر الشراء", "سعر البيع", "الكمية"};
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
                }
                aModel.addRow(objects);
            }
            table.setModel(aModel);
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
                
            } catch (SQLException e) {
                
            }
            
        }
    }//GEN-LAST:event_group_namePopupMenuWillBecomeInvisible

    private void group_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_group_nameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_group_nameMouseClicked

    private void group_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_group_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_group_nameActionPerformed

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
            java.util.logging.Logger.getLogger(Select_Producer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(() -> {
            new Select_Producer().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_exit3;
    private javax.swing.JButton bt_minimize2;
    private javax.swing.JButton bt_secure3;
    private javax.swing.JComboBox<String> group_name;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JFileChooser myChooser;
    private javax.swing.JComboBox<String> section_name;
    private javax.swing.JTable table;
    private javax.swing.JTextField tex_company_make;
    private javax.swing.JTextField tex_producer_id;
    private javax.swing.JTextField tex_producer_name;
    private javax.swing.JComboBox<String> type_name1;
    // End of variables declaration//GEN-END:variables
}
