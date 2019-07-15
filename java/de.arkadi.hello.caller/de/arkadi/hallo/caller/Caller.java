package de.arkadi.hallo.caller;

import java.lang.module.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Caller {
    private static final String MODULE_NAME = "de.arkadi.hello.printer";
    private static final String MODULE_CLASS = "de.arkadi.hello.printer.Printer";

    /**
     * set path to modules
     *
     * @param args
     */
    public static void main(String[] args) {

        String[] modulePaths = {args[0], args[1]};
        List<ModuleLayer> layers = Arrays.stream(modulePaths).map(Caller::createLayer).collect(Collectors.toList());
        layers.stream().forEach(Caller::lookUpAndStartModule);

    }

    /**
     * Create Configuration and ModuleLayer for given module location
     *
     * @param modulePath folder with modules
     * @return module layer with modules out og the folder
     */
    private static ModuleLayer createLayer(String modulePath) {
        ModuleFinder finder = ModuleFinder.of(Paths.get(modulePath));
        ModuleLayer parent = ModuleLayer.boot();
        Set<String> modules = finder.findAll().stream().map(ModuleReference::descriptor).map(ModuleDescriptor::name).collect(Collectors.toSet());
        Configuration newCfg = parent.configuration().resolve(finder, ModuleFinder.of(), modules);
        newCfg.modules().stream().map(ResolvedModule::name).forEach(System.out::println);

        return parent.defineModulesWithOneLoader(newCfg, ClassLoader.getSystemClassLoader());
    }

    /**
     * reflection
     *
     * @param layer
     */
    private static void lookUpAndStartModule(ModuleLayer layer) {

        Optional<Module> optMod = layer.findModule(MODULE_NAME);
        if (optMod.isPresent()) {
            try {
                Module mod = optMod.get();
                ModuleDescriptor modDescriptor = mod.getDescriptor();
                System.out.println(modDescriptor.toNameAndVersion());

                Class<?> testBClass = mod.getClassLoader().loadClass(MODULE_CLASS);
                Constructor<?> constructor = testBClass.getDeclaredConstructor();
                constructor.setAccessible(true);

                Object testB = constructor.newInstance();
                Method mainMethod = testB.getClass().getMethod("main", String[].class);
                String[] params = null;
                mainMethod.invoke(null, (Object) params);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

