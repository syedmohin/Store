package com.afc.validator;

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
		if (srcControl.get() instanceof TextInputControl) {
			evalTextInputField();
		}
	}

	private void evalTextInputField() {
		TextInputControl textField = (TextInputControl) srcControl.get();
		String text = textField.getText();
		hasErrors.set(false);

		if (!text.isEmpty()) {
			if (text.length() < stringLength - 1) {
				hasErrors.set(true);
			}
		}
	}

}
