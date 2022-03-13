package com.se2.ebankingproject.Repository;

import com.se2.ebankingproject.Model.InvestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestTypeRepository extends JpaRepository<InvestType, Integer>{

}
