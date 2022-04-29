package com.se2.ebankingproject.Repository;

import com.se2.ebankingproject.Model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Integer>{

}
