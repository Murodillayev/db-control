package uz.pdp.dbcontrol.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> {
    private T data;
    private Long totalElements;
    private Integer totalPages;

    public DataResponse(T data) {
        this.data = data;
    }

    public DataResponse(T data, Long totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }
}
