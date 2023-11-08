package com.hamitmizrak.tech5.data.repository;

import com.hamitmizrak.tech5.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CrudRepository
// JpaRepository
// PagingAndSortingRepository
@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    // Delivered Query
    // database rol adını bulmak için kullanıyoruz.
    Optional<RoleEntity> findByRoleName(String roleName);

    //  karmaşık sorgularda Query yazıyoruz.
   // @Query
   // RoleEntity userEmailFindRoles(@Param("email") String email);
}
