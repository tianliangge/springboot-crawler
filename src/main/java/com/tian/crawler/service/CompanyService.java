package com.tian.crawler.service;

import com.tian.crawler.entity.CompanyEntity;

/**
 * 公司服务接口
 */
public interface CompanyService {
    /**
     * 保存检测机构的信息
     * @param companyEntity
     */
    void save(CompanyEntity companyEntity);
}
