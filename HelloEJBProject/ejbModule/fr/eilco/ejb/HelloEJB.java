package fr.eilco.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.eilco.ing.bean.HelloBean;

/**
 * Session Bean implementation class HelloEJB
 */
@Stateless(mappedName = "HelloJNDI")
public class HelloEJB implements HelloEJBRemote, HelloEJBLocal {

	@PersistenceContext(unitName="managerHello")
	EntityManager mh;
    /**
     * Default constructor. 
     */
    public HelloEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public void insertEntity(HelloBean bean){
    	mh.persist(bean);   //pour l'insertion
    	mh.flush(); 
    	//merge method for updating
    	//delete // fir deleting 
    }
    
    public HelloBean sayHelloEntity(){
    	HelloBean bean = new HelloBean();
    	
    	//On écrit une requête avec JPA 1.0
    	Query q = mh.createQuery("select h from HelloBean h where h.id=:id");
    	q.setParameter("id", 2);
    	bean= (HelloBean) q.getSingleResult();
    	
    	return bean;
    }

}
