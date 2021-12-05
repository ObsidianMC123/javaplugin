package org.obsidian;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public String prefix = ("Staff Chat>> ");
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(prefix + "Was Enable");
		new StaffChat (this);
	}
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(prefix + "Was Disable");
	}

	}


