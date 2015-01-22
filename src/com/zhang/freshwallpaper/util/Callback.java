package com.zhang.freshwallpaper.util;

public interface Callback {

	void onBefore();

	boolean onRun();

	void onAfter(boolean b);
}
