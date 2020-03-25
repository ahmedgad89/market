/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package S_market;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author A7meD3H
 */
public class out {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void out() {
        try {
            Date date = GregorianCalendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String datestring = df.format(date);

            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String timestring = sdf.format(d);

            String value0 = timestring;
            String values = datestring;

            int userid = User_d.userid;
            String username = User_d.fusername;

            String userd = User_d.userd;
if(conn == null  || conn.isClosed() || rs.isClosed() || pst.isClosed()){conn = DB.jave_db();}
            String sql1 = "insert into loginuser "
                    + "(user_id,user_name,dol,date,status)" + " values ('" + userid + "','" + username + "','" + userd + "','" + values + "-" + value0 + "','خروج')";

            pst = conn.prepareStatement(sql1);
            pst.execute();

        } catch (Exception e) {

        } finally {
            try {

            } catch (Exception e) {

            }
        }

        System.exit(0);
    }
}
