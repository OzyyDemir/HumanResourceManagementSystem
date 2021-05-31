package javaCamp.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.business.abstracts.JobTitleService;
import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.core.utilities.results.SuccessDataResult;
import javaCamp.core.utilities.results.SuccessResult;
import javaCamp.dataAccess.abstracts.JobTitleDao;
import javaCamp.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService{

	private JobTitleDao jobTitleDao;
	
	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}


	@Override
	public DataResult< List<JobTitle>> getAll() {
		
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(),"Listed");
			
	}


	@Override
	public Result add(JobTitle jobTitle) {
		this.jobTitleDao.save(jobTitle);
		return new SuccessResult("Title added");
	}

}
