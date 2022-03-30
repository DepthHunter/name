package kz.narxoz.canvas;

import kz.narxoz.canvas.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Jpa Metods
    List<User> findByEmailEndsWith(String email1);

    List<User> findTop2ByNameStartsWith(String name2);

    List<User> findBySurnameContaining(String surname3);

    List<User> findByOrderById();

    List<User> findTop2ByOrderByIdDesc();

    List<User> findByOrderByNameDesc();

    List<User> findByEmailNotContaining(String email7);

    List<User> findDistinctTop1ByName(String name10);

    List<User> findByEmailContainingOrderByNameDesc(String email);


    @Query(value = "select * from users where email like '%gmail.com' or email like '%narxoz.kz' or email like '%yandex.ru'", nativeQuery = true)
    List<User> findAllZ();

    @Query(value = "select * from users where name = surname", nativeQuery = true)
    List<User> findAllX();

    @Query(value = "select distinct on (name) * from users", nativeQuery = true)
    List<User> findAllC();





    // Native Query
   /* @Query(value = "select * from users order by surname", nativeQuery = true)
    List<User> findAllSorted();

    @Query(value = "select * from users where id > :qid", nativeQuery = true)
    List<User> findByGreaterId(Long qid);*/



}