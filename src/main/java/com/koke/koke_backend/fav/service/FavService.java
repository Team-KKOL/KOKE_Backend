package com.koke.koke_backend.fav.service;

import com.koke.koke_backend.application.response.ResponseMapper;
import com.koke.koke_backend.fav.dto.FavProductDto;
import com.koke.koke_backend.fav.dto.FavRoasteryDto;
import com.koke.koke_backend.fav.entity.Fav;
import com.koke.koke_backend.fav.mapper.FavMapper;
import com.koke.koke_backend.fav.repository.FavRepository;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.product.repository.ProductRepository;
import com.koke.koke_backend.roastery.entity.Roastery;
import com.koke.koke_backend.roastery.repository.RoasteryRepository;
import com.koke.koke_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.koke.koke_backend.common.singleton.Constant.getNSEE;

@Service
@Transactional
@RequiredArgsConstructor
public class FavService {

    private final FavRepository favRepository;
    private final RoasteryRepository roasteryRepository;
    private final ProductRepository productRepository;
    private final FavMapper favMapper;

    @Transactional
    public ResponseEntity<ResponseMapper<Object>> saveFavRoastery(User current, String roasteryUuid) {
        Roastery roastery = roasteryRepository.findByUuid(roasteryUuid).orElseThrow(getNSEE.apply("로스터리 정보"));

        favRepository.getFavRoastery(current.getId(), roasteryUuid)
                .ifPresentOrElse(favRepository::delete,
                        () -> {
                    Fav fav = favMapper.createFavRoastery(current, roastery);
                    favRepository.save(fav);
                });

        return ResponseMapper.success();
    }

    @Transactional
    public ResponseEntity<ResponseMapper<List<FavRoasteryDto>>> getFavRoastery(User current) {
        List<FavRoasteryDto> list = favRepository.favRoasteryList(current.getId());
        return ResponseMapper.success(list);
    }

    @Transactional
    public ResponseEntity<ResponseMapper<Object>> saveFavProduct(User current, String productUuid) {
        Product product = productRepository.findByUuid(productUuid).orElseThrow(getNSEE.apply("커피 정보"));

        favRepository.getFavProduct(current.getId(), productUuid)
                .ifPresentOrElse(favRepository::delete,
                        () -> {
                            Fav fav = favMapper.createFavProduct(current, product);
                            favRepository.save(fav);
                        });

        return ResponseMapper.success();
    }

    @Transactional
    public ResponseEntity<ResponseMapper<List<FavProductDto>>> getFavProduct(User current) {
        List<FavProductDto> list = favRepository.favProductList(current.getId());
        return ResponseMapper.success(list);
    }
}
