package egzaminas.egzaminas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<Comment, Long> {
	@Override
	List<Comment> findAll();
	Comment findById(long id);
}