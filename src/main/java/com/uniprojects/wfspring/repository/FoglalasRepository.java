package com.uniprojects.wfspring.repository;

import com.uniprojects.wfspring.data.entity.FoglalasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoglalasRepository extends JpaRepository<FoglalasEntity, Long> {
}
