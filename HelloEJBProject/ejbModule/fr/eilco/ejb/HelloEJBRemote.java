package fr.eilco.ejb;

import javax.ejb.Remote;

import fr.eilco.ing.bean.HelloBean;

@Remote
public interface HelloEJBRemote {
	public void insertEntity(HelloBean bean);
	public HelloBean sayHelloEntity();
}
