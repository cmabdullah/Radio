package com.abdullah.RadioEkattor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");
		System.out.println("hi radio 98.4");
		ProcessDownload processDownload = new ProcessDownload();

		int startHour = 0;
		int startMinute = 0;
		final int endHour;
		final int endMinute;
		String flag = "";

		Scanner x = new Scanner(System.in);

		System.out.println("you wanted to start now? Y/L");

		flag = x.nextLine();

		switch (flag) {
		case "Y": {
			System.out.println("Working");
			String localDateTimeString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
			// System.out.println("currentMonthString : "+localDateTimeString);
			// 2018-12-01 21:31
			startHour = Integer.parseInt(localDateTimeString.substring(11, 13));
			startMinute = Integer.parseInt(localDateTimeString.substring(14, 16));
			break;
		}
		case "L": {
			System.out.println("Enter start hour : ");
			startHour = x.nextInt();
			System.out.println("Enter start minute : ");
			startMinute = x.nextInt();
			break;
		}
		case "y": {
			System.out.println("Working");
			String localDateTimeString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
			startHour = Integer.parseInt(localDateTimeString.substring(11, 13));
			startMinute = Integer.parseInt(localDateTimeString.substring(14, 16));
			break;
		}
		case "l": {
			System.out.println("Enter start hour : ");
			startHour = x.nextInt();
			System.out.println("Enter start minute : ");
			startMinute = x.nextInt();
			break;
		}
		default:
			break;
		}

		final LocalTime startTimeAuto = LocalTime.of(startHour, startMinute);// 08:30
		final LocalTime startTimeManual = LocalTime.of(startHour, startMinute);// 08:30

		final String f = flag;

		System.out.println("Enter end hour : ");
		endHour = x.nextInt();
		System.out.println("Enter end minute : ");
		endMinute = x.nextInt();

		final LocalTime endTimeManual = LocalTime.of(endHour, endMinute);// 08:30
		System.out.println("Enter class path! [ex /Users/cmabdullahkhan]");
		final String path = x.next();
		LocalDate date = LocalDate.now();// 2018-12-01

		processThread(processDownload, startTimeAuto, startTimeManual, f, endTimeManual, path, date);

	}

	private static void processThread(ProcessDownload processDownload, final LocalTime startTimeAuto,
			final LocalTime startTimeManual, final String f, final LocalTime endTimeManual, final String path,
			LocalDate date) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					if (f.equalsIgnoreCase("Y")) {
						processDownload.process(endTimeManual, path);
					} else if (f.equalsIgnoreCase("L")) {
						processDownload.process(endTimeManual, path);
					} else {
						System.out.println("process Press either Y/L");
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if (f.equalsIgnoreCase("Y")) {
						processDownload.consume(date, startTimeAuto);
					} else if (f.equalsIgnoreCase("L")) {
						processDownload.consume(date, startTimeManual);
					} else {
						System.out.println("Press either Y/L");
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
