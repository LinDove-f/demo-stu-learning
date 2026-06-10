package com.attust.mp.module.service.impl;

import com.attust.mp.module.entity.StudentEntity;
import com.attust.mp.module.mapper.StudentMapper;
import com.attust.mp.module.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fqlstart
 * {@code @create} 2026-06-07-17:49
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentEntity> list() {
        return studentMapper.selectList(null);
    }

    @Override
    public StudentEntity getById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public void add(StudentEntity student) {
        studentMapper.insert(student);
    }

    @Override
    public void update(StudentEntity student) {
        studentMapper.updateById(student);
    }

    @Override
    public void delete(Long id) {
        studentMapper.deleteById(id);
    }

    @Override
    public List<StudentEntity> getByName(String name) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StudentEntity::getName, name);
        return studentMapper.selectList(wrapper);
    }

    @Override
    public List<StudentEntity> multiConditionQuery(String name, Integer minAge, Integer maxAge, String major) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StudentEntity::getName,name)
                .between(StudentEntity::getAge,minAge,maxAge)
                .eq(StudentEntity::getMajor,"软件工程");
        return  studentMapper.selectList(wrapper);
    }

    @Override
    public List<StudentEntity> orderByAgeDesc() {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(StudentEntity::getAge);
        return studentMapper.selectList(wrapper);
    }

    @Override
    public IPage<StudentEntity> pageQuery(Long page, Long pageSize) {
        IPage<StudentEntity> iPage = new Page<>(page, pageSize);
        return studentMapper.selectPage(iPage, null);
    }

    @Override
    public  IPage<StudentEntity> pageQueryByMajor(Long page, Long pageSize, String major) {
        IPage<StudentEntity> iPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentEntity::getMajor, major);
        return studentMapper.selectPage(iPage, wrapper);
    }
}