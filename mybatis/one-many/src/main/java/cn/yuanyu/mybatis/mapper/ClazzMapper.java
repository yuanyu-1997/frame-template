package cn.yuanyu.mybatis.mapper;


import cn.yuanyu.mybatis.entity.Clazz;

/**
 * @author yuanyu
 */
public interface ClazzMapper {
    /**
     * 根据id查询班级信息
     *
     * @param id 班级id
     * @return 班级
     */
    Clazz selectClazzById(Integer id);
}
