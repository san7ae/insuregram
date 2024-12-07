package com.wundw.insuregram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wundw.insuregram.exceptions.ResourceNotFoundException;
import com.wundw.insuregram.model.User;
import com.wundw.insuregram.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public void followUser(Long id, Long followId) {
		// TODO Auto-generated method stub
		
	}

	public void unfollowUser(Long id, Long followId) {
		// TODO Auto-generated method stub
		
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User updateUser(Long id, User userDetails) {
		// Fetch the existing user
	    User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
	    // Update fields
	    existingUser.setUsername(userDetails.getUsername());
	    existingUser.setEmail(userDetails.getEmail());

	    // Save the updated user
	    return userRepository.save(existingUser);
	}

	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}
	
//	public User getUser(String userId){
//		return userRepository.findById(userId);
//	}
}
