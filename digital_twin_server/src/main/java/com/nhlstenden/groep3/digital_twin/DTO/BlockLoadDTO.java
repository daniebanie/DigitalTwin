package com.nhlstenden.groep3.digital_twin.DTO;

import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import org.locationtech.jts.geom.Polygon;
import tools.jackson.databind.JsonNode;

public class BlockLoadDTO {

        private Long id;

        private BlockType blockType;

        private Long mapId;

        private JsonNode geometry;

        private double height;

        public BlockLoadDTO() {
        }

        public BlockLoadDTO(Long id, BlockType blockType, Long mapId, JsonNode geometry, double height) {
            this.id = id;
            this.blockType = blockType;
            this.mapId = mapId;
            this.geometry = geometry;
            this.height = height;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BlockType getBlockType() {
            return blockType;
        }

        public void setBlockType(BlockType blockType) {
            this.blockType = blockType;
        }

        public Long getMapId() {
            return mapId;
        }

        public void setMapId(Long mapId) {
            this.mapId = mapId;
        }


        public JsonNode getGeometry() {
            return geometry;
        }

        public void setGeometry(JsonNode geometry) {
            this.geometry = geometry;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

    }
