package com.jayk.amazonapi.Controllers;

import com.jayk.amazonapi.CustomizedResponse;
import com.jayk.amazonapi.Models.Category;
import com.jayk.amazonapi.Models.Product;
import com.jayk.amazonapi.Services.CategoryService;
import com.jayk.amazonapi.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(value = "add",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addCategory(@RequestBody Category category){
        CustomizedResponse customizedResponse;
        try{
            service.addCategory(category);
            customizedResponse = new CustomizedResponse("Category Added!",null);
            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity getCategories(){
        return new ResponseEntity(service.getCategories(),HttpStatus.OK);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable("categoryId") String categoryId){
        CustomizedResponse customizedResponse;
        try{
            return new ResponseEntity(service.getCategoryById(categoryId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
