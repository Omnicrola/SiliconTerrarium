package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.IGameContext;
import com.omnicrola.silicon.core.IGameSubsystem;
import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.physics.CollisionManager;
import com.omnicrola.silicon.slick.IRenderWrapper;

public class EntityManager implements IGameSubsystem {

	private final ArrayList<ISiliconEntity> entities;
	private final CollisionManager collisionManager;
	private final TerrariumSettings settings;
	private final ResurrectionManager resurrectionManager;

	public EntityManager(TerrariumSettings settings, CollisionManager collisionManager,
			ResurrectionManager resurrectionManager) {
		this.settings = settings;
		this.collisionManager = collisionManager;
		this.resurrectionManager = resurrectionManager;
		this.entities = new ArrayList<>();
	}

	public void addEntity(ISiliconEntity entity) {
		this.entities.add(entity);
		this.collisionManager.addEntity(entity);
	}

	@Override
	public void init(IGameContext context, GameContainer container) {
		context.setContext(EntityManager.class, this);
	}

	@Override
	public void render(IRenderWrapper renderWrapper) {
		for (final ISiliconEntity entity : this.entities) {
			final EntityShape renderShape = entity.getShape();
			renderWrapper.renderShape(renderShape);
		}
	}

	@Override
	public void update(float delta) {
		spawnNewEntities();
		updateEntities(delta);
		wrapEdges();
		this.collisionManager.update(delta);
		cleanDeadEntities();
	}

	private void spawnNewEntities() {
		this.resurrectionManager.resurrect(this);
	}

	private void wrapEdges() {
		for (final ISiliconEntity entity : this.entities) {
			final Vector2f p = entity.getPosition();
			final int screenWidth = this.settings.getScreenWidth();
			final int screenHeight = this.settings.getScreenHeight();
			if (p.x < 0) {
				p.x = screenWidth;
			} else if (p.x > screenWidth) {
				p.x = 0;
			}
			if (p.y < 0) {
				p.y = screenHeight;
			} else if (p.y > screenHeight) {
				p.y = 0;
			}
			entity.setPosition(p);
		}
	}

	private void updateEntities(float delta) {
		for (final ISiliconEntity siliconEntity : this.entities) {
			siliconEntity.update(delta);
		}
	}

	private void cleanDeadEntities() {
		for (final ISiliconEntity siliconEntity : new ArrayList<>(this.entities)) {
			if (!siliconEntity.isAlive()) {
				this.entities.remove(siliconEntity);
				this.resurrectionManager.queueForReincarnation(siliconEntity);
			}
		}
	}

	public void clearAll() {
		this.entities.clear();
	}

	public void killAll() {
		for (final ISiliconEntity siliconEntity : this.entities) {
			siliconEntity.destroy();
		}
	}
}
