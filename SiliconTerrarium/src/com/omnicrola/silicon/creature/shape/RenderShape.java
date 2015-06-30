package com.omnicrola.silicon.creature.shape;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public class RenderShape {
	private final Shape shape;
	private final Color color;

	public RenderShape(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public Shape getShape() {
		return this.shape;
	}

}
