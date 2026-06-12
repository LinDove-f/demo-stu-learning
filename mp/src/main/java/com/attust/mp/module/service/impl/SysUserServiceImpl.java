package com.attust.mp.module.service.impl;

import com.attust.mp.common.TokenStore;
import com.attust.mp.dto.LoginDTO;
import com.attust.mp.module.entity.SysUserEntity;
import com.attust.mp.module.mapper.SysUserMapper;
import com.attust.mp.module.service.SysUserService;
import com.attust.mp.vo.LoginVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        LambdaQueryWrapper<SysUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserEntity::getUsername, loginDTO.getUsername());
        SysUserEntity user = this.getOne(wrapper);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        if(!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        String token = UUID.randomUUID().toString();

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token).setUsername(user.getUsername()).setNickname(user.getNickname()).setRole(user.getRole());
        TokenStore.put(token,loginVO);
        return loginVO;
    }
}
