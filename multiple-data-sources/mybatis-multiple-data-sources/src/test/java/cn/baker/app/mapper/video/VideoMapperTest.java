package cn.baker.app.mapper.video;

import cn.baker.app.bean.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoMapperTest {

    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void insert(){
        Video video = new Video();
        video.setUid(1);
        video.setPath("./2021/02/28/xxx.mp4");
        Integer res = videoMapper.insert(video);
        System.out.println("res => " + res);
    }
}