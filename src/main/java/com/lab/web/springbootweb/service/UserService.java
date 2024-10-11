package com.lab.web.springbootweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lab.web.springbootweb.domain.User;
import com.lab.web.springbootweb.repository.UserRepository;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;  

    public Boolean autheticateUser(User user) {    	
    	try {

	    	if(user!=null && !StringUtils.isEmpty(user.getUsername()) && !StringUtils.isEmpty(user.getPassword())) {
//                logger.info("Attempting to authenticate user: ", user.getUsername());
	    		User targetUser = userRepository.findByUsername(user.getUsername());
//                logger.info("User found: ", targetUser != null);

	    		if(targetUser!=null) {
                    logger.info("Comparing passwords.");

	    			if(targetUser.getEncryptedPassword().equals(user.getPassword())) {
	    			    logger.info("User authenticated successfully.", user.getUsername());
	    				return true;
	    			}else{
	    			    logger.info("Password mismatch for user: ", user.getUsername());
	    			}
	    		}else {
                    logger.info("User not found: ", user.getUsername());
                }
	    	} else {
                logger.warn("Username or password is empty.");
            }
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    	
    	return false;
    }

    
}
