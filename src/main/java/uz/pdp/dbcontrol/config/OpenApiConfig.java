package uz.pdp.dbcontrol.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Db control API",
                description = "API for DB control management system",
                version = "1",
                contact = @Contact(
                        name = "G53 soft",
                        email = "g53soft@gamil.com",
                        url = "https://olmos.soft.uz/api"
                )

        ),
        servers = {
                @Server(
                        description = "ser1",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "ser2",
                        url = "http://localhost:1111"
                )
        },
        security = {
                @SecurityRequirement(name = "bearerAuth")
//                @SecurityRequirement(name = "basicAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT scientification",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
