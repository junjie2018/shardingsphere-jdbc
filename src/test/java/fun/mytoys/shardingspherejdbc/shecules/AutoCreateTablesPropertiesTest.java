package fun.mytoys.shardingspherejdbc.shecules;

import fun.mytoys.shardingspherejdbc.constant.TimeShadingType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@Slf4j
@SpringBootTest
class AutoCreateTablesPropertiesTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String checkSqlPattern =
            "SELECT `table_name` from `mmp_auto_create_tables_record` where `table_name` = '%s'";
    private static final String insertSqlPattern =
            "INSERT INTO `mmp_auto_create_tables_record` (`table_name`) VALUES ('%s')";

    @Test
    void test() {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("config/auto-create-tables-schedule.yml"));
        Properties properties = factoryBean.getObject();

        if (properties != null) {
            properties.forEach((table, tableCreateSql) -> {

                if (!((String) table).contains("#year")) {
                    log.error("Wrong in auto-create-tables-schedule.yml table {}", table);
                    return;
                }

                String tableStr = (String) table;

                String tableName = tableStr.substring(0, tableStr.indexOf("#year") - 1);

                String[] tableCreateSqlStrs = ((String) tableCreateSql).split("\\n");

                if (!tableCreateSqlStrs[0].contains(tableName)) {
                    log.error("Wrong in auto-create-tables-schedule.yml table {}\n createSql {}",
                            table, tableCreateSql);
                    return;
                }

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int week = calendar.get(Calendar.WEEK_OF_YEAR);

                // 通过判断key中是否包含关键字符，判断使用了哪种时间分片
                TimeShadingType timeShadingType;
                if (tableStr.contains("#month")) {
                    timeShadingType = TimeShadingType.TABLE_YEAR_MONTH;
                } else if (tableStr.contains("#week")) {
                    timeShadingType = TimeShadingType.TABLE_YEAR_WEEK;
                } else {
                    timeShadingType = TimeShadingType.TABLE_YEAR;
                }

                // 创建表时，尽量提前一个时期创建，防止在在关键时间点无表可用
                switch (timeShadingType) {
                    case TABLE_YEAR:
                        for (int i = 0; i < 2; i++) {
                            String tableTarget = String.format(timeShadingType.getPattern(),
                                    tableName,
                                    year + i);

                            if (!isTableExist(tableTarget)) {
                                createTable(tableTarget,
                                        getTargetCrateSql(tableName, tableTarget, tableCreateSqlStrs));
                            }

                        }
                        break;

                    case TABLE_YEAR_MONTH:
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                String tableTarget = String.format(timeShadingType.getPattern(),
                                        tableName,
                                        year + i,
                                        month + j);

                                if (!isTableExist(tableTarget)) {
                                    createTable(tableTarget,
                                            getTargetCrateSql(tableName, tableTarget, tableCreateSqlStrs));
                                }
                            }
                        }
                        break;

                    case TABLE_YEAR_WEEK:
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                String tableTarget = String.format(timeShadingType.getPattern(),
                                        tableName,
                                        year + i,
                                        week + j);

                                if (!isTableExist(tableTarget)) {
                                    createTable(tableTarget,
                                            getTargetCrateSql(tableName, tableTarget, tableCreateSqlStrs));
                                }
                            }
                        }
                        break;
                }
            });
        }
    }

    private boolean isTableExist(String tableName) {
        /*
            ShardingSphere-JDBC配置的数据源不支持该SQL：SHOW TABLES LIKE 'mmp_member_info'
            所以这儿采用了创建记录表的方案，具体的思路为，创建表的时候，顺表插入到mmp_auto_create_tables_record表中
         */
        String sql = String.format(checkSqlPattern, tableName);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);


        return maps.size() != 0;
    }

    private void createTable(String tableNameToCreate, String createSql) {
        System.out.println("==============\n" + createSql + "==============\n");


        jdbcTemplate.execute(createSql);

        // 表名入库
        String sql = String.format(insertSqlPattern, tableNameToCreate);
        jdbcTemplate.execute(sql);
    }

    private String getTargetCrateSql(String originalTableName, String targetTableName, String[] createSqlStrs) {
        String[] createSqlStrsTmp = Arrays.copyOf(createSqlStrs, createSqlStrs.length);

        createSqlStrsTmp[0] = createSqlStrsTmp[0].replace(originalTableName, targetTableName);

        StringBuilder sb = new StringBuilder();

        for (String tmp : createSqlStrsTmp) {
            sb.append(tmp).append("\n");
        }

        return sb.toString();
    }
}