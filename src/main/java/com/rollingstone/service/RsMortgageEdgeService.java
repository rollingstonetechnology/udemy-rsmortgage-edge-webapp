package com.rollingstone.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollingstone.persistence.model.DegreeType;

@Service
@Transactional
public class RsMortgageEdgeService  {

	private final Logger slf4jLogger = LoggerFactory.getLogger(RsMortgageEdgeService.class);

	@Autowired
	RsMortgageDegreeTypesClient degreeTypesClient;
	
	public List<DegreeType> getAllDegreeTypes(){
		return degreeTypesClient.getDegreeTypes();
	}
    
	public void createDegreeType(DegreeType degreeType){
		degreeTypesClient.createDegreeType(degreeType);
	}
    
   
}
