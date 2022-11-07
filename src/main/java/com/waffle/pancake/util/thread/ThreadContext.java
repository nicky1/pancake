package com.waffle.pancake.util.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;
import com.waffle.pancake.dto.UserInfo;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
public class ThreadContext {

    private static final TransmittableThreadLocal<Map<Object, Object>> ttl = new TransmittableThreadLocal() {
        @Override
        protected Map<Object, Object> childValue(Object parentValue) {
            if (parentValue instanceof Map) {
                return new ConcurrentHashMap<>((Map) parentValue);
            }
            return null;
        }

        @Override
        protected Map<Object, Object> initialValue() {
            return new ConcurrentHashMap<>();
        }

        @Override
        public Map<Object, Object> copy(Object parentValue) {
            if (parentValue instanceof Map) {
                return new ConcurrentHashMap<>((Map) parentValue);
            }
            return null;
        }
    };

    private static final String USER_KEY = ThreadContext.class.getName() + "_USER_KEY";

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        ensureResourceInitialized();
        ttl.get().put(key, value);
    }

    private static void ensureResourceInitialized() {
        if (ttl.get() == null) {
            ttl.set(Maps.newHashMap());
        }
    }

    private static void remove(Object key) {
        Map<Object, Object> resources = ttl.get();
        if (resources != null) {
            resources.remove(key);
        }
    }

    private static Object getValues(Object key) {
        Map<Object, Object> resources = ttl.get();
        return Objects.isNull(resources) ? null : resources.get(key);
    }

    public static void bindUser(UserInfo userInfo) {
        put(USER_KEY, userInfo);
    }

    public static void unBindUser() {
        remove(USER_KEY);
    }

    public static UserInfo getUser() {
        return (UserInfo) getValues(USER_KEY);
    }

    public static void remove() {
        ttl.remove();
    }

}
