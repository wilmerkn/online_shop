package model;

import controller.Controller;

import java.sql.*;


public class Login {

    public static PreparedStatement st;
    public static ResultSet rs;
    public static boolean isAdmin;
    public static int customerID;

    public static boolean loginCheck(Connection c, String username, String password) {
        try {

            //FIXA PREP STATEMENT MED INPUT FRÅN VIEW TEXTFIELDS.... GÅR VIDARE SÅLÄNGE
            st = c.prepareStatement("SELECT * FROM login WHERE username = ?");
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                if (password.equals(rs.getString(3))) {
                    isAdmin = rs.getBoolean(5);
                    //kollar så att password stämmer och sen kolla om inlog är admin

                    c.close();
                    return true;

                } else {
                    System.out.println("Wrong password or username");
                    return false;
                }
            } else {
                System.out.println("Wrong password or username");
                return false;

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean isAdmin(){
        return isAdmin;

    }
}

    /*public Login (Connection c) {
        public void loginCheck (String username, String password){
            try {

                //FIXA PREP STATEMENT MED INPUT FRÅN VIEW TEXTFIELDS.... GÅR VIDARE SÅLÄNGE
                st = c.prepareStatement("SELECT * FROM login WHERE username = ?");
                st.setString(1, username);
                rs = st.executeQuery();

                if (rs.next()) {
                    if (password.equals(rs.getString(3))) {
                        //kollar så att password stämmer och sen kolla om inlog är admin
                        controller.loginOK(rs.getBoolean(5));
                        c.close();
                        return;
                    } else {
                        System.out.println("Wrong password or username");
                    }
                } else {
                    System.out.println("Wrong password or username");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
*/
