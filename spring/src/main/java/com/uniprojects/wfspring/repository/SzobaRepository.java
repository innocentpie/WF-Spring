package com.uniprojects.wfspring.repository;

import com.uniprojects.wfspring.data.entity.SzobaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SzobaRepository extends JpaRepository<SzobaEntity, Long> {
}
