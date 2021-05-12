package com.viettel.demo.repository;

import com.viettel.demo.model.entity.MappingTableData;
import com.viettel.demo.model.entity.MappingTableDataId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface MappingTableDataRepository extends JpaRepository<MappingTableData, MappingTableDataId> {

    Page<MappingTableData> findAll(Specification<MappingTableData> specs, Pageable pageable);

    MappingTableData findByNetworkTypeIdAndNetworkClassIdAndAreaId(Integer networkTypeId, Integer networkClassId, Integer areaId);

    @Transactional
    @Modifying
    @Query(value = "insert into cat_mapping_table_data (area_id, insert_time, module, network_class_id, network_type_id, status, table_counter, table_counter_custom, table_syslog, update_time) values (to_number(:area_id), :insert_time, :module, :network_class_id, :network_type_id, :status, :table_counter, :table_counter_custom, :table_syslog, :update_time)", nativeQuery = true)
    void insertMappingTableData(@Param("area_id")Integer areaId, @Param("insert_time")Date insertTime, @Param("module")String module, @Param("network_class_id")Integer networkClassId, @Param("network_type_id")Integer networkTypeId, @Param("status")Integer status, @Param("table_counter")String tableCounter, @Param("table_counter_custom")String tableCounterCustom, @Param("table_syslog")String tableSyslog, @Param("update_time")Date updateTime);

    @Transactional
    @Modifying
    @Query(value = "update cat_mapping_table_data set area_id = to_number(:area_id), insert_time = :insert_time, module = :module, network_class_id = :network_class_id, network_type_id = :network_type_id, status = :status, table_counter = :table_counter, table_counter_custom = :table_counter_custom, table_syslog = :table_syslog, update_time = :update_time where network_type_id = :old_network_type_id and network_class_id = :old_network_class_id and ((:old_area_id is null and area_id is null) or (:old_area_id is not null and area_id = to_number(:old_area_id)))", nativeQuery = true)
    void updateMappingTableData(@Param("old_network_type_id")Integer oldNetworkTypeId, @Param("old_network_class_id")Integer oldNetworkClassId, @Param("old_area_id")Integer oldAreaId, @Param("area_id")Integer areaId, @Param("insert_time")Date insertTime, @Param("module")String module, @Param("network_class_id")Integer networkClassId, @Param("network_type_id")Integer networkTypeId, @Param("status")Integer status, @Param("table_counter")String tableCounter, @Param("table_counter_custom")String tableCounterCustom, @Param("table_syslog")String tableSyslog, @Param("update_time")Date updateTime);

    @Transactional
    @Modifying
    @Query(value = "delete from cat_mapping_table_data where network_type_id = :network_type_id and network_class_id = :network_class_id and ((:area_id is null and area_id is null) or (:area_id is not null and area_id = to_number(:area_id)))", nativeQuery = true)
    void deleteMappingTableData(@Param("network_type_id")Integer networkTypeId, @Param("network_class_id")Integer networkClassId, @Param("area_id")Integer areaId);
}
