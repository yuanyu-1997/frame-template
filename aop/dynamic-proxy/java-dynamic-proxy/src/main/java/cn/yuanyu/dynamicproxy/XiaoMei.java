package cn.yuanyu.dynamicproxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XiaoMei implements Girl{
    /**
     * 吃饭
     */
    @Override
    public void date() {
        System.out.println("小美说：跟你约会好开心啊。");
        // 我这里想带她直接去看电影
        // 这里的本质是，我是直接通过小美调用这个接口，而不是通过他妈妈调这个接口
        log.info("this: name={},code={}", this.getClass().getName(), this.hashCode());
        this.watchMovie();
    }

    /**
     * 看电影
     */
    @Override
    public void watchMovie() {
        System.out.println("小美说：这个电影我不喜欢看。");
    }
}
