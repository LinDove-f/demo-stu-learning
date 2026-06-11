package com.attust.mp.module.mapper;

import com.attust.mp.dto.LoginDTO;
import com.attust.mp.module.entity.SysUserEntity;
import com.attust.mp.vo.LoginVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:01
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
}
