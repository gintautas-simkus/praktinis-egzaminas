package egzaminas.egzaminas;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("comments")
public class CommentsController {
	@Autowired
	private CommentsRepository commentsRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private MoviesRepository moviesRepository;

	@PostMapping("")
	public String create(@RequestParam(name = "body", required = true) String body,
			@RequestParam(name = "movie_id", required = true) Long movieId) throws Exception
	{
		Movie movie = moviesRepository.findById(movieId).get();
		String email = ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = usersRepository.findByEmail(email);
		Comment comment = new Comment(movie, user, body);
		commentsRepository.save(comment);
		return "redirect:/movies/" + movie.getId();
	}
}