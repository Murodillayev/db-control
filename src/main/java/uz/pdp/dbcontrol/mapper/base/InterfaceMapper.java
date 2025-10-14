package uz.pdp.dbcontrol.mapper.base;

import org.mapstruct.MappingTarget;

public interface InterfaceMapper<D, CD, UD, E> {
    D toDto(E entity);
    E toEntityFromCreate(CD dto);
    void updateEntityFromDto(UD dto, @MappingTarget E entity);
}
