package egzaminas.egzaminas;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected String body;
	@OneToOne(fetch = FetchType.EAGER)
	protected Movie movie;
	@OneToOne(fetch = FetchType.EAGER)
	protected User user;

	public Comment() {}

	public Comment(Movie movie, User user, String body) {
		this.movie = movie;
		this.user = user;
		this.body = body;
	}

	public String getBody() {
		return body;
	}
	
	public User getUser() {
		return user;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}