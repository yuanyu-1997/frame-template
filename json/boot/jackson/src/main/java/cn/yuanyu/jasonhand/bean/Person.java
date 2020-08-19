package cn.yuanyu.jasonhand.bean;


public class Person {
    private String name;
    private Integer age;
    public void setName(String name){this.name = name;}
    public void setAge(Integer age){this.age = age;}
    // com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class cn.yuanyu.jasonhand.bean.Person and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
    public String getName(){return name;}
    public Integer getAge(){return age; }
    @Override
    public String toString(){return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';}
}
