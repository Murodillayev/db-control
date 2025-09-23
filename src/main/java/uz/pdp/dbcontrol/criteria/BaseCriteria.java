package uz.pdp.dbcontrol.criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCriteria {
    private Integer size;
    private Integer page;
    private String search;
}
