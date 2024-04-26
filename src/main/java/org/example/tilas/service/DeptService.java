package org.example.tilas.service;

import org.example.tilas.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门数据
     */

    List<Dept> list();
    /**
     * 删除部门
     */

    void delete(Integer id);

    /**
     * 添加部门
     */
    void add(Dept dept);

    /**
     * 修改部门
     */
    void update(String name,Integer id);

}
