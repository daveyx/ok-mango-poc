package ok.mango.poc.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mangopay.entities.User;

import ok.mango.poc.mangopay.IMangopayService;

/**
 *
 * @author daveyx
 *
 */

@Controller
public class MangoController {

	@Autowired
	private IMangopayService mpService;

	@RequestMapping(method = RequestMethod.GET, value = "mango/return")
	public ModelAndView mangoReturn(
			@RequestParam(value = "transactionId", required = false) final String transactionId) {
		System.out.println("ok.mango.poc.controllers.MangoController.mangoReturn() transactionId=" + transactionId);
		final ModelAndView mav = new ModelAndView("mango/return");
		mav.addObject("transactionId", transactionId);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "mango/template")
	public String mangoTemplate() {
		System.out.println("ok.mango.poc.controllers.MangoController.mangoTemplate()");
		return "mango/template";
	}

	@RequestMapping(method = RequestMethod.GET, value = "mango/payment")
	public ModelAndView mangoPayment() {
		final String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
		final User mangoUser = mpService.getUserByEmail(email);

		final ModelAndView mav = new ModelAndView("mango/payment");
		mav.addObject("mangoinfo",
				mangoUser == null ? "you are not yet a mango user" : "your mango user id is " + mangoUser.getId());
		System.out.println("ok.mango.poc.controllers.MangoController.mangoTemplate()");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "execute")
	public void execute(@RequestParam(value = "action", required = false) final String action,
			final HttpServletResponse response) throws IOException {
		final String redirectUrl = mpService.createPayInDaveyx();
		response.sendRedirect(redirectUrl);
	}

}
