package com.omnicrola.silicon.creature.shape;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class EntityShape {
	private final Shape shape;
	private final Color color;

	public EntityShape(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public Shape getShape() {
		return this.shape;
	}

	public static EntityShape defaultCritterShape() {
		final Polygon renderShape = new Polygon();
		renderShape.addPoint(-5, -5);
		renderShape.addPoint(0, -5);
		renderShape.addPoint(10, 0);
		renderShape.addPoint(0, 5);
		renderShape.addPoint(-5, 5);
		final EntityShape entityShape = new EntityShape(renderShape, Color.white);
		return entityShape;
	}

	public static EntityShape createFoodShape() {
		final Polygon renderShape = new Polygon();
		renderShape.addPoint(-2, -2);
		renderShape.addPoint(2, -2);
		renderShape.addPoint(2, 2);
		renderShape.addPoint(-2, 2);
		final EntityShape entityShape = new EntityShape(renderShape, Color.green);
		return entityShape;
	}

}
