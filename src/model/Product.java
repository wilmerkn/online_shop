package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Product {
    public static PreparedStatement st;
    public static ResultSet rs;

    public static ArrayList<ArrayList<String>> selectProducts(Connection c) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        data.clear();
        try {
            st = c.prepareStatement("SELECT * FROM product");
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
                rowData.add(rs.getString("discount_id"));


                data.add(rowData);
                c.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }







    // Displaying Products
    public static ArrayList<ArrayList<String>> displayDiscountProductsWithNameSearch(Connection c, String nameSearch) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        data.clear();
        try {
            st = c.prepareStatement("SELECT * FROM product where product_name = ?");
            st.setString(1,nameSearch);
            //st = conn.prepareCall("{CALL selectproduct}");
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
                rowData.add(rs.getString("discount_id"));


                data.add(rowData);
                c.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<ArrayList<String>> displayProducts(Connection c) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        data.clear();
        try {
            st = c.prepareCall("{CALL selectproduct}");
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
                rowData.add(rs.getString("discount_id"));


                data.add(rowData);
                c.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    // Displaying discount products
    public static ArrayList<ArrayList<String>> displayProductsWithNameSearch(Connection c, String searchName){
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();


        data.clear();

        try {
            //st = conn.prepareCall("{CALL selectallproduct}");
            st = c.prepareStatement("Select * from product where product_name = ? and discount_id = NULL");
            st.setString(1,searchName);
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
                rowData.add(rs.getString("discount_id"));


                data.add(rowData);
                c.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<ArrayList<String>> displayDiscountProducts1(Connection c){
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        data.clear();

        try {
            st = c.prepareCall("{CALL selectallproduct}");
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
                c.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
