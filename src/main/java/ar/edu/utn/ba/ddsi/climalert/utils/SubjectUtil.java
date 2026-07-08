package ar.edu.utn.ba.ddsi.climalert.utils;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;

public class SubjectUtil
{
    public static String getSubject(String prefix, Weather weather)
    {
        return prefix + weather.getCity() + ", " + weather.getCountry();
    }
}
