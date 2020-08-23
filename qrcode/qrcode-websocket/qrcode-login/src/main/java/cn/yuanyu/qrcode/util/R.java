

package cn.yuanyu.qrcode.util;

import java.util.HashMap;

/**
 * 返回数据
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    public R() {
    }
    // error
    public static R error() {
        R r = new R();
        r.put("code", ResponseCode.FAIL);
        r.put("msg", "error");
        return r;
    }
    // ok
    public static R ok() {
        R r = new R();
        r.put("code", ResponseCode.SUCCESS);
        r.put("msg", "success");
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
