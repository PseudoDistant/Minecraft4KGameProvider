import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.entrypoint.EntrypointUtils;
import net.fabricmc.loader.impl.game.patch.GamePatch;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Function;

public class P extends GamePatch {
    @Override
    public void process(FabricLauncher launcher, Function<String, ClassReader> classSource, Consumer<ClassNode> classEmitter) {
        readClass(classSource.apply("M")).methods.get(2).instructions.iterator().add(new MethodInsnNode(Opcodes.INVOKESTATIC, "P", "init", "()V", false));
        classEmitter.accept(readClass(classSource.apply("M")));
    }

    public static void init() {
        FabricLoaderImpl.INSTANCE.prepareModInit(Paths.get("."), FabricLoaderImpl.INSTANCE.getGameInstance());
        EntrypointUtils.invoke("main", ModInitializer.class, ModInitializer::onInitialize);
    }
}
