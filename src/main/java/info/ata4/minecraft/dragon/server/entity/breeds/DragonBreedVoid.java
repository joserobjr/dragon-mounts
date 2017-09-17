/*
 ** 2013 October 24
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.minecraft.dragon.server.entity.breeds;

import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBreedVoid extends DragonBreed {

    DragonBreedVoid() {
        super("void", 0xab39be);
        
        addImmunity(DamageSource.inWall);
        addImmunity(DamageSource.outOfWorld);
        addImmunity(DamageSource.inFire);
        addImmunity(DamageSource.onFire);
        addImmunity(DamageSource.anvil);
        addImmunity(DamageSource.fallingBlock);
        
        addHabitatBlock(Blocks.BEDROCK);
        addHabitatBlock(Blocks.BARRIER);
        addHabitatBlock(Blocks.STRUCTURE_VOID);
        
        addHabitatBiome(Biomes.VOID);
    }

    @Override
    public void onEnable(EntityTameableDragon dragon) {
    }

    @Override
    public void onDisable(EntityTameableDragon dragon) {
    }

    @Override
    public void onDeath(EntityTameableDragon dragon) {
    }

}
