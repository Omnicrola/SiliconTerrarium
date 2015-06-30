package com.omnicrola.silicon.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class KeyInputHandler implements KeyListener {
	private final Map<Integer, List<IKeyListener>> allKeyListeners;

	public KeyInputHandler() {
		this.allKeyListeners = new HashMap<>();
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

	}

	@Override
	public void keyPressed(int key, char character) {
		if (this.allKeyListeners.containsKey(key)) {
			for (final IKeyListener iKeyListener : this.allKeyListeners.get(key)) {
				iKeyListener.keyDown();
			}
		}
	}

	@Override
	public void keyReleased(int key, char character) {
		if (this.allKeyListeners.containsKey(key)) {
			for (final IKeyListener iKeyListener : this.allKeyListeners.get(key)) {
				iKeyListener.keyUp();
			}
		}

	}

	public void addListener(int keyCode, IKeyListener listener) {
		List<IKeyListener> keyListeners = this.allKeyListeners.get(keyCode);
		if (keyListeners == null) {
			keyListeners = new ArrayList<>();
			this.allKeyListeners.put(keyCode, keyListeners);
		}
		keyListeners.add(listener);
	}

}
