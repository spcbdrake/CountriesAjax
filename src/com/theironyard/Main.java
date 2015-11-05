package com.theironyard;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS countries (id IDENTITY, name VARCHAR, abbrev VARCHAR)");
    }

    public static void insertCountry(Connection conn, String name, String abbrev) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO countries VALUES (NULL, ?, ?)");
        stmt.setString(1, name);
        stmt.setString(2, abbrev);
        stmt.execute();
    }

    public static ArrayList<Country> selectCountries(Connection conn) throws SQLException {
        ArrayList<Country> countries = new ArrayList();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM countries");
        ResultSet results = stmt.executeQuery();
        while (results.next()) {
            Country country = new Country();
            country.id = results.getInt("id");
            country.name = results.getString("name");
            country.abbrev = results.getString("abbrev");
            countries.add(country);
        }
        return countries;
    }

    public static void main(String[] args) throws SQLException {
        // open database
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        createTables(conn);
    }
}
