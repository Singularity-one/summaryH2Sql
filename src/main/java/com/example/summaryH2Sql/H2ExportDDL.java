package com.example.summaryH2Sql;

import java.sql.SQLException;
import org.h2.tools.Script;

public class H2ExportDDL {
    public static void main(String[] args) throws SQLException {
        Script.main(new String[]{
                "-url", "jdbc:h2:mem:testdb",  // 你的 H2 連線 URL
                "-user", "sa",
                "-password", "",
                "-script", "init.sql",        // 導出的檔案
                "-options", "drop"            // 包含 drop table
        });
    }
}
