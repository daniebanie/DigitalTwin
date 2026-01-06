package com.nhlstenden.groep3.digital_twin.Repository;

import com.nhlstenden.groep3.digital_twin.Model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
