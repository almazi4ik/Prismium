package com.prismium.client;

import com.prismium.PrismiumClient;
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
        int y = this.height / 2 - 30;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Entity Culling: " + (PrismiumClient.entityCulling ? "ON" : "OFF")),
                button -> {
                    PrismiumClient.entityCulling = !PrismiumClient.entityCulling;
                    button.setMessage(Text.literal(
                            "Entity Culling: " + (PrismiumClient.entityCulling ? "ON" : "OFF")
                    ));
                }
        ).dimensions(centerX - 100, y, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("-"),
                button -> {
                    PrismiumClient.distantCullingDistance =
                            Math.max(1, PrismiumClient.distantCullingDistance - 10);
                    updateDistance();
                }
        ).dimensions(centerX - 100, y + 30, 40, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Distance: " + PrismiumClient.distantCullingDistance + " blocks"),
                button -> {}
        ).dimensions(centerX - 55, y + 30, 110, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("+"),
                button -> {
                    PrismiumClient.distantCullingDistance =
                            Math.min(256, PrismiumClient.distantCullingDistance + 10);
                    updateDistance();
                }
        ).dimensions(centerX + 60, y + 30, 40, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                button -> this.close()
        ).dimensions(centerX - 100, y + 75, 200, 20).build());
    }

    private void updateDistance() {
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
                35,
                0xFFFFFF
        );

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal("Performance"),
                this.width / 2,
                this.height / 2 - 65,
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
