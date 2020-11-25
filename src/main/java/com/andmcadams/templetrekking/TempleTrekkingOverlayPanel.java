package com.andmcadams.templetrekking;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;

public class TempleTrekkingOverlayPanel extends OverlayPanel
{
	private final TempleTrekkingPlugin plugin;

	@Inject
	private TempleTrekkingOverlayPanel(TempleTrekkingPlugin plugin)
	{
		super(plugin);
		this.plugin = plugin;

		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(OverlayPriority.LOW);
	}

	private void addTextToOverlayPanel(String text)
	{
		panelComponent.getChildren().add(LineComponent.builder().left(text).build());
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		panelComponent.getChildren().clear();

		int currentPoints = plugin.getCurrentPoints();
		int lastPoints = plugin.getLastPoints();

		addTextToOverlayPanel("Current points: " + currentPoints);
		addTextToOverlayPanel("Last round points: " + lastPoints);
		return super.render(graphics);
	}

}