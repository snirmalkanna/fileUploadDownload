package com.nks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nks.entity.Business;
import com.nks.repository.BusinessRepository;

@Service
@Transactional
public class BusinessService {

	private final BusinessRepository businessRepository;

	public BusinessService(BusinessRepository businessRepository) {
		this.businessRepository = businessRepository;
	}

	public Business save(Business business) {
		business.setRecord_activity(1);
		businessRepository.save(business);
		return business;
	}

	public Business update(Business business) {
		businessRepository.save(business);
		return business;
	}

	public Optional<Business> findById(Long id) {
		Optional<Business> business = businessRepository.findById(id);
		return business;
	}

	public List<Business> retrieve() {
		List<Business> list = new ArrayList<Business>();
		list = businessRepository.findAll();
		return list;
	}

	public void deleteEmployee(Long id) {
		businessRepository.deleteById(id);
	}
}
