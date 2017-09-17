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
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBreedIce extends DragonBreed {
    
    DragonBreedIce() {
        super("ice", 0x6fc3ff);
        
        addImmunity(DamageSource.magic);
        
        addHabitatBlock(Blocks.SNOW);
        addHabitatBlock(Blocks.SNOW_LAYER);
        addHabitatBlock(Blocks.ICE);
        
        addHabitatBiome(Biomes.FROZEN_OCEAN);
        addHabitatBiome(Biomes.FROZEN_RIVER);
        addHabitatBiome(Biomes.ICE_MOUNTAINS);
        addHabitatBiome(Biomes.ICE_PLAINS);
    }
    
    @Override
    protected float getFootprintChance() {
        return 0.1f;
    }
    
    @Override
    protected void placeFootprintBlock(EntityTameableDragon dragon, BlockPos blockPos) {
        // place snow layer blocks, but only if the biome is cold enough
        World world = dragon.worldObj;
        
        if (world.getBiome(blockPos).getFloatTemperature(blockPos) > 0.8f) {
            return;
        }
        
        Block footprint = Blocks.SNOW_LAYER;
        if (!footprint.canPlaceBlockAt(world, blockPos)) {
            return;
        }
        
        world.setBlockState(blockPos, footprint.getDefaultState());
    }

    @Override
    protected void freezeNearby(EntityTameableDragon dragon, World worldIn, BlockPos pos, int level)
    {
        if (dragon.onGround)
        {
            float f = (float)Math.min(16, 2 + level);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f)))
            {
                if (blockpos$mutableblockpos1.distanceSqToCenter(dragon.posX, dragon.posY, dragon.posZ) <= (double)(f * f))
                {
                    blockpos$mutableblockpos.set(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);

                    if (iblockstate.getMaterial() == Material.AIR)
                    {
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1);

                        if (iblockstate1.getMaterial() == Material.WATER && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && worldIn.canBlockBePlaced(Blocks.FROSTED_ICE, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null, (ItemStack)null))
                        {
                            worldIn.setBlockState(blockpos$mutableblockpos1, Blocks.FROSTED_ICE.getDefaultState());
                            worldIn.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), Blocks.FROSTED_ICE, MathHelper.getRandomIntegerInRange(dragon.getRNG(), 60, 120));
                        }
                    }
                }
            }
        }
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
