package pers.dwl.sardine.ioc.test;

import pers.dwl.sardine.ioc.annotation.IocBean;

@IocBean
public class Myservice2 {
	
	public void doSome(){
		System.out.println("----------注入啦------------");
	}

}
