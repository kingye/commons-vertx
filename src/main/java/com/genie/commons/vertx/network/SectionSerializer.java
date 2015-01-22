package com.genie.commons.vertx.network;

import com.genie.commons.geo.GeoPos;
import com.genie.commons.network.Section;
import com.genie.commons.vertx.JsonSerializer;
import com.genie.commons.vertx.JsonWriter;
import com.genie.commons.vertx.JsonUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by d032459 on 15/1/6.
 */
public class SectionSerializer implements JsonSerializer<Section> {

    public final static SectionSerializer instance = new SectionSerializer();
    private SectionSerializer() {

    }
    @Override
    public JsonObject serialize(Section r) {
        JsonWriter json = new JsonWriter();
        json.object().key("sectionid").value(r.getId())
                .key("avgSpeed").value(r.getAvgSpeed())
                .key("distance").value(r.getDistance())
                .key("type").value(r.getType())
                .key("wayId").value(r.wayId)
                .key("path").array();
        for(GeoPos p : r.getPath()) {
            json.array().value(p.lng).value(p.lat).endArray();
        }
        json.endArray();
        json.endObject();
        return json.asJsonObject();
    }

    @Override
    public Section deserialize(JsonObject obj) {
        Section section = new Section(obj.getString("id"), obj.getString("wayId"));
        section.setAvgSpeed(obj.getNumber("avgSpeed").doubleValue());
        section.setType(obj.getString("type"));
        JsonArray path = obj.getArray("path");
        for(Object p : path) {
            JsonArray ja = (JsonArray) p;
            section.addPoint(JsonUtils.toGeoPos(ja));
        }
        section.calcDistance();
        return section;
    }

}
