package br.com.webber.apigateway;

import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import org.springframework.stereotype.Component;


@Component
public class AzureAdTokenService {

    private final DefaultAzureCredential credential =
            new DefaultAzureCredentialBuilder().build();

    public String getToken(String scope) {
        TokenRequestContext context = new TokenRequestContext()
                .addScopes(scope);

        return credential.getToken(context).block().getToken();
    }
}
