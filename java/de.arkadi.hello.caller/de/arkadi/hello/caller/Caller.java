package de.arkadi.hello.caller;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Caller {
    public static void main(String[] args) throws ReflectiveOperationException {

        createApplicationLayers()
                .findLoader("de.arkadi.caller")
                .loadClass("de.arkadi.hello.caller.Caller")
                .getMethod("main", String[].class)
                .invoke(null, (Object) new String[0]);


    }


    private static ModuleLayer createApplicationLayers() {

        Path mods = Paths.get("mods");

        ModuleLayer good = createLayer(
                List.of(ModuleLayer.boot()),
                mods.resolve("good"),
                "de.arkadi.good");

        ModuleLayer bad = createLayer(
                List.of(ModuleLayer.boot()),
                mods.resolve("bad"),
                "de.arkadi.bad");

        return createLayer(
                List.of(good, bad),
                mods.resolve("caller"),
                "de.arkadi.caller");
    }

    private static ModuleLayer createLayer(List<ModuleLayer> parentLayers, Path modulePath, String rootModule) {

        Configuration configuration = createConfiguration(parentLayers, modulePath, rootModule);

        return ModuleLayer
                .defineModulesWithOneLoader(configuration, parentLayers, ClassLoader.getSystemClassLoader())
                .layer();
    }

    private static Configuration createConfiguration(List<ModuleLayer> parentLayers, Path modulePath, String rootModule) {

        List<Configuration> configurations = parentLayers.stream()
                .map(ModuleLayer::configuration)
                .collect(Collectors.toList());

        return Configuration.resolveAndBind(ModuleFinder.of(),
                configurations,
                ModuleFinder.of(modulePath),
                List.of(rootModule)
        );
    }
}

