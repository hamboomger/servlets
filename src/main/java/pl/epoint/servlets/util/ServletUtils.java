package pl.epoint.servlets.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;


//skoro są to Utilsy to trzeba zrobić override konstruktora na prywatny, można to zrobić adnotacją lomboka, poniżej:

@UtilityClass
public class ServletUtils {

    @NotNull
    public static Integer getRequiredIntegerParam(String paramName, HttpServletRequest req) {
        //Nie wiem czy jest sens wynajdować koło na nowo:
        // De facto dzięki adnotacji NonNull można by zrezygnować w ogóle z getRequiredParam.
        return NumberUtils.createInteger(getRequiredParam(paramName, req));
    }


    @NotNull
    public static BigDecimal getRequiredBigDecimalParam(String paramName, HttpServletRequest req) {
        //Tutaj też.
        return NumberUtils.createBigDecimal(getRequiredParam(paramName, req));
    }

    @NotNull
    public static String getRequiredParam(String paramName, HttpServletRequest req) {
        //jak są to utilsy teoretycznie warto by sprwadzać czy paramName jest nullem.
        String parameter = req.getParameter(paramName);
        if(parameter == null) {
            throw new IllegalArgumentException(String.format("'%s' request parameter is missing", paramName));
        } else if(parameter.isEmpty()) {
            throw new IllegalArgumentException(String.format("'%s' request parameter is empty", paramName));
        }

        /* nie wiem czy jest sens się rozwodzić nad tym, czy jest missing czy jest empty, można:
            if (StringUtils.isEmpty(parameter)) { //code }

         */
        return parameter;
    }

}
