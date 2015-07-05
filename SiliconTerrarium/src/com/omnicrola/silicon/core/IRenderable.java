package com.omnicrola.silicon.core;

import com.omnicrola.silicon.slick.IRenderWrapper;

public interface IRenderable extends IUpdatable {

	public abstract void render(IRenderWrapper renderWrapper);

}
