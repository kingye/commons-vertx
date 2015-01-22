package com.genie.commons.vertx.route;

import com.genie.commons.network.Section;
import com.genie.commons.route.Route;
import com.genie.commons.vertx.JsonSerializer;
import com.genie.commons.vertx.JsonWriter;
import com.genie.commons.vertx.JsonUtils;
import com.genie.commons.vertx.network.SectionSerializer;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by d032459 on 15/1/6.
 */
public class RouteSerializer implements JsonSerializer<Route> {


    public final static RouteSerializer instance = new RouteSerializer();
    private RouteSerializer() {

    }
    @Override
    public JsonObject serialize(Route route) {
        JsonWriter json = new JsonWriter();
        json.object().key("distance").value(route.getDistance())
                .key("origin").array().value(route.getOrigin().lng).value(route.getOrigin().lat).endArray()
                .key("destination").array().value(route.getDestination().lng).value(route.getDestination().lat).endArray()
                .key("sections").array();
        for(Section s : route) {
            json.value(SectionSerializer.instance.serialize(s));
        }
        json.endArray();
        json.endObject();
        return json.asJsonObject();
    }

    @Override
    public Route deserialize(JsonObject obj) {
        JsonArray origin = obj.getArray("origin");
        JsonArray destination = obj.getArray("destination");
        if(origin == null || destination == null)
            return null;

        Route route = new Route(JsonUtils.toGeoPos(origin), JsonUtils.toGeoPos(destination));
        JsonArray sections = obj.getArray("sections");
        for(Object s : sections) {
            JsonObject j = (JsonObject) s;
            route.add(SectionSerializer.instance.deserialize(j));
        }
        return route;
    }





}
