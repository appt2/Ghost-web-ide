package Ninja.coder.Ghostemane.code.PluginManager;

import java.io.File;

public class Plugin {
  protected String name;
  protected boolean Enabel;
  protected String id;
  protected File file;
  protected PluginFactory f;

  public Plugin(String name, boolean Enabel) {
    this.name = name;
    this.Enabel = Enabel;
  }

  public void setCallBack(PluginFactory f) {
    this.f = f;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean getEnabel() {
    return this.Enabel;
  }

  public void setEnabel(boolean Enabel) {
    this.Enabel = Enabel;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public File getFile() {
    return this.file;
  }

  public void setFile(File file) {
    this.file = file;
  }
}
