package pers.dwl.sardine.ioc.test;

import pers.dwl.sardine.ioc.Ioc;
import pers.dwl.sardine.ioc.impl.SineIoc;

public class test {

	public static void main(String[] args) {
		Ioc ioc= new SineIoc();
		MyAction myBean = ioc.getEntity(MyAction.class);
		myBean.doSome();
	}

}
