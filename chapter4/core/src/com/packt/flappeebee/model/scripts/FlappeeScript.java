package com.packt.flappeebee.model.scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.github.br.ecs.simple.EcsScript;
import com.github.br.ecs.simple.component.PhysicsComponent;
import com.github.br.ecs.simple.component.TransformComponent;
import com.packt.flappeebee.model.GameConstants;
import com.github.br.ecs.simple.utils.ViewHelper;

/**
 * Created by user on 26.03.2017.
 */
public class FlappeeScript extends EcsScript {

    private TransformComponent transform;
    private PhysicsComponent physics;

    @Override
    public void init() {
        transform = getComponent(TransformComponent.class);
        physics = getComponent(PhysicsComponent.class);
    }

    @Override
    public void update(float delta) {
        transform.rotation +=1;

        blockFlappeeLeavingTheWorld();
//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//            flyUp();
//        }
    }

    private void blockFlappeeLeavingTheWorld() {
        if(transform.position.y <= 0 || transform.position.y >= ViewHelper.WORLD_HEIGHT){
            physics.movement.y = 0;
        }
        transform.position.y = MathUtils.clamp(transform.position.y, 0, ViewHelper.WORLD_HEIGHT);
    }

    public void flyUp() {
        physics.movement.add(GameConstants.FLY_ACCEL);
    }

}
