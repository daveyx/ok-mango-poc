package ok.mango.poc.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(method = RequestMethod.POST, value = "execute")
	public void execute(@RequestParam(value = "action", required = false) final String action,
			final HttpServletResponse response) throws IOException {
		final String redirectUrl = mpService.createPayInDaveyx();
		response.sendRedirect(redirectUrl);
	}

}
