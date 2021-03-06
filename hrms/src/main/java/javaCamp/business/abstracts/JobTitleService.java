package javaCamp.business.abstracts;

import java.util.List;


import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.entities.concretes.JobTitle;

public interface JobTitleService {
	DataResult< List<JobTitle>> getAll();
	Result add(JobTitle jobTitle);
}
