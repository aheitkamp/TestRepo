package ScritVsClass;

public class FirstJavaClass {

	private String name;
	
	public static void main(String[] args) {
		FirstJavaClass fj = new FirstJavaClass();
		fj.setName("Al");
		System.out.println(fj.success());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String success() {
		return "Hello " + name;
	}
}
