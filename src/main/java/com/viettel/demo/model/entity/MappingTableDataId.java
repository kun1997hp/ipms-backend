package com.viettel.demo.model.entity;

import java.io.Serializable;

public class MappingTableDataId implements Serializable {

//    private Integer networkByNetworkTypeId;
    private Integer networkByNetworkClassId;
    private Integer locationByAreaId;

    public MappingTableDataId(Integer networkClassId, Integer areaId) {
//        this.networkByNetworkTypeId = networkTypeId;
        this.networkByNetworkClassId = networkClassId;
        this.locationByAreaId = areaId;
    }

    public boolean equals(Object object) {
        if (object instanceof MappingTableDataId) {
            MappingTableDataId mappingTableDataId = (MappingTableDataId)object;
            return networkByNetworkClassId.equals(mappingTableDataId.networkByNetworkClassId) && locationByAreaId.equals(mappingTableDataId.locationByAreaId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (int)(networkByNetworkClassId.hashCode() + locationByAreaId.hashCode());
    }
}