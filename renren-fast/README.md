# 建库建表

```bash
docker run \
--name mysql_renren_fast \
-e MYSQL_ROOT_HOST=%.%.%.% \
-e MYSQL_ROOT_PASSWORD=123456 \
-p 30000:3306  \
-di --rm mysql:8.0.18
```

```sql
jdbc:mysql://121.36.33.154:30000/?serverTimezone=UTC
```

```sql
drop database if exists renren_fast;
create database renren_fast default char set utf8mb4;
use renren_fast;
```

# 核心表

```xml
<!-- 查询用户的所有权限 -->
<select id="queryAllPerms" resultType="string">
    select m.perms
    from sys_user_role ur
             LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id
             LEFT JOIN sys_menu m on rm.menu_id = m.menu_id
    where ur.user_id = #{userId}
</select>
```

```xml
<!-- 查询用户的所有菜单ID -->
<select id="queryAllMenuId" resultType="long">
    select distinct rm.menu_id
    from sys_user_role ur
             LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id
    where ur.user_id = #{userId}
</select>
```

![relation](./doc/relation.png)

# main.vue

![relation](./doc/layout.png)

# 添加目录或者菜单流程

![relation](./doc/1.png)

![relation](./doc/2.png)

![relation](./doc/3.png)

![relation](./doc/4.png)

# 动态添加路由流程分析



 http://localhost:1000/renren-fast/sys/menu/nav?t=1602227261263 

```json
{
    "msg": "success",
    "menuList": [
        {
            "menuId": 1,
            "parentId": 0,
            "parentName": null,
            "name": "系统管理",
            "url": null,
            "perms": null,
            "type": 0,
            "icon": "system",
            "orderNum": 0,
            "open": null,
            "list": [
                {
                    "menuId": 2,
                    "parentId": 1,
                    "parentName": null,
                    "name": "管理员列表",
                    "url": "sys/user",
                    "perms": null,
                    "type": 1,
                    "icon": "admin",
                    "orderNum": 1,
                    "open": null,
                    "list": null
                },
                {
                    "menuId": 3,
                    "parentId": 1,
                    "parentName": null,
                    "name": "角色管理",
                    "url": "sys/role",
                    "perms": null,
                    "type": 1,
                    "icon": "role",
                    "orderNum": 2,
                    "open": null,
                    "list": null
                },
                {
                    "menuId": 4,
                    "parentId": 1,
                    "parentName": null,
                    "name": "菜单管理",
                    "url": "sys/menu",
                    "perms": null,
                    "type": 1,
                    "icon": "menu",
                    "orderNum": 3,
                    "open": null,
                    "list": null
                },
                {
                    "menuId": 5,
                    "parentId": 1,
                    "parentName": null,
                    "name": "SQL监控",
                    "url": "http://127.0.0.1:1000/renren-fast/druid/sql.html",
                    "perms": null,
                    "type": 1,
                    "icon": "sql",
                    "orderNum": 4,
                    "open": null,
                    "list": null
                },
                {
                    "menuId": 6,
                    "parentId": 1,
                    "parentName": null,
                    "name": "系统日志",
                    "url": "sys/log",
                    "perms": "sys:log:list",
                    "type": 1,
                    "icon": "log",
                    "orderNum": 7,
                    "open": null,
                    "list": null
                }
            ]
        }
    ],
    "code": 0,
    "permissions": [
        "sys:user:list",
        "sys:menu:update",
        "sys:menu:delete",
        "sys:menu:save",
        "sys:role:save",
        "sys:role:info",
        "sys:menu:list",
        "sys:role:update",
        "sys:user:info",
        "sys:user:delete",
        "sys:role:delete",
        "sys:user:update",
        "sys:role:list",
        "sys:menu:info",
        "sys:menu:select",
        "sys:user:save",
        "sys:role:select",
        "sys:log:list"
    ]
}
```

fnAddDynamicMenuRoutes(data.menuList)

```json
[ // 调用fnAddDynamicMenuRoutes传入的data.menuList
    {
        "menuId": 1,
        "parentId": 0,
        "parentName": null,
        "name": "系统管理",
        "url": null,
        "perms": null,
        "type": 0,
        "icon": "system",
        "orderNum": 0,
        "open": null,
        "list": [ // fnAddDynamicMenuRoutes里面的temp变量
            {
                "menuId": 2,
                "parentId": 1,
                "parentName": null,
                "name": "管理员列表",
                "url": "sys/user",
                "perms": null,
                "type": 1,
                "icon": "admin",
                "orderNum": 1,
                "open": null,
                "list": null
            },
            {
                "menuId": 3,
                "parentId": 1,
                "parentName": null,
                "name": "角色管理",
                "url": "sys/role",
                "perms": null,
                "type": 1,
                "icon": "role",
                "orderNum": 2,
                "open": null,
                "list": null
            },
            {
                "menuId": 4,
                "parentId": 1,
                "parentName": null,
                "name": "菜单管理",
                "url": "sys/menu",
                "perms": null,
                "type": 1,
                "icon": "menu",
                "orderNum": 3,
                "open": null,
                "list": null
            },
            {
                "menuId": 5,
                "parentId": 1,
                "parentName": null,
                "name": "SQL监控",
                "url": "http://127.0.0.1:1000/renren-fast/druid/sql.html",
                "perms": null,
                "type": 1,
                "icon": "sql",
                "orderNum": 4,
                "open": null,
                "list": null
            },
            {
                "menuId": 6,
                "parentId": 1,
                "parentName": null,
                "name": "系统日志",
                "url": "sys/log",
                "perms": "sys:log:list",
                "type": 1,
                "icon": "log",
                "orderNum": 7,
                "open": null,
                "list": null
            }
        ]
    }
]
```

# 验证码登陆流程

前端在获取验证码的时候会传递一个uuid给后端

```javascript
// src/views/common/login.vue
<img :src="captchaPath" @click="getCaptcha()" alt="">
export default {
	data () {
	  return {
		captchaPath: ''
	  }
	},
	created () {
	  this.getCaptcha() // 获取二维码
	},
	methods: {
	  // 获取验证码
	  getCaptcha () {
		this.dataForm.uuid = getUUID()
		this.captchaPath = this.$http.adornUrl(`/captcha.jpg?uuid=${this.dataForm.uuid}`)
	  }
	}
}

// src/utils/index.js
/**
 * 获取uuid
 */
function getUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
    return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16)
  })
}
```
后端生成验证码的时候会把这个**uuid+code**存入数据库
```java
// io.renren.modules.sys.controller.SysLoginController#captcha
@GetMapping("captcha.jpg")
public void captcha(HttpServletResponse response, String uuid) throws IOException {
    response.setHeader("Cache-Control", "no-store, no-cache");
    response.setContentType("image/jpeg");
    // 获取图片验证码
    BufferedImage image = sysCaptchaService.getCaptcha(uuid);
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(image, "jpg", out);
    IOUtils.closeQuietly(out);
}

//io.renren.modules.sys.service.impl.SysCaptchaServiceImpl#getCaptcha
@Override
public BufferedImage getCaptcha(String uuid) {
	if (StringUtils.isBlank(uuid)) {
		throw new RRException("uuid不能为空");
	}
	//生成文字验证码
	String code = producer.createText();
	SysCaptchaEntity captchaEntity = new SysCaptchaEntity();
	captchaEntity.setUuid(uuid);
	captchaEntity.setCode(code);
	// 5分钟后过期
	captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
	this.save(captchaEntity); // 保存到sys_captcha表中
	return producer.createImage(code);
}
```

在用户登陆的时候会根据**uuid+code**来确定用户输入的验证码是否正确

```sql
// io.renren.modules.sys.controller.SysLoginController#login
@PostMapping("/sys/login")
public Map<String, Object> login(@RequestBody SysLoginForm form) {
	boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
	if (!captcha) {
		return R.error("验证码不正确");
	}
	//用户信息
	SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
	//账号不存在、密码错误
	if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
		return R.error("账号或密码不正确");
	}
	//账号锁定
	if (user.getStatus() == 0) {
		return R.error("账号已被锁定,请联系管理员");
	}
	//生成token，并保存到数据库
	return sysUserTokenService.createToken(user.getUserId());
}

// io.renren.modules.sys.service.impl.SysCaptchaServiceImpl#validate
@Override
public boolean validate(String uuid, String code) {
	// 通过uuid+code找到这条记录，来判断用户验证码输入是否正确
	SysCaptchaEntity captchaEntity = this.getOne(new QueryWrapper<SysCaptchaEntity>().eq("uuid", uuid)); 
	if (captchaEntity == null) {
		return false;
	}
	//删除验证码
	this.removeById(uuid);
	if (captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
		return true;
	}
	return false;
}
```



# renren-generator

```xaml
<mapper namespace="io.renren.dao.MySQLGeneratorDao">

</mapper>
```
![velocity-1](./doc/velocity-1.png)

## 查看表信息

```sql
SELECT table_name    tableName,
       ENGINE        ENGINE,
       table_comment tableComment,
       create_time   createTime
FROM information_schema.tables
WHERE table_schema = (SELECT DATABASE()) AND table_name = 'tb_user'
```

![velocity-1](./doc/velocity-2.png)

## 查看表字段信息

```sql
SELECT column_name    columnName,
       data_type      dataType,
       column_comment columnComment,
       column_key     columnKey,
       extra          extra
FROM information_schema.columns
WHERE table_name = 'tb_user' AND table_schema = (SELECT DATABASE())
ORDER BY ordinal_position
```

![velocity-1](./doc/velocity-3.png)







 https://blog.csdn.net/qq_39126213/article/details/106183467 

https://zhuanlan.zhihu.com/p/100414292