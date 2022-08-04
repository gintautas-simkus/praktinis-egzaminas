package egzaminas.egzaminas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
	  List<User> findAll();
	  User findByEmail(String email);
}