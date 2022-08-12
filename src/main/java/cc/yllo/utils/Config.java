package cc.yllo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.event.Listener;

import cc.yllo.main;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;

public class Config implements Listener {
    private YamlDocument config;

    public Config(){
        main.plugin.getLogger().info("[Clans] Config loading...");
        try {
            config = YamlDocument.create(new File(main.plugin.getDataFolder(), "config.yml"), main.plugin.getResource("config.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlDocument getConfig(){
        return config;
    }

    public static ArrayList<YamlDocument> getClans(){
        // Look inside: clans/
        // Return all the files inside the folder
        File folder = new File("plugins/clans");
        YamlDocument[] clans = new YamlDocument[folder.listFiles().length - 1];
        int i = 0;
        File[] listOfFiles = folder.listFiles();
        // do not include the config.yml file
        for(File clan : listOfFiles){
            if(clan.getName().equals("config.yml")){
                continue;
            }
            try {
                clans[i] = YamlDocument.create(clan, main.plugin.getResource("clan_template.yml"),
                        GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<YamlDocument>(java.util.Arrays.asList(clans));
    }
}
