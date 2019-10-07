package de.arkadi.hello.starter;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static List<ModuleLayer> layers;

    public static void main(String[] args) throws ReflectiveOperationException {
        new Main();
    }

    public Main() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        layers = createApplicationLayers();

        layers.get(0).findLoader("de.arkadi.hello.ui")
                .loadClass("de.arkadi.hello.ui.Caller")
                .getMethod("main", String[].class)
                .invoke(null, (Object) new String[0]);
    }

    public static List<ModuleLayer> getLayers() {
        return layers;
    }


    private static List<ModuleLayer> createApplicationLayers() {

        Path mods = Paths.get("out/libs");

        ModuleLayer good = createLayer(
                List.of(ModuleLayer.boot()),
                mods.resolve("left"),
                "de.arkadi.hello.left");

        ModuleLayer bad = createLayer(
                List.of(ModuleLayer.boot()),
                mods.resolve("right"),
                "de.arkadi.hello.right");

        ModuleLayer iService = createLayer(
                List.of(ModuleLayer.boot()),
                mods.resolve("serviceinterface"),
                "de.arkadi.hello.serviceinterface");

        ModuleLayer iCaller = createLayer(
                List.of(good, bad, iService),
                mods.resolve("ui"),
                "de.arkadi.hello.ui");

        ModuleLayer iProvider = createLayer(
                List.of(good, iService),
                mods.resolve("provider"),
                "de.arkadi.hello.provider");


        return List.of(iCaller, iService, iProvider, good, bad);
    }

    private static ModuleLayer createLayer(List<ModuleLayer> parentLayers, Path modulePath, String rootModule) {

        Configuration configuration = createConfiguration(parentLayers, modulePath, rootModule);

        ModuleLayer layer = ModuleLayer
                .defineModulesWithOneLoader(configuration, parentLayers, ClassLoader.getSystemClassLoader())
                .layer();

        return layer;
    }

    private static Configuration createConfiguration(List<ModuleLayer> parentLayers, Path modulePath, String rootModule) {

        List<Configuration> configurations = parentLayers.stream()
                .map(ModuleLayer::configuration)
                .collect(Collectors.toList());

        Configuration configuration = Configuration.resolve(ModuleFinder.of(),
                configurations,
                ModuleFinder.of(modulePath, Path.of("libs")),
                List.of(rootModule));
        return configuration;
    }

}
