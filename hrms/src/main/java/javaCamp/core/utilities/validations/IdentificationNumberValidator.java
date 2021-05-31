package javaCamp.core.utilities.validations;

import javaCamp.core.utilities.adapters.MernisDemo;
import javaCamp.core.utilities.results.ErrorResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.core.utilities.results.SuccessResult;

public class IdentificationNumberValidator {

	public static Result isRealPerson(String tcno) {
		
		MernisDemo mernisDemo = new MernisDemo();
		
		if (mernisDemo.isValidNationolityIdentity(tcno)) {
			return new SuccessResult();
		}
		return new ErrorResult("Lütfen TC Kimlik Numaranizi giriniz!");
	}
}