package galo.app.dto;
import javax.validation.constraints.*;
public record UpdateCartItemRequest(@NotNull Long cartItemId, @Min(1) int quantity) {}
