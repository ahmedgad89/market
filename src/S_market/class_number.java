/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;




/**
 *
 * @author A7meD3H
 */
public class class_number {
   public void  Number_dot (JTextField field,java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if ((Character.isDigit(c)) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE) || (c == '.')) {
            int punto = 0;
            if (c == KeyEvent.VK_PERIOD) {
                String s = field.getText();
                int dot = s.indexOf('.');
                punto = dot;
                if (dot != -1) {
                    
                    evt.consume();
                }
            }
        } else {
          
            evt.consume();
        }
   }
    public void  Just_Number (JTextField field,java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if ((Character.isDigit(c)) ||(c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_BACK_SPACE) ) {
            int punto = 0;
            if (c == KeyEvent.VK_PERIOD) {
                String s = field.getText();
                int dot = s.indexOf('.');
                punto = dot;
                if (dot != -1) {
                    
                    evt.consume();
                }
            }
        } else {
          
            evt.consume();
        }
   }
     public void  None_None (JTextField field,java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
     
             evt.consume();
        
   }
}
