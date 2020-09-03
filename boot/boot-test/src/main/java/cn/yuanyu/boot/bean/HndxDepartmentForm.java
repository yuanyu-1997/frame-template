package cn.yuanyu.boot.bean;

import java.util.HashMap;
import java.util.Map;

//@ApiModel(description = "部门")
public class HndxDepartmentForm {

    /**
     * 部门id
     */
    //@ApiModelProperty(value = "部门id")
    private String uidroleid;

    /**
     * 部门名称
     */
    //@ApiModelProperty(value = "部门名称")
    private String strrolename;

    /**
     * 父部门id
     */
    //@ApiModelProperty(value = "父部门id")
    private String uidparentroleid;

    /**
     * 部门描述
     */
    //@ApiModelProperty(value = "部门描述")
    private String strroledesc;


    public Map<String, String> toMap() {
        HashMap<String, String> ret = new HashMap<>();
        ret.put("uidroleid", uidroleid);
        ret.put("strrolename", strrolename );
        ret.put("uidparentroleid", uidparentroleid);
        ret.put("strroledesc", strroledesc);
        return ret;
    }

    public String getUidroleid() {
        return uidroleid;
    }

    public void setUidroleid(String uidroleid) {
        this.uidroleid = uidroleid;
    }

    public String getStrrolename() {
        return strrolename;
    }

    public void setStrrolename(String strrolename) {
        this.strrolename = strrolename;
    }

    public String getUidparentroleid() {
        return uidparentroleid;
    }

    public void setUidparentroleid(String uidparentroleid) {
        this.uidparentroleid = uidparentroleid;
    }

    public String getStrroledesc() {
        return strroledesc;
    }

    public void setStrroledesc(String strroledesc) {
        this.strroledesc = strroledesc;
    }
}