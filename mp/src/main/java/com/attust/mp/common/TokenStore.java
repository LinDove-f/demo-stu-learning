package com.attust.mp.common;

import com.attust.mp.vo.LoginVO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fqlStart
 * {@code @create} 2026-06-12-9:02
 */
public class TokenStore {
    private static final Map<String, LoginVO> TOKEN_MAP = new ConcurrentHashMap<>();

    public static void put(String token, LoginVO loginVO) {
        TOKEN_MAP.put(token, loginVO);
    }

    public static LoginVO get(String token) {
        return TOKEN_MAP.get(token);
    }

    public static void remove(String token) {
        TOKEN_MAP.remove(token);
    }
}
