package com.attust.mp.module.service.impl;

import com.attust.mp.common.PageResult;
import com.attust.mp.module.entity.StudentEntity;
import com.attust.mp.module.mapper.StudentMapper;
import com.attust.mp.module.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author fqlstart
 * {@code @create} 2026-06-07-17:49
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,StudentEntity>implements StudentService {

    @Override
    public PageResult<StudentEntity> queryPage(Long page, Long pageSize) {
        IPage<StudentEntity> iPage = new Page<>(page,pageSize);
        return new PageResult<>(this.page(iPage,null));
    }

    @Override
    public PageResult<StudentEntity> getByName(String name){
        LambdaQueryWrapper<StudentEntity> Wrapper = new LambdaQueryWrapper<>();
        Wrapper.eq(StudentEntity::getName,name);
        IPage<StudentEntity> iPage = new Page<>(1,10);
        return new PageResult<>(this.page(iPage,Wrapper));
    }
}