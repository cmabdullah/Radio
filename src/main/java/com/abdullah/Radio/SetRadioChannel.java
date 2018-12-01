package com.abdullah.Radio;

public class SetRadioChannel {
	
	private Radio radio;

	public void setRadio(Radio radio) {
		this.radio = radio;
	}
	
	public void startRadio() {
		radio.startRadio();
	}
}
