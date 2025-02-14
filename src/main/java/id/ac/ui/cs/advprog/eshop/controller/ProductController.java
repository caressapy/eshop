package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);  // ✅ Must match the form in HTML
        return "createProduct";
    }


    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:/product/list"; // ✅ Menambahkan '/' agar path lebih jelas
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product existingProduct = service.findById(productId);
        if (existingProduct == null) {
            // Handle the case where the product is not found
            return "redirect:/product/list";  // Or show an error page
        }
        model.addAttribute("product", existingProduct);
        return "editProduct";
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(@PathVariable String productId, @ModelAttribute Product updatedProduct) {
        service.update(productId, updatedProduct);
        return "redirect:/product/list";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        service.delete(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> products = service.findAll();
        model.addAttribute("products", products);
        return "productList";  // ✅ Must match the filename in "templates" folder
    }
}

