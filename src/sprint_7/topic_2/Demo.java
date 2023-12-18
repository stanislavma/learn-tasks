package sprint_7.topic_2;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class Demo {
    public static void main(String[] args) {
        // создаём экземпляр местного времени и даты
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        // создаём экземпляр временной зоны
        ZoneId zone = ZoneId.of("UTC+3");
        System.out.println(zone);

        // создаём экземпляр ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, zone);
        System.out.println(zonedDateTime);

        LocalDateTime dateTimeRegion = LocalDateTime.now();
        ZoneId zoneReg = ZoneId.of("Europe/Moscow");
        ZonedDateTime zonedDateTimeReg = ZonedDateTime.of(dateTimeRegion, zoneReg);

        System.out.println(zonedDateTimeReg);



        // сохраняем временную отметку запуска первого искусственного спутника
        Instant moment = Instant.ofEpochSecond(-386310686L);
        System.out.println("Timestamp: " + moment);

        // сохраняем её как московское время:
        ZoneId zoneVal = ZoneId.of("Europe/Moscow");
        ZonedDateTime zonedDateTimeVal = ZonedDateTime.ofInstant(moment, zoneVal);

        System.out.println(zonedDateTimeVal);


        Instant now = Instant.now();

        // сохраняем московское время:
        ZoneId moscowZone = ZoneId.of("Europe/Moscow");
        ZonedDateTime moscowDateTime = ZonedDateTime.ofInstant(now, moscowZone);

        // узнаём время в Нью-Йорке:
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime newYorkDateTime = moscowDateTime.withZoneSameInstant(newYorkZone);

        System.out.println(moscowDateTime);
        System.out.println(newYorkDateTime);


        Instant nowDemo = Instant.now();

        // сохраняем московское время:
        ZoneId moscowZoneDemo = ZoneId.of("Europe/Moscow");
        ZonedDateTime moscowDateTimeDemo = ZonedDateTime.ofInstant(nowDemo, moscowZoneDemo);

        // меняем регион на Нью-Йорк
        ZoneId newYorkZoneDemo = ZoneId.of("America/New_York");
        ZonedDateTime newYorkDateTimeDemo = moscowDateTimeDemo.withZoneSameLocal(newYorkZoneDemo);

        System.out.println(moscowDateTimeDemo);
        System.out.println(newYorkDateTimeDemo);



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Время: HH:mm:ss. Регион: VV, смещение: ZZZZZ");

        ZoneId zoneRegion = ZoneId.of("Asia/Dubai");
        ZonedDateTime dateTimeReg = ZonedDateTime.ofInstant(now, zoneRegion);

        System.out.println(dateTimeReg.format(formatter));
    }
}