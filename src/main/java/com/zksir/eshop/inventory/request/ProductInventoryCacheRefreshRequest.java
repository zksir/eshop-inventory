package com.zksir.eshop.inventory.request;

/**
 * 重新加载商品库存的缓存
 */
public class ProductInventoryCacheRefreshRequest implements Request {
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
