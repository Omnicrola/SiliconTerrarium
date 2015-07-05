package com.omnicrola.silicon.input;

import java.util.ArrayList;

import org.newdawn.slick.MouseListener;

public class MouseInputHandler extends AbstractInputHandler implements MouseListener {
	private final ArrayList<IMouseMoveListener> moveListeners;
	private final ArrayList<IMouseClickListener> clickListeners;

	public MouseInputHandler() {
		this.moveListeners = new ArrayList<>();
		this.clickListeners = new ArrayList<>();
	}

	public void addMouseMoveListener(IMouseMoveListener moveListener) {
		this.moveListeners.add(moveListener);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		for (final IMouseClickListener clickListener : this.clickListeners) {
			clickListener.mouseClick(button, x, y, clickCount);
		}
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		for (final IMouseMoveListener moveListener : this.moveListeners) {
			moveListener.mouseMoved(oldX, oldY, newX, newY);
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
	}

	@Override
	public void mouseWheelMoved(int change) {
	}

}
