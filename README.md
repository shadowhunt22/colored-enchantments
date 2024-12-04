# Enchantment Text Styling

### Info and How it Works

Customize an enchantment's text style, whether it's a vanilla enchantment or modded enchantment!

There are two parts to how this works: datapacks and applying the data through the use of this mod.

#### Datapacks

This mod defines a registry in `data/enchantment-text-styling/styling/enchantment`.  This is the home directory to all the JSON files containing styling information for enchantments.

#### Mod

When the server (integrated or dedicated) loads, it will grab the registry manager from the world so it can read and apply styling values from the datapacks which are loaded in the dynamic registry.  All that this mod does for the player is change the text styling of the enchantment.

### Features

With datapacks and this mod installed on the server and client, change the enchantment's text style and apply for all players to see!  See "Modpack Creators and Mod Developers" below for more details.

### Limitations

- Not all dynamic registries can be reloaded through `/reload` (one day, hopefully™).  This means in order to add more or remove some stylings, a full server restart must happen.

### Versions

This mod is **Fabric** ONLY.  There is no plan to support Forge.

This mod is available for the following Minecraft versions:

`1.21`
`1.21.1`
`1.21.2`
`1.21.3`
`1.21.4`

### Client and Server Support

This mod is required to be on the client and server.  If this mod is not on the client or server, the datapacks are sitting there with none of their data being used!

### Modpack Creators and Mod Developers

Want to add your enchantments or change the style of existing enchantments?  Head on over to the [wiki](https://shadowhunt22.github.io/mod-wiki/apis/enchantment-text-styling/)!