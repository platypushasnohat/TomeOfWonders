package com.platypushasnohat.tome_of_wonders.client.particles;

import com.platypushasnohat.tome_of_wonders.blocks.WhirliboxBlock;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class WhirliwindParticle extends TextureSheetParticle {

    private static final int[] COLORS = {
            0xffffff, 0xeafdff, 0xe1f7fe, 0xd7ecef
    };

    private WhirliwindParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ, int variant) {
        super(level, x, y, z, motionX, motionY, motionZ);
        int color = selectColor(variant, this.random);
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        this.setColor(r, g, b);
        this.quadSize *= 0.75F;
        this.lifetime = 40 + level.random.nextInt(20);
        this.xd = motionX + (Math.random() * 2.0D - 1.0D) * (double) 0.02F;
        this.yd = motionY + (Math.random() * 2.0D - 1.0D) * (double) 0.02F;
        this.zd = motionZ + (Math.random() * 2.0D - 1.0D) * (double) 0.02F;
    }

    public static int selectColor(int variant, RandomSource random) {
        return COLORS[random.nextInt(COLORS.length - 1)];
    }

    @Override
    public void move(double x, double y, double z) {
        if (this.hasPhysics && (x != 0.0D || y != 0.0D || z != 0.0D) && x * x + y * y + z * z < Mth.square(100)) {
            Vec3 moveDir = new Vec3(x, y, z);
            Vec3 vec3 = Entity.collideBoundingBox(null, moveDir, this.getBoundingBox(), this.level, List.of());
            if (moveDir.distanceToSqr(vec3) > 0.000000001D && !(level.getBlockState(BlockPos.containing(x, y, z)).getBlock() instanceof WhirliboxBlock)) {
                this.remove();
                return;
            }
        }
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            WhirliwindParticle particle = new WhirliwindParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, 0);
            particle.pickSprite(spriteSet);
            return particle;
        }
    }
}