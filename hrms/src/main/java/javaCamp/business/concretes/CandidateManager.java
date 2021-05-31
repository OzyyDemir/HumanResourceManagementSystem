package javaCamp.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.business.abstracts.CandidateService;
import javaCamp.business.abstracts.UserService;
import javaCamp.business.abstracts.VerificationCodeService;
import javaCamp.core.utilities.BusinessEngine;
import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.ErrorDataResult;
import javaCamp.core.utilities.results.ErrorResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.core.utilities.results.SuccessDataResult;
import javaCamp.core.utilities.results.SuccessResult;
import javaCamp.core.utilities.validations.IdentificationNumberValidator;
import javaCamp.dataAccess.abstracts.CandidateDao;
import javaCamp.entities.concretes.Candidate;
import javaCamp.entities.concretes.VerificationCode;
@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private VerificationCodeService verificationCodeService;
	private UserService userService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, VerificationCodeService verificationCodeService,
			UserService userService) {
		super();
		this.candidateDao = candidateDao;
		this.verificationCodeService = verificationCodeService;
		this.userService = userService;
	}

	@Override
	public DataResult<List<Candidate>> findAll() {

		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"");
	}

	@Override
	public DataResult<Candidate> add(Candidate candidate) {

		Result engine = BusinessEngine.run(firstNameValidator(candidate), 
				lastNameValidator(candidate),
				IdentificationNumberValidator.isRealPerson(candidate.getIdentificationNumber()), 
				IdValidator(candidate),
				birthDateValidator(candidate), 
				emailNullValidator(candidate), 
				isRealEmail(candidate),
				passwordNullValidator(candidate), 
				isMailRegistered(candidate));
		
		if (!engine.isSuccess()) {
			return new ErrorDataResult(null, engine.getMessage());
		}

		 this.userService.add(candidate);
		this.verificationCodeService.generateCode(new VerificationCode(), candidate.getId());
		return new SuccessDataResult<Candidate>(this.candidateDao.save(candidate),
				"");

	}

	private Result firstNameValidator(Candidate candidate) {
		if (candidate.getFirstname().isBlank() || candidate.getFirstname().equals(null)) {
			return new ErrorResult("");

		}
		return new SuccessResult();
	}

	private Result lastNameValidator(Candidate candidate) {
		if (candidate.getLastname().isBlank() || candidate.getLastname().equals(null)) {
			return new ErrorResult("");
		}
		return new SuccessResult();
	}

	private Result birthDateValidator(Candidate candidate) {
		if (candidate.getBirthDate().equals(null)) {
			return new ErrorResult("");
		}
		return new SuccessResult();
	}

	private Result emailNullValidator(Candidate candidate) {
		if (candidate.getEmail().isBlank() || candidate.getEmail().equals(null)) {
			return new ErrorResult("");
		}
		return new SuccessResult();
	}

	private Result passwordNullValidator(Candidate candidate) {
		if (candidate.getPassword().isBlank() || candidate.getPassword().equals(null)) {
			return new ErrorResult("");
		}
		return new SuccessResult();
	}

	private Result isRealEmail(Candidate candidate) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(candidate.getEmail());
		if (!matcher.matches()) {
			return new ErrorResult("");
		}
		return new SuccessResult();

	}

	private Result IdValidator(Candidate candidate) {
		if (candidate.getIdentificationNumber().isBlank()) {
			return new ErrorResult("");
		}

		return new SuccessResult();
	}

	private Result isMailRegistered(Candidate candidate) {
		if (candidateDao.findAllByEmail(candidate.getEmail()).stream().count() != 0) {
			return new ErrorResult("");
		}
		return new SuccessResult();
	}

	private Result isIdRegistered(Candidate candidate) {
		if (candidateDao.findAllByIdentificationNumber(candidate.getIdentificationNumber()).stream().count() != 0) {
			return new ErrorResult("");
		}
		return new SuccessResult();
	}

}