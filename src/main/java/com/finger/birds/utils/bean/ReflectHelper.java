package com.finger.birds.utils.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class ReflectHelper {
	private static Logger log = Logger.getLogger(ReflectHelper.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Object reflectObj(T t, Map map) throws Exception {
		Class clazz = t.getClass();
		// 实例化类
		T entity = (T) clazz.newInstance();
		Set<String> keys = map.keySet();
		// 变量map 赋值
		for (String key : keys) {
			String fieldName = key;
			if (key.equals(fieldName.toUpperCase())) {
				// 获取所有域变量
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					if (field.getName().toUpperCase().equals(key))
						fieldName = field.getName();
					break;
				}
			}
			// 设置赋值
			try {
				// 参数的类型 clazz.getField(fieldName)
				Class<?> paramClass = clazz.getDeclaredField(fieldName)
						.getType();
				// 拼装set方法名称
				String methodName = "set"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				// 根据名称获取方法
				Method method = clazz.getMethod(methodName, paramClass);
				// 调用invoke执行赋值
				method.invoke(entity, map.get(key));
			} catch (Exception e) {
				// NoSuchMethod
			}
		}

		return entity;
	}

	/**
	 * 通过反射调用相应的get方法获取值，并封装成map返回
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> flectObj(Object obj){
		Map<String, String> map = new HashMap<String, String>();

		Class<?> objClass = obj.getClass();
		Field[] field = objClass.getDeclaredFields();
		for (Field f : field) {
			try {
				String fieldName = f.getName();
				// 参数的类型 clazz.getField(fieldName)
				// 拼装set方法名称
				String methodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				// 根据名称获取方法
				Method method = objClass.getDeclaredMethod(methodName);
				String value = (String) method.invoke(obj);
				map.put(fieldName, value);
			} catch (Exception e) {
				log.error("映射对象属性为map异常：" + e.getMessage());
				//TODO
				//throw new BusinessException("反射失败");
			}
			// 调用invoke执行get方法获取值
		}
		return map;
	}
}
