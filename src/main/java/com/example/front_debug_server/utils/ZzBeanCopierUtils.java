package com.example.front_debug_server.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cglib的BeanCopier的性能是最好的无论是数量较大的1万次的测试，还是数量较少10次，几乎都是趋近与零损耗。 Spring是在次数增多的情况下，性能较好，在数据较少的时候，性能比PropertyUtils的性能差一些。
 * PropertyUtils的性能相对稳定，表现是呈现线性增长的趋势。 而Apache的BeanUtil的性能最差，无论是单次Copy还是大数量的多次Copy性能都不是很好
 *
 **/
@Slf4j
public class ZzBeanCopierUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T strToBean(String str, Class<T> clazz) {
        try {
            T t = mapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            log.error("{}", e);
        }
        return null;
    }

    /**
     * 采用缓存提高效率
     */
    private static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * @param s 源对象
     * @param t 目标对象
     * @return void
     */
    public static <S, T> T copy(S s, T t) {
        String key = generateKey(s.getClass(), t.getClass());
        BeanCopier beanCopier;
        if (BEAN_COPIER_CACHE.contains(key)) {
            beanCopier = BEAN_COPIER_CACHE.get(key);
        } else {
            beanCopier = BeanCopier.create(s.getClass(), t.getClass(), false);
            BEAN_COPIER_CACHE.put(key, beanCopier);
        }
        beanCopier.copy(s, t, null);
        return t;
    }

    /**
     * @param srcClazz 源文件的class
     * @param tgtClazz 目标文件的class
     */
    private static String generateKey(Class<?> srcClazz, Class<?> tgtClazz) {
        return srcClazz.getName() + tgtClazz.getName();
    }

    /**
     * bean to map
     *
     * @param bean
     * @param notKey
     * @return java.util.Map
     */
    public static <T> Map beanToMap(T bean, List<String> notKey) {
        Map<String, Object> map = new HashMap<>();
        if (null != bean) {
            BeanMap beanMap = BeanMap.create(bean);
            Set set = beanMap.keySet();
            set.forEach(key -> {
                boolean contains = false;
                if (CollectionUtils.isNotEmpty(notKey)) {
                    contains = notKey.contains(key.toString());
                }
                if (beanMap.get(key) != null && !contains) {
                    map.put(key.toString(), beanMap.get(key));
                }
            });
        }
        return map;
    }

    /**
     * 深比较两个对象
     */
    public static <T> boolean compareObject (T target, T obj) {
        String s1 = MD5Util.MD5(JSON.toJSONString(target));
        String s2 = MD5Util.MD5(JSON.toJSONString(obj));
        return s1.equals(s2);
    }
}