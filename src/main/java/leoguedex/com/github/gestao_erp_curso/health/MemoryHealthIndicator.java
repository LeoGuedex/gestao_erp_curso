package leoguedex.com.github.gestao_erp_curso.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {

  private static final long MINIMAL_MEMORY = 100 * 1024 * 1024;

  @Override
  public Health health() {
    long freeMemory = Runtime.getRuntime().freeMemory();
    long totalMemory = Runtime.getRuntime().totalMemory();
    long maxMemory = Runtime.getRuntime().maxMemory();

    if (freeMemory < MINIMAL_MEMORY){
      return Health.down()
          .withDetail("memoria Total", totalMemory / 1024 / 1024 + "Mb")
          .withDetail("memoria Livre", freeMemory / 1024 / 1024 + "Mb")
          .withDetail("memoria Maxima", maxMemory / 1024 / 1024 + "Mb")
          .withDetail("limite  Minimo", MINIMAL_MEMORY / 1024 / 1024 + "Mb")
          .build();
    }

    return Health.up()
        .withDetail("memoria Total", totalMemory / 1024 / 1024 + "Mb")
        .withDetail("memoria Livre", freeMemory / 1024 / 1024 + "Mb")
        .withDetail("memoria Maxima", maxMemory / 1024 / 1024 + "Mb")
        .withDetail("limite  Minimo", MINIMAL_MEMORY / 1024 / 1024 + "Mb")
        .build();
  }
}
