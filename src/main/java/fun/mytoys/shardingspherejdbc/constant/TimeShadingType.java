package fun.mytoys.shardingspherejdbc.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum TimeShadingType {
    TABLE_YEAR("%s_%s"),
    TABLE_YEAR_MONTH("%s_%s_%s"),
    TABLE_YEAR_WEEK("%s_%s_%s"),
    ;

    private String pattern;
}
