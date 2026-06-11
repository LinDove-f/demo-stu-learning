package com.attust.mp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fqlStart
 * {@code @create} 2026-06-11-14:17
 */
@Data
@Accessors(chain = true)
public class LoginDTO {
    private String username;
    private String password;
}
