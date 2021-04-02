package hw.spring.market.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JwtRequest {
    private String username;
    private String pass;
    private UUID cartId;
}
