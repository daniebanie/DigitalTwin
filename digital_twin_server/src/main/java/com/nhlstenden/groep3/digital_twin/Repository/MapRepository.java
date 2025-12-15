package com.nhlstenden.groep3.digital_twin.Repository;

import com.nhlstenden.groep3.digital_twin.Model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
    Map findMapBy(String name);
}
