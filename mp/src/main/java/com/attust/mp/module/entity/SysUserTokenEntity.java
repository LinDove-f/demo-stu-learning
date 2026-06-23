package com.attust.mp.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author fqlStart
 * {@code @create} 2026-06-23-18:17
 */
@TableName("sys_user_token")
@Data
@Accessors(chain = true)
public class SysUserTokenEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String token;
    private LocalDateTime expireTime;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
