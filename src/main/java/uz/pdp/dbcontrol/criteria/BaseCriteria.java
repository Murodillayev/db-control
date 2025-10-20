package uz.pdp.dbcontrol.criteria;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseCriteria {
    private String search;
    private Integer page=0;
    private Integer size=10;
}
