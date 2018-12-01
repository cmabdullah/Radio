package com.abdullah.Radio;

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
			
			SetRadioChannel setRadioChannel = new SetRadioChannel();
			
			setRadioChannel.setRadio(new RadioEkattor(endTimeManual, path));
			setRadioChannel.startRadio();
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
