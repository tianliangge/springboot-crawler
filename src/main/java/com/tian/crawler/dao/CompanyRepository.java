package com.tian.crawler.dao;


import com.tian.crawler.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 检测公司
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
}
