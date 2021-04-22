package com.jayk.amazonapi.Services;

import com.jayk.amazonapi.Models.Product;
import com.jayk.amazonapi.Repositories.ProductRepository;
import com.mongodb.client.DistinctIterable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addproduct(Product product) throws Exception
    {
        if(product.getName().isEmpty() || product.getName()==""){
            throw new Exception("Product Name Required!");
        }
//        else if(product.getCategory().isEmpty()){
//            throw  new Exception("Product Category Required!");
//        }
        repository.insert(product);
    }

    public List<Product> allproducts(){
        return  repository.findAll();
    }

    public List<Product> productsByCategory(String categoryId) throws Exception{
        Query query = new Query();
        query.addCriteria(Criteria.where("category.$id").is(new ObjectId(categoryId)));
        List<Product> products= mongoTemplate.find(query,Product.class);
//        if(products.isEmpty()){
//            throw new Exception("No product Found");
//        }
        return products;
    }

    public List allcategories(){
        return mongoTemplate.query(Product.class).distinct("category").as(String.class).all();
    }

    public List<Product> bestsellerproducts(){
        Query query = new Query();
        query.addCriteria(Criteria.where("bestseller").is(true));
        return mongoTemplate.find(query,Product.class);
    }

    public Optional<Product> getproduct(String id){
        return repository.findById(id);
    }

    public void updateproduct(String id,Product newproduct) throws  Exception{
        Optional<Product> product;
        product = repository.findById(id);
        if(product.isPresent()){
            Product oldproduct = product.get();
            oldproduct.setName(newproduct.getName());
            oldproduct.setBestseller(newproduct.getBestseller());
            oldproduct.setCategory(newproduct.getCategory());
            oldproduct.setDescription(newproduct.getDescription());
            oldproduct.setImg(newproduct.getImg());
            oldproduct.setPrice(newproduct.getPrice());
            oldproduct.setQuantity(newproduct.getQuantity());
            repository.save(oldproduct);
        }else{
            throw new Exception("Invalid Product Id");
        }

    }

    public void deleteproduct(String id) throws  Exception{
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
        }else{
            throw new Exception("Invalid Product Id!");
        }
    }

}
