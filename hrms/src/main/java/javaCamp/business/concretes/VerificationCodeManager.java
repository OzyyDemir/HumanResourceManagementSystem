	package javaCamp.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.business.abstracts.VerificationCodeService;
import javaCamp.core.utilities.GenerateRandomCode;
import javaCamp.core.utilities.results.ErrorDataResult;
import javaCamp.core.utilities.results.Result;
import javaCamp.core.utilities.results.SuccessDataResult;
import javaCamp.dataAccess.abstracts.UserDao;
import javaCamp.dataAccess.abstracts.VerificationCodeDao;
import javaCamp.entities.concretes.VerificationCode;

@Service
	public class VerificationCodeManager implements VerificationCodeService {

		VerificationCodeDao verificationCodeDao;
		UserDao userDao;

		@Autowired
		public VerificationCodeManager(VerificationCodeDao verificationCodeDao, UserDao userDao) {
			super();
			this.verificationCodeDao = verificationCodeDao;
			//this.userDao = userDao;
		}

		 @Override
			public void generateCode(VerificationCode code,Integer id) {

						code.setCode(null);
						code.setVerified(false);
						if(code.isVerified() == false) {
							GenerateRandomCode generator = new GenerateRandomCode();
							String code_create = generator.create();
							code.setCode(code_create);
							code.setId(id);
					
							verificationCodeDao.save(code);
							
						}
						return ;
			}
			
			@Override
			public Result verify(String verificationCode,Integer id) {

				VerificationCode ref = verificationCodeDao.findById(id).stream().findFirst().get();
				if(ref.getCode().equals(verificationCode) && ref.isVerified() != true) {
					ref.setVerified(true);
					return  new SuccessDataResult<VerificationCode>(this.verificationCodeDao.save(ref),"Başarılı");
				}
				else if(ref.isVerified() == true) {
					return  new ErrorDataResult<VerificationCode>(null,"Zaten Doğrulanmış Hesap");
				}
				return  new ErrorDataResult<VerificationCode>(null,"Doğrulama Kodu Geçersiz");
				
			}
	}