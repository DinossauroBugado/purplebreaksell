package me.platinumdev.breaksell.model.utils;

import me.platinumdev.breaksell.model.enums.Configuration;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class FormatUtils {

    public String getNumberFormat(double number) {
        return new DecimalFormat("#,###.##", new DecimalFormatSymbols(new Locale("pt", "BR"))).format(number);
    }

    public String formatedValue(double value){
        List<String> index = Configuration.FORMAT_PREFIXES.getStringList();
        double valor = value;
        int i = 0;
        while(valor / 1000.0D >= 1.0D) {
            valor /= 1000.0D;
            i++;
        }
        return i < index.size() ? getNumberFormat(valor) + index.get(i) : getNumberFormat(valor);
    }

    public String getProgressBar(int current, int max) {
        float percent = (float) current / max;
        if(percent > 1) percent = 1;
        int bars1 = Math.round(Configuration.FORMAT_PROGRESSBAR_BARS.getValue() * percent);
        int bars2 = Configuration.FORMAT_PROGRESSBAR_BARS.getValue() - bars1;
        String progress = Configuration.FORMAT_PROGRESSBAR_COMPLETED.getStringValue() + StringUtils.repeat(Configuration.FORMAT_PROGRESSBAR_SYMBOL.getStringValue(), bars1) + Configuration.FORMAT_PROGRESSBAR_NOT_COMPLETED.getStringValue() + StringUtils.repeat(Configuration.FORMAT_PROGRESSBAR_SYMBOL.getStringValue(), bars2);
        return Configuration.FORMAT_PROGRESSBAR_FORMAT.getStringValue().replace("@bar", progress);
    }

}
