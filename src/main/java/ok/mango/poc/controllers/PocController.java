package ok.mango.poc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ok.mango.poc.mangopay.IMangopayService;
import ok.mango.poc.mangopay.MangopayService;

/**
 *
 * @author daveyx
 *
 */

@Controller
public class PocController {
	
	@Autowired
	private IMangopayService mpService;
	
    @RequestMapping(method=RequestMethod.GET, value="/")
    public String poc(@RequestParam(value="name", required=false, defaultValue="World") String name, final Model model) {
        mpService.reqestMangoPay();
        return "index";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="execute")
    public ModelAndView execute(@RequestParam(value="action", required=false) final String action, final Model model) {
    	System.out.println("action=" + action);
    	final ModelAndView mav = new ModelAndView("index"); 
    	mav.addObject("message", "peter");
    	return mav;
    }
    
    @Bean
    public IMangopayService getMangepayService() {
    	return new MangopayService();
    }
}
