package Ninja.coder.Ghostemane.code.model;

import android.view.View;

public class ItemSheet {

  private String name;

  private int icon;
  protected boolean click;

  public ItemSheet(String name, int icon) {
    this.name = name;
    this.icon = icon;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIcon() {
    return this.icon;
  }

  public void setIcon(int icon) {
    this.icon = icon;
  }

  public boolean getClick() {
    return this.click;
  }

  public void setClick(boolean click) {
    this.click = click;
  }
}
