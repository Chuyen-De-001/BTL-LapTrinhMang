package File;

import java.io.File;
	

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\input.jpg";
		File file = new File(path);
		if(file.exists()) {
//			System.out.print(file.getPath());
			String extention = file.getName().substring(file.getName().lastIndexOf('.')+1);
			System.out.print(file.getParent());
			System.out.print("file exits");
		}else {
			System.out.print("file not exits");
		}
	}

}
