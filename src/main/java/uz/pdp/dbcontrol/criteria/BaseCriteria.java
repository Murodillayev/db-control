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
    private String search;
    private Integer page;
    private Integer size;



}
