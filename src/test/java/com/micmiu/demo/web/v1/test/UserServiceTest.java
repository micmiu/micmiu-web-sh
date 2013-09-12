package com.micmiu.demo.web.v1.test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.micmiu.framework.web.v1.system.entity.User;
import com.micmiu.framework.web.v1.system.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceTest {

	AtomicInteger atom = new AtomicInteger();

	@Autowired
	private UserService userService;

	@Test
	public void testCreate() {

		int beforeDbCount = userService.findAll().size();

		userService.save(genRandomUser());

		int afterDbCount = userService.findAll().size();

		Assert.assertEquals(beforeDbCount + 1, afterDbCount);
	}

	@Test
	public void testUpdate() {

		User user = genRandomUser();
		Long ID = userService.save(user);
		String expectedPassword = "123234";
		user.setPassword(expectedPassword);
		userService.update(user);

		String actualPassword = userService.findById(ID).getPassword();

		Assert.assertEquals(expectedPassword, actualPassword);

	}

	@Test
	public void testDelete() {

		int beforeDbCount = userService.findAll().size();

		Long id = userService.save(genRandomUser());

		userService.delete(id);

		int afterDbCount = userService.findAll().size();

		Assert.assertEquals(beforeDbCount, afterDbCount);
	}

	@Test
	public void testList() {
		User user = genRandomUser();
		userService.save(user);
		List<User> userList = userService.findAll();

		MatcherAssert.assertThat(userList, Matchers.hasItem(user));
	}

	public User genRandomUser() {
		long randomKey = System.nanoTime() + atom.addAndGet(1);
		User user = new User();
		user.setLoginName("Michael" + randomKey);
		user.setEmail("sjsky" + randomKey + "@micmiu.com");
		user.setPassword("micmiu");
		user.setOther("test");
		return user;
	}
}