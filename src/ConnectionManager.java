import java.sql.*;
import java.util.ArrayList;
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
        properties.setProperty("user","ah0773");
        properties.setProperty("password","1db3dcbp");
        properties.setProperty("ssl","false");
        url = "jdbc:postgresql://pgserver.mau.se/ah0773";
        this.controller = controller;
    }

    public ArrayList<ArrayList<String>> selectProducts() {
        data.clear();

        try {

            conn = DriverManager.getConnection(url,properties);
            st = conn.prepareStatement("SELECT * FROM supplier");
            rs = st.executeQuery();
            int rowIndex = 0;

            while (rs.next()) {

                ArrayList<String> rowData = new ArrayList<String>();


                rowData.add(rs.getString("company_name"));
                rowData.add(rs.getString("address"));
                rowData.add(rs.getString("phone_number"));
                System.out.println(rowData.get(1));
                data.add(rowData);
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }


    public ArrayList<ArrayList<String>> selectSuppliers() {
        data.clear();
        try {

            conn = DriverManager.getConnection(url,properties);
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

    // Displaying Products
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

    // Displaying discount products
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



    public void inserCustomer() {

        try {
            Connection conn = DriverManager.getConnection(url,properties);


            st = conn.prepareStatement("SELECT FROM CUSTOMER WHERE firstname = ?");
            st.setString(1,"Kalle");
            rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("Username in use");
                conn.close();
                return;
            }
            System.out.println("Procceding!");



            /*
            st = conn.prepareStatement("INSERT INTO CUSTOMER (firstname) VALUES (?)");

            st.setInt(1,1);
            st.setString(2,"Kalle");
            st.executeUpdate();*/
            conn.close();

        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }


    }
}
