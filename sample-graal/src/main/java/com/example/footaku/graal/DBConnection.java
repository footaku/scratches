package com.example.footaku.graal;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/graal";

    private final String user;
    private final String pass;

    public DBConnection(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public Connection get() throws SQLException {
        var ds = new MysqlDataSource();
        ds.setUrl(URL);
        ds.setUser(user);
        ds.setPassword(pass);
        return ds.getConnection();
    }
}
