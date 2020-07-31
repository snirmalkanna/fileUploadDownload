package com.nks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nks.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

}
