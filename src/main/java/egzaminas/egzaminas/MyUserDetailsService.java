package egzaminas.egzaminas;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }

        // https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/User.UserBuilder.html
        UserDetails userDetail = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
        		.password(user.getPassword())
                .roles(rolesFor(user)).build();
        return userDetail;
    }

    private String[] rolesFor(User u) {
    	String[] roles;
    	if (u.isAdmin()) {
    		roles = new String[] { "ADMIN", "USER" };
    	} else {
    		roles = new String[] { "USER" };
    	}
    	return roles;
    }
}