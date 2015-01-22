package com.genie.commons.vertx;

import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonElement;
import org.vertx.java.core.json.JsonObject;

import java.util.Date;
import java.util.Stack;

/**
 * Created by d032459 on 14/12/22.
 */
public class JsonWriter {
    Stack<JsonElement> jsonStack;
    private String key;
    private JsonElement element;
    public JsonWriter() {
       jsonStack = new Stack<JsonElement>();
    }


    public JsonWriter object(JsonObject obj) {
        if(!jsonStack.isEmpty()) {
            JsonElement e = jsonStack.peek();
            if (e != null) {
                if (e.isObject() && key != null) {

                    e.asObject().putObject(key, obj);


                } else if (e.isArray()) {
                    e.asArray().addObject(obj);
                }
            }
        }
        jsonStack.push(obj);


        return this;
    }
    public JsonWriter array(JsonArray obj) {
        if(!jsonStack.isEmpty()) {
            JsonElement e = jsonStack.peek();
            if (e != null) {
                if (e.isObject() && key != null) {

                    e.asObject().putArray(key, obj);


                } else if (e.isArray()) {
                    e.asArray().addArray(obj);
                }
            }
        }
        jsonStack.push(obj);


        return this;
    }
    public JsonWriter object() {
        JsonObject obj = new JsonObject();
        if(!jsonStack.isEmpty()) {
            JsonElement e = jsonStack.peek();
            if (e != null) {
                if (e.isObject() && key != null) {

                    e.asObject().putObject(key, obj);


                } else if (e.isArray()) {
                    e.asArray().addObject(obj);
                }
            }
        }
        jsonStack.push(obj);


        return this;
    }

    public JsonWriter endObject() {
        JsonElement element = jsonStack.pop();
        if(jsonStack.isEmpty())
            this.element = element;
        return  this;
    }

    public JsonWriter array() {
        JsonArray obj = new JsonArray();
        if(!jsonStack.isEmpty()) {
            JsonElement e = jsonStack.peek();
            if (e != null) {
                if (e.isObject() && key != null) {

                    e.asObject().putArray(key, obj);


                } else if (e.isArray()) {
                    e.asArray().addArray(obj);
                }
            }
        }
        jsonStack.push(obj);


        return this;
    }

    public JsonWriter endArray() {
        JsonElement element = jsonStack.pop();
        if(jsonStack.isEmpty())
            this.element = element;
        return  this;
    }

    public JsonWriter key(String key) {
        this.key = key;
        return this;
    }

    public JsonWriter value(String v) {
        JsonElement json = jsonStack.peek();
        if(json.isObject()) {
            JsonObject obj = (JsonObject) json;
            obj.putString(key, v);
        } else {
            JsonArray array = (JsonArray) json;
            array.addString(v);
        }
        return this;
    }

    public JsonWriter value(Object o) {
        JsonElement json = jsonStack.peek();
        if(json.isObject()) {
            JsonObject obj = (JsonObject) json;
            obj.putValue(key, o);
        } else {
            JsonArray array = (JsonArray) json;
            array.add(o);
        }
        return this;
    }

    public JsonWriter value(Number v) {
        JsonElement json = jsonStack.peek();
        if(json.isObject()) {
            JsonObject obj = (JsonObject) json;
            obj.putNumber(key, v);
        } else {
            JsonArray array = (JsonArray) json;
            array.addNumber(v);
        }
        return this;
    }




    public JsonWriter value(Date d) {
        JsonElement json = jsonStack.peek();
        if(json.isObject()) {
            JsonObject obj = (JsonObject) json;
            obj.putString(key, JsonUtils.toJsonString(d));
        } else {
            JsonArray array = (JsonArray) json;
            array.addString(JsonUtils.toJsonString(d));
        }
        return this;
    }

    public JsonWriter value(Boolean b) {
        JsonElement json = jsonStack.peek();
        if(json.isObject()) {
            JsonObject obj = (JsonObject) json;
            obj.putBoolean(key, b);
        } else {
            JsonArray array = (JsonArray) json;
            array.addBoolean(b);
        }
        return this;
    }

    public JsonWriter value(JsonObject v) {
        JsonElement json = jsonStack.peek();
        if(json.isObject()) {
            JsonObject obj = (JsonObject) json;
            obj.putObject(key, v);
        } else {
            JsonArray array = (JsonArray) json;
            array.addObject(v);
        }
        return this;
    }
    public JsonWriter value(JsonArray v) {
        JsonElement json = jsonStack.peek();
        if(json.isObject()) {
            JsonObject obj = (JsonObject) json;
            obj.putArray(key, v);
        } else {
            JsonArray array = (JsonArray) json;
            array.addArray(v);
        }
        return this;
    }
    public String toString() {
        if(element.isObject())
            return  element.asObject().encode();
        else
            return element.asArray().encode();
    }

    public JsonObject asJsonObject() {
        if(element.isObject())
            return element.asObject();
        else
            return null;
    }

    public JsonArray asJsonArray() {
        if(element.isArray())
            return element.asArray();
        else
            return null;
    }



}
