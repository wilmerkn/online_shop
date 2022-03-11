package model;

import java.sql.*;
import java.util.ArrayList;

public class Cart {
    public static PreparedStatement st;
    public static ResultSet rs;


    public static boolean addProduct(Connection c,String id, int count) {
        try {
            //FIXA PREP STATEMENT MED INPUT FRÅN product table i view.... GÅR VIDARE SÅLÄNGE
            st = c.prepareStatement("SELECT * FROM product WHERE product_id = ?");
            st.setInt(1, Integer.parseInt(id));
            rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(4));
                if (Integer.parseInt(rs.getString(4)) < count || count <= 0) {
                    System.out.println("Sorry, we don't have enough in stock to match your purchase request, please lower the amount");
                    c.close();
                }
                else {
                    st = c.prepareStatement("UPDATE product set quantity = ? WHERE product_id = ?");
                    st.setInt(1, Integer.parseInt(rs.getString(4)) - count);
                    st.setInt(2, Integer.parseInt(id));
                    st.executeUpdate();
                    c.close();
                    return true;
                }
            }
        }
        catch(SQLException throwables){
                throwables.printStackTrace();
            }
        return false;
    }
        public static boolean addToCart (Connection c, String product_id,int quantity){
            try {



                st = c.prepareStatement("INSERT INTO order_item (product_id, quantity) VALUES( ?, ?)");
                st.setInt(1, Integer.parseInt(product_id));
                st.setInt(2, quantity);

                st.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        }
    public static ArrayList<ArrayList<String>> selectCart(Connection c) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        data.clear();
        try {

            st = c.prepareStatement("SELECT * FROM order_item");
            rs = st.executeQuery();
            int rowIndex = 0;
            while (rs.next()) {

                ArrayList<String> rowData = new ArrayList<String>();

                rowData.add(rs.getString("product_id"));
                rowData.add(rs.getString("order_id"));
                rowData.add(rs.getString("quantity"));

                data.add(rowData);
                c.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void emptyCart(Connection c){
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            st = c.prepareStatement("DELETE FROM order_item");
            st.executeUpdate();

            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
   /* public static void confirmOrder(Connection c){
        try {
            st = c.prepareStatement("SELECT o.product_id, o.quantity");
            st.setInt(1, Integer.parseInt(product_id));
            st.setInt(2, quantity);

            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
*/

}

