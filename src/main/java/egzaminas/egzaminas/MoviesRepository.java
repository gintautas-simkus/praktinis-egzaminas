package egzaminas.egzaminas;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movie, Long> {
	@Override
	List<Movie> findAll();
	Movie findById(long id);
	@Query(value = "SELECT * FROM movies WHERE movies.title like %?1%", nativeQuery = true)
	List<Movie> findByName(String name);
}