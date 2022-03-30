public class adil {
}
webcontroller


        package com.example.demo;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
public class WebController {

    @Autowired
    UserRepository repository;

    @GetMapping("/")
    public String showUserList(@RequestParam(name = "email", required = false, defaultValue = "") String email,
                               @RequestParam(name = "email1", required = false, defaultValue = "") String email1,
                               @RequestParam(name = "name2", required = false, defaultValue = "") String name2,
                               @RequestParam(name = "surname3", required = false, defaultValue = "") String surname3,
                               @RequestParam(name = "id4", required = false, defaultValue = "") Long id4,
                               @RequestParam(name = "id5", required = false, defaultValue = "") Long id5,
                               @RequestParam(name = "name6", required = false, defaultValue = "") String name6,
                               @RequestParam(name = "email7", required = false, defaultValue = "") String email7,
                               @RequestParam(name = "name8", required = false, defaultValue = "") String name8,
                               @RequestParam(name = "email9", required = false, defaultValue = "") String email9,
                               @RequestParam(name = "name10", required = false, defaultValue = "") String name10,
                               Model model) {

        List<User> users = repository.findAll();

        if (!email.isEmpty()) {
            users = repository.findByEmailContainingOrderByNameDesc(email);
        }
        if (!email1.isEmpty()) {
            users = repository.findByEmailEndsWith(email1);
        }

        if (!name2.isEmpty()) {
            users = repository.findTop2ByNameStartsWith(name2);
        }

        if (!surname3.isEmpty()) {
            users = repository.findBySurnameContaining(surname3);
        }

        if (id4 != null) {
            users = repository.findByOrderById();
        }
        if (id5 != null) {
            users = repository.findTop2ByOrderByIdDesc();
        }

        if (!name6.isEmpty()){
            users = repository.findByOrderByNameDesc();
        }

        if (!email7.isEmpty()) {
            users = repository.findByEmailNotContaining(email7);
        }
        if(!name8.isEmpty()){
            users = repository.findAllW();
        }

        if(!email9.isEmpty()){
            users = repository.findAllQ();
        }

        if (!name10.isEmpty()) {
            users = repository.findAllE();
        }

        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    private void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void addUser(User newUser) {
        repository.save(newUser);
    }

    private void updateUser(User updateUser) {

        User user = repository.getById(updateUser.getId());

        Forwarded message
        R.Shokhzod
        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());
        user.setSurname(updateUser.getEmail());
        repository.save(user);
    }
}


            userrepository


        package com.example.demo;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // Jpa

    List<User> findByEmailEndsWith(String email1);

    List<User> findTop2ByNameStartsWith(String name2);

    List<User> findBySurnameContaining(String surname3);

    List<User> findByOrderById();

    List<User> findTop2ByOrderByIdDesc();

    List<User> findByOrderByNameDesc();

    List<User> findByEmailNotContaining(String email7);

    List<User> findDistinctTop1ByName(String name10);

    List<User> findByEmailContainingOrderByNameDesc(String email);

    // Native Query
    @Query(value = "select * from users where email like '%gmail.com' or email like '%narxoz.kz' or email like '%yandex.ru'", nativeQuery = true)
    List<User> findAllQ();

    @Query(value = "select * from users where name = surname", nativeQuery = true)
    List<User> findAllW();

    @Query(value = "select distinct on (name) * from users", nativeQuery = true)
    List<User> findAllE();
}
/*
public interface UserRepository extends JpaRepository<User, Long> {

    // Jpa
    List<User> findByNameStartsWith(String name);

    List<User> findByEmailContainingOrderByNameDesc(String email);

    // Native Query
    @Query(value = "select * from users order by surname", nativeQuery = true)
    List<User> findAllSorted();

    @Query(value = "select * from users where id > :qid", nativeQuery = true)
    List<User> findByGreaterId(Long qid);

    @Query(value = "select * from users where id > :qid", nativeQuery = true)
    List<User> findByGreaterId(Long qid);
}*/


