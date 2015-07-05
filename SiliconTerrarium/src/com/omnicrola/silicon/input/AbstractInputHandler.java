package com.omnicrola.silicon.input;

import org.newdawn.slick.ControlledInputReciever;
import org.newdawn.slick.Input;

public class AbstractInputHandler implements ControlledInputReciever {

	protected Input input;

	public AbstractInputHandler() {
		super();
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {
		this.input = input;
	}

}