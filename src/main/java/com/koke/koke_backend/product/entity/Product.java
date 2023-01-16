package com.koke.koke_backend.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.koke.koke_backend.category.entity.ProductCategory;
import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.file.entity.FileMst;
import com.koke.koke_backend.roastery.entity.Roastery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

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
    @Comment("커피 ID")
    private String id;

    @Column(nullable = false, length = 30)
    @Comment("커피명")
    private String name;

    @Column(nullable = false, length = 50)
    @Comment("커피 맛")
    private String taste;

    @Column(nullable = false, length = 20)
    private String weight;

    @Column(nullable = false, length = 20)
    private String price;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "logoImgMstId", referencedColumnName = "fileMstId")
    private FileMst logoImg;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "productImgMstId", referencedColumnName = "fileMstId")
    private FileMst productImg;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "roasteryId", referencedColumnName = "id")
    private Roastery roastery;

    @Lob
    @Column
    private String textInfo;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
    }, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductCategory> productCategories = new ArrayList<>();

}
