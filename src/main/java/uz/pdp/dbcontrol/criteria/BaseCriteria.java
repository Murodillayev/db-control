package uz.pdp.dbcontrol.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BaseCriteria {
    private Integer size;
    private Integer page;
    private String search;
}
