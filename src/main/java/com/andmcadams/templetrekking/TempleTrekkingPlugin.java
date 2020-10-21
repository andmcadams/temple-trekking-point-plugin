package com.andmcadams.templetrekking;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.VarbitComposition;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Temple Trekking"
)
public class TempleTrekkingPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private TempleTrekkingOverlayPanel overlayPanel;

	@Getter
	private int currentPoints;

	@Getter
	private int lastPoints;

	private static final int TEMPLE_TREKKING_POINTS = 1955;
	@Override
	protected void startUp() throws Exception
	{
		log.info("Temple Trekking started!");
		currentPoints = 0;
		lastPoints = 0;
		overlayManager.add(overlayPanel);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Temple Trekking stopped!");
		overlayManager.remove(overlayPanel);
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		int tempPoints = client.getVarbitValue(TEMPLE_TREKKING_POINTS);

		// If the current point count is nonzero, but the new count is zero, the trek ended.
		if (tempPoints == 0 && currentPoints != 0)
			lastPoints = currentPoints;
		currentPoints = tempPoints;
	}
}
