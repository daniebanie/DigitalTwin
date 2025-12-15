package com.nhlstenden.groep3.digital_twin.Repository;

import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BlockTypeRepository extends JpaRepository<BlockType, Integer> {
    Optional<BlockType> findByBlockCode(String blockCode);
}