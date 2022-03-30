package kz.narxoz.canvas;

import kz.narxoz.canvas.User;
import kz.narxoz.canvas.UserRepository;
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
            users = repository.findAllX();
        }

        if(!email9.isEmpty()){
            users = repository.findAllZ();
        }

        if (!name10.isEmpty()) {
            users = repository.findAllC();
        }





        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user) {
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    private void deleteById(long id) {
        repository.deleteById(id);
    }

    private void addUser(User newUser) {
        repository.save(newUser);
    }

    private void updateUser(User updateUser) {
        User oldUser = repository.getById(updateUser.getId());

        oldUser.setName(updateUser.getName());
        oldUser.setSurname(updateUser.getSurname());
        oldUser.setEmail(updateUser.getEmail());

        repository.save(oldUser);
    }
}