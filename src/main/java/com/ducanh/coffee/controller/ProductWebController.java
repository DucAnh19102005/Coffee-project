package com.ducanh.coffee.controller;

import com.ducanh.coffee.entity.Account;
import com.ducanh.coffee.entity.Product;
import com.ducanh.coffee.service.CategoryService;
import com.ducanh.coffee.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductWebController {

    //tiêm ProductService qua nhiều cách: field, constructor, setter
    @Autowired
    private ProductService productService; //tự IoC Container của Spring new, tiêm

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String showProducts(@RequestParam(value = "kw", required = false) String kw, Model model, HttpSession session) {

        //TODO: CODE CÒN BAD SMELLS (ROBERT C. MARTIN -> SOLID, CLEAN CODE)
        //TODO: REFACTOR, TÁCH HÀM, TỐI ƯU CODE
        Account acc = (Account) session.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }

        if (kw != null && !kw.equals("")) {
            model.addAttribute("listProduct", productService.searchProductByName(kw));
        } else {
            model.addAttribute("listProduct", productService.getAllProducts());
        }
        return "products";
    }

    @GetMapping("/products/update/{id}")
    public String edit(@PathVariable("id") String id, Model model, HttpSession session) {

        //TODO: CODE CÒN BAD SMELLS (ROBERT C. MARTIN -> SOLID, CLEAN CODE)
        //TODO: REFACTOR, TÁCH HÀM, TỐI ƯU CODE
        Account acc = (Account) session.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }
        if(acc.getRole() == 2){
            return "redirect:/products";
        }

        model.addAttribute("selectedProduct", productService.getProductById(id));
        model.addAttribute("cates", categoryService.getAllCategories());
        model.addAttribute("formMode", "edit");
        return "product-form";
    }

    @GetMapping("/products/new")
    public String create(Model model, HttpSession session) {

        //TODO: CODE CÒN BAD SMELLS (ROBERT C. MARTIN -> SOLID, CLEAN CODE)
        //TODO: REFACTOR, TÁCH HÀM, TỐI ƯU CODE
        Account acc = (Account) session.getAttribute("loggedInUser");
        if (acc == null) {
            return "redirect:/login";
        }
        if(acc.getRole() == 2){
            return "redirect:/products";
        }

        model.addAttribute("selectedProduct", new Product());
        model.addAttribute("cates", categoryService.getAllCategories());
        model.addAttribute("formMode", "new");
        return "product-form";
    }

    @PostMapping("/products/save")
    public String save(@Valid @ModelAttribute("selectedProduct") Product product, BindingResult result, Model model, @RequestParam("mode") String mode) {
        //Nếu có lỗi thì trở lại form để nhập lại
        if (result.hasErrors()) {
            model.addAttribute("cates", categoryService.getAllCategories());
            model.addAttribute("formMode", mode);
            return "product-form"; //đã gửi kèm BindingResult lên form
        }

        if (mode.equals("new")) {
            if (productService.isExist(product.getId())) {
                model.addAttribute("cates", categoryService.getAllCategories());
                model.addAttribute("formMode", mode);
                model.addAttribute("duplicated", "Id has duplicated!!!");
                return "product-form";
            }
        }

        productService.saveProduct(product); //nếu id mới thì thêm vào table, nếu id cũ thì update
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        productService.deleteProduct(productService.getProductById(id));
        return "redirect:/products";
    }
}
