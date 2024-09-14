 package com.gen.codegen.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @ClassName MapperUtils
 * @Description 实体映射工具类
 * @Author ywt
 * @Date 2019-12-26 20:12
 */
public class MapperUtils {

    public static final  MapperUtils INSTANCE = new  MapperUtils();
    /**
     * 获取默认字段工厂
     */
    private final MapperFactory mapperFactory = getMapperFactory();
    /**
     * 默认字段实例
     */
    private final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

    private final Map<String, MapperFacade> cacheMapperFacadeMap = new HashMap<>();

    /**
     * 映射实体（默认字段）
     * 这种映射就是dto字段名称和实体对象DO之间字段名称一致
     *
     * @param toClass 映射类对象 dto对象
     * @param data      数据（对象）do对象
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data) {
        return mapperFacade.map(data, toClass);
    }

    /**
     * 映射实体（自定义配置）
     *
     * @param toClass     映射类对象 dto对象
     * @param data          数据（对象）do对象
     * @param configMap 自定义配置,dto与do对象字段名称不一致时配置的映射 {dataAttr, toClassAttr}
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data, Map<String, String> configMap) {
        MapperFacade mapperFacade = getMapperFacade(toClass, data.getClass(), configMap);
        return mapperFacade.map(data, toClass);
    }

    /**
     * 映射集合（默认字段）
     * 映射为集合的形式
     *
     * @param toClass   映射类对象 dto对象
     * @param data         数据（集合）do对象
     * @param configMap 自定义配置,dto与do对象字段名称不一致时配置的映射 {dataAttr, toClassAttr}
     * @return 映射类对象
     */
    public <E, T> List<E> mapAsList(Class<E> toClass, Collection<T> data, Map<String, String> configMap) {
        List<E> toList = Lists.newArrayList();
        for (T datum : data) {
            toList.add(map(toClass, datum, configMap));
        }
        return toList;
    }

    /**
     * 映射集合（默认字段）
     * 映射为集合的形式
     *
     * @param toClass 映射类对象 dto对象
     * @param data       数据（集合）do对象
     * @return 映射类对象
     */
    public <E, T> List<E> mapAsList(Class<E> toClass, Collection<T> data) {
        return mapperFacade.mapAsList(data, toClass);
    }

    /**
     * 映射分页对象
     *
     * @param toClass 映射目标对象
     * @param data    映射源对象
     * @param <E>     映射目标类型
     * @param <T>     映射源类型
     * @return 映射结果
     */
    public <E, T> Page<E> mapAsPage(Class<E> toClass, Page<T> data) {
        Page<E> result = new Page<>();
        BeanUtils.copyProperties(data, result);
        List<T> records = data.getRecords();
        result.setRecords( MapperUtils.INSTANCE.mapAsList(toClass, records));
        return result;
    }

    /**
     * 获取自定义映射
     *
     * @param toClass     映射类
     * @param dataClass 数据映射类
     * @param configMap 自定义配置
     * @return 映射类对象
     */
    private <E, T> MapperFacade getMapperFacade(Class<E> toClass, Class<T> dataClass, Map<String, String> configMap) {
        String mapKey = dataClass.getCanonicalName() + "_" + toClass.getCanonicalName();
        MapperFacade mapperFacade = cacheMapperFacadeMap.get(mapKey);
        if (Objects.isNull(mapperFacade)) {
            MapperFactory factory = new DefaultMapperFactory.Builder().build();
            ClassMapBuilder<T, E> classMapBuilder = factory.classMap(dataClass, toClass);
            configMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault().register();
            mapperFacade = factory.getMapperFacade();
            cacheMapperFacadeMap.put(mapKey, mapperFacade);
        }
        return mapperFacade;
    }

    private MapperFactory getMapperFactory() {
        DefaultMapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.getConverterFactory().registerConverter(new LocalDateTimeConverter());
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.getConverterFactory().registerConverter(new LocalTimeConverter());
        return factory;
    }

    private static class LocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {
        @Override
        public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> destinationType) {
            return LocalDateTime.from(source);
        }

        @Override
        public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> destinationType) {
            return LocalDateTime.from(source);
        }
    }

    private static class LocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
        @Override
        public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType) {
            return LocalDate.from(source);
        }

        @Override
        public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType) {
            return LocalDate.from(source);
        }
    }

    private static class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {
        @Override
        public LocalTime convertTo(LocalTime source, Type<LocalTime> destinationType) {
            return LocalTime.from(source);
        }

        @Override
        public LocalTime convertFrom(LocalTime source, Type<LocalTime> destinationType) {
            return LocalTime.from(source);
        }
    }
}
