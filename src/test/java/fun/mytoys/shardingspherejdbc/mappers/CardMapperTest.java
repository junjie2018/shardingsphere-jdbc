package fun.mytoys.shardingspherejdbc.mappers;

import fun.mytoys.shardingspherejdbc.entities.Card;
import fun.mytoys.shardingspherejdbc.entities.CardConsumeRecord;
import fun.mytoys.shardingspherejdbc.entities.CardExtend;
import fun.mytoys.shardingspherejdbc.mappers_custom.CardMapperCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class CardMapperTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CardMapper cardMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CardMapperCustom cardMapperCustom;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CardExtendMapper cardExtendMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CardConsumeRecordMapper cardConsumeRecordMapper;

    @Test
    void insert() {
        for (int i = 0; i < 10; i++) {
            long cardNo = (long) new Random().nextInt(1000000000);

            Card cardInsert = Card.builder()
                    .cardNo(cardNo)
                    .mobile(13579246810L)
                    .firstName("First")
                    .lastName("Last")
                    .birthday(new Date())
                    .cardType(0)
                    .isEmployee("Y")
                    .isMajor(0)
                    .joinDate("null")
                    .cardStatus(0)
                    .memberStatus(0)
                    .memberSubStatus(0)
                    .updateAt(new Date())
                    .build();

            cardMapper.insertSelective(cardInsert);

//            CardExtend cardExtendInsert = CardExtend.builder()
//                    .cardNo(cardNo)
//                    .cardInitialPassword("12345")
//                    .cardPassword("12345")
//                    .validStartDate(new Date())
//                    .validEndDate(new Date())
//                    .employeeNo("12345")
//                    .employeeInitialPointValue(100)
//                    .clearDate(new Date())
//                    .build();
//
//            cardExtendMapper.insertSelective(cardExtendInsert);
        }

        System.out.println("");
    }

    @Test
    void select() {
        List<Card> cards = cardMapper.selectAll();
        System.out.println("Going");
    }

    @Test
    void selectCustom() {
        Card card = cardMapperCustom.selectCard(30084291);

        CardMapperCustom.CardAll cardAll = cardMapperCustom.selectCardAll(30084291);

        System.out.println("");
    }

    @Test
    void insert2() {
        CardConsumeRecord cardConsumeRecordInsert = CardConsumeRecord.builder()
                .createAt(new Date())
                .build();

        cardConsumeRecordMapper.insertSelective(cardConsumeRecordInsert);
    }
}