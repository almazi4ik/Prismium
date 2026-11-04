# Prismium — Your Little Performance Helper

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Mod Loaders](https://img.shields.io/badge/Loaders-Fabric%20%7C%20NeoForge-blue)](https://curseforge.com)

**Vanilla+ performance optimizations featuring smart camera culling and fully configurable rendering distances.**

Prismium is a lightweight, client-side performance mod designed to boost your FPS by eliminating unnecessary rendering work. Instead of forcing fixed, aggressive settings that ruin the game's visuals, Prismium gives you full control. It automatically hides what you can't see behind your camera view and provides intuitive in-game sliders to tweak rendering limits for specific elements. All this while keeping your vanilla Minecraft experience completely intact!

Available for both **Fabric** and **NeoForge**!

---

## 📝 What's New

### Version 0.3.1
* **NeoForge Support:** Now fully compatible with the NeoForge mod loader!
* **In-Game Configuration Sliders:** Take exact control over your performance. Tweak individual rendering distances for various elements using clean, visual sliders right in the settings menu.

### Version 0.3.0
* **Sign Text Distance Culling:** Added a configurable rendering distance for text on signs. Text beyond your chosen distance is skipped entirely to maximize performance in dense areas.
* **Expanded Compatibility:** Fully updated and optimized for modern Minecraft versions.

---

## ⚡ Key Features

* **Smart Camera Culling:** Automatically skips rendering for all entities that are outside your immediate camera view (when you turn away from them), saving massive performance.
* **Configurable Sliders:** 
  * **Sign Text Distance:** Customize the exact render distance for text on signs to eliminate text rendering lag from distant blocks.
  * **Particle Distance:** Tweak the maximum particle rendering distance to fit your hardware needs.
  * **Dropped Item Distance:** Independently control how far away dropped items render — perfect for keeping automated farms lag-free!
  * **Distant Culling:** Adjust the maximum distance at which entities are allowed to render at all.

---

## ⚙️ Easy Configuration & Integration

Prismium is built around player agency. No complex config file hunting required:

* **In-Game GUI:** Press **G** (default keybind) to open the Prismium Settings screen, adjust the sliders, and watch your FPS improve instantly. Changes save automatically!
* **Custom Keybinds:** Easily rebind the settings menu key in Minecraft’s standard Controls menu.
* **Mod Menu Support:** Fully compatible with Mod Menu (for Fabric users)—access Prismium's options directly from your mod list.
* **Persistent Settings:** All configurations are safely stored in `config/prismium.json` and persist between game sessions.

---

## 🛡️ Links & Safety

* **CurseForge:** [Prismium on CurseForge](https://www.curseforge.com/minecraft/mc-mods/prismium)
* **License:** [MIT License](LICENSE)

⚠️ **Author's Note:** I am officially active **ONLY** on GitHub, Modrinth and CurseForge. Any profiles or downloads on other platforms are fake or unauthorized reuploads. Thank you for supporting the official release!
