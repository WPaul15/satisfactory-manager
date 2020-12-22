package com.wd40.satisfactorymanager.contoller;

import com.wd40.satisfactorymanager.model.Factory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

	@PostMapping(
		path = "/factory/{name}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public Factory createNewFactory(@PathVariable("name") String name) {
		return new Factory(name);
	}
}
