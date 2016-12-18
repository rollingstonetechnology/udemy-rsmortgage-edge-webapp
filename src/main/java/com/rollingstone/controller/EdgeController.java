package com.rollingstone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.persistence.model.DegreeType;
import com.rollingstone.service.EdgeService;

@RestController
@RequestMapping(value = "/rsmortgage-edge-service")
public class EdgeController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(EdgeController.class);

	@Autowired
	EdgeService edgeService; 
	
	  @RequestMapping(value = "/v1/degreeType",
	            method = RequestMethod.GET,
	            produces = {"application/json", "application/xml"})
	    @ResponseStatus(HttpStatus.OK)
	    public
	    @ResponseBody
	    List<DegreeType> getAllDegreeTypes(HttpServletRequest request, HttpServletResponse response) {
		  slf4jLogger.info("In EdgeController");
	        return this.edgeService.getAllDegreeTypes();
	    }
}
