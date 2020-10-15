package fun.mytoys.shardingspherejdbc.config.shardingsphere;

import fun.mytoys.shardingspherejdbc.constant.TimeShadingType;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Configuration
public class YearMonthShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

    private static final String TABLE_YEAR_MONTH = "%s_%s_%s";

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        Calendar calendar = Calendar.getInstance();

        return String.format(TimeShadingType.TABLE_YEAR_MONTH.getPattern(),
                preciseShardingValue.getLogicTableName(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH));
    }
}