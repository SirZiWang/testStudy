package com.wangzi.test.jdk18;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ConcurrentAndSerial {
	
	public static void main(String[] args) {
		int max = 1000000;
		List<String> values = new  ArrayList<>(max);
		for(int x=0; x<max; x++){
			values.add(UUID.randomUUID().toString());
		}
		//串行执行
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		//并行执行
		long t2 = System.nanoTime();
		long count2 = values.parallelStream().sorted().count();
		System.out.println(count2);
		long t3 = System.nanoTime();
		long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
		System.out.println(String.format("parallel sort took: %d ms", millis2));
		
		Map<Integer, String> map = new HashMap<>();
		for(int i=0; i<10; i++){
			map.putIfAbsent(i, "value:" + i);
		}
		map.forEach((id, val) -> System.out.println(val));
		map.computeIfPresent(3, (num, val) -> val + num);
		String value = map.get(3);
		System.out.println(value);
		
		map.computeIfPresent(9, (num, val) -> null);
		boolean containsKey = map.containsKey(9);     // false
		System.out.println(containsKey);
		map.computeIfAbsent(23, num -> "val" + num);
		map.containsKey(23);    // true
		map.computeIfAbsent(3, num -> "bam");
		map.get(3);             // val33
		
		map.remove(3, "val3");
		map.get(3);             // val33
		map.remove(3, "val33");
		map.get(3);             // null
		
		map.getOrDefault(42, "not found");  // not found
		
		map.merge(9, "val9", (value2, newValue) -> value2.concat(newValue));
		String string = map.get(9);             // val9
		System.out.println(string);
		map.merge(9, "concat", (value2, newValue) -> value2.concat(newValue));
		String string2 = map.get(9);             // val9concat
		System.out.println(string2);
		
		Clock clock = Clock.systemDefaultZone();
		long millis1 = clock.millis();
		System.out.println(millis1);
		
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);
		System.out.println(legacyDate);
		
		
		System.out.println(ZoneId.getAvailableZoneIds());
		// prints all available timezone ids
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());

		// ZoneRules[currentStandardOffset=+01:00]
		// ZoneRules[currentStandardOffset=-03:00]
		
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		System.out.println(now1.isBefore(now2));  // false

		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

		System.out.println(hoursBetween);       // -4
		System.out.println(minutesBetween);     // -299
		
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late);       // 23:59:59
		DateTimeFormatter germanFormatter =
				DateTimeFormatter
				.ofLocalizedTime(FormatStyle.SHORT)
				.withLocale(Locale.GERMAN);

		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime);   // 13:37
	}
}
