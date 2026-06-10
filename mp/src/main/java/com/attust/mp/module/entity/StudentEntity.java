package com.attust.mp.module.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author fqlstart
 * @create 2026-06-07-17:43
 */
@Data
@TableName("student")
public class StudentEntity {
    @TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String major;
    private String phone;
    private Date createTime;
}
