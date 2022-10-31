package com.example.footaku.graal;

import com.example.footaku.graal.data.Content;

import java.sql.Connection;
import java.sql.SQLException;

public class Reader {
    private static final String SELECT = """
                select code, description from content where code = ?
            """.stripIndent();

    private final Connection con;

    public Reader(Connection con) {
        this.con = con;
    }

    public Content read(Content content) {
        try (var statement = con.prepareStatement(SELECT)) {
            statement.setInt(1, content.code());
            var rs = statement.executeQuery();
            rs.next();
            return new Content(
                    rs.getInt("code"),
                    rs.getString("description")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
