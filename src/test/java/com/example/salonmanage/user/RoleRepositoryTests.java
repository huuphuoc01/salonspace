package  com.example.salonmanage.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.example.salonmanage.Entities.Role;
import com.example.salonmanage.Entities.User;
import com.example.salonmanage.reponsitory.RoleRepository;
import com.example.salonmanage.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class RoleRepositoryTests {
	@Autowired private RoleRepository repo;

	@Autowired private UserService userService;

	@Test
	public void testCreateRoles() {
		Role admin = new Role("ROLE_ADMIN");
		Role editor = new Role("ROLE_EDITOR");
		Role customer = new Role("ROLE_CUSTOMER");
		
		repo.saveAll(List.of(admin, editor, customer));
		
		long count = repo.count();
		assertEquals(3, count);
	}

	@Test
	public void testCreateUsers() {
		User user = new User();
		user.setName("lượng");
		user.setPhone("03769101971");
		user.setPassword("abc");
		user.setEmail("abc@gmail.com");
		User newUser =  userService.save(user);


		assertEquals(newUser.getId(), 2);
	}
}
