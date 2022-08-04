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
@Table(name = "movies")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected String title;
	protected String description;
	protected float imdbScore;
	//@ManyToOne(fetch = FetchType.EAGER)
	//protected User user;
	@ManyToOne(fetch = FetchType.EAGER)
	protected Category category;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="movie")
	protected Set<Comment> comments;

	public Movie() {}

	public Movie(String title, String desc, float score, Category category) {
		this.title = title;
		this.description = desc;
		this.imdbScore = score;
		this.category = category;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public float getImdbScore() {
		return imdbScore;
	}
	
	public Category getCategory() {
		return category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}
}
