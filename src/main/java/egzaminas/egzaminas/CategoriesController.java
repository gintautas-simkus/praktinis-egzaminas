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
@RequestMapping("categories")
public class CategoriesController {
	@Autowired
	private CategoriesRepository categoriesRepository;

	@GetMapping("")
	public String index(Model model)
	{	
		model.addAttribute("categories", categoriesRepository.findAll());
		return "categories/index";
	}
	
	@GetMapping(value = "{id}")
	public String show(@PathVariable Long id, Model model)
	{		
		model.addAttribute("category", categoriesRepository.findById(id).get());
		return "categories/show";
	}
	
	@PostMapping("")
	public String create(@RequestParam(name = "name", required = true) String name) throws Exception
	{
		Category category = new Category(name);
		categoriesRepository.save(category);
		return "redirect:/admin/categories";
	}
	
	@PostMapping("delete")
	public void delete(@RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response)
	{
		categoriesRepository.deleteById(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}