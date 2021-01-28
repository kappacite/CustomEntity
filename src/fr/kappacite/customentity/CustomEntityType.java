package fr.kappacite.customentity;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import net.minecraft.server.v1_14_R1.*;


import java.util.Map;

public enum CustomEntityType {

    CUSTOM_ZOMBIE ("custom_zombie", EntityTypes.ZOMBIE, EntityZombie.class, CustomZombie.class);

    private String name;
    private EntityTypes<? extends EntityInsentient> entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;

    private CustomEntityType(String name, EntityTypes<? extends EntityInsentient> entityType,
                             Class<? extends EntityInsentient> nmsClass,
                             Class<? extends EntityInsentient> customClass) {
        this.name = name;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public String getName() {
        return name;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return nmsClass;
    }

    public EntityTypes<?> getEntityType() {
        return entityType;
    }

    public Class<? extends EntityInsentient> getCustomClass() {
        return customClass;
    }

    public static void registerEntities() {
        for (CustomEntityType entity : values()) {
            registerCustomEntity(entity);
        }
    }

    public static void unregisterEntities() {
        for (CustomEntityType entity : values()) {
            MinecraftKey minecraftKey = MinecraftKey.a(entity.getName());

            Map<Object, Type<?>> typeMap = (Map<Object, Type<?>>) DataConverterRegistry.a().getSchema(DataFixUtils.makeKey(SharedConstants.a().getWorldVersion())).findChoiceType(DataConverterTypes.ENTITY).types();
            typeMap.remove(minecraftKey);
        }
    }

    private static void registerCustomEntity(CustomEntityType type) {
        MinecraftKey minecraftKey = MinecraftKey.a(type.getName());

        Map<Object, Type<?>> typeMap = (Map<Object, Type<?>>) DataConverterRegistry.a().getSchema(DataFixUtils.makeKey(SharedConstants.a().getWorldVersion())).findChoiceType(DataConverterTypes.ENTITY).types();
        typeMap.put(minecraftKey.toString(), typeMap.get(MinecraftKey.a(type.getName()).toString()));

        EntityTypes.a<Entity> entity = EntityTypes.a.a(CustomZombie::new, EnumCreatureType.CREATURE);
        IRegistry.a(IRegistry.ENTITY_TYPE, type.getName(), entity.a(type.getName()));
    }
}
