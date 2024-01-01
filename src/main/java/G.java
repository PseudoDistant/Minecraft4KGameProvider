import net.fabricmc.loader.impl.game.GameProvider;
import net.fabricmc.loader.impl.game.patch.GamePatch;
import net.fabricmc.loader.impl.game.patch.GameTransformer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.metadata.BuiltinModMetadata;
import net.fabricmc.loader.impl.util.Arguments;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

public class G implements GameProvider {
	private static final GameTransformer T = new GameTransformer(new P());

	@Override
	public String getGameId() {
		return "mc4k";
	}

	@Override
	public String getGameName() {
		return "mc4k";
	}

	@Override
	public String getRawGameVersion() {
		return "0.0.0";
	}

	@Override
	public String getNormalizedGameVersion() {
		return "0.0.0";
	}

	@Override
	public Collection<BuiltinMod> getBuiltinMods() {
		return Collections.singletonList(new BuiltinMod(Collections.singletonList(Paths.get("./MC4K.jar")), new BuiltinModMetadata.Builder(getGameId(), getNormalizedGameVersion()).build()));
	}

	@Override
	public String getEntrypoint() {
		return "Viewer";
	}

	@Override
	public Path getLaunchDirectory() {
		return Paths.get(".");
	}

	@Override
	public boolean isObfuscated() {
		return false;
	}

	@Override
	public boolean requiresUrlClassLoader() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean locateGame(FabricLauncher a, String[] b) {
		return true;
	}

	@Override
	public void initialize(FabricLauncher a) {
		T.locateEntrypoints(a, Collections.singletonList(Path.of("./MC4K.jar")));
	}

	@Override
	public GameTransformer getEntrypointTransformer() {
		return T;
	}

	@Override
	public void unlockClassPath(FabricLauncher a) {
		a.addToClassPath(Paths.get("./MC4K.jar"));
	}

	@Override
	public void launch(ClassLoader a) {
		try {a.loadClass("Viewer").getMethod("main", String[].class).invoke(null, (Object) new String[0]);}
		catch(Exception b) {}
	}

	@Override
	public Arguments getArguments() {
		return new Arguments();
	}

	@Override
	public String[] getLaunchArguments(boolean a) {
		return new String[0];
	}
}

class P extends GamePatch {
	@Override
	public void process(FabricLauncher a, Function<String, ClassNode> b, Consumer<ClassNode> c) {
		ClassNode d = b.apply("Viewer");
		for (MethodNode e : d.methods) {
			if (e.name.equals("<init>")) {
				for (AbstractInsnNode i : e.instructions) {
					if (i instanceof LdcInsnNode f) {
						if (f.cst instanceof String g) {
							if (g.contains("M")) {
								e.instructions.set(i, new LdcInsnNode(g + " - Fabric"));
								c.accept(d);
							}
						}
					}
				}
			}
		}
	}
}
