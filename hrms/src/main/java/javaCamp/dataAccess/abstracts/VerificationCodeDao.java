package javaCamp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.entities.concretes.VerificationCode;

public interface VerificationCodeDao extends JpaRepository<VerificationCode,Integer> {

	VerificationCode findByCode(String code);
	
	//Tüm dogrulama kodlari listelenir
	List<VerificationCode> findAllByCode(String code);
}
