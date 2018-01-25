package pl.epoint.servlets.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class ServletUtils {

    /**
     * @return parameter, if exists and can be parsed to Integer.
     * Otherwise resp.sendError() is called, and method returns null.
     */
    @NotNull
    public static Integer getRequiredIntegerParam(String paramName,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String parameterStr = getRequiredParam(paramName, req, resp);

        try {
            return Integer.parseInt(parameterStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(
                    "'%s' request parameter should be Integer", paramName));
        }
    }

    /**
     * @return parameter, if exists and can be parsed to BigDecimal.
     * Otherwise resp.sendError() is called, and method returns null.
     */
    @NotNull
    public static BigDecimal getRequiredBigDecimalParam(String paramName,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String parameterStr = getRequiredParam(paramName, req, resp);

        try {
            return new BigDecimal(parameterStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(
                    "'%s' request parameter should be BigDecimal", paramName));
        }
    }

    /**
     * @return parameter, if exists. Otherwise resp.sendError() is called, and method returns null.
     */
    @NotNull
    public static String getRequiredParam(String paramName,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String parameter = req.getParameter(paramName);
        if(parameter == null) {
            throw new IllegalArgumentException(String.format("'%s' request parameter is missing", paramName));
        } else if(parameter.isEmpty()) {
            throw new IllegalArgumentException(String.format("'%s' request parameter is empty", paramName));
        }
        return parameter;
    }

    /**
     * @return whether parameter value is the same as expected.
     * Otherwise resp.sendError() is called, and method returns false.
     */
    public static boolean requiredParamHasValue(String paramName, String value,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String parameter = getRequiredParam(paramName, req, resp);
        return parameter.equals(value);
    }

}
