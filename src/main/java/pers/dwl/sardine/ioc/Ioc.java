package pers.dwl.sardine.ioc;

/**
 * ioc 容器接口
 * @author dwl
 *
 */
public interface Ioc {

	/**
	 * 获取实例对象
	 * @param clazz
	 * @return 实例对象
	 */
	<T> T getEntity(Class<T> clazz);
	
    /**
     * ioc中该是否有实例对象
     * @param clazz
     * @return boolean 
     */
    boolean hasEntity(Class<?> clazz);
    
    /**
     * 将容器注销
     */
    void depose();

}
