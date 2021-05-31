package javaCamp.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="employees")
public class Employee extends User{

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	public Employee(int id, String email,String password,boolean isActive,String firstName, String lastName)
	{
		super(id,email,password,isActive);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	

}
