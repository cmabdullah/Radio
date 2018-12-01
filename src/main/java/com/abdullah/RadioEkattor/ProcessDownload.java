package com.abdullah.RadioEkattor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProcessDownload {
	public void process(LocalTime endTimeManual, String path) throws InterruptedException {
		synchronized (this) {
			System.out.println("Process start : " + LocalDateTime.now());
			wait();
			System.out.println("handler control return : " + LocalDateTime.now());
			System.out.println("process Terminated : " + endTimeManual);
			System.out.println("download path : " + path);
			
			SetRadioChannelContext setRadioChannel = new SetRadioChannelContext();
			
			//setRadioChannel.setRadio(new RadioEkattor(endTimeManual, path));
			setRadioChannel.setRadio(new RadioEkattor());
			//setRadioChannel.startRadio();
			
			/***
			URLConnection conn;
			try {
				conn = new URL("http://103.253.47.173:8000/;stream/1;").openConnection();
				InputStream is = conn.getInputStream();
				path = path.concat("radioEkattor.mp3");
				LocalDate localDate = LocalDate.now();
				LocalDateTime localDateTime = LocalDateTime.of(localDate, endTimeManual); // 2018-12-01T08:30

			    OutputStream outstream = new FileOutputStream(new File(path));
			    byte[] buffer = new byte[4096];
			    int len;

			    while ((len = is.read(buffer)) > 0) {
			        outstream.write(buffer, 0, len);
			        System.out.println("-");
			        System.out.println("|");
			        //2018-12-01T17:11:48.184
			        LocalDateTime localDateTimeNow = LocalDateTime.now();
			        if(localDateTimeNow.isAfter(localDateTime)) {
			        	break;
			        }
			    }
			    System.out.println("Done");
			    outstream.close();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			***/

		}

	}

	public void consume(LocalDate date, LocalTime time) throws InterruptedException {
		synchronized (this) {
			System.out.println("read consume");
			notify();

			final long currentTimeJava8 = Instant.now().toEpochMilli();

			System.out.println("currentTimeJava8 : " + currentTimeJava8);

			// LocalTime time = LocalTime.of(hour, minute, 0);//08:30
			LocalDateTime localDateTimeUpdate = LocalDateTime.of(date, time); // 2018-12-01T08:30
			System.out.println("localDateTime Now : "+LocalDateTime.now());
			System.out.println("localDateTimeUpdate : "+localDateTimeUpdate);
			System.out.println("changed time : " + localDateTimeUpdate);

			long milliSeconds = Timestamp.valueOf(localDateTimeUpdate).getTime();
			System.out.println("After milliSeconds : " + milliSeconds);

			long difference = milliSeconds - currentTimeJava8;
			System.out.println(difference);

			if (difference >= 0)
				Thread.sleep(difference);

		}
	}
}
