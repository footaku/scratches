package com.example.snakecase;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class Controller {

  @GetMapping
  public String get(
    @ParameterObject @Validated Item item
  ) {
    return String.format("""
      { "id": %s, "name": %s }
      """.stripIndent(), item.itemId(), item.itemName());
  }
}
