package com.hamitmizrak.tech5.data.repository;

import com.hamitmizrak.tech5.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// CrudRepository
// JpaRepository
// PagingAndSortingRepository
@Repository
public interface IRegisterRepository extends CrudRepository<RegisterEntity,Long> {

    //Kendi Sorgumu Yazdım
    //@Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    //public UserEntity getUserByUsername(@Param("username") String username);

    // Delivered Query
    Optional<RegisterEntity> findByRegisterEmail(String email);
    RegisterEntity findByRegisterSurname (String surname);

    // @ManyToMany roles==> User Inner Join Roles
    @Query("select t from Users t join t.roles u where u.roleName = :roleName")
    List<RegisterEntity> findAllByUserInJoinRolesRoleName1(@Param("roleName") String roleName);
}
