package model;

import java.sql.*;
import java.util.ArrayList;

public class Supplier {

    public static PreparedStatement st;
    public static ResultSet rs;

    public static ArrayList<ArrayList<String>> selectSuppliers(Connection c) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        data.clear();
        try {

            st = c.prepareStatement("SELECT * FROM supplier");
            rs = st.executeQuery();
            int rowIndex = 0;

            while (rs.next()) {

                ArrayList<String> rowData = new ArrayList<String>();

                rowData.add(rs.getString("company_name"));
                rowData.add(rs.getString("address"));
                rowData.add(rs.getString("phone_number"));
                data.add(rowData);
                c.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
