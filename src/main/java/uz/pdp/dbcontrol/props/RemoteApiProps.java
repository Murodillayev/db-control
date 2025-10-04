package uz.pdp.dbcontrol.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.remote-api")
@Getter @Setter
public class RemoteApiProps {
    private String productsApi;
    private String productApi;
}
