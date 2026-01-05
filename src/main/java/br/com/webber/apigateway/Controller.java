package br.com.webber.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    private final FunctionClient functionClient;

    public Controller(FunctionClient functionClient) {
        this.functionClient = functionClient;
    }

    @GetMapping("/health")
    public String health() {
        return functionClient.callHealth();
    }
}
