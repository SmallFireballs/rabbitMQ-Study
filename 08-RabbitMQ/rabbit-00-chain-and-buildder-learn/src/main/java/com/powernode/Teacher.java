package com.powernode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //支持链式编程
@Builder //支持使用建造者模式创建对象
public class Teacher implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String address;

}
