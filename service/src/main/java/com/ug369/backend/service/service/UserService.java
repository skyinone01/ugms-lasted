package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.UserRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.repository.mysql.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roy on 2017/3/17.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUser(String username, String password){

        User user = userRepository.findByUsername(username);
        if (user == null || bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new UserException(UgmsStatus.AUTH_FAILED,"用户名或者明码错误");
        }
        return user;
    }

    @Transactional
    public void createOrUpdate(UserRequest request) {

        if (request.getId() != 0){
            User user = userRepository.findOne(request.getId());
            if (user == null){
                throw new UserException(UgmsStatus.NOT_FOUND,"用户:"+request.getId());
            }
            if (StringUtils.isNotEmpty(request.getUsername())){
                user.setName(request.getName());
            }
            if (StringUtils.isNotEmpty(request.getUsername())){
                user.setUsername(request.getUsername());
            }
            if (StringUtils.isNotEmpty(request.getMobile())){
                user.setMobile(request.getMobile());
            }
            if (request.getRole() != 0){
                user.setRole(request.getRole());
            }
            if (StringUtils.isNotEmpty(request.getPassword())){
                user.setUsername(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            userRepository.save(user);
        }else {
            if (userRepository.existsByUsername(request.getUsername())){
                throw new UserException(UgmsStatus.ALREADY_EXISTS,"username :"+request.getUsername());
            }
            User user = new User();
            user.setMobile(request.getMobile());
            user.setUsername(request.getUsername());
            user.setName(request.getName());
            user.setRole(request.getRole());
            user.setPassword(bCryptPasswordEncoder.encode("1234abcd"));

            userRepository.save(user);
        }

    }

    public boolean exists(Long userId) {
        return userRepository.exists(userId);
    }

    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }

    public PagedResult getAll(PageRequest pageRequest) {
        Map map = new HashMap();
        return userRepository.getDataPageBatch("User.getAll", "User.getCount", map, pageRequest);
    }

    public void delete(long id) {
        userRepository.delete(id);
    }
}
