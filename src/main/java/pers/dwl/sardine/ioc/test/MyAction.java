package pers.dwl.sardine.ioc.test;

import pers.dwl.sardine.ioc.annotation.Inject;
import pers.dwl.sardine.ioc.annotation.IocBean;

@IocBean
public class MyAction {
	
	@Inject
	private MyService myService;
	
	public void doSome(){
		myService.doSome();
	}

}
