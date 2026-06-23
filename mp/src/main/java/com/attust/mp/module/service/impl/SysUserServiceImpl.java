package com.attust.mp.module.service.impl;

import com.attust.mp.common.TokenStore;
import com.attust.mp.dto.LoginDTO;
import com.attust.mp.exception.BusinessException;
import com.attust.mp.module.entity.SysUserEntity;
import com.attust.mp.module.entity.SysUserTokenEntity;
import com.attust.mp.module.mapper.SysUserMapper;
import com.attust.mp.module.mapper.SysUserTokenMapper;
import com.attust.mp.module.service.SysUserService;
import com.attust.mp.vo.LoginVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        log.info("[Flow-3] 进入 SysUserServiceImpl.login, username={}", loginDTO.getUsername());

        LambdaQueryWrapper<SysUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserEntity::getUsername, loginDTO.getUsername());
        SysUserEntity user = this.getOne(wrapper);

        if (user == null) {
            log.warn("[AUTH] 用户名不存在, username={}",loginDTO.getUsername());

            throw new BusinessException(401,"用户不存在");
        }

        if(!user.getPassword().equals(loginDTO.getPassword())) {
            log.warn("[AUTH] 密码错误，username={}",loginDTO.getUsername());

            throw new BusinessException(401,"密码错误");
        }
        String token = UUID.randomUUID().toString();

        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token).setUsername(user.getUsername()).setNickname(user.getNickname()).setRole(user.getRole());

        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(user.getId());
        tokenEntity.setToken(token);
        tokenEntity.setExpireTime(LocalDateTime.now().plusHours(2));
        tokenEntity.setStatus(1);

        sysUserTokenMapper.insert(tokenEntity);

        log.info("[AUTH] 登录成功, username={}", user.getUsername());

        return loginVO;
    }
}
