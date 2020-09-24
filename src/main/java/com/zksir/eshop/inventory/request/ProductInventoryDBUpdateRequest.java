package com.zksir.eshop.inventory.request;

/**
 * 更改库存的请求
 * cache aside pattern
 *
 * （1）删除缓存
 * （2）更新数据库
 */
public class ProductInventoryDBUpdateRequest implements Request {
    @Override
    public void process() {

    }

    @Override
    public Integer getProductId() {
        return null;
    }

    @Override
    public boolean isForceRefresh() {
        return false;
    }
}