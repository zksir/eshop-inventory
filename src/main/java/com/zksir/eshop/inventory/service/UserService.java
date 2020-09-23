package com.zksir.eshop.inventory.service;

import com.zksir.eshop.inventory.model.User;

/**
 * 用户Service接口
 */
public interface UserService {
    /**
     * 查询用户信息
     * @return 用户信息
     */
    public User findUserInfo();

    /**
     * 查询redis中缓存的用户信息
     * @return
     */
    public User getCachedUserInfo();
}
