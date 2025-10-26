package com.example.summaryH2Sql;

import org.h2.tools.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/api/database")
public class DatabaseExportController {

    @Autowired
    private DataSource dataSource;

    @GetMapping(value = "/export-ddl", produces = MediaType.TEXT_PLAIN_VALUE)
    public String exportDDL() {
        StringBuilder result = new StringBuilder();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // H2 的 SCRIPT 命令直接生成 DDL
            ResultSet rs = stmt.executeQuery("SCRIPT");

            while (rs.next()) {
                result.append(rs.getString(1)).append("\n");
            }

            rs.close();

            return result.toString();

        } catch (SQLException e) {
            result.append("-- Error exporting DDL\n");
            result.append("-- ").append(e.getMessage()).append("\n");
            return result.toString();
        }
    }

    @GetMapping("/health")
    public String health() {
        return "Database Export API is running!";
    }
}