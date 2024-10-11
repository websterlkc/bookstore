package com.lab.web.springbootweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${header.sourceSystem.id}")
    private String validSourceSystemId;

    @Value("${header.sourceSystem.token}")
    private String validSourceSystemToken;

    protected boolean authenticate(HttpServletRequest request) {
        String sourceSystemId = request.getHeader("sourceSystem");
        String sourceSystemToken = request.getHeader("sourceSystemToken");

        if (sourceSystemId == null || sourceSystemToken == null || 
            !sourceSystemId.equals(validSourceSystemId) || 
            !sourceSystemToken.equals(validSourceSystemToken)) {
            logger.info("Invalid Request");
            return false;
        }

        logger.info("Authenticated sourceSystem:", sourceSystemId);
        return true;
    }

    protected Map<String, Object> createResponse(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        return response;
    }
}
