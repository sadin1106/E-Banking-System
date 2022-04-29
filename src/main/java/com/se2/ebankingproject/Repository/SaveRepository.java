package com.se2.ebankingproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.se2.ebankingproject.Model.Save;

@Repository
public interface SaveRepository extends JpaRepository<Save, Integer>{

}