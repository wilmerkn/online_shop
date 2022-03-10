import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionManager {
    private Properties properties;
    private String url;
    private PreparedStatement st;
    private ResultSet rs;
    private Connection conn;
    private Controller controller;

    ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();


    public ConnectionManager(Controller controller) {
        this.properties = new Properties();
        properties.setProperty("user", "ah0773");
        properties.setProperty("password", "1db3dcbp");
        properties.setProperty("ssl", "false");
        url = "jdbc:postgresql://pgserver.mau.se/ah0773";
        this.controller = controller;
    }


    public ArrayList<ArrayList<String>> selectProducts() {
        data.clear();
        try {
            conn = DriverManager.getConnection(url, properties);

            st = conn.prepareStatement("SELECT * FROM product");
            rs = st.executeQuery();
            int rowIndex = 0;
            while (rs.next()) {

                ArrayList<String> rowData = new ArrayList<String>();

                rowData.add(rs.getString("product_id"));
                rowData.add(rs.getString("product_name"));
                rowData.add(rs.getString("description"));
                rowData.add(rs.getString("quantity"));
                rowData.add(rs.getString("base_price"));
                rowData.add(rs.getString("supplier_id"));

                data.add(rowData);
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<ArrayList<String>> selectSuppliers() {
        data.clear();
        try {

            conn = DriverManager.getConnection(url, properties);
            st = conn.prepareStatement("SELECT * FROM supplier");
            rs = st.executeQuery();
            int rowIndex = 0;

            while (rs.next()) {

                ArrayList<String> rowData = new ArrayList<String>();

                rowData.add(rs.getString("company_name"));
                rowData.add(rs.getString("address"));
                rowData.add(rs.getString("phone_number"));
                data.add(rowData);
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
    public ArrayList<ArrayList<String>> displayProducts() {
        data.clear();
        try {
            conn = DriverManager.getConnection(url, properties);

            st = conn.prepareStatement("SELECT * FROM PRODUCT WHERE NOT discount_id = NULL;");
            rs = st.executeQuery();
            int rowIndex = 0;
            while (rs.next()) {

                ArrayList<String> rowData = new ArrayList<String>();

                rowData.add(rs.getString("product_id"));
                rowData.add(rs.getString("product_name"));
                rowData.add(rs.getString("description"));
                rowData.add(rs.getString("quantity"));
                rowData.add(rs.getString("base_price"));
                rowData.add(rs.getString("supplier_id"));

                data.add(rowData);
                conn.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> displayDiscountProducts(){

        data.clear();

        try {
            conn = DriverManager.getConnection(url, properties);
            st = conn.prepareStatement("SELECT * FROM product");
            rs = st.executeQuery();
            int rowIndex = 0;
            while (rs.next()) {

                ArrayList<String> rowData = new ArrayList<String>();

                rowData.add(rs.getString("product_id"));
                rowData.add(rs.getString("product_name"));
                rowData.add(rs.getString("description"));
                rowData.add(rs.getString("quantity"));
                rowData.add(rs.getString("base_price"));
                rowData.add(rs.getString("supplier_id"));

                data.add(rowData);
                conn.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }


    public boolean validate() {
        return true;
    }


    public void insertCustomer() {

        try {
            Connection conn = DriverManager.getConnection(url, properties);


            st = conn.prepareStatement("SELECT FROM CUSTOMER WHERE firstname = ?");
            st.setString(1, "Kalle");
            rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("Username in use");
                conn.close();
                return;
            }
            System.out.println("Procceding!");


            st = conn.prepareStatement("INSERT INTO CUSTOMER (firstname) VALUES (?)");

            st.setInt(1, 1);
            st.setString(2, "Kalle");
            st.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }


    }

    public void addProduct(String id, int count) {
        try {
            // kolla i databas om det finns tillräckligt många av vald produkt, isåfall lägg till i order, order ska sen visas i cart som är jtable
            Connection conn = DriverManager.getConnection(url, properties);

            //FIXA PREP STATEMENT MED INPUT FRÅN product table i view.... GÅR VIDARE SÅLÄNGE
            st = conn.prepareStatement("SELECT * FROM product WHERE product_id = ?");
            st.setInt(1, Integer.parseInt(id));
            rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString(4));
                if (Integer.parseInt(rs.getString(5)) < count){
                    System.out.println("Sorry, we don't have enough in stock to match your purchase request, please lower the amount");
                }



            }
        }
        catch(SQLException throwables){
                throwables.printStackTrace();
            }

    }
//NÄSTAN KLAR
    public void loginCheck(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, properties);

            //FIXA PREP STATEMENT MED INPUT FRÅN VIEW TEXTFIELDS.... GÅR VIDARE SÅLÄNGE
            st = conn.prepareStatement("SELECT * FROM login WHERE username = ?");
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                if (password.equals(rs.getString(3))) {
                    //kolla så att password stämmer och sen kolla om admin, skickas till controller som intierar login
                    controller.loginOK(rs.getBoolean(5));
                    conn.close();
                    return;
                }
                else {
                    System.out.println("Wrong password or username");
                }
            }
            else {
                System.out.println("Wrong password or username");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
