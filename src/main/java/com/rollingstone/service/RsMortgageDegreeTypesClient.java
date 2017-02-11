package com.rollingstone.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rollingstone.persistence.model.DegreeType;


@FeignClient("rsmortgage-degreetype-service")
interface RsMortgageDegreeTypesClient {
	
	@RequestMapping(method = RequestMethod.GET, value="/rsmortgage-degreetype-service/v1/degreeType/all")
	List<DegreeType> getDegreeTypes();
	
	@RequestMapping(method = RequestMethod.POST, value="/rsmortgage-degreetype-service/v1/degreeType", 
			consumes = "application/json")
	void createDegreeType(DegreeType degreeType);
}