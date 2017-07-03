package ok.mango.poc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author daveyx
 *
 */

@Controller
public class MangoController {

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

}
