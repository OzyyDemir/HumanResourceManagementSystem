package javaCamp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.entities.concretes.User;

public interface UserDao  extends JpaRepository<User, Integer>{

}
