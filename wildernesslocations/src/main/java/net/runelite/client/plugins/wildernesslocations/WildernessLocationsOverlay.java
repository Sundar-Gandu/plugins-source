/*
 * Copyright (c) 2019. PKLite  - All Rights Reserved
 * Unauthorized modification, distribution, or possession of this source file, via any medium is strictly prohibited.
 * Proprietary and confidential. Refer to PKLite License file for more information on
 * full terms of this copyright and to determine what constitutes authorized use.
 * Written by PKLite(ST0NEWALL, others) <stonewall@thots.cc.usa>, 2019
 *
 */

package net.runelite.client.plugins.wildernesslocations;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.TextComponent;

@Singleton
public class WildernessLocationsOverlay extends Overlay
{
	private final WildernessLocationsPlugin plugin;
	private final WildernessLocationsConfig config;
	private final TextComponent textComponent;

	@Inject
	public WildernessLocationsOverlay(final WildernessLocationsPlugin plugin, final WildernessLocationsConfig config)
	{
		this.plugin = plugin;
		this.config = config;
		determineLayer();
		setPriority(OverlayPriority.HIGH);
		setPosition(OverlayPosition.BOTTOM_RIGHT);
		textComponent = new TextComponent();
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (plugin.isRenderLocation() && config.drawOverlay())
		{
			textComponent.setText(plugin.getLocationString());
			return textComponent.render(graphics);
		}
		return null;
	}

	public void determineLayer()
	{
		if (config.mirrorMode())
		{
			setLayer(OverlayLayer.AFTER_MIRROR);
		}
		if (!config.mirrorMode())
		{
			setLayer(OverlayLayer.ABOVE_WIDGETS);
		}
	}
}
