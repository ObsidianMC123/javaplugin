package org.obsidian.addon;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_12_R1.PlayerConnection;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

public class PacketUtils {
	
	public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		CraftPlayer craftplayer = (CraftPlayer) player;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent titleJSON  = ChatSerializer.a("{\"text\": \"" + title +"\"}"); 		
		IChatBaseComponent subtitleJSON  = ChatSerializer.a("{\"text\": \"" + subtitle +"\"}"); 		
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleJSON, fadeIn, stay, fadeOut);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleJSON); 		
		connection.sendPacket(titlePacket);
		connection.sendPacket(subtitlePacket);		
	}
	
	public static void sendTabHF(Player player, String header, String footer) {	
		
		CraftPlayer craftplayer = (CraftPlayer) player ;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent headerJSON = ChatSerializer.a("{\"text\": \"" + header +"\"}");
		IChatBaseComponent footerJSON = ChatSerializer.a("{\"text\": \"" + footer +"\"}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
	 	
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, headerJSON);
			headerField.setAccessible(!headerField.isAccessible());
			
			Field footerField = packet.getClass().getDeclaredField("a");
			footerField.setAccessible(true);
			footerField.set(packet, footerJSON);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception e){
			e.printStackTrace();
		}
		
		connection.sendPacket(packet);
	}
	
	public static void sendActionBar(Player player, String msg) {
		IChatBaseComponent cbc  = ChatSerializer.a("{\"text\": \"" + msg +"\"}"); 
		PacketPlayOutTitle ppoc = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, cbc);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
	}

}
