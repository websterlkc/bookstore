package com.lab.web.springbootweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.web.springbootweb.domain.User;
import com.lab.web.springbootweb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
	@Autowired
    private UserService userService;
	
    @PostMapping("/auth")
    public @ResponseBody ResponseEntity<Map<String, Object>> authenticateUser(HttpServletRequest request, @RequestBody User user) {
    	if (!authenticate(request)) {
            return new ResponseEntity<>(createResponse("Invalid request", null), HttpStatus.FORBIDDEN);
        }
    	
        Map<String, Object> response = new HashMap<>();
        Boolean isAuth = userService.autheticateUser(user);
        HttpSession session = request.getSession();

        if (isAuth) {
            session.setAttribute("AUTH_USER_ROLE", "admin");     
            
            response.put("message", "Authenticated User");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "No Authorized Access");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }        
    }

}
