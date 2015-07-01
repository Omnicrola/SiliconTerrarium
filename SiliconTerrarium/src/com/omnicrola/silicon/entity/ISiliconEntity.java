package com.omnicrola.silicon.entity;

import com.omnicrola.silicon.creature.shape.RenderShape;

public interface ISiliconEntity {

	void update(float delta);

	RenderShape getShape();

}
