/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import com.sun.prism.paint.Stop;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author A7meD3H
 */
public class class_for_txt  {
    public  boolean b;
  public boolean Text_Field (JTextField jTextField ){
  
      if ((jTextField.getText().matches("")) || (jTextField.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "هنالك حقل فارق");
             b= true;
        }
      return b;
  }
   public boolean Text_Pane (JTextPane jTextPane ){
  
      if ((jTextPane.getText().matches("")) || (jTextPane.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "هنالك حقل فارق");
             b= true;
        }
      return b;
  }
  
}
