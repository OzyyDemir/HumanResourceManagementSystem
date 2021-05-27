package javaCamp.business.abstracts;

import java.util.List;

import javaCamp.entities.concretes.JobTitle;

public interface JobTitleService {
	List<JobTitle> getAll();
}
