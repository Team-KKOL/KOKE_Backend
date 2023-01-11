package com.koke.koke_backend.product.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.file.entity.FileMst;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Product extends BaseTimeEntity {

//    {
//        "id": "product_S_D_121",
//            "link": "/allCoffee/coffeeDetail",
//            "name": "멕시코 치아파스 디카페인",
//            "taste01": "밸런스",
//            "taste02": "고소",
//            "weight": "200",
//            "price": "16,000",
//            "cafeName": "BACS",
//            "logoImg": "logo_32.png",
//            "productImg": "pd_121.png",
//            "text_Info": "깔끔한 맛이 일품인 멕시코 치아파스 디카페인입니다. 워터 디카페인 공정을 통해 카페인을 제거했으며 군밤, 은행과도 같은 달달하면서 고소한듯한 향미가 돋보이는 매력적인 커피입니다.",
//            "info_00": "디카페인  ",
//            "info_01_t": "3일 내 볶은 원두만 배송",
//            "info_01_p": "",
//            "info_02_t": "산미가 없어요",
//            "info_02_p": "신 맛이 없어서 고소해요.",
//            "info_03_t": "싱글 오리진",
//            "info_03_p": "",
//            "info_04_t": "다크 로스팅",
//            "info_05_t": "",
//            "info_05_p": ""
//    }

    @Id
    @Column(length = 30)
    private String productId;

    @Column(length = 30)
    private String productName;

    @Column(length = 50)
    private String taste;

    @Column
    private String weight;

    @Column
    private String price;

    @ManyToOne
    @JoinColumn(name = "logoImgFileMstId", referencedColumnName = "fileMstId")
    private FileMst logoImg;

    @ManyToOne
    @JoinColumn(name = "productImgFileMstId", referencedColumnName = "fileMstId")
    private FileMst productImg;

    @Lob
    @Column
    private String textInfo;

}
