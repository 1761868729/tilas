package org.example.tilas.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tilas.pojo.Dept;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门数据
     */
    @Select("select * from dept")
    List<Dept> list();
    /**
     * 根据Id删除部门
     */
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 修改部门
     */
    @Update("update dept set name = '#{name}' where id = '#{id}'")
    void updateById(String name,Integer id);
}
