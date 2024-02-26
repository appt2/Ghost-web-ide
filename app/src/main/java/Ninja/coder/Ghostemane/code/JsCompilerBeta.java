package Ninja.coder.Ghostemane.code;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.Main;
public class JsCompilerBeta {
	public static String jscode(String code) {
		final StringBuilder result = new StringBuilder();
		
		new Thread(
		new Runnable() {
			@Override
			public void run() {
				Global global = Main.getGlobal();
				Context context = Context.enter();
				context.setOptimizationLevel(-1);
				Scriptable scope = context.initStandardObjects(global);
				context.evaluateString(scope, code, "<cmd>", 1, null);
				Object output = Context.jsToJava(scope.get("output", scope), String.class);
				result.append(output);
			}
		})
		.start();
		
		return result.toString();
	}
}
