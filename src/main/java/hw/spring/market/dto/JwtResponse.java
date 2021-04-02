package hw.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;

    public JwtResponse(String token) { // не понимаю! почему ругался пока не создал руками этот конструктор. Ругался в AuthControllere
        this.token = token;            //в строке        return ResponseEntity.ok(new JwtResponse(token));
    }
}
