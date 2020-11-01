package com.github.br.ecs.simple.system.render;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.br.ecs.simple.engine.EcsComponent;

public class RendererComponent implements EcsComponent {

    public String layer;
    public TextureRegion textureRegion;
    public boolean flipX = false;
    public boolean flipY = false;

    public String newLayerTitle; // изменяем слой отрисовки сущности.

}
