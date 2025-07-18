package com.example.trackforce.domain.mapper;

import com.example.trackforce.data.remote.models.Cloud;
import com.example.trackforce.data.remote.models.Coord;
import com.example.trackforce.data.remote.models.Main;
import com.example.trackforce.data.remote.models.Sys;
import com.example.trackforce.data.remote.models.Weather;
import com.example.trackforce.data.remote.models.WeatherResponse;
import com.example.trackforce.data.remote.models.Wind;
import com.example.trackforce.domain.model.CloudData;
import com.example.trackforce.domain.model.CoordData;
import com.example.trackforce.domain.model.MainData;
import com.example.trackforce.domain.model.SysData;
import com.example.trackforce.domain.model.WeatherData;
import com.example.trackforce.domain.model.WeatherResponseData;
import com.example.trackforce.domain.model.WindData;
import com.example.trackforce.domain.util.DomainHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WeatherDomainMapper {
    private final DomainHelper domainHelper;

    @Inject
    public WeatherDomainMapper(DomainHelper domainHelper) {
        this.domainHelper = domainHelper;
    }



    public WeatherResponseData fromResponse(WeatherResponse response) {
        return new WeatherResponseData(
                response.getName(),
                domainHelper.formatUnixTimestamp(response.getDt()),
                domainHelper.formatTimezone(response.getTimezone()),
                domainHelper.addMeterMetric(response.getVisibility()),
                response.getCod(),
                mapCoord(response.getCoord()),
                mapMain(response.getMain()),
                mapCloud(response.getClouds()),
                mapSys(response.getSys()),
                mapWeatherList(response.getWeather()),
                mapWind(response.getWind())
        );
    }



    private static CoordData mapCoord(Coord coord) {
        if (coord == null) return new CoordData(0, 0);
        return new CoordData(coord.getLat(), coord.getLon());
    }

    private MainData mapMain(Main main) {
        if (main == null) return null;
        return new MainData(
                domainHelper.kelvinToCelsius(main.getTemp()),
                domainHelper.kelvinToCelsius(main.getFeelsLike()),
                domainHelper.kelvinToCelsius(main.getTempMin()),
                domainHelper.kelvinToCelsius(main.getTempMax()),
                domainHelper.addHectopascalsMetric(main.getPressure()),
                domainHelper.addPercentageMetric(main.getHumidity()),
                domainHelper.addHectopascalsMetric(main.getSeaLevel()),
                domainHelper.addHectopascalsMetric(main.getGrndLevel())
        );
    }

    private CloudData mapCloud(Cloud cloud) {
        if (cloud == null) return new CloudData("");
        return new CloudData(
                domainHelper.addPercentageMetric(cloud.getAll())
        );
    }

    private SysData mapSys(Sys sys) {
        if (sys == null) return null;
        return new SysData(
                sys.getCountry(),
                sys.getId(),
                domainHelper.getUtcTimeHms(sys.getSunrise()),
                domainHelper.getUtcTimeHms(sys.getSunset()),
                sys.getType()
        );
    }

    private List<WeatherData> mapWeatherList(List<Weather> weatherList) {
        List<WeatherData> result = new ArrayList<>();
        if (weatherList == null) return result;

        for (Weather w : weatherList) {
            result.add(
                    new WeatherData(
                            w.getDescription(),
                            w.getIcon(),
                            w.getId(),
                            w.getMain()
                    )
            );
        }
        return result;
    }

    private WindData mapWind(Wind wind) {
        if (wind == null) return null;
        return new WindData(
                wind.getDeg(),
                domainHelper.addSpeedMetric(wind.getSpeed())
        );
    }
}
