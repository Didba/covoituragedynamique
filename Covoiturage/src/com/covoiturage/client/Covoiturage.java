package com.covoiturage.client;

import com.covoiturage.client.place.LoginPlace;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class Covoiturage implements EntryPoint {
	private final Place defaultPlace = new LoginPlace("Covoiturage");
	private final LayoutContainer layoutContainer = new LayoutContainer();
	private final BorderLayout layoutPanel = new BorderLayout();
	private final ContentPanel mainPanel = new ContentPanel();
	private final ContentPanel FooterPanel = new ContentPanel();
	private final ContentPanel horizMasterPanel = new ContentPanel();
	private final ContentPanel vertMasterPanel = new ContentPanel();
	private final ContentPanel asidePanel = new ContentPanel();
	
	

	AcceptsOneWidget horizMasterDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			horizMasterPanel.setVisible(widget != null);
			if (widget != null)
				horizMasterPanel.add(widget);
			layoutContainer.layout();
		}
	};
	AcceptsOneWidget asideDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			asidePanel.setVisible(widget != null);

			if (widget != null)
				asidePanel.add(widget);
			layoutContainer.layout();
		}
	};
	AcceptsOneWidget vertMasterDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			vertMasterPanel.setVisible(widget != null);

			if (widget != null)
				vertMasterPanel.add(widget);
			layoutContainer.layout();

		}
	};

	AcceptsOneWidget mainDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			mainPanel.setVisible(widget != null);
			if (mainPanel.getWidget(0) != null)
				mainPanel.remove(mainPanel.getWidget(0));

			if (widget != null)
				mainPanel.add(widget);
			layoutContainer.layout();
			hidePanels();

		}
	};
	AcceptsOneWidget footerDisplay = new AcceptsOneWidget() {
		@Override
		public void setWidget(IsWidget activityWidget) {
			Widget widget = Widget.asWidgetOrNull(activityWidget);
			FooterPanel.setVisible(widget != null);

			if (widget != null)
				FooterPanel.add(widget);
			layoutContainer.layout();

		}
	};

	private void hidePanels() {
		if (horizMasterPanel.getWidget(0) == null)
			horizMasterPanel.hide();
		if (vertMasterPanel.getWidget(0) == null)
			vertMasterPanel.hide();
		if (asidePanel.getWidget(0) == null)
			asidePanel.hide();
	}

	@Override
	public void onModuleLoad() {
		final Viewport v = new Viewport();
		v.setLayout(new FitLayout());
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,
				60);
		layoutContainer.add(horizMasterPanel, northData);
		BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.EAST, 300);
		eastData.setCollapsible(true);
		layoutContainer.add(asidePanel, eastData);
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 110);
		layoutContainer.add(vertMasterPanel, westData);
		BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH,
				50);
		layoutContainer.add(FooterPanel, southData);

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		layoutContainer.add(mainPanel, centerData);

		FooterPanel.setHeaderVisible(false);
		mainPanel.setHeaderVisible(false);
		mainPanel.setScrollMode(Scroll.AUTO);
		horizMasterPanel.setHeaderVisible(false);
		vertMasterPanel.setHeaderVisible(false);
		
		//vertMasterPanel.setBodyStyleName("menuPanel");
		//vertMasterPanel.addStyleName("menuPanel");
		vertMasterPanel.setStyleName("menuPanel",true);

		ClientFactory clientFactory = new ClientFactoryImpl();
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		ActivityMapper horizMasterActivityMapper = new HorizMasterAppActivityMapper(
				clientFactory);
		ActivityMapper vertMasterActivityMapper = new VertMasterAppActivityMapper(
				clientFactory);
		ActivityMapper mainActivityMapper = new MainAppActivityMapper(
				clientFactory);
		ActivityMapper asideActivityMapper = new AsideAppActivityMapper(
				clientFactory);
		ActivityMapper footerActivityMapper = new FooterAppActivityMapper(
				clientFactory);

		ActivityManager horizMasterActivityManager = new ActivityManager(
				horizMasterActivityMapper, eventBus);
		ActivityManager vertMasterActivityManager = new ActivityManager(
				vertMasterActivityMapper, eventBus);
		ActivityManager mainActivityManager = new ActivityManager(
				mainActivityMapper, eventBus);
		ActivityManager asideActivityManager = new ActivityManager(
				asideActivityMapper, eventBus);
		ActivityManager footerActivityManager = new ActivityManager(
				footerActivityMapper, eventBus);

		horizMasterActivityManager.setDisplay(horizMasterDisplay);
		vertMasterActivityManager.setDisplay(vertMasterDisplay);
		asideActivityManager.setDisplay(asideDisplay);
		mainActivityManager.setDisplay(mainDisplay);
		footerActivityManager.setDisplay(footerDisplay);

		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		layoutContainer.setLayout(layoutPanel);
		v.add(layoutContainer);
		RootLayoutPanel.get().add(v);

		historyHandler.handleCurrentHistory();

	}
}
