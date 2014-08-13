package com.epam.podorozhniki.ui;

import java.sql.SQLException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.podorozhniki.core.DBConnection;
import com.epam.podorozhniki.core.Driver;

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

	public void typeLogin(String loginForRegistrationNewUser) {
		this.login.clear();
		this.login.sendKeys(loginForRegistrationNewUser);
	}

	public void typePassword(String passwordForRegistrationNewUser) {
		this.password.clear();
		this.password.sendKeys(passwordForRegistrationNewUser);
	}
	
	public void confirmPassword(String passwordForRegistrationNewUser) {
		this.confirmPassword.clear();
		this.confirmPassword.sendKeys(passwordForRegistrationNewUser);
	}

    public void typeEmail(String emailForRegistrationNewUser) {
        this.email.clear();
        this.email.sendKeys(emailForRegistrationNewUser);
    }

	public void typephone(String phoneForRegistrationNewUser) {
		this.phone.clear();
		this.phone.sendKeys(phoneForRegistrationNewUser);
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
	
	public void deleteNewUserFromDB(String loginForRegistrationNewUser) throws SQLException{
		DBConnection db = new DBConnection();
		//3 delete statements
		String deleteIDFromPerson = "DELETE FROM person WHERE ID_CLIENT IN (SELECT id_client FROM client WHERE login = '"+loginForRegistrationNewUser+"')";
		String deleteIDFromRoles = "DELETE FROM roles WHERE id_client in (select id_client from client where login = '"+loginForRegistrationNewUser+"')";
		String deleteLoginFromClient = "DELETE FROM client WHERE id_client in (select id_client from client where login = '"+loginForRegistrationNewUser+"')";
		db.queryExecutor(deleteIDFromPerson);
		db.queryExecutor(deleteIDFromRoles);
		db.queryExecutor(deleteLoginFromClient);

	}
}
