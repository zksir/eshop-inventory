package com.zksir.eshop.inventory.controller;

import com.zksir.eshop.inventory.model.ProductInventory;
import com.zksir.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.zksir.eshop.inventory.request.Request;
import com.zksir.eshop.inventory.service.ProductInventoryService;
import com.zksir.eshop.inventory.service.RequestAsyncProcessService;
import com.zksir.eshop.inventory.vo.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品库存Controller
 */
@Controller
public class ProductInventoryController {
    @Resource
    private RequestAsyncProcessService requestAsyncProcessService;

    @Resource
    private ProductInventoryService productInventoryService;

    /**
     * 更新商品库存
     */
    @RequestMapping("/updateProductInventory")
    @ResponseBody
    public Response updateProductInventory(ProductInventory productInventory) {
        System.out.println("===========日志===========: 接收到更新商品库存的请求，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());

        Response response = null;
        try {
            Request request = new ProductInventoryDBUpdateRequest(
                    productInventory,productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
