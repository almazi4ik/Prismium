package com.prismium.client;

import com.prismium.PrismiumClient;
import com.prismium.config.PrismiumConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PrismiumSettingsScreen extends Screen {

    private final Screen parent;

    public PrismiumSettingsScreen(Screen parent) {
        super(Text.literal("Prismium Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int y = 60;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Entity Culling: " +
                        (PrismiumClient.entityCulling ? "ON" : "OFF")),
                button -> {
                    PrismiumClient.entityCulling = !PrismiumClient.entityCulling;
                    button.setMessage(Text.literal(
                            "Entity Culling: " +
                                    (PrismiumClient.entityCulling ? "ON" : "OFF")
                    ));
                }
        ).dimensions(centerX - 100, y, 200, 20).build());

        addDistanceButtons(
                centerX, y + 30,
                "Distance: ",
                PrismiumClient.distantCullingDistance,
                () -> {
                    PrismiumClient.distantCullingDistance =
                            Math.max(1, PrismiumClient.distantCullingDistance - 10);
                    rebuild();
                },
                () -> {
                    PrismiumClient.distantCullingDistance =
                            Math.min(256, PrismiumClient.distantCullingDistance + 10);
                    rebuild();
                }
        );

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Particle Culling: " +
                        (PrismiumClient.particleCulling ? "ON" : "OFF")),
                button -> {
                    PrismiumClient.particleCulling =
                            !PrismiumClient.particleCulling;
                    button.setMessage(Text.literal(
                            "Particle Culling: " +
                                    (PrismiumClient.particleCulling ? "ON" : "OFF")
                    ));
                }
        ).dimensions(centerX - 100, y + 60, 200, 20).build());

        addDistanceButtons(
                centerX, y + 90,
                "Particle Distance: ",
                PrismiumClient.particleCullingDistance,
                () -> {
                    PrismiumClient.particleCullingDistance =
                            switch (PrismiumClient.particleCullingDistance) {
                                case 32 -> 24;
                                case 24 -> 16;
                                case 16 -> 8;
                                case 8 -> 1;
                                default -> 1;
                            };
                    rebuild();
                },
                () -> {
                    PrismiumClient.particleCullingDistance =
                            switch (PrismiumClient.particleCullingDistance) {
                                case 1 -> 8;
                                case 8 -> 16;
                                case 16 -> 24;
                                case 24 -> 32;
                                default -> 32;
                            };
                    rebuild();
                }
        );

        addDistanceButtons(
                centerX, y + 120,
                "Dropped Item Distance: ",
                PrismiumClient.droppedItemDistance,
                () -> {
                    PrismiumClient.droppedItemDistance =
                            switch (PrismiumClient.droppedItemDistance) {
                                case 128 -> 96;
                                case 96 -> 64;
                                case 64 -> 48;
                                case 48 -> 32;
                                case 32 -> 16;
                                case 16 -> 8;
                                case 8 -> 1;
                                default -> 1;
                            };
                    rebuild();
                },
                () -> {
                    PrismiumClient.droppedItemDistance =
                            switch (PrismiumClient.droppedItemDistance) {
                                case 1 -> 8;
                                case 8 -> 16;
                                case 16 -> 32;
                                case 32 -> 48;
                                case 48 -> 64;
                                case 64 -> 96;
                                case 96 -> 128;
                                default -> 128;
                            };
                    rebuild();
                }
        );

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                button -> {
                    PrismiumConfig.save();
                    this.close();
                }
        ).dimensions(centerX - 100, this.height - 35, 200, 20).build());
    }

    private void addDistanceButtons(
            int centerX,
            int y,
            String label,
            int value,
            Runnable minus,
            Runnable plus
    ) {
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("-"),
                button -> minus.run()
        ).dimensions(centerX - 120, y, 40, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal(label + value + " blocks"),
                button -> {}
        ).dimensions(centerX - 75, y, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("+"),
                button -> plus.run()
        ).dimensions(centerX + 80, y, 40, 20).build());
    }

    private void rebuild() {
        this.clearChildren();
        this.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                this.title,
                this.width / 2,
                25,
                0xFFFFFF
        );

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal("Performance"),
                this.width / 2,
                45,
                0xAAAAAA
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(parent);
        }
    }
}
