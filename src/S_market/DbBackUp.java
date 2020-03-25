/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ayman
 */
public class DbBackUp extends javax.swing.JPanel {

    /**
     * Creates new form DbBackUp
     */
    public DbBackUp() {
        initComponents();
    }

    public void setColor(JButton button) {
        button.setBackground(new java.awt.Color(255, 255, 255));
    }

    public void resetColor(JButton button) {
        button.setBackground(new java.awt.Color(240, 240, 240));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        txtbackuppath = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnchoose = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(93, 156, 236));
        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240), 2), "أخذ نسخة إحتياطية", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(240, 240, 240))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("نسخة إحتياطية الي");

        txtbackuppath.setBackground(new java.awt.Color(236, 240, 241));
        txtbackuppath.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtbackuppath.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));

        jProgressBar1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(93, 156, 236));
        jProgressBar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jProgressBar1.setStringPainted(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 76, 60));
        jLabel1.setText("تذكر مسار تخزين النسخة الإحتياطية للإستعادة");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(231, 76, 60));
        jLabel2.setText("يفضل وضع النسخة الإحتياطية في وسيط تخزين خارجي (فلاش - هارديسك خاريجي)");

        btnchoose.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnchoose.setForeground(new java.awt.Color(75, 137, 220));
        btnchoose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_File_32px_1.png"))); // NOI18N
        btnchoose.setText("إختار المسار النسخة الإحتياطية  ");
        btnchoose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        btnchoose.setContentAreaFilled(false);
        btnchoose.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnchoose.setOpaque(true);
        btnchoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnchooseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnchooseMouseExited(evt);
            }
        });
        btnchoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchooseActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(75, 137, 220));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Send_File_32px.png"))); // NOI18N
        jButton1.setText("      أخذ نسخة إحتياطة           ");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setOpaque(true);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtbackuppath, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(btnchoose))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnchoose, jButton1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnchoose)
                    .addComponent(jLabel4)
                    .addComponent(txtbackuppath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jProgressBar1, txtbackuppath});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnchoose, jButton1});

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void btnchooseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnchooseMouseEntered
        setColor(btnchoose);
    }//GEN-LAST:event_btnchooseMouseEntered

    private void btnchooseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnchooseMouseExited
        resetColor(btnchoose);
    }//GEN-LAST:event_btnchooseMouseExited

    private void btnchooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchooseActionPerformed
        txtbackuppath.setText(getBackUpPath());
    }//GEN-LAST:event_btnchooseActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        setColor(jButton1);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        resetColor(jButton1);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ("".equals(txtbackuppath.getText())) {
            JOptionPane.showMessageDialog(this, "الرجاء إختيار مسار");
            return;
        }

        Process p = null;
        InputStream input = null;
        OutputStream output = null;
        try {
            String Dir = System.getProperty("user.dir");
            //      Runtime runtime = Runtime.getRuntime();
            //   p = runtime.exec("jdbc:sqlite:" + Dir + "\\foot_report.sqlite --add-drop-database -B foot_report -r " + txtbackuppath.getText() + "\\foot_report" + ".sqlite");
            //change the dbpass and dbname with your dbpass and dbname
            // int processComplete = p.waitFor();
            Date date = GregorianCalendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("d-MM-yyyy");
            String datestring = df.format(date);

            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
            String timestring = sdf.format(d);

            String value0 = timestring;
            String values = datestring;
            input = new FileInputStream(Dir + "\\archiv_search_DB.sqlite");
            File Dir1 = new File(txtbackuppath.getText() + "\\نسخة إحتياطية برنامج البحث الإرشيف" + " " + values + "-" + value0);
            Dir1.mkdir();
            output = new FileOutputStream(Dir1 + "\\archiv_search_DB.sqlite");

            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {

                output.write(buf, 0, bytesRead);
                jProgressBar1.setValue(bytesRead);

            }
            if (jProgressBar1.getValue() == 100) {
                JOptionPane.showMessageDialog(null, "تم أخذ نسخة إحتياطية");
            }
            jProgressBar1.setValue(0);

//            if((bytesRead = input.read(buf)) > 0){
//                JOptionPane.showMessageDialog(null, "تم أخذ نسخة إحتياطية");
//            }
        } catch (HeadlessException | IOException e) {
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(DbBackUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchoose;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField txtbackuppath;
    // End of variables declaration//GEN-END:variables

    public static String getBackUpPath() {

        String backUpPath = "";
        JFileChooser fc = null;
        if (fc == null) {
            fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);
        }
        int returnVal = fc.showDialog(null, "Open");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            backUpPath = file.getAbsolutePath();
        }
        return backUpPath;

    }

}
