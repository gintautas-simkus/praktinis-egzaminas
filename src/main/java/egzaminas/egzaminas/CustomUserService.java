package egzaminas.egzaminas;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
@Transactional
public class CustomUserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private WebSecurityConfig webSecurityConfig;

    public void registerNewUserAccount(UserDto userDto) throws UserAlreadyExistsException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: "
              + userDto.getEmail());
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(webSecurityConfig.encoder().encode(userDto.getPassword()));
        usersRepository.save(user);
    }

    private boolean emailExists(String email) {
    	// TODO: cia tikrinimas is isEmpty?
        return usersRepository.findByEmail(email) != null;
    }
}