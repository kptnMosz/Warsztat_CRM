package pl.coderslab;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
    /**
     * metoda do ustawiania  daty ze stringa
     * format daty musi byc taki:
     * rrrr-mm-dd lub rrrr/mm/dd
     *
     * @return LocalDate jesli format tekstu byl prawidlowy, null jesli nie
     */
    public static LocalDate setDateFormString(String date) {
        Pattern pattern = Pattern.compile("[12][0-9]{3}\\-[01][0-9]\\-[0-3][0-9]");
        Matcher matcher = pattern.matcher(date);
        LocalDate wynik = null;
        if (matcher.matches()) {
            wynik = LocalDate.parse(date);
            return wynik;
        }

        pattern = Pattern.compile("[12][0-9]{3}\\/[01][0-9]\\/[0-3][0-9]");
        matcher = pattern.matcher(date);
        if (matcher.matches()) {
            wynik = LocalDate.parse(date.replaceAll("\\/", "-"));
            return wynik;
        }
        return null;
    }
}
