package com.example.userscrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.userscrud.entity.User;
import com.example.userscrud.exception.UserNotFoundException;
import com.example.userscrud.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service

public class UserServiceImpl implements UserService {
	
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository repository) {
		this.userRepository=repository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		if(user == null) {
			throw new UserNotFoundException("User with email : "+email+" doesn't exist.");
		}
		
		return user;
	}
	
	@Override
	public List<User> getUserByName(String name) {
		List<User> userList = userRepository.findByName(name);
		if(userList == null) {
			throw new UserNotFoundException("User with name : "+name+" doesn't exist.");
		}
		
		return userList;
	}

	@Override
	public void deleteUser(String email) {
		User user = userRepository.findByEmailAddress(email);
		userRepository.delete(user);
	}
	
	@Override
	public void deleteUserByName(String name) {
		List<User> userList = userRepository.findByName(name);
		if(userList.size() == 1)
		{
			User user = userList.get(0);		
			userRepository.delete(user);
		}
			
		else
		{
			System.out.print("More than One Users are found");
		}
	}
	
	

}
