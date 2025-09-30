package uz.pdp.dbcontrol.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DataResponse<T> {
    protected T data;

    private Long allElements;

    private Integer allPages;


    public DataResponse(T data) {
        this.data = data;
    }

    public DataResponse(T data, Long allElements) {
        this(data);
        this.allElements = allElements;
    }

    public DataResponse(T data, Long allElements, Integer allPages) {
        this(data, allElements);
        this.allPages = allPages;
    }
}
