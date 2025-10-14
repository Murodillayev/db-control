package uz.pdp.dbcontrol.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppErrorResponse {
    private Integer status;
    private String message;
    private String error;
    private String path;
    private String developerMessage;

    public AppErrorResponse(Integer status, String message) {
        this.status = status;
        this.error = message;
    }

    public AppErrorResponse(Integer status, String message, String developerMessage) {
        this.status = status;
        this.error = message;
        this.developerMessage = developerMessage;
    }
}
