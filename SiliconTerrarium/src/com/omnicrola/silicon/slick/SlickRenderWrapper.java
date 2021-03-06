package com.omnicrola.silicon.slick;

import org.newdawn.slick.Graphics;

import com.omnicrola.silicon.creature.shape.EntityShape;

public class SlickRenderWrapper implements IRenderWrapper {

	private final Graphics graphics;

	public SlickRenderWrapper(Graphics graphics) {
		this.graphics = graphics;
	}

	@Override
	public void renderShape(EntityShape renderShape) {
		this.graphics.setColor(renderShape.getColor());
		this.graphics.draw(renderShape.getShape());
	}

}
