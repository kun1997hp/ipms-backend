package com.viettel.demo.model.entity;

import java.io.Serializable;

public class MappingTableDataId implements Serializable {

    private Integer networkTypeId;
    private Integer networkClassId;
    private Integer areaId;

    public MappingTableDataId(Integer networkTypeId , Integer networkClassId, Integer areaId) {
        this.networkTypeId = networkTypeId;
        this.networkClassId = networkClassId;
        this.areaId = areaId;
    }

    public boolean equals(Object object) {
        if (object instanceof MappingTableDataId) {
            MappingTableDataId mappingTableDataId = (MappingTableDataId)object;
            return networkTypeId.equals(mappingTableDataId.networkTypeId) && networkClassId.equals(mappingTableDataId.networkClassId) && areaId.equals(mappingTableDataId.areaId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (int)(networkTypeId.hashCode() + networkClassId.hashCode() + areaId.hashCode());
    }
}