package com.devmaster.dangerzone.world.gen;

import com.devmaster.dangerzone.configs.DZConfig;
import com.devmaster.dangerzone.misc.DangerZone;
import com.devmaster.dangerzone.util.RegistryHandler;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = DangerZone.MOD_ID)
public class EntitySpawns {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addSpawn(BiomeLoadingEvent event) {
        if (event.getName() != null) {
            Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
            if (biome != null) {
                RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, event.getName());
                List<BiomeDictionary.Type> includeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.NotBreeBreeinclude.get()));
                List<BiomeDictionary.Type> excludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.NotBreeBreeexclude.get()));
                List<BiomeDictionary.Type> StampyLongNoseincludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.StampyLongNoseinclude.get()));
                List<BiomeDictionary.Type> StampyLongNoseexcludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.StampyLongNoseexclude.get()));
                List<BiomeDictionary.Type> TewityincludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.Tewityinclude.get()));
                List<BiomeDictionary.Type> TewityexcludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.Tewityexclude.get()));
                List<BiomeDictionary.Type> EntincludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.Entinclude.get()));
                List<BiomeDictionary.Type> EntexcludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.Entexclude.get()));
                List<BiomeDictionary.Type> CaveFisherincludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.CaveFisherinclude.get()));
                List<BiomeDictionary.Type> CaveFisherexcludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(DZConfig.CaveFisherexclude.get()));

                if (!includeList.isEmpty()) {
                    Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biomeKey);
                    if (biomeTypes.stream().noneMatch(excludeList::contains) && biomeTypes.stream().anyMatch(includeList::contains)) {
                        event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RegistryHandler.NOTBREEBREE.get(), DZConfig.NotBreeBreeweight.get(), DZConfig.NotBreeBreemin.get(), DZConfig.NotBreeBreemax.get()));
                    }
                    if (biomeTypes.stream().noneMatch(StampyLongNoseexcludeList::contains) && biomeTypes.stream().anyMatch(StampyLongNoseincludeList::contains)) {
                        event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RegistryHandler.STAMPYLONGNOSE.get(), DZConfig.StampyLongNoseweight.get(), DZConfig.StampyLongNosemin.get(), DZConfig.StampyLongNosemax.get()));
                    }
                    if (biomeTypes.stream().noneMatch(TewityexcludeList::contains) && biomeTypes.stream().anyMatch(TewityincludeList::contains)) {
                        event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RegistryHandler.TEWTIY.get(), DZConfig.Tewityweight.get(), DZConfig.Tewitymin.get(), DZConfig.Tewitymax.get()));
                    }
                    if (biomeTypes.stream().noneMatch(EntexcludeList::contains) && biomeTypes.stream().anyMatch(EntincludeList::contains)) {
                        event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RegistryHandler.ENT.get(), DZConfig.Entweight.get(), DZConfig.Entmin.get(), DZConfig.Entmax.get()));
                    }
                    if (biomeTypes.stream().noneMatch(CaveFisherexcludeList::contains) && biomeTypes.stream().anyMatch(CaveFisherincludeList::contains)) {
                        event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(RegistryHandler.CAVE_FISHER.get(), DZConfig.CaveFisherweight.get(), DZConfig.CaveFishermin.get(), DZConfig.CaveFishermax.get()));
                    }
                } else {
                    throw new IllegalArgumentException("Do not leave the BiomeDictionary type inclusion list empty. If you wish to disable spawning of an entity, set the weight to 0 instead.");
                }
            }
        }
    }
}