package com.genie.commons.vertx.postgresql;

import com.genie.commons.vertx.JdbcHelper;

import org.postgresql.util.PGobject;
import org.vertx.java.core.json.JsonObject;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Created by d032459 on 15/1/2.
 */
public abstract class PostgreSQLHelper<T> extends JdbcHelper<T> {
    @Override
    public Connection createConnection(JsonObject jdbcConf) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return super.createConnection(jdbcConf);
    }


    public Object toJdbcValue(JsonObject json) throws SQLException {
            PGobject pg = new PGobject();
            pg.setType("json");
            pg.setValue(json.encode());
            return pg;


    }


    public JsonObject toJsonValue(Object jdbcValue) throws SQLException {

        PGobject pg = (PGobject) jdbcValue;
        if("json".equals(pg.getType()))
            return  new JsonObject(pg.getValue());
        else
            return new JsonObject();

    }
}
