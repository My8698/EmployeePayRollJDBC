package com.bridgelabz;

import org.junit.Test;

import java.sql.Connection;

public class JDBCDemo {

    @Test
    public void getDB_Connection() {
        Connection dbConnection = new JDBCConnection().getDBConnection();
        System.out.println(dbConnection);
    }
}
