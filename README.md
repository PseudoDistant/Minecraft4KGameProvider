# Minecraft 4K GameProvider
This project does three things:
- loads Minecraft 4K on Fabric Loader 0.13.X
- Acts as a complete GameProvider under the 4 KiB filesize requirement for Java4K
- Crashes catastrophically if not used *precisely* as intended.

# How to use
### Step 1:
Download Fabric Loader: https://maven.fabricmc.net/net/fabricmc/fabric-loader/0.13.0/fabric-loader-0.13.0.jar
### Step 2:
Download all of Fabric Loader's dependencies *manually*, and put them somewhere (A "fabric/" folder, for example).
### Step 3:
Download CrunchyCat's Minecraft4K mod here: \
https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1290821-minecraft-4k-improved-by-crunchycat-download-now \
(Get the Standard Client Version) \
You will need to put this in the same directory as the Fabric Loader Jar (That 4 KiB filesize does have its drawbacks).
### Step 4:
Run Fabric Loader from the KnotClient class, like this: \
`java -Dfabric.skipMcProvider=true -classpath fabric-loader-0.13.0.jar:mc4kprovider-0.0.0.jar:fabric/* net.fabricmc.loader.launch.knot.KnotClient`
###### The exact command wil vary depending on where you put everything. This assumes you put Fabric's dependencies in a "fabric/" subdirectory, as I had done, and that all other files are in the current working directory.
###### `-Dfabric.skipMcProvider=true` *is* optional, but I like putting it there anyway just in case.

I chose CrunchyCat's mod for a few reasons:
```
Vanilla's a Java applet that only runs in a browser, good luck running that in 2022.
Every other jarmod that allowed the game to run without a browser from 2013 was over 4 KiB.
It's the closest to vanilla that works.
```