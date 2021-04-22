package com.jayk.amazonapi.Services;

import com.jayk.amazonapi.Models.Category;
import com.jayk.amazonapi.Models.Product;
import com.jayk.amazonapi.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addCategory(Category category) throws Exception
    {
        if(category.getName().isEmpty() || category.getName()==""){
            throw new Exception("Product Name Required!");
        }
        repository.insert(category);
    }

    public List<Category> getCategories(){
        return  repository.findAll();
    }

    public Optional<Category> getCategoryById(String categoryId){
        return  repository.findById(categoryId);
    }
}
