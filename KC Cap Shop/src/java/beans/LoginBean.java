package beans;


import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Named
@RequestScoped
public class LoginBean extends SuperBb implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	transient Logger log;

	private String username;
	private String password;

	// Sign in

	public String login() {
		HttpServletRequest request = getRequest();
		try {
			log.info("user: " + username + "/ " + password);
			request.login(username, password);
		} catch (ServletException ex) {
			facesErrorMsg("We cannot find an account with that username and password.");
			return null;
		}

		return "/index.xhtml?faces-redirect=true";
	}

	//　Sign out

	public String logout() {
		try {
			getServlet().invalidateSession();
			HttpServletRequest request = getRequest();
			request.logout();       //delate the sign in info
		} catch (ServletException ex) {}
               
		return "/index.xhtml?faces-redirect=true ";
	}

  
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
