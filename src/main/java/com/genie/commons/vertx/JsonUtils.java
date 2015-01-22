package com.genie.commons.vertx;

import com.genie.commons.geo.GeoPos;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by d032459 on 14/12/23.
 */
public class JsonUtils {
    private final  static SimpleDateFormat JSON_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
    public final static GeoPos toGeoPos(JsonArray array) {
        return new GeoPos((Number) array.get(0), (Number) array.get(1));
    }

    public final static JsonArray toJson(GeoPos p) {
        if(p == null)
            return new JsonArray();
        JsonWriter json = new JsonWriter();
        json.array().value(p.lng).value(p.lat).endArray();
        return json.asJsonArray();
    }

    public final static Date toDate(String jsonStr) throws ParseException {
        return JSON_FORMAT.parse(jsonStr);

    }

    public final static String toJsonString(Date d) {
        return  JSON_FORMAT.format(d);
    }

    public static Properties toProperties(JsonObject json) {

        final Properties properties = new Properties();
        for(String k : json.getFieldNames()) {
            properties.put(k, json.getString(k));
        }
        return properties;
    }

    public static List<GeoPos> getGeoJsonPoints(JsonArray jsonArray) {
        ArrayList<GeoPos> list = new ArrayList<GeoPos>();
        Iterator<Object> it = jsonArray.iterator();
        while (it.hasNext()) {
            JsonObject feature = (JsonObject) it.next();
            JsonObject geometry = feature.getObject("geometry");
            if("Point".equals(geometry.getField("type"))) {
                JsonArray coord = geometry.getArray("coordinates");
                double lng = coord.get(0);
                double lat = coord.get(1);
                list.add(new GeoPos(lng, lat));
            }
        }

        return list;

    }

    public static JsonObject read(Reader in) throws IOException {
        BufferedReader br = new BufferedReader(in);

        String  thisLine = null;
        StringBuilder builder = new StringBuilder();

        while ((thisLine = br.readLine()) != null) {
            builder.append(thisLine);

        }
        br.close();
        return new JsonObject(builder.toString());
    }

    public static void write(JsonObject obj, Writer out) throws  IOException {
        BufferedWriter w = new BufferedWriter(out);
        w.write(obj.encode());
        w.close();
    }
}
