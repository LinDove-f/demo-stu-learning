package com.attust.mp.module.controller;

import com.attust.mp.common.PageResult;
import com.attust.mp.common.Result;
import com.attust.mp.module.entity.StudentEntity;
import com.attust.mp.module.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fqlstart
 * @create 2026-06-07-17:45
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public Result<List<StudentEntity>> list() {
        return Result.success(studentService.list());
    }

    @GetMapping("/getById")
    public Result<StudentEntity> getById(@RequestParam Long id) {
        return Result.success(studentService.getById(id));
    }

    @PostMapping("/add")
    public Result<StudentEntity> add(@RequestBody StudentEntity studentEntity) {
        studentService.add(studentEntity);
        return Result.success(studentEntity);
    }

    @PostMapping("/update")
    public Result<StudentEntity> update(@RequestBody StudentEntity studentEntity) {
        studentService.update(studentEntity);
        return Result.success(studentEntity);
    }

    @DeleteMapping("/delete")
    public Result<Void> delete(@RequestParam Long id) {
        studentService.delete(id);
        return Result.success();
    }

    @GetMapping("/getByName")
    public Result<List<StudentEntity>> getByName(@RequestParam String name) {
        return Result.success(studentService.getByName(name));
    }

    @GetMapping("/multiConditionQuery")
    public Result<List<StudentEntity>> multiConditionQuery(@RequestParam String name, @RequestParam Integer minAge, @RequestParam Integer maxAge, @RequestParam String major) {
        return Result.success(studentService.multiConditionQuery(name, minAge, maxAge, major));
    }

    @GetMapping("/orderByAgeDesc")
    public Result<List<StudentEntity>> orderByAgeDesc() {
        return Result.success(studentService.orderByAgeDesc());
    }

    @GetMapping("/pageQuery")
    public Result<IPage<StudentEntity>> pageQuery(@RequestParam Long page, @RequestParam Long pageSize) {
        return Result.success(studentService.pageQuery(page, pageSize));
    }

    @GetMapping("/pageQueryByMajor")
    public Result<IPage<StudentEntity>> pageQueryByMajor(@RequestParam Long page, @RequestParam Long pageSize, @RequestParam String major) {
        return Result.success(studentService.pageQueryByMajor(page, pageSize, major));
    }

    // 对PageResult的测试
    @GetMapping("/pageResult")
    public Result<PageResult<StudentEntity>> pageResult(@RequestParam Long page, @RequestParam Long pageSize, @RequestParam String major) {
        IPage<StudentEntity> iPage = studentService.pageQueryByMajor(page, pageSize, major);
        return Result.success(new PageResult<>(iPage));
    }
}