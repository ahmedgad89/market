/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author A7meD3H
 */
public class DB {

    Connection conn = null;

    public static Connection jave_db()   {
        try {
            String Ip_Port = null;
            String db_name = null;
            String db_user = null;
            String db_pass = null;
            Properties prop = new Properties();
            File f = new File(System.getProperty("user.dir"), "\\server.properties.txt");
            InputStream input = null;
            if (f.canExecute()) {
                input = new FileInputStream(f);
                prop.load(input);
                Ip_Port = prop.getProperty("Ip_Port");
                db_name = prop.getProperty("db_name");
                db_user = prop.getProperty("db_user");
                db_pass = prop.getProperty("db_pass");
                if (Ip_Port == null || db_name==null || db_user == null || db_pass == null) {
                    OutputStream output = null;

                    output = new FileOutputStream(f);
                    prop.setProperty("Ip_Port", "localhost:3306");
                    prop.setProperty("db_name", "S_market_DB");
                    prop.setProperty("db_user", "root");
                    prop.setProperty("db_pass", "");
                    prop.store(output, null);
                    Ip_Port = prop.getProperty("Ip_Port");
                    db_name = prop.getProperty("db_name");
                    db_user = prop.getProperty("db_user");
                    db_pass = prop.getProperty("db_pass");

                }
            } else {
                OutputStream output = null;

                output = new FileOutputStream(f);
                prop.setProperty("Ip_Port", "localhost:3306");
                prop.setProperty("db_name", "S_market_DB");
                prop.setProperty("db_user", "root");
                prop.setProperty("db_pass", "");
                prop.store(output, null);
            }

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;

            conn = DriverManager.getConnection("jdbc:mysql://" + Ip_Port + "/"+db_name+"?useUnicode=yes&characterEncoding=UTF-8", db_user, db_pass);

            return conn;

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "خطأ في جلب البيانات من قاعدة البيانات ..");
            return null;

       
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "تأكد من أيبي السيرفر الموجود في server.properties ");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
