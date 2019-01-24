package com.ad.ad_core.fragment.main;

public class BottomTabBean {
    private int icon;
    private CharSequence title;

    public BottomTabBean(int icon, CharSequence title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon(){
        return this.icon;
    }

    public CharSequence getTitle(){
        return this.title;
    }
}
