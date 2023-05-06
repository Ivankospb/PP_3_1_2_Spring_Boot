package ru.yakovlev.springboot.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev.springboot.dao.UserDao;
import ru.yakovlev.springboot.model.User;

@Controller
@RequestMapping("/people")
public class HelloController {
	private final UserDao userDao;

	public HelloController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping()
	public String index(Model model) {
		// Получим всех людей из DAO(Service) и передадим на отображение в представление
		model.addAttribute("people", userDao.index());
		return "people/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		// Получим одного человека по id из DAO и передадим на отображение и представление
		model.addAttribute("user", userDao.show(id));
		return "people/show";
	}

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "people/new";
	}

	@PostMapping
	public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) // если будут ошибки с валидацией, будет возврат
			return "people/new";

		userDao.save(user);
		return "redirect:/people"; // переход на другую страницу, после записи нового человека
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userDao.show(id));
		return "people/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "people/edit";

		userDao.update(id, user);
		return "redirect:/people";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		userDao.delete(id);
		return "redirect:/people";
	}
}