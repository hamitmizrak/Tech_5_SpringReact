package com.hamitmizrak.tech5;

import com.hamitmizrak.tech5.bean.PasswordEncoderBeanClass;
import com.hamitmizrak.tech5.data.repository.IRegisterRepository;
import lombok.RequiredArgsConstructor;
// import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// LOMBOK
@RequiredArgsConstructor

/*
Dikkat:
void olmalı (return olmamalı)
metotla aynı isim olmalı
Atomik testler olmalıdır.
@Test annotation unutmuyoruz.
 */

// TEST
@SpringBootTest
class Tech5SpringReactApplicationTests implements ICrudData {

	// INJECTION
	private final IRegisterRepository iRegisterRepository;
	private final PasswordEncoderBeanClass passwordEncoderBeanClass;

	/*
	    @Test
		void contextLoads() {
		}
	*/
	//////////////////////////////////////////////////////
	@BeforeAll
	static void getBeforeAllMethod(){
		System.out.println("Bütün metotlardan önce ben çalışırım");
	}

	@BeforeEach
	void getBeforeEach(){
		System.out.println("Testten hemen önce çalışırım.");
	}

	//////////////////////////////////////////////////////
	// CREATE
	@Override
	@Test
	public void testCreate() {

	}

	// FIND BY ID
	@Override
	@Test
	public void testFindById() {

	}

	// LIST
	@Override
	@Test
	public void testList() {

	}

	// UPDATE
	@Override
	@Test
	public void testUpdate() {

	}

	// DELETE
	@Override
	@Test
	public void testDelete() {

	}

	//////////////////////////////////////////////////////

	//////////////////////////////////////////////////////


} //end class
