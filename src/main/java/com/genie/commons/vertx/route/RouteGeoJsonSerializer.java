package com.genie.commons.vertx.route;

import com.genie.commons.geo.GeoPos;
import com.genie.commons.network.Section;
import com.genie.commons.route.Route;
import com.genie.commons.vertx.JsonSerializer;
import com.genie.commons.vertx.JsonUtils;
import com.genie.commons.vertx.JsonWriter;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by d032459 on 15/1/10.
 */
public class RouteGeoJsonSerializer implements JsonSerializer<Route> {
    public final static RouteGeoJsonSerializer instance = new RouteGeoJsonSerializer();
    @Override
    public JsonObject serialize(Route route) {
        JsonWriter json = new JsonWriter();
        json.object().key("type").value("FeatureCollection");
        json.key("features").array();

        json.object().key("type").value("Feature");
        json.key("geometry").object().key("type").value("MultiLineString");
        json.key("coordinates").array();
        for (Section s : route) {
            json.array();
            for (GeoPos p : s.getPath()) {
                json.value(JsonUtils.toJson(p));
            }
            json.endArray();
        }
        json.endArray(); // coordinates

        json.endObject();// geometry
        json.endObject();// Feature

        json.endArray(); // features
        json.endObject();
        return json.asJsonObject();
    }

    @Override
    public Route deserialize(JsonObject obj) {
        return null;
    }
}
