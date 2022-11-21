package com.example.snakecase;

import io.swagger.v3.oas.annotations.Parameter;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

public record Item(
  @Parameter(name = "item_id", description = "id", required = true)
  @NotNull
  String itemId,
  @Parameter(name = "item_name", description = "name", required = true)
  @NotNull
  String itemName
) {
  @ConstructorProperties({
    "item_id",
    "item_name"
  })
  public Item {
  }
}
