package com.genie.commons.vertx;

import org.vertx.java.core.json.JsonObject;

/**
 * Created by d032459 on 15/1/21.
 */
public class JsonMessages {
    final JsonWriter writer = new JsonWriter();
    public JsonMessages() {
        writer.object().key("messages").array();

    }
    public void info(String msg, int code) {
        writer.object().key("message").value(msg).key("type").value("info");
        if(code >= 0)
            writer.key("code").value(code);
        writer.endObject();
    }
    public void info(String msg) {
        info(msg, -1);
    }
    public void error(String msg, int code, Throwable e) {

        writer.object().key("message").value(msg).key("type").value("error");
        if(code >= 0) {
            writer.key("code").value(code);
        }
        writer.key("exception").array();
        StackTraceElement[] el = e.getStackTrace();
        for(int i = 0; i < el.length; i++) {
            writer.value(el[i].toString());
        }
        writer.endArray().endObject();

    }

    public void error(String msg, Throwable e) {
        error(msg, -1, e);
    }

    public void error(int code, Throwable e) {
        error(e.getMessage(), code, e);
    }

    public void error(Throwable e) {
        error(e.getMessage(), -1, e);
    }

    public JsonObject asJsonObject() {
        return writer.endArray().endObject().asJsonObject();
    }


}
