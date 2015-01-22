package com.genie.commons.vertx;

import org.vertx.java.core.json.JsonObject;

/**
 * Created by d032459 on 15/1/6.
 */
public interface JsonSerializer<E> {

    public JsonObject serialize(E obj);

    public E deserialize(JsonObject obj);
}
