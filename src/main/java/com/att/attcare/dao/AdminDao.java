package com.att.attcare.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.att.attcare.model.User;
import com.att.attcare.repository.UserRepository;

@Component
public class AdminDao {

	 private final UserRepository userRepository;

	    public AdminDao(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

		public List<User> getAllByRole(String string) {
			// TODO Auto-generated method stub
			return userRepository.findAllByRole(string);
		}

		public Optional<User> getByEmail(String email) {
			// TODO Auto-generated method stub
			return userRepository.findByEmail(email);
		}

		public Optional<User> getById(int id) {
			// TODO Auto-generated method stub
			return userRepository.findById(id);
		}

		public void saveuser(User updatedAdmin) {
			// TODO Auto-generated method stub
			userRepository.save(updatedAdmin);
			
		}

		public void delete(int id) {
			// TODO Auto-generated method stub
			userRepository.deleteById(id);
			
		}

	
}
