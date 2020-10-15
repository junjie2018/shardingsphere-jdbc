package fun.mytoys.shardingspherejdbc.mappers_custom;

import fun.mytoys.shardingspherejdbc.base.IBaseMapper;
import fun.mytoys.shardingspherejdbc.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Select;

import java.util.Date;


public interface CardMapperCustom extends IBaseMapper<Card> {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class CardAll {
        private Long cardNo;

        private Long mobile;
        private String firstName;
        private String lastName;
        private Date birthday;
        private Integer cardType;
        private String isEmployee;
        private Integer isMajor;
        private String joinDate;
        private Integer cardStatus;
        private Integer memberStatus;
        private Integer memberSubStatus;
        private Date updateAt;

//        private Long combineCardNo;
//        private String cardInitialPassword;
//        private String cardPassword;
//        private Date validStartDate;
//        private Date validEndDate;
//        private String employeeNo;
//        private Integer employeeInitialPointValue;
//        private Date clearDate;
//        private Date createAt;
//        private String createBy;
//        private String updateBy;
    }

    @Select("select * from mmp_card where card_no = #{cardNo}")
    Card selectCard(long cardNo);

//    @Select("select mmp.card_no as card_no from mmp_card join mmp_card_no on card_no where mmp_card.card_no = ${cardNo}")
    @Select("select mc.mobile as mobile from mmp_card mc join mmp_card_extend mce on mc.card_no=mce.card_no where mc.card_no = #{cardNo}")
    CardAll selectCardAll(long cardNo);
}
