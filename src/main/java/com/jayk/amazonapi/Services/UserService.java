package com.jayk.amazonapi.Services;

import com.jayk.amazonapi.Models.User;
import com.jayk.amazonapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getusers(){
        return repository.findAll();
    }

    public void adduser(User user) throws Exception
    {
        if(user.getEmail()==null || user.getEmail()==""){
            throw new Exception("Email Required!");
        }
        else if(user.getPassword()==null || user.getPassword()==""){
            throw new Exception("Password Required!");
        }else{

            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(user.getEmail()));

            List<User> op;
            op=mongoTemplate.find(query,User.class);
            if(!op.isEmpty()){
                throw new Exception("Email Already Used!");
            }

            String encodedpassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedpassword);
            repository.insert(user);
        }

    }

    public Optional<User> getuser(String id) throws Exception{
        Optional<User> user= repository.findById(id);
        if(!user.isPresent()){
            throw new Exception("Invalid user id");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User founduser = repository.findByemail(email);

        String Email = founduser.getEmail();
        String password = founduser.getPassword();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+founduser.getType()));
        return new org.springframework.security.core.userdetails.User(Email,password,grantedAuthorities);

    }
}
