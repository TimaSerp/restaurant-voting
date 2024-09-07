package ru.serpov.restaurantvoting.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.ParameterizedType;

@UtilityClass
public class ReflectionUtil {

    public static <T> Class<T> getGenericArgument(Class<?> sourceClass, int argumentIndex) {
        return (Class<T>) ((ParameterizedType) sourceClass.getGenericSuperclass()).getActualTypeArguments()[argumentIndex];
    }
}
