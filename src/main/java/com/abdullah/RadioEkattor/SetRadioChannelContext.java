package com.abdullah.RadioEkattor;

public class SetRadioChannelContext {
	
	private Radio radio;

	public void setRadio(Radio radio) {
		this.radio = radio;
	}
	
	public void startRadio() {
		radio.startRadio();
	}
	

}
