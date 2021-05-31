package javaCamp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.entities.concretes.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
