package javaCamp.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.business.abstracts.UserService;
import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.core.utilities.results.SuccessDataResult;
import javaCamp.core.utilities.results.SuccessResult;
import javaCamp.dataAccess.abstracts.UserDao;
import javaCamp.entities.concretes.User;

@Service
public class UserManager implements UserService{
	
	
	private UserDao userDao;
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		
		return new SuccessDataResult<List<User>>(this.userDao.findAll(),"Listed");
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("User added");
	}

}
