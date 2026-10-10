package com.prismium.mixin;

import com.prismium.PrismiumClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.entity.SignText;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignBlockEntityRenderer.class)
public class SignTextCullingMixin {

    @Inject(
            method = "renderText",
            at = @At("HEAD"),
            cancellable = true
    )
    private void prismium$signTextDistance(
            BlockPos pos,
            SignText signText,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int lineHeight,
            int lineWidth,
            boolean front,
            CallbackInfo ci
    ) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.gameRenderer.getCamera() == null) {
            return;
        }

        double dx = pos.getX() + 0.5 - client.gameRenderer.getCamera().getPos().x;
        double dy = pos.getY() + 0.5 - client.gameRenderer.getCamera().getPos().y;
        double dz = pos.getZ() + 0.5 - client.gameRenderer.getCamera().getPos().z;

        double distanceSq = dx * dx + dy * dy + dz * dz;
        double maxDistance = PrismiumClient.signTextDistance;

        if (distanceSq > maxDistance * maxDistance) {
            ci.cancel();
        }
    }
}
