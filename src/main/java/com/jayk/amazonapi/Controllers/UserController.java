package com.jayk.amazonapi.Controllers;

import com.jayk.amazonapi.CustomizedResponse;
import com.jayk.amazonapi.Models.User;
import com.jayk.amazonapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService service;


    //To get list of all users
    @GetMapping("/all")
    public ResponseEntity getusers(){
        return new ResponseEntity(service.getusers(), HttpStatus.OK);
    }


    //To Create New User
    @PostMapping(value = "/register",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity adduser(@RequestBody User user)  {
        CustomizedResponse customizedResponse;
        try {
            service.adduser(user);
            customizedResponse = new CustomizedResponse("User Created!",null );
            return new ResponseEntity(customizedResponse,HttpStatus.OK);
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse,HttpStatus.BAD_REQUEST);
        }

    }


    //To get user by userid
    @GetMapping("/{id}")
    public ResponseEntity getuser(@PathVariable("id") String id){
        CustomizedResponse customizedResponse;
        try{
            customizedResponse = new CustomizedResponse("User Found", service.getuser(id));
            return  new ResponseEntity(customizedResponse,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }


}
