package com.nullptr.mod.renderer;

import com.nullptr.mod.Main;
//import com.nullptr.mod.renderer.RenderCreature;
//import com.lycanitesmobs.core.info.CreatureInfo;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryCreature<T extends Entity> implements IRenderFactory {
    //protected CreatureInfo creatureInfo;

    public RenderFactoryCreature(/*CreatureInfo creatureInfo*/) {
       // this.creatureInfo = creatureInfo;
    }

    @Override
    public Render createRenderFor(RenderManager manager) {
        return null;
    }

}
