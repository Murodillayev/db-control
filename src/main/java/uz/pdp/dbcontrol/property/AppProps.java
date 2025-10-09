package uz.pdp.dbcontrol.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppProps {

    @Value("${application.token.access.exp:86400000}")
    private Long accessTokenExp;

    @Value("${application.token.refresh.exp:}")
    private Long refreshTokenExp;

}
