package zerobase.weather.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "date_weather")
public class DateWeather {
    @Id
    private LocalDate date; // 날씨를 pk로 설정한 이유는 설정한 시간마다 DB에 한번 저장하기 위함

    private String weather;
    private String icon;
    private double temperature;
}
