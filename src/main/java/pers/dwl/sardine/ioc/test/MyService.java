package pers.dwl.sardine.ioc.test;

import pers.dwl.sardine.ioc.annotation.Inject;
import pers.dwl.sardine.ioc.annotation.IocBean;

@IocBean
public class MyService {
	
	@Inject
	private Myservice2 myservice2;
	
	public void doSome(){
		myservice2.doSome();
	}

}
