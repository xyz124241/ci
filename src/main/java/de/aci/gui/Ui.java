package de.aci.gui;

import de.aci.ItemEffect;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Ui {
    private static final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.legacyAmpersand();
    private static final String[] ROMAN = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    private Ui() {}

    /** Text mit &-Farbcodes, standardmaessig nicht kursiv. */
    public static Component text(String legacy) {
        return LEGACY.deserialize(legacy).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

    public static String plain(String legacy) {
        return PlainTextComponentSerializer.plainText().serialize(LEGACY.deserialize(legacy));
    }

    public static ItemStack button(Material m, String name, String... lore) {
        ItemStack s = new ItemStack(m);
        ItemMeta meta = s.getItemMeta();
        meta.displayName(text(name));
        if (lore.length > 0) meta.lore(Arrays.stream(lore).map(Ui::text).toList());
        meta.addItemFlags(ItemFlag.values());
        s.setItemMeta(meta);
        return s;
    }

    public static ItemStack pane() {
        return button(Material.GRAY_STAINED_GLASS_PANE, " ");
    }

    /** Haengt Zeilen an die vorhandene Lore an. */
    public static ItemStack addLore(ItemStack s, String... lines) {
        ItemMeta meta = s.getItemMeta();
        List<Component> lore = meta.lore() == null ? new ArrayList<>() : new ArrayList<>(meta.lore());
        for (String l : lines) lore.add(text(l));
        meta.lore(lore);
        s.setItemMeta(meta);
        return s;
    }

    public static String roman(int level) {
        return level >= 1 && level < ROMAN.length ? ROMAN[level] : String.valueOf(level);
    }

    public static String fmtSeconds(int sec) {
        return (sec / 60) + ":" + String.format("%02d", sec % 60) + " min";
    }

    /** Lore-Zeile fuer einen Effekt, z.B. "Nebenhand: Staerke II". */
    public static Component effectLine(ItemEffect e) {
        Component line = Component.text(e.slot.label + ": ", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.translatable(e.type.translationKey(), NamedTextColor.GREEN))
                .append(Component.text(" " + roman(e.level), NamedTextColor.GREEN));
        if (e.seconds > 0) line = line.append(Component.text(" (" + fmtSeconds(e.seconds) + ")", NamedTextColor.DARK_GRAY));
        return line;
    }
}
