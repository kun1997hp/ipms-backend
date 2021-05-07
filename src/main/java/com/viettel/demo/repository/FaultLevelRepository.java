package com.viettel.demo.repository;

import com.viettel.demo.model.entity.FaultLevel;
import com.viettel.demo.model.view.FaultLevelNameView;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FaultLevelRepository extends JpaRepository<FaultLevel, Integer> {

    List<FaultLevelNameView> findBy();

    FaultLevel getFaultLevelByFaultLevelId(Integer faultLevelId);
}
