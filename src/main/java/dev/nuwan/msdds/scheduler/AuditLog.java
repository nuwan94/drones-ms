package dev.nuwan.msdds.scheduler;

import dev.nuwan.msdds.service.DroneService;
import java.util.concurrent.TimeUnit;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@CommonsLog
@Configuration
@EnableScheduling
public class AuditLog {

  final DroneService droneService;

  public AuditLog(DroneService droneService) {
    this.droneService = droneService;
  }

  @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
  public void scheduleTaskUsingCronExpression() {
    log.info("Drone battery level audit log started");
    droneService.getAllBatteryInfo().forEach(droneAuditDto ->
        log.info("Battery level of Drone (" + droneAuditDto.getSerialNo() + ") is "
            + droneAuditDto.getBattery() + "%")
    );
    log.info("Drone battery level audit log ended");
  }

}
