package javaCamp.business.abstracts;

import java.util.List;

import javaCamp.core.utilities.results.DataResult;
import javaCamp.entities.concretes.Employer;

public interface EmployerService {
	DataResult<List<Employer>> findAll();
	DataResult<Employer> add(Employer employer);
}
