package galo.app.dto;
import javax.validation.constraints.*;
public record CheckoutRequest(@Email @NotBlank String email, String name, String shippingAddress) {}
