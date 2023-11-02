package com.hamitmizrak.tech5.data.repository;

import com.hamitmizrak.tech5.data.entity.RegisterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// CrudRepository
// JpaRepository
// PagingAndSortingRepository
@Repository
public interface IRegisterRepository extends CrudRepository<RegisterEntity,Long> {

    // Delivered Query
    // Email
    Optional<RegisterEntity> findByRegisterEmail(String email);

    // Login Surname
    Optional<RegisterEntity> findByRegisterSurname(String surname);
}
