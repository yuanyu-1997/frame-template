package cn.yuanyu.app.controller;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;


public class CommodityControllerTest {
    private RestTemplate restTemplate = new RestTemplate();
    @Test
    public void add() {
        String  url = "http://localhost:6969/commodity/add?token="+"2ef9ae3a53f6e1fdf3fe0fd420be9891";

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void select() {
    }

    @Test
    public void list() {
    }
}