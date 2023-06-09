package com.hlfront.result;

import java.util.stream.Stream;

/**
 * 获取枚举值
 *
 * @param <T> 值类型
 * @author bing_huang
 * @since 3.0.0
 */
public interface ValueEnum<T> {
    /**
     * <p>
     * 根据value获取枚举
     * </p>
     *
     * @param enumType 枚举类型
     * @param value    枚举值
     * @param <V>      值泛型
     * @param <E>      枚举泛型
     * @return 对应的枚举
     */
    static <V, E extends ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {
        return Stream.of(enumType.getEnumConstants())
                .filter(item -> item.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown database value: " + value));
    }

    /**
     * Gets enum value.
     *
     * @return enum value
     */
    T getValue();
}
