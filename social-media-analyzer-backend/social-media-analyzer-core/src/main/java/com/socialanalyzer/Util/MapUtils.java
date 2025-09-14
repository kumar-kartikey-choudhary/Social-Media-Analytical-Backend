package com.socialanalyzer.Util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class MapUtils {

    public static <E,D> E dtoToEntity(D dto , Class<E> entityClass ) throws Exception {
        E entity = entityClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static <D,E> D entityToDto(E entity, Class<D> dtoClass) throws Exception
    {
        D dto = dtoClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
