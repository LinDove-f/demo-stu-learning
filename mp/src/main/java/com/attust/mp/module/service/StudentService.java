package com.attust.mp.module.service;

import com.attust.mp.common.PageResult;
import com.attust.mp.module.entity.StudentEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author fqlstart
 * @create 2026-06-07-17:48
 */
public interface StudentService extends IService<StudentEntity> {

    // 获取所有学生信息

    // 跟据id获取学生信息

    // 添加学生信息

    // 更新学生信息

    // 删除学生信息

    // 根据姓名获取学生信息

    // 多条件查询
    PageResult<StudentEntity> getByName(String name);
    // 排序查询

    // 分页查询
    PageResult<StudentEntity> queryPage(Long page, Long pageSize);
    // 跟据学生专业分页查询学生信息
}