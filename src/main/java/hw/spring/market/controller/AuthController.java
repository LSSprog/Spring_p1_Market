package hw.spring.market.controller;

import hw.spring.market.beans.JwtTokenUtil;
import hw.spring.market.dto.JwtRequest;
import hw.spring.market.dto.JwtResponse;
import hw.spring.market.exeptionsHandling.MarketError;
import hw.spring.market.service.CartService;
import hw.spring.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final CartService cartService;

    @PostMapping ("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (jwtRequest.getUsername(), jwtRequest.getPass()));
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(),
                    "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtil.doToken(userDetails);

        cartService.getCartForUser(jwtRequest.getUsername(), jwtRequest.getCartId());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
