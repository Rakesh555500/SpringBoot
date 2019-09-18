package com.sogeti.api.filmland.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sogeti.api.filmland.exception.UserExistsException;
import com.sogeti.api.filmland.model.UserInfo;
import com.sogeti.api.filmland.repository.UserDetailsRepository;
import com.sogeti.api.filmland.service.impl.FilmLandUserServiceImpl;

/**
 * 
 * @author Rakesh
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FilmLandUserServiceTest {
	@InjectMocks
	private FilmLandUserServiceImpl filmLandUserService;

	@Mock
	private UserDetailsRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Test
	public void whenLoadUserByUserName_thenReturnUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1L);
		userInfo.setUsername("rakesh.govindegowd@gmail.com");
		userInfo.setPassword("TestPassword");
		userInfo.setSubscribedCategories(Collections.emptyList());

		doReturn(userInfo).when(userRepository).findByUsername("rakesh.govindegowd@gmail.com");

		UserDetails found = filmLandUserService.loadUserByUsername("rakesh.govindegowd@gmail.com");
		assertNotNull("User searched is not present", found);
		assertThat(found.getUsername()).isEqualTo("rakesh.govindegowd@gmail.com");
		assertThat(found.getPassword()).isEqualTo("TestPassword");
	}

	@Test(expected = UsernameNotFoundException.class)
	public void whenLoadUserByName_thenThrowException() {
		doReturn(null).when(userRepository).findByUsername("rahul.jha@gmail.com");
		filmLandUserService.loadUserByUsername("rahul.jha@gmail.com");
	}

	@Test(expected = UserExistsException.class)
	public void whenRegisterNewUser_thenReturnUserInfo() throws UserExistsException {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1L);
		userInfo.setUsername("rakesh.govindegowd@gmail.com");
		userInfo.setPassword("TestPassword");
		userInfo.setSubscribedCategories(Collections.emptyList());

		doReturn(userInfo).when(userRepository).findByUsername("rakesh.govindegowd@gmail.com");

		filmLandUserService.registerNewUser(userInfo);
	}
}