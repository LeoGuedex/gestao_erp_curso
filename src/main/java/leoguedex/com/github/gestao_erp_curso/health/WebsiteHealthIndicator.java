package leoguedex.com.github.gestao_erp_curso.health;

import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class WebsiteHealthIndicator implements HealthIndicator {

  private static final String WEBSITE_TO_CHECK = "https://www.google.com";

  @Override
  public Health health() {
    try {
      URL url = new URL(WEBSITE_TO_CHECK);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setConnectTimeout(3000);
      connection.connect();

      if (connection.getResponseCode() == 200) {
        return Health.up().withDetail("Site", "Disponivel").build();
      } else {
        return Health.down().withDetail("Site", "Indisponivel").build();
      }
    } catch (Exception e) {
      return Health.down().withDetail("Site", "Indisponivel").build();
    }
  }
}
