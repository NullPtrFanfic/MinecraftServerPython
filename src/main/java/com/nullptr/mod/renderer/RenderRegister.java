package com.lycanitesmobs.client.renderer;

//import com.nullptr.mod.ObjectManager;
import com.nullptr.mod.core.entity.BaseCreatureEntity;
import com.nullptr.mod.core.entity.BaseProjectileEntity;
import com.nullptr.mod.core.entity.CustomProjectileEntity;
import com.nullptr.mod.core.entity.CustomProjectileModelEntity;
//import com.lycanitesmobs.core.info.CreatureInfo;
//import com.lycanitesmobs.core.info.CreatureManager;
//import com.lycanitesmobs.core.info.ModInfo;
import com.nullptr.mod.core.info.projectile.ProjectileManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderRegister {
    //public ModInfo modInfo;

    public RenderRegister(/*ModInfo modInfo*/) {
        //this.modInfo = modInfo;
    }

    public void registerRenderFactories() {
        // Creatures:

        // Projectiles:
        RenderingRegistry.registerEntityRenderingHandler(CustomProjectileModelEntity.class, new RenderFactoryProjectile<CustomProjectileEntity>(true));
        RenderingRegistry.registerEntityRenderingHandler(CustomProjectileEntity.class, new RenderFactoryProjectile<CustomProjectileEntity>(false));

        // Old Sprite Projectiles:
        for(String projectileName : ProjectileManager.getInstance().oldSpriteProjectiles.keySet()) {
            Class projectileClass = ProjectileManager.getInstance().oldSpriteProjectiles.get(projectileName);
            RenderingRegistry.registerEntityRenderingHandler(projectileClass, new RenderFactoryProjectile<BaseProjectileEntity>(projectileName, projectileClass, false));
        }

        // Old Model Projectiles:
        for(String projectileName : ProjectileManager.getInstance().oldModelProjectiles.keySet()) {
            Class projectileClass = ProjectileManager.getInstance().oldModelProjectiles.get(projectileName);
            RenderingRegistry.registerEntityRenderingHandler(projectileClass, new RenderFactoryProjectile<BaseProjectileEntity>(projectileName,projectileClass, true));
        }
    }
}
