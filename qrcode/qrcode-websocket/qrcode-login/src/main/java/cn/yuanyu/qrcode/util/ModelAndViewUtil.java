package cn.yuanyu.qrcode.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ModelAndViewUtil {
    private ModelAndViewUtil() { }
    public static Map<String, String> fetchParameterMap(HttpServletRequest request) {
        Map<String, String> data = new HashMap<>();
        for (Object key : request.getParameterMap().keySet()) {
            String value = request.getParameter(key.toString());
            if (!StringUtils.isBlank(value)) {
                data.put(key.toString(), value);
            }
        }
        return data;
    }
}

