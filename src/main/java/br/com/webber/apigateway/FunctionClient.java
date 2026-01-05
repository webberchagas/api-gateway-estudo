package br.com.webber.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FunctionClient {

    private final AzureAdTokenService tokenService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${function.url}")
    private String functionUrl;

    @Value("${function.scope}")
    private String functionScope;

    public FunctionClient(AzureAdTokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String callHealth() {
        String token = tokenService.getToken(functionScope);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        functionUrl + "/api/health",
                        HttpMethod.GET,
                        entity,
                        String.class
                );

        return response.getBody();
    }
}
