package com.attust.demo_student.service.impl;

import com.attust.demo_student.entity.Student;
import com.attust.demo_student.mapper.StudentMapper;
import com.attust.demo_student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fqlstart
 * @create 2026-06-03-21:18
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //获取学生列表
    @Override
    public List<Student> list() {
        return studentMapper.selectList();
    }

    //跟据学生id获取学生信息
    @Override
    public Student getById(Long id) {
        return studentMapper.selectById(id);
    }

    //新增学生信息
    @Override
    public void add(Student student) {
        studentMapper.insert(student);
    }

    //修改学生信息
    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    //删除学生信息
    @Override
    public void delete(Long id) {
        studentMapper.delete(id);
    }

    //模糊查询（跟据姓名）
    @Override
    public List<Student> getByName(String name) {
        return studentMapper.selectByName(name);
    }

    //分页查询学生列表
    @Override
    public List<Student> page(Integer page, Integer pageSize) {
        int offSet = (page - 1) * pageSize;
        return studentMapper.page(offSet, pageSize);
    }
}
