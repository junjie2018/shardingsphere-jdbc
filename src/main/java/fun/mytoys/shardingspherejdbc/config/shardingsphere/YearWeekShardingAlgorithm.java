package fun.mytoys.shardingspherejdbc.config.shardingsphere;

import fun.mytoys.shardingspherejdbc.constant.TimeShadingType;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Configuration
public class YearWeekShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        Calendar calendar = Calendar.getInstance();

        return String.format(TimeShadingType.TABLE_YEAR_WEEK.getPattern(),
                preciseShardingValue.getLogicTableName(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.WEEK_OF_YEAR));
    }
}