package com.example.Demo2.controller;

import com.example.Demo2.entity.Product;
import com.example.Demo2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1) Show index page with two options: Add Product or Display Product
    @GetMapping
    public String index() {
        return "index";  // points to templates/index.html
    }

    // 2) Show form to add product
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";  // points to templates/add-product.html
    }

    // 3) Process form submission
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/product/display";
    }

    // 4) Display all products
    @GetMapping("/display")
    public String displayAllProducts(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "display-product"; // points to templates/display-product.html
    }

    // 5) (Optional) Display products by a specific category
    @GetMapping("/category/{category}")
    public String displayProductsByCategory(@PathVariable String category, Model model) {
        model.addAttribute("productList", productService.getProductsByCategory(category));
        return "display-product";
    }
}
