package javaCamp.business.abstracts;

import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.Result;

import java.util.List;

import javaCamp.entities.concretes.User;


public interface UserService {
	DataResult<List<User>> getAll();
	Result add(User user);

}
