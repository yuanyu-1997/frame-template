package cn.yuanyu.ssm.bean;

//@Component//("benz")
public class Benz implements Car{
    private String tag = "default";
    public void setTag(String tag) {
        this.tag = tag;
    }
    @Override
    public String toString() {
        return "Benz{" + "tag='" + tag + '\'' + '}';
    }
}