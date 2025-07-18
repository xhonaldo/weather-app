package com.example.trackforce;

import static org.junit.Assert.*;

import com.example.trackforce.domain.util.DomainHelper;

import org.junit.Before;
import org.junit.Test;

public class DomainHelperTest {

    private DomainHelper domainHelper;

    @Before
    public void setUp() {
        domainHelper = new DomainHelper();
    }

    @Test
    public void testFormatUnixTimestamp() {
        String formatted = domainHelper.formatUnixTimestamp(0);
        assertTrue(formatted.contains("1970"));
    }

    @Test
    public void testFormatTimezone_PositiveOffset() {
        String tz = domainHelper.formatTimezone(7200);
        assertEquals("GMT+02:00", tz);
    }

    @Test
    public void testFormatTimezone_NegativeOffset() {
        String tz = domainHelper.formatTimezone(-19800);
        assertEquals("GMT-05:30", tz);
    }

    @Test
    public void testGetUtcTimeHms() {
        String time = domainHelper.getUtcTimeHms(0);
        assertEquals("00:00:00", time);
    }

    @Test
    public void testGetLogoUrl() {
        String logoCode = "10d";
        String expectedUrl = "https://openweathermap.org/img/wn/10d@4x.png";
        assertEquals(expectedUrl, domainHelper.getLogoUrl(logoCode));
    }

    @Test
    public void testKelvinToCelsius() {
        double kelvin = 300.15;
        // 300.15 K - 273.15 = 27.0 °C
        String result = domainHelper.kelvinToCelsius(kelvin);
        assertEquals("27.0 °C", result);
    }

    @Test
    public void testAddCelsiusMetric() {
        assertEquals("25 °C", domainHelper.addCelsiusMetric(25));
        assertEquals("", domainHelper.addCelsiusMetric(null));
    }

    @Test
    public void testAddPercentageMetric() {
        assertEquals("80%", domainHelper.addPercentageMetric(80));
        assertEquals("", domainHelper.addPercentageMetric(null));
    }

    @Test
    public void testAddHectopascalsMetric() {
        assertEquals("1013 Hpa", domainHelper.addHectopascalsMetric(1013));
        assertEquals("", domainHelper.addHectopascalsMetric(null));
    }

    @Test
    public void testAddMeterMetric() {
        assertEquals("1500 M", domainHelper.addMeterMetric(1500));
        assertEquals("", domainHelper.addMeterMetric(null));
    }

    @Test
    public void testAddSpeedMetric() {
        assertEquals("5 m/s", domainHelper.addSpeedMetric(5));
        assertEquals("", domainHelper.addSpeedMetric(null));
    }
}