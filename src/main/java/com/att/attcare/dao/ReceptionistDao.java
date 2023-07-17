package com.att.attcare.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.att.attcare.model.Receptionist;
import com.att.attcare.repository.ReceptionistRepository;

@Component
public class ReceptionistDao {
	private final ReceptionistRepository repository;

    public ReceptionistDao(ReceptionistRepository repository) {
        this.repository = repository;
    }

	public List<Receptionist> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Optional<Receptionist> getById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	public Receptionist saveReceptionist(Receptionist receptionist) {
		// TODO Auto-generated method stub
		return repository.save(receptionist);
	}

	public Receptionist getByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		 repository.deleteById(id);
	}
}
