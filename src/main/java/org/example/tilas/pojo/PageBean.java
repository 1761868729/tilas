package org.example.tilas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

/*
  分页查询结果封装类
 */
public class PageBean {
    private Long total;
    private List<Emp> rows;
}
