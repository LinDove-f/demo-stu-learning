package com.attust.mp.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:18
 */
@Data
@Accessors(chain = true)
public class LoginVO {
    private String token;
    private String username;
    private String nickname;
    private String role;
}
