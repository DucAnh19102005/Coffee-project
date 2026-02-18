package com.ducanh.coffee.apicontroller;

import com.ducanh.coffee.entity.Product;
import com.ducanh.coffee.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductApiController {

    /*các method
    GET     : LẤY DỮ LIỆU
    POST    : TẠO MỚI
    PUT     : CẬP NHẬT TOÀN BỘ OBJECT
    PATCH   : CẬP NHẬT MỘT PHẦN
    DELETE  : XÓA
     */

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(
            summary = "Create new product",
            description = "API to create a new coffee product"
    )

    @ApiResponse(
            responseCode = "201",
            description = "Product created successfully"
    )
    @PostMapping("/products")
    public String saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/products/{id}")
    public Product searchProductById(@PathVariable("id") String id) {
        return productService.searchProductById(id);
    }

}
