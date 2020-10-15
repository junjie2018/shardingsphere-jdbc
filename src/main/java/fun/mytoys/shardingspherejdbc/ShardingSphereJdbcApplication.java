package fun.mytoys.shardingspherejdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {
        "fun.mytoys.shardingspherejdbc.mappers",
        "fun.mytoys.shardingspherejdbc.mappers_custom"
})
public class ShardingSphereJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereJdbcApplication.class, args);
    }

}
