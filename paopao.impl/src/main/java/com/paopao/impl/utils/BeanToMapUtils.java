package com.paopao.impl.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * vo 类转成 hashMap ,
 *
 * Created by huangzx on 2016/1/21.
 */
public class BeanToMapUtils {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(BeanToMapUtils.class);

    public static Map<String,Object>  toMap(Object bean){

        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Map<String,Object> map = new HashMap<String, Object>(fields.length);
        for (Field field : fields){
            setValue(field.getName(),bean,clazz,map);
        }
        return map;
    }


    private static void setValue(String fieldName,Object bean,Class<?> clazz,Map<String,Object> map){

        try{
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Object value = pd.getReadMethod().invoke(bean);
            if (value != null){
                //值不为空才赋值
                map.put(fieldName,value);
            }
        }catch (Exception e){
            LOG.error("vo 转map 赋值异常 ：Field : " + fieldName + " error :" + e.getMessage() );
        }
    }
}