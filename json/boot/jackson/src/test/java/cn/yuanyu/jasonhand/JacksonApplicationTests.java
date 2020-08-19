package cn.yuanyu.jasonhand;

import cn.yuanyu.jasonhand.bean.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JacksonApplicationTests {

    @Test
    public void test_1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person();
        person.setName("蔡徐坤");
        person.setAge(40);

// 序列化JSON（带缩进）
        String serialize = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(person);
        System.out.println(serialize);

// 反序列化为对象
        Person deserialization = mapper.readValue(serialize, Person.class);
        System.out.println(deserialization);

    }

}
