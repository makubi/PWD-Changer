package at.makubi.wicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	// Alt + Shift + S
	private String password;

	// TODO Add any page properties or variables here

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters) {

		// Add the simplest type of label
		add(new Label("message",
				"If you see this message wicket is properly configured and running"));
		
		
		PropertyModel formModel = new PropertyModel<String>(this, "password");
		
		final TextField<String> oldpwField = new TextField<String>("oldpw",formModel);
		PasswordTextField newpw1Field = new PasswordTextField("newpw1");
		PasswordTextField newpw2Field = new PasswordTextField("newpw2"); 

		
		Form form = new Form("form") {
			@Override
		    public void onSubmit() {
		        // handle the submission of the form.
		    	System.out.println("test2");
		    }
			@Override
		    public void onError() {
		        // do something special when an error occurs,
		        // instead of displaying messages.
		    	System.out.println("test2 error");
		    }
		};

		Button button1 = new Button("button1") {
			@Override
			public void onSubmit() {
				System.out.println("test1");
				System.out.println(password);
			}
		};

		form.add(oldpwField);
		//form.add(newpw1Field);
		//form.add(newpw2Field);
		form.add(button1);
		
		add(new FeedbackPanel("feedback"));
		add(form);

		// TODO Add your page's components here
	}
}
