package com.omnicrola.silicon.entity;

import com.omnicrola.silicon.creature.shape.EntityShape;

public interface ISiliconEntity {

	void update(float delta);

	EntityShape getShape();

}
