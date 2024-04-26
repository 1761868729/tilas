package org.example.tilas.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.example.tilas.anno.Log;
import org.example.tilas.pojo.Dept;
import org.example.tilas.pojo.Result;
import org.example.tilas.service.DeptService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    //日志函数
    //private static Logger log = (Logger) LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式为GET

    /**
     * 查询部门数据
     * @return
     */

    @GetMapping
    public Result list(){
        //日志记录
        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<Dept> deptList =deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

        log.info("根据id删除部门：{}",id);
        /*
          调用Service删除部门数据
         */
        deptService.delete(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：{}",dept);
        /*
          调用Service添加部门
         */
        deptService.add(dept);
        return Result.success();
    }
    @Log
    @PutMapping
    public Result update(@RequestBody String name, Integer id){
        log.info("修改部门：{}",id);
        /*
            调用Service修改部门
         */
        deptService.update(name,id);
        return Result.success();
    }

}
