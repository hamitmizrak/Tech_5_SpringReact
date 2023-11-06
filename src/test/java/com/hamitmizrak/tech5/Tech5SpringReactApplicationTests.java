package com.hamitmizrak.tech5;

import com.hamitmizrak.tech5.bean.PasswordEncoderBeanClass;
import com.hamitmizrak.tech5.data.entity.RegisterEntity;
import com.hamitmizrak.tech5.data.repository.IRegisterRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Ben ekledim (Önemli)
import static org.assertj.core.api.Assertions.assertThat;


/*
Dikkat:
void olmalı (return olmamalı)
metotla aynı isim olmalı
Atomik testler olmalıdır.
@Test annotation unutmuyoruz.
All geçen yerde => static yazmalısın.
Lombok Kütüphanesini hiç kullanma
instance kullan (new)
NOT: Ekledikten sonra ki ID:1 olana göre gidiyorum.
 */

// TEST
@SpringBootTest
class Tech5SpringReactApplicationTests implements ITestCrudData {

    // INJECTION
    private final IRegisterRepository iRegisterRepository;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;

    @Autowired
    public Tech5SpringReactApplicationTests(IRegisterRepository iRegisterRepository, PasswordEncoderBeanClass passwordEncoderBeanClass) {
        this.iRegisterRepository = iRegisterRepository;
        this.passwordEncoderBeanClass = passwordEncoderBeanClass;
    }

    /*
            @Test
            void contextLoads() {
            }
        */
    //////////////////////////////////////////////////////
    @BeforeAll
    static void getBeforeAllMethod() {
        System.out.println("Bütün metotlardan önce ben çalışırım");
    }

    @BeforeEach
    void getBeforeEach() {
        System.out.println("Testten hemen önce çalışırım.");
    }

    //////////////////////////////////////////////////////
    // CREATE
    @Override
    @Test
    public void testCreate() {
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setRegisterNickName("test nick name");
        registerEntity.setRegisterName("test register  name");
        registerEntity.setRegisterSurname("test register surname");
        registerEntity.setRegisterEmail("hamitmizrak@gmail.com");
        registerEntity.setRegisterPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode("root"));
        //registerEntity.setRegisterPassword("root");
        registerEntity.setRemaningNumber(5L);
        registerEntity.setRegisterIsPassive(true);
        iRegisterRepository.save(registerEntity);
        // Test ( eğer veri varsa)
        // eğer ilgili ID yoksa => java.util.NoSuchElementException: No value present istisnasını döner
        Assertions.assertNotNull(iRegisterRepository.findById(1L).get());
    }

    // FIND BY ID
    // Expected :hamitmizrak44@gmail.com
    // Actual   :hamitmizrak@gmail.com
    @Override
    @Test
    public void testFindById() {
        RegisterEntity registerEntity = iRegisterRepository.findById(1L).get();
        Assertions.assertEquals("hamitmizrak44@gmail.com", registerEntity.getRegisterEmail());
    }

    // LIST
    @Override
    @Test
    public void testList() {
        Iterable<RegisterEntity> list = iRegisterRepository.findAll();
        // Eğer listede veri yoksa   0  to be greater than: 0
        assertThat(list).size().isGreaterThan(0);
    }

    // UPDATE
    @Override
    @Test
    public void testUpdate() {
        RegisterEntity registerEntity = iRegisterRepository.findById(1L).get();
        registerEntity.setRegisterEmail("hamitmizrak44@gmail.com");
        iRegisterRepository.save(registerEntity);
        Assertions.assertNotEquals("hamitmizrak@gmail.com", registerEntity.getRegisterEmail());
    }

    // DELETE
    @Override
    @Test
    public void testDelete() {
        iRegisterRepository.deleteById(1L);
        assertThat(iRegisterRepository.existsById(1L)).isFalse();
    }

    //////////////////////////////////////////////////////
    @Test
    public void getFail() {
        Assertions.fail("Bilerek isteyerek hata gönderdim");
    }

    @Test
    @Disabled
    public void getDisable() {
        Assertions.fail("Testi kapattım");
    }

    @AfterEach
    public void getAfterEach() {
        System.out.println("Testten hemen sonra çalışacak");
    }

    @AfterAll
    static void getAfterAllMethod() {
        System.out.println("Bütün metotlardan sonra çalışacaktır");
    }

    //////////////////////////////////////////////////////


} //end class
