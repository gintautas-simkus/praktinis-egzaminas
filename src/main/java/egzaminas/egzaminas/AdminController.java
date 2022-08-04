package egzaminas.egzaminas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private MoviesRepository moviesRepository;
	@Autowired
	private CategoriesRepository categoriesRepository;

	@GetMapping("movies")
	public String movies(Model model)
	{
		model.addAttribute("movies", moviesRepository.findAll());
		model.addAttribute("categories", categoriesRepository.findAll());
		return "admin/movies";
	}
	
	@GetMapping("categories")
	public String categories(Model model)
	{
		model.addAttribute("categories", categoriesRepository.findAll());
		return "admin/categories";
	}
}