package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String list(Model model) {
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return "list";
	}

	@GetMapping("/user")
	public String form(Model model) {

		model.addAttribute("user", new User());
		return "form";
	}

	@PostMapping("/user")
	public String save(@ModelAttribute User user, Model model) {
		model.addAttribute("user", userService.save(user));
		return "form";
	}

	@GetMapping(value = "/user/{id}")
	public String show(@PathVariable(value = "id") long id, Model model) {
		User user = userService.find(id);
		if (user == null) {
			return "404";
		}
		model.addAttribute("user", user);
		return "form";
	}

	@PostMapping("/user/{id}")
	public String edit(@ModelAttribute User user, Model model) {
		model.addAttribute("user", userService.update(user));
		return "form";
	}

	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") long id, Model model) {
		User user = userService.find(id);
		if (user == null) {
			return "404";
		}
		userService.delete(user);
		return list(model);
	}
}
