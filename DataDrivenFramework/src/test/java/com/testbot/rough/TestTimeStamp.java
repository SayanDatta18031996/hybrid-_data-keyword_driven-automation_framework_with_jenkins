package com.testbot.rough;

import java.util.Date;

public class TestTimeStamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d= new Date();
		String timeStamp= d.toString().replace(":", "_").replace(" ", "_");
		System.out.println(timeStamp);
	}

}
