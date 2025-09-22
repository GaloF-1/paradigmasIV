package galo.app.dto;
import javax.validation.constraints.*;

public record AddCartItemRequest(@NotNull Long productId, @Min(1) int quantity) {}
