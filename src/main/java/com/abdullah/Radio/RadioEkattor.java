package com.abdullah.Radio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RadioEkattor implements Radio{
	LocalTime localTime;
	LocalDate localDate = LocalDate.now();
	
	String path;
	public RadioEkattor() {
		// TODO Auto-generated constructor stub
	}
	
	public RadioEkattor(LocalTime localTime, String path) {
		this.localTime = localTime;
		this.path = path;
	}
	
	@Override
	public void startRadio() {
		System.out.println("Redio 71 start ");
		System.out.println("localTime "+ localTime);
		System.out.println("path "+ path);
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime); // 2018-12-01T08:30
		System.out.println(localDateTime);

		URLConnection conn;
		try {
			conn = new URL("http://103.253.47.173:8000/;stream/1;").openConnection();
			InputStream is = conn.getInputStream();
			path = path.concat("/radioEkattor.mp3");

		    OutputStream outstream = new FileOutputStream(new File(path));
		    byte[] buffer = new byte[4096];
		    int len;
		    	
		    int i = 0;
		    
		    while ((len = is.read(buffer)) > 0) {
		        outstream.write(buffer, 0, len);
		        i = animation(i);
		        LocalDateTime localDateTimeNow = LocalDateTime.now();
		        if(localDateTimeNow.isAfter(localDateTime)) {
		        	break;
		        }
		    }
		    System.out.println("Done");
		    outstream.close();
		    System.exit(0);
		    
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int animation(int i) {
		if (i<10)
		System.out.print("-");
		
		if(i == 10) {
			System.out.println();
			i = 0;
		}
		System.out.print("|");
		i++;
		return i;
	}
}
