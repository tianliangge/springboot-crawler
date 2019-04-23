package com.tian.crawler.service.impl;

import com.tian.crawler.dao.CompanyRepository;
import com.tian.crawler.entity.CompanyEntity;
import com.tian.crawler.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司接口服务类
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private final static Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(CompanyEntity companyEntity) {
        try {
            logger.info(">>>>>>>>>保存数据");
            companyRepository.save(companyEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
