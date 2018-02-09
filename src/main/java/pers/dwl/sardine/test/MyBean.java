package pers.dwl.sardine.test;

import pers.dwl.sardine.ioc.annotation.Inject;
import pers.dwl.sardine.ioc.annotation.IocBean;

@IocBean
public class MyBean {
	
	@Inject
	private MyService myService;
	
	@Inject(ServiceImpl.class)
	private Service service;
	
	public void doThing(){
		myService.doThing();
		service.doSome();
	}

}
