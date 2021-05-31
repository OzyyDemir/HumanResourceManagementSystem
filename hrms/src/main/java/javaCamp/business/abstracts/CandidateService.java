package javaCamp.business.abstracts;

import java.util.List;

import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.entities.concretes.Candidate;

public interface CandidateService {

	
	   DataResult<List<Candidate>> findAll();
		
		Result add(Candidate candidate);
}
