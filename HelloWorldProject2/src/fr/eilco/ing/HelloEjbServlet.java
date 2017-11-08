package fr.eilco.ing;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context; // use of JNDI Java Naming Directory Interface 
import javax.naming.InitialContext; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eilco.ejb.HelloEJBRemote;
import fr.eilco.ing.bean.HelloBean;

/**
 * Servlet implementation class HelloEjbServlet
 */
@WebServlet("/HelloEjbServlet")
public class HelloEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloEjbServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello from deGet of HelloEJBServlet");
		exo2(request, response);
	}

	private void exo1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession(true);
		String name=request.getParameter("nom");
		HelloBean bean= new HelloBean();
		bean.setName(name);
		//connexion via JNDI (annuaire pour localiser EJB)
		System.out.println("Hello "+bean.getName());
				
		try{
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context= new InitialContext(jndiProperties);
			
			final String appName="HelloEAR";
			final String moduleName = "HelloEJBProject";
			final String distinctName="";
			final String beanName="HelloEJB";
			final String viewClassName = HelloEJBRemote.class.getName();
			
			HelloEJBRemote remote =(HelloEJBRemote) context.lookup("ejb:"+appName+"/"+moduleName +
					"/"+distinctName+"/"+beanName+"!"+viewClassName);
			System.out.println("Hello again "+bean.getName());
			
			remote.insertEntity(bean);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Hello after jndi connection "+bean.getName());
		
		session.setAttribute("beanHello", bean);
		//response.sendRedirect("Hello.jsp");
	}
	
	
	
	//exo2
	private void exo2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession(true);
		String name=request.getParameter("nom");
		HelloBean bean= new HelloBean();
		bean.setName(name);
		//connexion via JNDI (annuaire pour localiser EJB)
		System.out.println("Hello "+bean.getName());
				
		try{
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context= new InitialContext(jndiProperties);
			
			final String appName="HelloEAR";
			final String moduleName = "HelloEJBProject";
			final String distinctName="";
			final String beanName="HelloEJB";
			final String viewClassName = HelloEJBRemote.class.getName();
			
			HelloEJBRemote remote =(HelloEJBRemote) context.lookup("ejb:"+appName+"/"+moduleName +
					"/"+distinctName+"/"+beanName+"!"+viewClassName);
			
			System.out.println("Hello again "+bean.getName());
			bean=remote.sayHelloEntity();
			
			System.out.println("Le nom dans le bean est : "+ bean.getName());
			//remote.insertEntity(bean);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Hello after jndi connection "+bean.getName());
		
		session.setAttribute("beanHello", bean);
		//response.sendRedirect("Hello.jsp");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
