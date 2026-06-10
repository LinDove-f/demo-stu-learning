package com.attust.demo_student.mapper;

import com.attust.demo_student.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fqlstart
 * @create 2026-06-03-21:05
 */
@Mapper
public interface StudentMapper {
    //查询学生列表
    List<Student> selectList();

    //跟据学生id查询学生信息
    Student selectById(Long id);

    //新增学生信息
    void insert(Student student);

    //修改学生信息
    void update(Student student);

    //删除学生信息
    void delete(Long id);

    //模糊查询（跟据姓名）
    List<Student> selectByName(String name);

    //分页查询学生列表
    List<Student> page(@Param("offSet") Integer page,@Param("pageSize") Integer pageSize);
}
