package org.example.tilas.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.tilas.pojo.DeptLog;

/**
 * @author dream
 */
@Mapper
public interface DeptLogMapper {
    @Insert("insert into dept_log(create_time,description) values (#{createTime},#{description})")
    void insert(DeptLog log);
}
