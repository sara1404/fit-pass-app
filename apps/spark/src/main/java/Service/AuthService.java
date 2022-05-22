package Service;

import Model.User;
import Repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private UserRepository userRepository;
    private String secret = "tajna";

    public AuthService(UserRepository userService) {
        this.userRepository = userService;
    }

    public User login(String username, String password) throws Exception {
        return userRepository.login(username, password);
    }

    public String signToken(User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("auth0")
                .withPayload(userMap)
                .sign(algorithm);
        return token;
    }

    public String verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

    public String extractTokenFromRequest(Request request) {
        String token = request.cookie("Authorization");
        if(token == null) token = request.headers("Authorization");
        if(token == null) return token;
        return splitBearerFromToken(token);
    }

    private String splitBearerFromToken(String bearerToken) {
        String token = bearerToken.split(" ")[1];
        return token;
    }
}