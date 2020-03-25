/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import ConDB.DBConn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.openide.util.Exceptions;

/**
 *
 * @author Ahmedfg
 */
public class date_test extends javax.swing.JFrame {

    DBConn db = new DBConn();
    /**
     * Creates new form date_test
     */
     private Date nw = new Date();
 String to = "";
private SimpleDateFormat dm = new SimpleDateFormat("yyyy-MM-dd");

    public date_test() {
        initComponents();
        //joen_Test();
        to = dm.format(nw);
        jTextField1.setText(to);
        tes();
    }
    
    public void joen_Test(){
        String sql = "SELECT o.id as \"رقم\" , o.exp_name as \"اسم المنصرف\" , o.exp_values as \"قيمة المنصرف\", o.exp_date as \"تارخ المنصرف\" ,\n" +
"r.id_expenses as \"رقم\" , r.amount_paid as \"قيمة المردود\" , r.dat_return as \"تاريخ الحفظ\"\n" +
"FROM tb_expenses o \n" +
"JOIN tb_sales_returns r ON o.id = r.id_expenses";
        
        try {
            PreparedStatement prs = DBConn.conn().prepareStatement(sql);
            
            ResultSet rs = prs.executeQuery();
            
           
            
            
        table.setModel(DbUtils.resultSetToTableModel(rs));

       
            
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        
          try {
            db.conn().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        
    }
    
    public void tes(){
       
        //sysdate() - pro_end_dat
        //pro_end_dat -  sysdate() 
        
        String sql = "SELECT id as\"رقم\",pro_name as\"أسم المنتج\","
     + "pro_dat as\"تاريخ الانتاج\" ,DATE_SUB(pro_end_dat,INTERVAL 0 DAY) AS \"تاريخ انهاء الصلاحيه\""
     + "FROM tb_producers ORDER BY pro_end_dat - sysdate()";
        
         try {
            PreparedStatement prs = DBConn.conn().prepareStatement(sql);
            
            ResultSet rs = prs.executeQuery();
   
            
        table.setModel(DbUtils.resultSetToTableModel(rs));
       
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
   
          //ResultSet rs = db.selecte("tb_producers", new String[]{"id as \"الرقم\"", "pro_name as \"أسم المنتج\"", 
           // "pro_purchase as  \"سعر\"", "pro_sale as  \" بيع\"", "pro_dep_num as  \"بيع2\"", 
            //"pro_dat as  \"تاريخ الانتاج\"", "pro_end_dat as  \" تاريخ الانتهاء\""}, 
            //new String[]{"pro_end_dat - pro_dat ,ORDER BY -id"}, new String[]{""}, "=", "and");
        // ResultSet rs= tdb.selecte("tb_employees", new String[]{"id as \"الرقم\"","emp_id as \" رقم الموظف\"","emp_name as  \"إسم الموظف \"","emp_tel as  \" الهاتف\"","emp_Job_title as  \"  الوظيفي\"","emp_w_time as  \" فترة الدوام\"","emp_dat_hiring as  \" تاريخ التعين\""}, new String[]{"id"}, new String[]{"9999"}, "!=", "and");

      //  table.setModel(DbUtils.resultSetToTableModel(rs));

     //   db.tab(table);

       

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

        fromdate01 = new com.toedter.calendar.JDateChooser();
        todate01 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fromdate01.setDateFormatString(org.openide.util.NbBundle.getMessage(date_test.class, "date_test.fromdate01.dateFormatString")); // NOI18N

        todate01.setDateFormatString(org.openide.util.NbBundle.getMessage(date_test.class, "date_test.todate01.dateFormatString")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(date_test.class, "date_test.jTextField1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(date_test.class, "date_test.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(75,137,220)));

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
        jScrollPane7.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fromdate01, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(todate01, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(todate01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromdate01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
         try {  
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
                        Date firstDate = fromdate01.getDate();
                        Date secondDate = todate01.getDate();

                        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                       jTextField1.setText(String.valueOf(diff));

  } catch (Exception e) {
   e.printStackTrace();
  }
        
      
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:

       

        

    }//GEN-LAST:event_tableMouseClicked

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
            java.util.logging.Logger.getLogger(date_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(date_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(date_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(date_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new date_test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fromdate01;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable table;
    private com.toedter.calendar.JDateChooser todate01;
    // End of variables declaration//GEN-END:variables
}
