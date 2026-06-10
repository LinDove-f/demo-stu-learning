package com.attust.mp.module.service;

import com.attust.mp.module.entity.StudentEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author fqlstart
 * @create 2026-06-07-17:48
 */
public interface StudentService {
    // 获取所有学生信息
    List<StudentEntity> list();

    // 跟据id获取学生信息
    StudentEntity getById(Long id);

    // 添加学生信息
    void add(StudentEntity student);

    // 更新学生信息
    void update(StudentEntity student);

    // 删除学生信息
    void delete(Long id);

    // 根据姓名获取学生信息
    List<StudentEntity> getByName(String name);

    // 多条件查询
    List<StudentEntity> multiConditionQuery(String name, Integer minAge, Integer maxAge, String major);

    // 排序查询
    List<StudentEntity> orderByAgeDesc();

    // 分页查询
    IPage<StudentEntity> pageQuery(Long page, Long pageSize);

    // 跟据学生专业分页查询学生信息
    IPage<StudentEntity> pageQueryByMajor(Long page, Long pageSize, String major);
}