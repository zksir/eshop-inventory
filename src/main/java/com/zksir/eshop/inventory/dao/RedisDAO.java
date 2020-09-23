package com.zksir.eshop.inventory.dao;

/**
 * redis dao
 */
public interface RedisDAO {
    void set(String key, String value);
    String get(String key);
}