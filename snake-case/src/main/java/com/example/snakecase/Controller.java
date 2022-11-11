package com.example.snakecase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class Controller {

  @GetMapping
  public String get(
    Item item
  ) {
    System.out.println(item.itemId());
    return "{}";
  }
}
