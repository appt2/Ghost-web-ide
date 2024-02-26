package Ninja.coder.Ghostemane.code;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;


public class fab extends FloatingActionButton {
	public fab(Context t) {
		super(t);
	}

	public fab(Context context, AttributeSet a) {
		super(context, a);
		init();
	}

	private fab init() {
		ShapeAppearanceModel.Builder shapeAppearanceModel = new ShapeAppearanceModel.Builder();
		shapeAppearanceModel.setAllCorners(CornerFamily.CUT, 20f);
		setShapeAppearanceModel(shapeAppearanceModel.build());
		return this;
	}
}
