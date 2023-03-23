package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;

import tacos.jpa.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}
