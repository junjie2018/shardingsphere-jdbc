package fun.mytoys.shardingspherejdbc.base;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@RegisterMapper
public interface IBaseMapper<T> extends Mapper<T> {

}
