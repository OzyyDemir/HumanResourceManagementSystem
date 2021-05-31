package javaCamp.business.abstracts;

import javaCamp.core.utilities.results.Result;
import javaCamp.entities.concretes.VerificationCode;

public interface VerificationCodeService {

	/*
	 * VerificationCode findByCode(String code); DataResult<List<VerificationCode>>
	 * findAllByCode(); String createActivationCode(User user); Result
	 * activateUser(String activationCode);
	 */
	
	void generateCode(VerificationCode code, Integer id);
	Result verify(String verificationCode, Integer id);
}