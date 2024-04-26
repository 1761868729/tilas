package org.example.tilas.service.impl;

import org.example.tilas.mapper.DeptLogMapper;
import org.example.tilas.pojo.DeptLog;
import org.example.tilas.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DeptLogServicelmpl implements DeptLogService {
    @Autowired
    private DeptLogMapper deptLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}