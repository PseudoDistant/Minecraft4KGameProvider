import net.fabricmc.loader.impl.game.GameProvider;
import net.fabricmc.loader.impl.game.patch.GameTransformer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.metadata.BuiltinModMetadata;
import net.fabricmc.loader.impl.util.Arguments;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;

public class G implements GameProvider {
	private static final GameTransformer TRANSFORMER = new GameTransformer(
			new P()
	);

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
	public boolean locateGame(FabricLauncher launcher, String[] args) {
		return true;
	}

	@Override
	public void initialize(FabricLauncher launcher) {
		TRANSFORMER.locateEntrypoints(launcher, Paths.get("./MC4K.jar"));
	}

	@Override
	public GameTransformer getEntrypointTransformer() {
		return TRANSFORMER;
	}

	@Override
	public void unlockClassPath(FabricLauncher launcher) {
		launcher.addToClassPath(Paths.get("./MC4K.jar"));
	}

	@Override
	public void launch(ClassLoader loader) {
		try {loader.loadClass("Viewer").getMethod("main", String[].class).invoke(null, (Object) new String[0]);}
		catch(Exception ignored) {}
	}

	@Override
	public Arguments getArguments() {
		return new Arguments();
	}

	@Override
	public String[] getLaunchArguments(boolean sanitize) {
		return new String[0];
	}
}
