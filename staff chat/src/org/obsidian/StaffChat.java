package org.obsidian;



	import org.bukkit.Bukkit;
	import org.bukkit.ChatColor;
	import org.bukkit.Sound;
	import org.bukkit.command.Command;
	import org.bukkit.command.CommandExecutor;
	import org.bukkit.command.CommandSender;
	import org.bukkit.entity.Player;

	public class StaffChat implements CommandExecutor {

	    public StaffChat (Main plugin) {
	        plugin.getCommand("StaffChat").setExecutor(this);
	    }

	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	        if (!(sender instanceof Player)) {
	        	
	            sender.sendMessage("Lệnh này không được phép sài từ console");
	            return false;
	        }
	        Player p = (Player) sender;
	        if (!(p.hasPermission("staffchat.chat"))) {
	            p.sendMessage(Color("&cBạn không có quyền để sài lệnh này"));
	            return false;
	        }

	        if (args.length < 1) {
	            p.sendMessage(Color("&cTin nhắn không được để trống"));
	            return false;
	        }

	        String mess = ChatColor.RED + "[StaffChat] " + p.getDisplayName() + ": ";
	        for (String s : args) {
	            mess = mess + s + " ";
	        }

	        for (Player player : Bukkit.getOnlinePlayers()) {
	            if (player.hasPermission("staffchat.view")) {
	            	player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10f, 1.0f);
	                player.sendMessage(Color(mess));
	            }
	        }
	        return true;
	    }

	    private String Color(String s) {
	        s = ChatColor.translateAlternateColorCodes('&', s);
	        return s;
	    }
	}

