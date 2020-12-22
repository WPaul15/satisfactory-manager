package com.wd40.satisfactorymanager.contoller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

	@GetMapping(path = "/helloworld", produces = MediaType.APPLICATION_JSON_VALUE)
	public String helloWorld() {
		return "Hello world!";
	}
}
