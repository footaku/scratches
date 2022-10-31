package com.example.footaku.graal;

import com.example.footaku.graal.data.Content;

import java.sql.Connection;
import java.sql.SQLException;

public class Writer {
    private static final String INSERT = """
                insert into content(code, description) values (?, ?)
            """.stripIndent();
    private static final String DELETE = """
                delete from content where code = ?
            """.stripIndent();

    private final Connection con;

    public Writer(Connection con) {
        this.con = con;
    }

    public int write(Content content) {
        try (var statement = con.prepareStatement(INSERT)) {
            statement.setInt(1, content.code());
            statement.setString(2, content.description());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(Content content) {
        try (var statement = con.prepareStatement(DELETE)) {
            statement.setInt(1, content.code());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
