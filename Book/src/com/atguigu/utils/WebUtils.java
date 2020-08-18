package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-21 10:38 下午
 */
public class WebUtils {
    /**
     * 将map中的值注入到javaBean中
     *
     * @param map
     * @param bean
     */
    public static <T> T copyParamToBean(Map map, T bean) {
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strId, int defaultValue) {
        try {
            return Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
