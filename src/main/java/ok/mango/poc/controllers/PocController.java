package ok.mango.poc.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ok.mango.poc.Constants;
import ok.mango.poc.mangopay.IMangopayService;
import ok.mango.poc.persistence.PUser;
import ok.mango.poc.persistence.UserRepository;

/**
 *
 * @author daveyx
 *
 */

@Controller
public class PocController {

	@Autowired
	private IMangopayService mpService;

	@Autowired
	private Facebook facebook;

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView poc(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			final Model model) {
		final Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		boolean isFacebookUser = false;
		for (final GrantedAuthority ga : authorities) {
			if (ga.getAuthority().equals(Constants.ROLE_FACEBOOK_USER)) {
				isFacebookUser = true;
				break;
			}
		}
		final ModelAndView mav = new ModelAndView("index");
		if (isFacebookUser) {
			final String[] fields = { "id", "email", "first_name", "last_name", "cover" };
			final User userProfile = facebook.fetchObject("me", User.class, fields);
			mav.addObject("fbuser", true);
			mav.addObject("id", userProfile.getId());
			mav.addObject("email", userProfile.getEmail());
			mav.addObject("name", userProfile.getFirstName() + " " + userProfile.getLastName());
			mav.addObject("img", "https://graph.facebook.com/" + userProfile.getId() + "/picture");
		} else {
			mav.addObject("fbuser", false);
			final org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			final PUser pUser = userRepo.findByEmail(user.getUsername());
			mav.addObject("email", pUser.getEmail());
			mav.addObject("name", pUser.getFirstName() + " " + pUser.getLastName());
			mav.addObject("password", pUser.getPassword());
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "execute")
	public ModelAndView execute(@RequestParam(value = "action", required = false) final String action,
			final Model model) {
		System.out.println("action=" + action);
		final ModelAndView mav = new ModelAndView("index");
		mav.addObject("message", "peter");
		// mpService.authMangoPay();
		mpService.createWalletDaveyx();
		return mav;
	}

}
