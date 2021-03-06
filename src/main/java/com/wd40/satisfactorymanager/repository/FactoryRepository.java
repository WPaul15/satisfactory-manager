package com.wd40.satisfactorymanager.repository;

import com.wd40.satisfactorymanager.model.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Integer> {}
