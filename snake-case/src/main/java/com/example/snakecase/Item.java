package com.example.snakecase;

import java.beans.ConstructorProperties;

public record Item(
  String itemId,
  String itemName
) {
  @ConstructorProperties({
    "item_id",
    "item_name"
  })
  public Item {
  }
}
