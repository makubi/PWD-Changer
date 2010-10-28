package at.makubi.wicket;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

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
		CompoundPropertyModel<String> propModel = new CompoundPropertyModel<String>(
				pwObject);

		final TextField<String> usernameField = new TextField<String>(
				"username");
		final PasswordTextField oldpwField = new PasswordTextField("oldpw");
		final PasswordTextField newpw1Field = new PasswordTextField("newpw1");
		final PasswordTextField newpw2Field = new PasswordTextField("newpw2");
		setDefaultModel(propModel);

		Form form = new Form("form");
		
		Button button1 = new Button("button1") {
			@Override
			public void onSubmit() {
				System.out.println("Username:" + pwObject.getUsername());
				System.out.println("Old pw:" + pwObject.getOldpw());
				System.out.println("New pw1:" + pwObject.getNewpw1());
				System.out.println("New pw2:" + pwObject.getNewpw2());
			
				changePassword(pwObject);
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
	
	private void changePassword(PasswordObject pwObject) {
		BufferedReader in = null;
		BufferedReader err = null;
		OutputStream out = null;
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash");
			Process process = processBuilder.start();
			
			out = process.getOutputStream();
			
			out.write(("su - "+pwObject.getUsername()+"\n").getBytes());
			out.write((pwObject.getOldpw()+"\n").getBytes());
			out.write("passwd\n".getBytes());
			out.write((pwObject.getNewpw1()+"\n").getBytes());
			out.write((pwObject.getNewpw2()+"\n").getBytes());
			out.close();
			
			in = new BufferedReader (new InputStreamReader(process.getInputStream()));			
			err = new BufferedReader (new InputStreamReader(process.getErrorStream()));
			
			printStream(in);
			printStream(err);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			closeStream(out);
			closeStream(in);
			closeStream(err);
		}
	}
	
	private void closeStream(Closeable stream) {
		try {
			if(stream != null) {
				stream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void printStream(BufferedReader br) {
		try {
			for(String line = br.readLine(); line != null; line = br.readLine()) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
