package com.afc.utility;

import com.jfoenix.validation.base.ValidatorBase;

import javafx.scene.control.TextInputControl;

public class LengthValidation extends ValidatorBase {

	int stringLength;

	public LengthValidation(int stringLength, String message) {
		this.stringLength = stringLength + 1;
		setMessage(message + stringLength);
	}

	@Override
	protected void eval() {
		if (srcControl.get() instanceof TextInputControl)
			evalTextInputField();
	}

	private void evalTextInputField() {
		var textField = (TextInputControl) srcControl.get();
		var text = textField.getText();
		hasErrors.set(false);

		if (!text.isEmpty() && text.length() < stringLength - 1)
			hasErrors.set(true);
	}

}
