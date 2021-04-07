package com.viettel.demo.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.viettel.demo.model.entity.Location;
import com.viettel.demo.model.entity.QLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>,
        QuerydslPredicateExecutor<Location>, QuerydslBinderCustomizer<QLocation> {

    List<Location> findAllByLocationNameEquals(String locationCode);

    Page<Location> findAll(Specification<Location>specs, Pageable pageable);

    Location getLocationByLocationId(int locationId);

    @Override
    default void customize(QuerydslBindings bindings, QLocation root) {
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(root.locationName);
    }
}
