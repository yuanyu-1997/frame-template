```bash
docker run \
--name redis \
-p 6379:6379 \
-di redis
```



在Service方法中使用二级缓存，不建议在Controller和DAO中使用二级缓存

```
不是所有请求都是经过Controller到达Service，也可以直接到达Service中（Dubbo），不写在DAO上是应该一个Service可能调用多个DAO，然后整个组装成一个值在缓存
```



单条数据适合缓存，多条数据不适合缓存；单条数据缓存相当于数据缓存，多条数据缓存相当于查询缓存

