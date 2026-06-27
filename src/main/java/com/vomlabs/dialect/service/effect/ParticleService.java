package com.vomlabs.dialect.service.effect;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import java.util.logging.Logger;

public class ParticleService {

    private static final Particle PARTICLE_TRANSLATE = Particle.ENCHANTED_HIT;
    private static final Particle PARTICLE_DENY = Particle.SMOKE;
    private static final Particle PARTICLE_WARN = Particle.CRIT;
    private static final Particle PARTICLE_ALLOW = Particle.HAPPY_VILLAGER;
    private static final Particle PARTICLE_DETECT = Particle.END_ROD;
    private static final Particle PARTICLE_LANGUAGE = Particle.WITCH;
    private static final Particle PARTICLE_STAFF = Particle.NOTE;

    private final boolean enabled;
    private final Logger logger;

    public ParticleService(boolean enabled, Logger logger) {
        this.enabled = enabled;
        this.logger = logger;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void spawnTranslationParticles(Player player) {
        spawnAround(player, PARTICLE_TRANSLATE, 20, 0.5, 0.3, 0.5);
    }

    public void spawnDenyParticles(Player player) {
        spawnAround(player, PARTICLE_DENY, 10, 0.5, 0.3, 0.5);
    }

    public void spawnWarnParticles(Player player) {
        spawnAround(player, PARTICLE_WARN, 8, 0.3, 0.3, 0.3);
    }

    public void spawnAllowParticles(Player player) {
        spawnAround(player, PARTICLE_ALLOW, 6, 0.3, 0.3, 0.3);
    }

    public void spawnDetectionParticles(Player player) {
        spawnAround(player, PARTICLE_DETECT, 12, 0.4, 0.4, 0.4);
    }

    public void spawnLanguageParticles(Player player) {
        spawnAround(player, PARTICLE_LANGUAGE, 15, 0.3, 0.3, 0.3);
    }

    public void spawnStaffParticles(Player player) {
        spawnAround(player, PARTICLE_STAFF, 8, 0.3, 0.3, 0.3);
    }

    private void spawnAround(Player player, Particle particle, int count, double offsetX, double offsetY, double offsetZ) {
        if (!enabled || player == null) return;
        Location loc = player.getLocation().add(0, 1, 0);
        player.spawnParticle(particle, loc, count, offsetX, offsetY, offsetZ, 0.01);
    }
}
