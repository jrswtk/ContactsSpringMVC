package com.user.reg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class IndexController {

	@RequestMapping("/")
	public String getIndex(@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "confirm", required = false) String confirm,
			@RequestParam(value = "unsuccess", required = false) String unsuccess,
			Model model) {
		
		if (success != null) {
			model.addAttribute("message", "Zostales zarejestrowany.");
		}
		
		if (confirm != null) {
			model.addAttribute("message", "Konto wymaga potwierdzenia.");
		}
		
		if (unsuccess != null) {
			model.addAttribute("message", "Niepowodzenie przy weryfikacji konta.");
		}
		
		return "index";
	}
	
}
