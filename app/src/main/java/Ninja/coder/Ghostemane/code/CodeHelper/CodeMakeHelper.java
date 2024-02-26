package Ninja.coder.Ghostemane.code.CodeHelper;

public class CodeMakeHelper {
	public static String java(String className) {
		return "// Made by Ghost web ide\n\npublic class " + className.replace(".java", "")
		+ " {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, World!\");\n\t}\n}";
	}
	
	public static String c() {
		return "// Made by Ghost web ide\n\n#include <stdio.h>\n\nint main() {\n\tprintf(\"Hello, World!\\n\");\n\treturn 0;\n}";
	}
	
	public static String cpp() {
		return "// Made by Ghost web ide\n\n#include <iostream>\n\nint main() {\n\tstd::cout << \"Hello, World!\" << std::endl;\n\treturn 0;\n}";
	}
	
	public static String kt() {
		return "// Made by Ghost web ide\n\nfun main(args: Array<String>) {\n\tprintln(\"Hello, World!\")\n}";
	}
	
	public static String ruby() {
		return "# Made by Ghost web ide\n\nputs \"Hello, World!\"";
	}
	
	public static String js() {
		return "// Made by Ghost web ide\n\nconsole.log(\"Hello, World!\");";
	}
	
	public static String html() {
		return "<!-- Made by Ghost web ide -->\n<!DOCTYPE html>\n<html>\n<body>\n\n<h1>Hello, World!</h1>\n\n</body>\n</html>";
	}
	
	public static String sh() {
		return "# Made by Ghost web ide\n\necho \"Hello, World!\"";
	}
	
	public static String php() {
		return "<!-- Made by Ghost web ide -->\n<?php\n\necho \"Hello, World!\";\n?>";
	}
	
	public static String cs() {
		return "// Made by Ghost web ide\n\nusing System;\n\nclass MainClass {\n\tpublic static void Main (string[] args) {\n\t\tConsole.WriteLine(\"Hello, World!\");\n\t}\n}";
	}
	
	public static String xml() {
		return "<!-- Made by Ghost web ide -->\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n\t<message>Hello, World!</message>\n</root>";
	}
	
	public static String ninja(String className) {
		return "/**\n**@TypeNinja -> Comment user\n**@Data \n*/\n\n// This is a welcome message.\npublic class "
		+ className.replace(".ninja", "")
		+ " {\n\n\n  void main(String... args) {\n\t\tprintor\"Hello\"\n\t}\n}";
	}
	
	public static String go(String packageName) {
		return "// Made by Ghost web ide\n\npackage " + packageName
		+ "\n\nimport \"fmt\"\n\nfunc main() {\n\tfmt.Println(\"Hello, World!\")\n}";
	}
	
	public static String rust(String appName) {
		return "// Made by Ghost web ide\n\nfn main() {\n\tprintln!(\"Hello, World!\");\n}";
	}
	
	public static String lua() {
		return "-- Made by Ghost web ide\n\nprint(\"Hello, World!\")";
	}
	
	public static String dart(String className) {
		return "// Made by Ghost web ide\n\nvoid main() {\n\tprint(\"Hello, World!\");\n}";
	}
	
	public static String pas() {
		return "// Made by Ghost web ide\n\nprogram HelloWorld;\n\nbegin\n\tWriteLn('Hello, World!');\nend.";
	}
	
	public static String Scala(String className) {
		return "// Made by Ghost web ide\n\nobject " + className
		+ " {\n\tdef main(args: Array[String]): Unit = {\n\t\tprintln(\"Hello, World!\")\n\t}\n}";
	}
	
	public static String Julia() {
		return "# Made by Ghost web ide\n\nprintln(\"Hello, World!\")";
	}
	
	public static String Groovy(String className) {
		return "// Made by Ghost web ide\n\nclass " + className
		+ " {\n\tstatic void main(args) {\n\t\tprintln(\"Hello, World!\")\n\t}\n}";
	}
	
	public static String Elixir() {
		return "# Made by Ghost web ide\n\nIO.puts(\"Hello, World!\")";
	}
	
	public static String ts() {
		return "// Made by Ghost web ide\n\nconsole.log(\"Hello, World!\");";
	}
	
	public static String json() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		builder.append("\t\"name\": \"NINJA CODER\",\n");
		builder.append("\t\"age\": 27,\n");
		builder.append("\t\"favoriteLanguage\": \"JAVA\"\n");
		builder.append("}");
		return builder.toString();
	}
	public static String py(){
		return "#Made by Ghost web ide \n\n import os\n print(\"Hello\") ";
	}
	
}
