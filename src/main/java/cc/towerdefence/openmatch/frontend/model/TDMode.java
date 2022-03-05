package cc.towerdefence.openmatch.frontend.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public enum TDMode {
    STANDARD("standard"),
    ONE_VS_ONE("1v1");

    private final @NotNull String stringMode;

    TDMode(@NotNull String openMatchMode) {
        this.stringMode = openMatchMode;
    }

    public @NotNull String getStringMode() {
        return this.stringMode;
    }

    public @NotNull String getOpenMatchMode() {
        return "mode.".concat(this.stringMode);
    }

    public static @Nullable TDMode fromOpenMatchMode(@NotNull String openMatchMode) {
        for (TDMode tdMode : TDMode.values())
            if (tdMode.stringMode.equals(openMatchMode))
                return tdMode;

        return null;
    }

    public static @Nullable TDMode fromTags(Set<String> tags) {
        for (String tag : tags)
            if (tag.startsWith("mode."))
                return TDMode.fromOpenMatchMode(tag.split("mode\\.")[1]);

        return null;
    }
}
