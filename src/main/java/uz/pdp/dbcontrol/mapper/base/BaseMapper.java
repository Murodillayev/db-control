package uz.pdp.dbcontrol.mapper.base;

import org.mapstruct.MappingTarget;

public interface BaseMapper<D, CD, UD, E> {
    D toDto(E entity);

    E fromCreateDto(CD dto);

    void fromUpdateDto(UD dto, @MappingTarget E entity);
}
