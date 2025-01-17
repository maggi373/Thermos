package org.bukkit.craftbukkit.scoreboard;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

final class CraftCriteria {
    static final Map<String, CraftCriteria> DEFAULTS;
    static final CraftCriteria DUMMY;

    static {
        ImmutableMap.Builder<String, CraftCriteria> defaults = ImmutableMap.builder();

        for (Map.Entry<?, ?> entry : ((Map<?,?> ) net.minecraft.scoreboard.IScoreObjectiveCriteria.field_96643_a).entrySet()) {
            String name = entry.getKey().toString();
            net.minecraft.scoreboard.IScoreObjectiveCriteria criteria = (net.minecraft.scoreboard.IScoreObjectiveCriteria) entry.getValue();
            if (!criteria.func_96636_a().equals(name)) {
                throw new AssertionError("Unexpected entry " + name + " to criteria " + criteria + "(" + criteria.func_96636_a() + ")");
            }

            defaults.put(name, new CraftCriteria(criteria));
        }

        DEFAULTS = defaults.build();
        DUMMY = DEFAULTS.get("dummy");
    }

    final net.minecraft.scoreboard.IScoreObjectiveCriteria criteria;
    final String bukkitName;

    private CraftCriteria(String bukkitName) {
        this.bukkitName = bukkitName;
        this.criteria = DUMMY.criteria;
    }

    private CraftCriteria(net.minecraft.scoreboard.IScoreObjectiveCriteria criteria) {
        this.criteria = criteria;
        this.bukkitName = criteria.func_96636_a();
    }

    static CraftCriteria getFromNMS(net.minecraft.scoreboard.ScoreObjective objective) {
        return DEFAULTS.get(objective.getCriteria().func_96636_a());
    }

    static CraftCriteria getFromBukkit(String name) {
        final CraftCriteria criteria = DEFAULTS.get(name);
        if (criteria != null) {
            return criteria;
        }
        return new CraftCriteria(name);
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof CraftCriteria)) {
            return false;
        }
        return ((CraftCriteria) that).bukkitName.equals(this.bukkitName);
    }

    @Override
    public int hashCode() {
        return this.bukkitName.hashCode() ^ CraftCriteria.class.hashCode();
    }
}
