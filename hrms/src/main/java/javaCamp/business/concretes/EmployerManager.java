package javaCamp.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.business.abstracts.EmployerService;
import javaCamp.business.abstracts.UserService;
import javaCamp.business.abstracts.VerificationCodeService;
import javaCamp.core.utilities.BusinessEngine;
import javaCamp.core.utilities.results.DataResult;
import javaCamp.core.utilities.results.ErrorDataResult;
import javaCamp.core.utilities.results.ErrorResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.core.utilities.results.SuccessDataResult;
import javaCamp.core.utilities.results.SuccessResult;
import javaCamp.dataAccess.abstracts.EmployerDao;
import javaCamp.entities.concretes.Employer;
import javaCamp.entities.concretes.VerificationCode;

@Service
public class EmployerManager implements EmployerService{


	private EmployerDao employerDao;
	private UserService userService;
	private VerificationCodeService verificationCodeService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, UserService userService,
			VerificationCodeService verificationCodeService) {
		super();
		this.employerDao = employerDao;
		this.userService = userService;
		this.verificationCodeService = verificationCodeService;
	}

	@Override
	public DataResult<List<Employer>> findAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),
				"Employers listed");
	}

	@Override
	public DataResult<Employer> add(Employer employer) {

		Result engine = BusinessEngine.run(
				isEmailAlreadyRegistered(employer));
		if (!engine.isSuccess()) {
			return new ErrorDataResult<Employer>(null, engine.getMessage());
		}
		this.userService.add(employer);
		this.verificationCodeService.generateCode(new VerificationCode(), employer.getId());
		return new SuccessDataResult<Employer>(this.employerDao.save(employer),
				"İş Veren Hesabı Eklendi, Doğrulama Kodu Gönderildi ID:" + employer.getId());
	}

	private Result companyNameValidator(Employer employer) {
		if (employer.getCompanyName().isBlank() || employer.getCompanyName() == null) {
			return new ErrorResult("Lütfen firma isminizi bos gecmeyiniz");
		}
		return new SuccessResult();
	}

	private Result webSiteValidator(Employer employer) {
		if (employer.getWebAdress().isBlank() || employer.getWebAdress() == null) {
			return new ErrorResult("Lütfen web adresinizi bos gecmeyiniz");
		}
		return new SuccessResult();
	}

	private Result isRealEmployer(Employer employer) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(employer.getEmail());
		if (!matcher.matches()) {
			return new ErrorResult("Geçersiz Email Adresi");
		} else if (!employer.getEmail().contains(employer.getWebAdress())) {
			System.out.println(employer.getEmail() + " " + employer.getWebAdress());
			return new ErrorResult("Domain adresi girmek zorundasınız");
		}
		return new SuccessResult();

	}

	private Result isEmailAlreadyRegistered(Employer employer) {
		if (employerDao.findByEmail(employer.getEmail()).stream().count() != 0) {
			return new ErrorResult("Email zaten kayıtlı");
		}
		return new SuccessResult();
	}

	private Result passwordNullValidator(Employer employer) {
		if (employer.getPassword().isBlank() || employer.getPassword() == null) {
			return new ErrorResult("Şifre Bilgisi Doldurulmak zorundadır");
		}
		return new SuccessResult();
	}

	private Result isRealPhoneNumber(Employer employer) {
		String patterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		/*
		 * ÖRNEK TELEFON NUMARALARI String[] validPhoneNumbers =
		 * {"2055550125","202 555 0125", "(202) 555-0125", "+111 (202) 555-0125",
		 * "636 856 789", "+111 636 856 789", "636 85 67 89", "+111 636 85 67 89"};
		 */
		Pattern pattern = Pattern.compile(patterns);
		Matcher matcher = pattern.matcher(employer.getPhoneNumber());
		if (!matcher.matches()) {
			return new ErrorResult("Geçersiz Telefon Numarası");
		}
		return new SuccessResult();

	}
}
