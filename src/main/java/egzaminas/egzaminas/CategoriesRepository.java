package egzaminas.egzaminas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Category, Long> {
	@Override
	List<Category> findAll();
	Category findById(long id);
}