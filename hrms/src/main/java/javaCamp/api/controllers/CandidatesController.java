package javaCamp.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.business.abstracts.CandidateService;
import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.entities.concretes.Candidate;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {

	
	  CandidateService candidateService;
	  
	  @Autowired public CandidatesController(CandidateService candidateService) {
	  super(); 
	  this.candidateService = candidateService; 
	  }
	  
	  @GetMapping("/getall") 
	  public DataResult<List<Candidate>> getAll(){ 
		  return candidateService.findAll(); 
	  }
	  
	  @PostMapping("/add") 
	  public Result Add(@RequestBody Candidate candidate){
	  return candidateService.add(candidate); 
	  }	
}