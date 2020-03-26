package Test.StudentService;

import java.sql.ResultSet;

/**
 * @Classname IRowMapper
 * @Description TODO
 * @Date 2020/3/26 13:51
 * @Created by ASUS
 */
public interface IRowMapper<E> {
    /**
     *功能描述：根据结果集的类型进行对应处理
     *@param resultSet 待处理的结果集
     *Author Jason
     *Date 2020-03-26 13:52
     */
    E mapping(ResultSet resultSet)throws Exception;
}
