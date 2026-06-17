package com.attust.mp.module.controller;

import com.attust.mp.common.Result;
import com.attust.mp.dto.LoginDTO;
import com.attust.mp.module.service.SysUserService;
import com.attust.mp.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:06
 */
@RestController
public class LoginController {
    private static final  Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("[FLOW-2] 进入 LoginController.login, username={}", loginDTO.getUsername());

        LoginVO loginVO = sysUserService.login(loginDTO);

        log.info("[FLOW-2] 登录接口执行完成, username={}", loginDTO.getUsername());

        return Result.success(loginVO);
    }
}
