package com.prismium.mixin;

import com.prismium.PrismiumClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ParticleManager.class)
public class ParticleMixin {

    @Redirect(
            method = "renderParticles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/particle/Particle;buildGeometry(Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/render/Camera;F)V"
            )
    )
    private void prismium$particleDistance(
            Particle particle,
            VertexConsumer vertexConsumer,
            Camera camera,
            float tickDelta
    ) {
        if (!PrismiumClient.particleCulling) {
            particle.buildGeometry(vertexConsumer, camera, tickDelta);
            return;
        }

        ParticleAccessor accessor = (ParticleAccessor) (Object) particle;

        double dx = accessor.prismium$getX() - camera.getPos().x;
        double dy = accessor.prismium$getY() - camera.getPos().y;
        double dz = accessor.prismium$getZ() - camera.getPos().z;

        double distanceSq = dx * dx + dy * dy + dz * dz;
        double maxDistance = PrismiumClient.particleCullingDistance;

        if (distanceSq <= maxDistance * maxDistance) {
            particle.buildGeometry(vertexConsumer, camera, tickDelta);
        }
    }
}
