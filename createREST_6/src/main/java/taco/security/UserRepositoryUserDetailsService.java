package taco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import taco.User;
import taco.data.UserRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private UserRepository UserRepo;
	
	@Autowired
	public UserRepositoryUserDetailsService(UserRepository UserRepo) {
		this.UserRepo=UserRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = UserRepo.findByUsername(username);
		if(user != null) {
			return user;
		}
		throw new UsernameNotFoundException("user" + username + "not found");
	}

}
