package com.training;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.training.entity.Customer;
import com.training.repository.CustomerRepository;

@SpringBootTest
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void findByEmail() {
		Optional<Customer> c = customerRepository.fetchByEmail("majrul@gmail");
		//System.out.println(c.get());
		assertThat(c.isPresent()).isEqualTo(true);
	}
	
	@Test
	public void findByCity() {
		List<Customer> list = customerRepository.findByCity("Mumbai");
		//System.out.println(c.get());
		assertThat(list.size()).isGreaterThan(0);
	}
	
	@Test
	public void findByCityWithPagination() {
		long count = customerRepository.findHowManyCustomersFromCity("Mumbai");
		int pageSize = 2;
		int pages = (int) (count / pageSize);
		
		for(int i=0; i<pages; i++) {
			PageRequest pr = PageRequest.of(i, pageSize, Sort.by("name"));
			Iterable<Customer> list = customerRepository.findByCity("Mumbai", pr);
			list.forEach(System.out::println);
		}
	}
}