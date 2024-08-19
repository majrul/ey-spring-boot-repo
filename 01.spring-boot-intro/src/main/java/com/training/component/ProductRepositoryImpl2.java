package com.training.component;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("repo2")
//@Profile("prod")
@Repository
public class ProductRepositoryImpl2 implements ProductRepository {

	public ProductRepositoryImpl2() {
		System.out.println("ProductRepositoryImpl2()");
	}

	@Override
	public void doSomething() {
		System.out.println("ProductRepositoryImpl2 does something..");
	}
}
