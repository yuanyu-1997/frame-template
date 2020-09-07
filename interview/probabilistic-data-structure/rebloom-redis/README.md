-  https://github.com/RedisBloom/RedisBloom 
-  https://hub.docker.com/r/redislabs/rebloom/tags 
-  https://oss.redislabs.com/redisbloom/Quick_Start/ 

```bash
docker run \
--name redis_redisbloom \
-p 6600:6379 \
-di redislabs/rebloom:2.0.0
```

```bash
docker exec -it redis_redisbloom bash
redis-cli
```

```bash
bf.add name jerry
bf.exists name jerry
```



