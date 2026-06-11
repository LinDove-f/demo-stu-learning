package com.attust.mp.module.controller;

import com.attust.mp.common.Result;
import com.attust.mp.dto.LoginDTO;
import com.attust.mp.module.service.SysUserService;
import com.attust.mp.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:06
 */
@RestController
@RequestMapping("/student")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        return Result.success(sysUserService.login(loginDTO));
    }
}
