package pers.dwl.sardine.ioc.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pers.dwl.sardine.ioc.Ioc;
import pers.dwl.sardine.ioc.annotation.Inject;
import pers.dwl.sardine.ioc.annotation.IocBean;
import pers.dwl.sardine.util.ArrayUtil;
import pers.dwl.sardine.util.ClassHelper;

/**
 * ioc实现类
 * @author dwl
 *
 */
public class SineIoc implements Ioc {
	
	/**
	 * 实例对象集合-未完成注入
	 */
	private static Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();
	
	/**
	 * 实例对象集合-已完成注入（第一次调用时注入）
	 */
	private static Map<Class<?>, Object> injectMap = new HashMap<Class<?>, Object>();
	
	//初始化
	static{
		//获取基础包下所有class
		List<Class<?>> classes = ClassHelper.getClassList();
		classes.forEach(clazz->{
			if(clazz.isAnnotationPresent(IocBean.class)){
				try {
					//加入实例对象集合
					beanMap.put(clazz, clazz.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	

	@SuppressWarnings("unchecked")
	public <T> T getEntity(Class<T> clazz) {
		if(!injectMap.containsKey(clazz)){
			if(!beanMap.containsKey(clazz)){
				throw new RuntimeException("没有该示例对象 - "+clazz);
			}else{
				setFiled(clazz);//依赖注入，加入缓存
			}
		}
		return (T)injectMap.get(clazz);
	}

	public boolean hasEntity(Class<?> clazz) {
		return injectMap.containsKey(clazz);
	}
	
	public void depose() {
		beanMap.clear();
		injectMap.clear();
	}
	
	/**
	 * 给属性赋值-依赖注入
	 * @param clazz
	 */
	public void setFiled(Class<?> clazz){
		 //获取所有属性
		 Field[] fields = clazz.getDeclaredFields();
		 //获取实例对象
		 Object instance = beanMap.get(clazz);
		 //有属性
		 if(!ArrayUtil.isEmpty(fields)){
			 for(Field field:fields){
				 //有待注入的属性
				 if(field.isAnnotationPresent(Inject.class)){
					 //获取属性上的inject信息
					 Inject inject = field.getAnnotation(Inject.class);
					 Class<?> type = inject.value();
					 //默认String.class,表示该属性类型是类不是接口
					 if(type.isAssignableFrom(String.class)){
						 Class<?> implClass = field.getType();
						//为属性对象实例进行依赖注入，不考虑循环依赖
						 setFiled(implClass);
						 //从injectMap获取已经注入好的属性对象实例
						 Object implBean =injectMap.get(implClass);
						 field.setAccessible(true);
						 try {
							 //为属性赋值
							field.set(instance, implBean);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					 }else{//为接口时value要声明具体实现类
						 setFiled(type);//为属性对象实例进行依赖注入，不考虑循环依赖
						//从injectMap获取已经注入好的属性对象实例
						 Object implBean =injectMap.get(type);
						 field.setAccessible(true);
						 try {
							 //为属性赋值
							field.set(instance, implBean);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					 }
				 }
			 }
			
		 }
		 injectMap.put(clazz, instance);//加入缓存
		 
	}

}
