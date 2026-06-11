package com.attust.mp.module.service;

import com.attust.mp.dto.LoginDTO;
import com.attust.mp.module.entity.SysUserEntity;
import com.attust.mp.vo.LoginVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:03
 */
public interface SysUserService extends IService<SysUserEntity> {
    LoginVO login(LoginDTO loginDTO);
}
