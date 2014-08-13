package com.epam.podorozhniki.ui;

import java.sql.SQLException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.epam.podorozhniki.core.Driver;
import com.epam.podorozhniki.db.DBConnection;

public class RegistrationPage {
	
	@FindBy(id = "login")
	WebElement login;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(id = "phone")
	WebElement phone;

    @FindBy(id = "email")
    private WebElement email;
	
	@FindBy(name = "agree")
	WebElement checkbox;

	@FindBy (id = "b")
	WebElement submitButton;

    @FindBy(xpath = "//span[@id='login.errors']")
    WebElement error;
	
	public RegistrationPage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	public void typeLogin() {
		this.login.clear();
		this.login.sendKeys(System.getProperty("test.loginForRegistrationNewUser"));
	}

	public void typePassword() {
		this.password.clear();
		this.password.sendKeys(System.getProperty("test.passwordForRegistrationNewUser"));
	}
	
	public void confirmPassword() {
		this.confirmPassword.clear();
		this.confirmPassword.sendKeys(System.getProperty("test.passwordForRegistrationNewUser"));
	}

    public void typeEmail() {
        this.email.clear();
        this.email.sendKeys(System.getProperty("test.emailForRegistrationNewUser"));
    }

	public void typephone() {
		this.phone.clear();
		this.phone.sendKeys(System.getProperty("test.phoneForRegistrationNewUser"));
	}
	
	public void agreeChekButton() {
		this.checkbox.click();
		this.checkbox.isSelected();
	}
	
	public MainPageAfterLogin typeSubmitButton() {
		this.submitButton.click();
		return new MainPageAfterLogin();
	}

    public void verifyError(String incorrectLoginErrorMessage) {error.getText().equals(incorrectLoginErrorMessage); }
	
	public void deleteNewUserFromDB() throws SQLException{
		DBConnection db = new DBConnection();
		//3 delete statements
		String deleteIDFromPerson = "DELETE FROM person WHERE ID_CLIENT IN (SELECT id_client FROM client WHERE login = '"+System.getProperty("test.loginForRegistrationNewUser")+"')";
		String deleteIDFromRoles = "DELETE FROM roles WHERE id_client in (select id_client from client where login = '"+System.getProperty("test.loginForRegistrationNewUser")+"')";
		String deleteLoginFromClient = "DELETE FROM client WHERE id_client in (select id_client from client where login = '"+System.getProperty("test.loginForRegistrationNewUser")+"')";
		db.queryExecutor(deleteIDFromPerson);
		db.queryExecutor(deleteIDFromRoles);
		db.queryExecutor(deleteLoginFromClient);

	}
}
