package org.example.tilas.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    //ID
    private Integer id;
    //操作人ID
    private Integer operateUser;
    //操作时间
    private LocalDateTime operateTime;
    //操作类名
    private String className;
    //操作方法名
    private String methodName;
    //操作方法参数
    private String methodParams;
    //操作方法返回值
    private String returnValue;
    //操作耗时
    private Long costTime;
}
