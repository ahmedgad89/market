/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author A7meD3H
 */
public class JButt extends DefaultCellEditor {

    public JButt(JTextField textField) {
        super(textField);
    }

    public JButt(JCheckBox checkBox) {
        super(checkBox);
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

}
