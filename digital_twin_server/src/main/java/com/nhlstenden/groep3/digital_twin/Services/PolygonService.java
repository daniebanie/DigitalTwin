package com.nhlstenden.groep3.digital_twin.Services;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

@Service
public class PolygonService {

    private final GeometryFactory geometryFactory = new GeometryFactory();


    public Polygon toPolygon(JsonNode node){
        Coordinate[] coords = new Coordinate[node.size()+1];

        for (int i = 0; i < node.size(); i++) {
            JsonNode tempNode = node.get(i);
            coords[i] = new Coordinate(tempNode.get("longitude").asDouble(), tempNode.get("latitude").asDouble());
        }

        coords[node.size()] = coords[0];
        LinearRing shell = geometryFactory.createLinearRing(coords);
        return geometryFactory.createPolygon(shell);
    }
}
