package com.wd40.satisfactorymanager.contoller;

import com.wd40.satisfactorymanager.dto.request.FactoryUpdateDto;
import com.wd40.satisfactorymanager.dto.response.FactoryDto;
import com.wd40.satisfactorymanager.service.FactoryService;
import com.wd40.satisfactorymanager.util.FactoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factory")
public class FactoryController {

  private final FactoryService factoryService;

  @Autowired
  public FactoryController(FactoryService factoryService) {
    this.factoryService = factoryService;
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public FactoryDto getFactoryById(@PathVariable Integer id) {
    return FactoryMapper.INSTANCE.toDto(factoryService.getFactoryById(id));
  }

  @PostMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public FactoryDto createNewFactory(@PathVariable("name") String name) {
    return FactoryMapper.INSTANCE.toDto(factoryService.createNewFactory(name));
  }

  @PutMapping(
      path = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public FactoryDto updateFactory(
      @RequestBody FactoryUpdateDto updateDto, @PathVariable("id") Integer id) {
    return FactoryMapper.INSTANCE.toDto(
        factoryService.updateFactory(id, updateDto.getName(), updateDto.getChanges()));
  }
}
