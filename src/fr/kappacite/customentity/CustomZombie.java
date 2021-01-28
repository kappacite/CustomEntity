package fr.kappacite.customentity;

import net.minecraft.server.v1_14_R1.*;
import org.bukkit.Location;


public class CustomZombie extends EntityVillager{

    public static final CustomEntityType TYPE = CustomEntityType.CUSTOM_ZOMBIE;

    public CustomZombie(EntityTypes<? extends EntityVillager> entityTypes, World world)
    {
        super((EntityTypes<? extends EntityVillager>) TYPE.getEntityType(), world);
        this.setCustomName(IChatBaseComponent.ChatSerializer.a("{\"text\":\"§6Zombie\"}"));
        this.setCustomNameVisible(true);
    }

    public CustomZombie(World world)
    {
        super((EntityTypes<? extends EntityVillager>) TYPE.getEntityType(), world);
        this.attachedToPlayer = true;

        this.setCustomName(IChatBaseComponent.ChatSerializer.a("{\"text\":\"§6Zombie\"}"));
        this.setCustomNameVisible(true);

    }

    public void setLocation(Location loc){
        super.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
    }

    @Override
    protected void initPathfinder(){

        this.goalSelector.a(0, new PathfinderGoalFloat(this));

        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.0, true));
        this.goalSelector.a(2, new PathfinderGoalPanic(this, 0D));

        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, new Class[0]));
        this.targetSelector.a(4, new PathfinderGoalNearestAttackableTarget<>(this, EntityZombie.class, true));

        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0));

        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0f));
        this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));

    }

    @Override
    protected void initAttributes(){
        super.initAttributes();
        this.getAttributeMap().b(GenericAttributes.ATTACK_DAMAGE);
        this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(5);
        this.getAttributeInstance(GenericAttributes.ARMOR).setValue(5.0);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.7f);
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(5.0);
    }

    @Override
    public void b(NBTTagCompound nbttagcompound)
    {
        super.b(nbttagcompound);
        nbttagcompound.setString("id", TYPE.getName());
    }

}
