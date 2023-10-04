package io.chat;

import io.chat.entity.User;
import io.chat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateUser(){
		User user = new User();
		user.setName("Farid Faisal");
		user.setEmail("farid@gmail.com");
		user.setPassword("1234");
		User result = userService.saveOrUpdate(user);
		
	}

}
