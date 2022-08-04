package egzaminas.egzaminas;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
@RequestMapping("movies")
public class MoviesController {
	@Autowired
	private MoviesRepository moviesRepository;
	@Autowired
	private CategoriesRepository categoriesRepository;

	@GetMapping("")
	public String index(@RequestParam(name = "search", required = false) String search, Model model)
	{	
		if (search == null) {
			model.addAttribute("movies", moviesRepository.findAll());
		} else {
			model.addAttribute("movies", moviesRepository.findByName(search));		
		}
		return "movies/index";
	}
	
	@GetMapping(value = "{id}")
	public String show(@PathVariable Long id, Model model)
	{		
		model.addAttribute("movie", moviesRepository.findById(id).get());
		return "movies/show";
	}
	
	@PostMapping("")
	public String create(
			@RequestParam(name = "title", required = true) String title,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "imdb_score", required = true) float imdbScore,
			@RequestParam(name = "category_id", required = true) Long categoryId
			) throws Exception
	{
		Category cat = categoriesRepository.findById(categoryId).get();
		Movie movie = new Movie(title, description, imdbScore, cat);
		moviesRepository.save(movie);
		return "redirect:/admin/movies";
	}
	
	@PostMapping("delete")
	public void delete(@RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response)
	{
		moviesRepository.deleteById(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}