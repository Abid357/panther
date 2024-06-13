package GUI;

public class Log {
	private String className;
	private String variableName1;
	private String variableName2;
	private String variableName3;
	private int value1;
	private int value2;
	private int value3;
	private String stringValue1;
	private String stringValue2;
	private String stringValue3;
	private String action;
	public static String ADD = "added";
	public static String UPDATE = "updated";
	public static String DELETE = "deleted";

	public Log(String className, String variableName1, String stringValue1, int value1, String variableName2,
			String stringValue2, int value2, String variableName3, String stringValue3, int value3, String action) {
		super();
		this.className = className;
		this.variableName1 = variableName1;
		this.variableName2 = variableName2;
		this.variableName3 = variableName3;
		this.stringValue1 = stringValue1;
		this.stringValue2 = stringValue2;
		this.stringValue3 = stringValue3;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.action = action;
	}

	// 0 string, 3 ints, i-i-i
	public Log(String className, String variableName1, int value1, String variableName2, int value2,
			String variableName3, int value3, String action) {
		this(className, variableName1, null, value1, variableName2, null, value2, variableName3, null, value3, action);
	}

	// 0 string, 2 ints, i-i
	public Log(String className, String variableName1, int value1, String variableName2, int value2, String action) {
		this(className, variableName1, null, value1, variableName2, null, value2, null, null, -1, action);
	}

	// 0 string, 1 int, i
	public Log(String className, String variableName1, int value1, String action) {
		this(className, variableName1, null, value1, null, null, -1, null, null, -1, action);
	}

	// 3 strings, 0 int, s-s-s
	public Log(String className, String variableName1, String stringValue1, String variableName2, String stringValue2,
			String variableName3, String stringValue3, String action) {
		this(className, variableName1, stringValue1, -1, variableName2, stringValue2, -1, variableName3, stringValue3,
				-1, action);
	}

	// 2 strings, 0 int, s-s
	public Log(String className, String variableName1, String stringValue1, String variableName2, String stringValue2,
			String action) {
		this(className, variableName1, stringValue1, -1, variableName2, stringValue2, -1, null, null, -1, action);
	}

	// 1 string, 0 int, s
	public Log(String className, String variableName1, String stringValue1, String action) {
		this(className, variableName1, stringValue1, -1, null, null, -1, null, null, -1, action);
	}

	// 1 string, 1 int, s-i
	public Log(String className, String variableName1, String stringValue1, String variableName2, int value2,
			String action) {
		this(className, variableName1, stringValue1, -1, variableName2, null, value2, null, null, -1, action);
	}

	// 1 string, 1 int, i-s
	public Log(String className, String variableName1, int value1, String variableName2, String stringValue2,
			String action) {
		this(className, variableName1, null, value1, variableName2, stringValue2, -1, null, null, -1, action);
	}

	// 1 string, 2 ints, s-i-i
	public Log(String className, String variableName1, String stringValue1, String variableName2, int value2,
			String variableName3, int value3, String action) {
		this(className, variableName1, stringValue1, -1, variableName2, null, value2, variableName3, null, value3,
				action);
	}

	// 1 string, 2 ints, i-s-i
	public Log(String className, String variableName1, int value1, String variableName2, String stringValue2,
			String variableName3, int value3, String action) {
		this(className, variableName1, null, value1, variableName2, stringValue2, -1, variableName3, null, value3,
				action);
	}

	// 1 string, 2 ints, i-i-s
	public Log(String className, String variableName1, int value1, String variableName2, int value2,
			String variableName3, String stringValue3, String action) {
		this(className, variableName1, null, value1, variableName2, null, value2, variableName3, stringValue3, -1,
				action);
	}

	// 2 strings, 1 int, i-s-s
	public Log(String className, String variableName1, int value1, String variableName2, String stringValue2,
			String variableName3, String stringValue3, String action) {
		this(className, variableName1, null, value1, variableName2, stringValue2, -1, variableName3, stringValue3, -1,
				action);
	}

	// 2 strings, 1 int, s-i-s
	public Log(String className, String variableName1, String stringValue1, String variableName2, int value2,
			String variableName3, String stringValue3, String action) {
		this(className, variableName1, stringValue1, -1, variableName2, null, value2, variableName3, stringValue3, -1,
				action);
	}

	// 2 strings, 1 int, s-s-i
	public Log(String className, String variableName1, String stringValue1, String variableName2, String stringValue2,
			String variableName3, int value3, String action) {
		this(className, variableName1, stringValue1, -1, variableName2, stringValue2, -1, variableName3, null, value3,
				action);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getVariableName1() {
		return variableName1;
	}

	public void setVariableName1(String variableName1) {
		this.variableName1 = variableName1;
	}

	public String getVariableName2() {
		return variableName2;
	}

	public void setVariableName2(String variableName2) {
		this.variableName2 = variableName2;
	}

	public String getVariableName3() {
		return variableName3;
	}

	public void setVariableName3(String variableName3) {
		this.variableName3 = variableName3;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public int getValue3() {
		return value3;
	}

	public void setValue3(int value3) {
		this.value3 = value3;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStringValue1() {
		return stringValue1;
	}

	public void setStringValue1(String stringValue1) {
		this.stringValue1 = stringValue1;
	}

	public String getStringValue2() {
		return stringValue2;
	}

	public void setStringValue2(String stringValue2) {
		this.stringValue2 = stringValue2;
	}

	public String getStringValue3() {
		return stringValue3;
	}

	public void setStringValue3(String stringValue3) {
		this.stringValue3 = stringValue3;
	}
}
