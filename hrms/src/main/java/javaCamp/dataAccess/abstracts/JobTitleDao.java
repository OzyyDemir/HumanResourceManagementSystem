package javaCamp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.entities.concretes.JobTitle;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer>{
	

}
