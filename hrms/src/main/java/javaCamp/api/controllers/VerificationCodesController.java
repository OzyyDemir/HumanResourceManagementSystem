package javaCamp.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.business.abstracts.VerificationCodeService;
import javaCamp.core.utilities.results.Result;



@RestController
@RequestMapping("/api/userverify")
public class VerificationCodesController {

	private VerificationCodeService verificationCodeService;


	@Autowired
	public VerificationCodesController(VerificationCodeService verificationCodeService) {
		super();
		this.verificationCodeService = verificationCodeService;
	}


	@PostMapping("/active/{verificationCode}/{id}")
	public Result setVerify(@RequestParam String verificationCode,@RequestParam Integer id) {
			return verificationCodeService.verify(verificationCode,id);
	}
	
}