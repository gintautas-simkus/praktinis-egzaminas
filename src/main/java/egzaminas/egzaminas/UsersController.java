package egzaminas.egzaminas;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {
	@Autowired
	protected CustomUserService userService;

	@GetMapping("/register")
	public String register(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "users/registration";
	}

	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
			  HttpServletRequest request, Errors errors) {
	    try {
	        userService.registerNewUserAccount(userDto);
	    } catch (UserAlreadyExistsException uaeEx) {
	    	//System.out.println("EXCEPTION CAUGHT");
	        //return mav;
	    }

	    return "redirect:/";
	}

	@GetMapping("/login")
	public String login(Model model)
	{
		return "users/login";
	}

	@GetMapping("/")
	public String home() {
		return "users/home";
	}

	@GetMapping("/admin")
	public String admin() {
		return "users/admin";
	}
}
