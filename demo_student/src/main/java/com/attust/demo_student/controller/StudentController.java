package com.attust.demo_student.controller;

import com.attust.demo_student.common.Result;
import com.attust.demo_student.entity.Student;
import com.attust.demo_student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fqlstart
 * @create 2026-06-03-21:27
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //获取学生列表
    @GetMapping("/list")
    public Result<List<Student>> list(){
        return Result.success(studentService.list());
    }

    //跟据学生id获取学生信息
    @GetMapping("/{id}")
    public  Result<Student> getById(@PathVariable Long id){
        return Result.success(studentService.getById(id));
    }

    //新增学生信息
    @PostMapping("/add")
    public Result<Student> add(@RequestBody Student student){
        studentService.add(student);
        return Result.success(student);
    }

    //修改学生信息
    @PutMapping("/update")
    public Result<Student> update(@RequestBody Student student){
        studentService.update(student);
        return Result.success(student);
    }

    //删除学生信息
    @DeleteMapping("/delete/{id}")
    public Result<Long> delete(@PathVariable Long id){
        studentService.delete(id);
        return Result.success(id);
    }

    //模糊查询（跟据姓名）
    @GetMapping("/search")
    public Result<List<Student>> search(@RequestParam String name) {
        return Result.success(studentService.getByName(name));
    }

    //分页查询学生列表
    @GetMapping("/page")
    public Result<List<Student>> page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return Result.success(studentService.page(page, pageSize));
    }
}
