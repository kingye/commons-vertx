package com.genie.commons.vertx;

import org.vertx.java.core.json.JsonObject;

import java.sql.*;

/**
 * Created by d032459 on 15/1/21.
 */
public abstract class JdbcHelper<T> {
    public abstract T getObject(String id, ResultSet set) throws SQLException;
    public abstract void setStatement(T obj, String id, PreparedStatement stmt) throws SQLException;
    public Connection createConnection(JsonObject jdbcConf) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(jdbcConf.getString("url"), JsonUtils.toProperties(jdbcConf.getObject("properties")));
        return connection;
    }


}
