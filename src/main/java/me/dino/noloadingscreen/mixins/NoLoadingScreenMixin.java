package me.dino.noloadingscreen.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class NoLoadingScreenMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    private boolean positionLookSetup = false;

    @Inject(at = @At("TAIL"), method = "onPlayerPositionLook")
    private void onPlayerPositionLook(PlayerPositionLookS2CPacket packet, CallbackInfo ci) {
        if (!positionLookSetup) {
            positionLookSetup = true;
                client.setScreen(null);
    }}}