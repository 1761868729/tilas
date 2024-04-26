package org.example.tilas.service.impl;

import org.example.tilas.mapper.DeptLogMapper;
import org.example.tilas.mapper.DeptMapper;
import org.example.tilas.mapper.EmpMapper;
import org.example.tilas.pojo.Dept;
import org.example.tilas.pojo.DeptLog;
import org.example.tilas.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServicelmpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogMapper deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //Spring事务管理
    //@Transactional(rollbackFor = Exception.class)
    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            //根据Id删除部门
            deptMapper.deleteById(id);
            //根据部门Id删除该部门下的员工
            empMapper.deleteById(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门操作，此次解散的时“+id+"+"部门号");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(String name, Integer id) {
        deptMapper.updateById(name, id);
    }


}
