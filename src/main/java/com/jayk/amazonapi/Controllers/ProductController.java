package com.jayk.amazonapi.Controllers;

import com.jayk.amazonapi.CustomizedResponse;
import com.jayk.amazonapi.Models.Product;
import com.jayk.amazonapi.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(value = "",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addProduct(@RequestBody Product product){
        CustomizedResponse customizedResponse;
        try{
            service.addproduct(product);
            customizedResponse = new CustomizedResponse("Product Added!",null);
            return new ResponseEntity(customizedResponse,HttpStatus.OK);
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all")
    public ResponseEntity getProducts(){
        return new ResponseEntity(service.allproducts(),HttpStatus.OK);
    }

    @GetMapping("/by-category")
    public ResponseEntity getProductsByCategory(
            @RequestParam("categoryId") String categoryId
    ) throws Exception {

        return new ResponseEntity(service.productsByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("products/getproducts/{category}")
    public ResponseEntity getproductsByCategory(@PathVariable("category") String category){
        CustomizedResponse customizedResponse;
        try{
            customizedResponse = new CustomizedResponse("List of product",service.productsByCategory(category));
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("bestsellers")
    public ResponseEntity bestsellerProducts(){
        return new ResponseEntity(service.bestsellerproducts(), HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity getproductById(@PathVariable("productId") String productId){
        return new ResponseEntity(service.getproduct(productId), HttpStatus.OK);
    }

    @PutMapping(value = "/products/updateproduct/{id}",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateproduct(@PathVariable("id") String id, @RequestBody Product product){

        CustomizedResponse customizedResponse;

        try{
            service.updateproduct(id,product);
            customizedResponse = new CustomizedResponse("Product Update",null);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/products/deleteproduct/{id}")
    public ResponseEntity deleteproduct(@PathVariable("id") String id){

        CustomizedResponse customizedResponse;

        try {
            service.deleteproduct(id);
            customizedResponse= new CustomizedResponse("Product Deleted!",null);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
