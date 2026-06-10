package com.attust.demo_student.service;

import com.attust.demo_student.entity.Student;

import java.util.List;

/**
 * @author fqlstart
 * @create 2026-06-03-21:15
 */
public interface StudentService {
    //获取学生列表
    List<Student> list();

    //跟据学生id获取学生信息
    Student getById(Long id);

    //新增学生信息
    void add(Student student);

    //修改学生信息
    void update(Student student);

    //删除学生信息
    void delete(Long id);

    //模糊查询（跟据姓名）
    List<Student> getByName(String name);

    //分页查询学生列表
    List<Student> page(Integer page, Integer pageSize);
}
