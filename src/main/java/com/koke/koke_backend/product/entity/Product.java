package com.koke.koke_backend.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.koke.koke_backend.category.entity.ProductCategory;
import com.koke.koke_backend.common.converter.StringListConverter;
import com.koke.koke_backend.common.entity.BaseTimeEntity;
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
//        "CoffeeName" :"Campfire (캠프파이어)",
//            "Price" :"12,000원",
//            "Weight" :"200g",
//            "Summary" :"견과류의 고소함과 후미에 남는 초콜릿한 느낌 큰사이즈 컵이나 묵직한 커피를 찾는분들께 추천",
//            "ImgURL" :
//        "https://d2wosiipoa41qn.cloudfront.net/Oe3g3caPNiTlKsfWbga_Nkq5NeE=/650x650/s3.ap-northeast-2.amazonaws.com/koke-uploads/images/20221227/d467d2df7d454702b6b20ebb4eb22371.png",
//                "Roastery" :"UFO 커피로스터스",
//            "Flavor" : ["고소", "묵직한" ],
//        "ExtractionType" : ["에스프레소머신 BEST 모카포트 에어로프레스" ],
//        "CookingType" : ["블랙 그대로 우유 넣은 라떼" ],
//        "style" : ["3일 내 볶은 원두만 배송 산미가 없어요 신 맛이 없어서 고소해요. 블렌드 브라질 산타루시아, 인도 마이소르 미디엄 다크 로스팅" ]
//    }

    @Id
    @Column(length = 30)
    @Comment("상품 ID")
    private String id;

    @Column(nullable = false, length = 30)
    @Comment("상품명")
    private String name;

    @Column(columnDefinition = "MEDIUMTEXT NOT NULL")
    @Convert(converter = StringListConverter.class)
    @Comment("상품 설명")
    private List<String> description;

    @Column(columnDefinition = "TEXT NOT NULL")
    @Convert(converter = StringListConverter.class)
    @Comment("맛")
    private List<String> flavor;

    @Column(columnDefinition = "TEXT NOT NULL")
    @Convert(converter = StringListConverter.class)
    @Comment("상품 무게")
    private List<String> weight;

    @Column(columnDefinition = "TEXT NOT NULL")
    @Convert(converter = StringListConverter.class)
    @Comment("잘 맞는 추출 방법")
    private List<String> extractionType;

    @Column(columnDefinition = "TEXT NOT NULL")
    @Convert(converter = StringListConverter.class)
    @Comment("맛있게 먹는 방법")
    private List<String> cookingType;

    @Column(columnDefinition = "MEDIUMTEXT NOT NULL")
    @Convert(converter = StringListConverter.class)
    @Comment("스타일")
    private List<String> style;

    @Column(nullable = false, length = 20)
    @Comment("상품 가격")
    private String price;

    @Column(length = 300)
    @Comment("상품 이미지 URL")
    private String photoImgUrl;

//    @OneToOne(fetch = LAZY)
//    @JoinColumn(name = "logoImgMstId", referencedColumnName = "fileMstId")
//    @Comment("상품 로고 이미지 ID")
//    private FileMst logoImg;
//
//    @OneToOne(fetch = LAZY)
//    @JoinColumn(name = "productImgMstId", referencedColumnName = "fileMstId")
//    @Comment("상품 이미지 ID")
//    private FileMst productImg;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "roasteryId", referencedColumnName = "id")
    @Comment("로스터리 카페 ID")
    private Roastery roastery;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
    }, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductCategory> productCategories = new ArrayList<>();

}
