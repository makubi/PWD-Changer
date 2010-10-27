package at.makubi.wicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	// Alt + Shift + S
	
	// TODO Add any page properties or variables here

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
		
		
		final PasswordObject pwObject = new PasswordObject();
		//PropertyModel formModel = new PropertyModel<String>(this, "password");
		CompoundPropertyModel<String> propModel = new CompoundPropertyModel<String>(pwObject);
		
		final TextField<String> usernameField = new TextField<String>("username");
		final PasswordTextField oldpwField = new PasswordTextField("oldpw");
		final PasswordTextField newpw1Field = new PasswordTextField("newpw1");
		final PasswordTextField newpw2Field = new PasswordTextField("newpw2"); 
		setDefaultModel(propModel);
		
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
				System.out.println(pwObject.getUsername());
				System.out.println(pwObject.getOldpw());
			}
		};

		form.add(usernameField);
		form.add(oldpwField);
		form.add(newpw1Field);
		form.add(newpw2Field);
		form.add(button1);
		
		add(form);
		add(new FeedbackPanel("feedback"));

		// TODO Add your page's components here
	}
}
