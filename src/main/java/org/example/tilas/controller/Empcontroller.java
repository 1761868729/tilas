package org.example.tilas.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tilas.anno.Log;
import org.example.tilas.pojo.Emp;
import org.example.tilas.pojo.PageBean;
import org.example.tilas.pojo.Result;
import org.example.tilas.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class Empcontroller {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询，参数：{}，{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用Service分页查询
        PageBean pageBean=empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids：｛｝",ids);
        empService.delete(ids);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工，emp：｛｝",emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工信息，id：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
