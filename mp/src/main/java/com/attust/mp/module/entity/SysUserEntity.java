package com.attust.mp.module.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-13:59
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserEntity {
    @TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String role;
    private Date createTime;
}
