package ok.mango.poc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String poc(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        mpService.reqestMangoPay();
        return "index";
    }
    
    @Bean
    public IMangopayService getMangepayService() {
    	return new MangopayService();
    }
}
