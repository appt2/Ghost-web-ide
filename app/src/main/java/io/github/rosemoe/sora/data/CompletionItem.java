/*
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2022  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 */
/*
    public CompletionItem shiftCount(int shiftCount) {
        return cursorOffset(commit.length() - shiftCount);
    }

    public CompletionItem cursorOffset(int offset) {
        if (offset < 0 || offset > commit.length()) {
            throw new IllegalArgumentException();
        }
        cursorOffset = offset;
        return this;
    }
    public final static Comparator<CompletionItem> COMPARATOR_BY_NAME = (p1, p2) -> p1.label.compareTo(p2.label);
*/
package io.github.rosemoe.sora.data;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.Comparator;

/**
 * The class used to save auto complete result items
 *
 * @author Rose
 */
@SuppressWarnings("CanBeFinal")
public class CompletionItem {

  public static final Comparator<CompletionItem> COMPARATOR_BY_NAME =
      (p1, p2) -> p1.label.compareTo(p2.label);

  /** Icon for displaying in adapter */
  public Drawable icon;

  /** Text to commit when selected */
  public String commit;

  /** Text to display as title in adapter */
  public String label;

  /** Text to display as description in adapter */
  public String desc;

  /** Cursor offset in {@link CompletionItem#commit} */
  public int cursorOffset;

  /** From Read File */
  public File file;

  private String prefix;
  private String body;
  private String description;


  public String getPrefix() {
    return prefix;
  }

  public String getBody() {
    return body;
  }

  public String getDescription() {
    return description;
  }

  @Nullable protected CompletionItemKind kinds;

  public CompletionItem() {}

  public CompletionItem(String str, String desc) {
    this(str, desc, (Drawable) null);
  }

  public CompletionItem(String label, String commit, String desc) {
    this(label, commit, desc, null);
  }

  public CompletionItem(String label, String desc, Drawable icon) {
    this(label, label, desc, icon);
  }

  public CompletionItem(String label, String commit, String desc, Drawable icon) {
    this.label = label;
    this.commit = commit;
    this.desc = desc;
    this.icon = icon;
    cursorOffset = commit.length();
  }

  public CompletionItem kind(CompletionItemKind kinds) {
    this.kinds = kinds;
    return this;
  }

  public CompletionItem shiftCount(int shiftCount) {
    return cursorOffset(commit.length() - shiftCount);
  }

  public CompletionItem cursorOffset(int offset) {
    if (offset < 0 || offset > commit.length()) {
      throw new IllegalArgumentException();
    }
    cursorOffset = offset;
    return this;
  }

  public String getCommit() {
    return this.commit;
  }

  public void setCommit(String commit) {
    this.commit = commit;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public enum CompletionItemKind {
    Identifier(0, 0xffabb6bd),
    Text(0, 0xffabb6bd),
    Method(1, 0xfff4b2be),
    Function(2, 0xfff4b2be),
    Constructor(3, 0xfff4b2be),
    Field(4, 0xfff1c883),
    Variable(5, 0xfff1c883),
    Class(6, 0xff85cce5),
    Interface(7, 0xff99cb87),
    Module(8, 0xff85cce5),
    Property(9, 0xffcebcf4),
    Unit(10),
    Value(11, 0xfff1c883),
    Enum(12, 0xff85cce5),
    Keyword(13, 0xffcc7832),
    Snippet(14),
    Color(15, 0xfff4b2be),
    Reference(17),
    File(16),
    Folder(18),
    EnumMember(19),
    Constant(20, 0xfff1c883),
    Struct(21, 0xffcebcf4),
    Event(22),
    Operator(23, 0xffeaabb6),
    TypeParameter(24, 0xfff1c883),
    User(25),
    Issue(26);

    private final int value = 0;
    private final long defaultDisplayBackgroundColor = 0;
    private String displayString = "";

    CompletionItemKind(int value, long defaultDisplayBackgroundColor) {
      value = value;
      defaultDisplayBackgroundColor = defaultDisplayBackgroundColor;
      displayString = name().substring(0, 1);
    }

    CompletionItemKind(int value) {
      this(value, 0);
    }

    public String getDisplayChar() {
      return displayString;
    }
  }
}
