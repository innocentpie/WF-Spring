package com.uniprojects.wfspring.repository;

import com.uniprojects.wfspring.data.entity.FoglalasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoglalasRepository extends JpaRepository<FoglalasEntity, Long> {
    List<FoglalasEntity> findAllByFelhasznaloId(final long felhasznaloId);
}
