package com.koke.koke_backend.fav.service;

import com.koke.koke_backend.common.dto.ApiResponse;
import com.koke.koke_backend.common.security.JwtTokenProvider;
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
import com.koke.koke_backend.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final RoasteryRepository roasteryRepository;
    private final ProductRepository productRepository;
    private final FavMapper favMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public ResponseEntity<ApiResponse<Object>> saveFavRoastery(User current, String roasteryId) {
        Roastery roastery = roasteryRepository.findById(roasteryId).orElseThrow(getNSEE.apply("로스터리 정보"));

        favRepository.getFavRoastery(current.getUserId(), roasteryId)
                .ifPresentOrElse(favRepository::delete,
                        () -> {
                    Fav fav = favMapper.createFavRoastery(current, roastery);
                    favRepository.save(fav);
                });

        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<List<FavRoasteryDto>>> getFavRoastery(User current) {
        List<FavRoasteryDto> list = favRepository.favRoasteryList(current.getUserId());
        return ApiResponse.success(list);
    }

    @Transactional
    public ResponseEntity<ApiResponse<Object>> saveFavProduct(User current, String productId) {
        Product product = productRepository.findById(productId).orElseThrow(getNSEE.apply("커피 정보"));

        favRepository.getFavProduct(current.getUserId(), productId)
                .ifPresentOrElse(favRepository::delete,
                        () -> {
                            Fav fav = favMapper.createFavProduct(current, product);
                            favRepository.save(fav);
                        });

        return ApiResponse.success();
    }

    @Transactional
    public ResponseEntity<ApiResponse<List<FavProductDto>>> getFavProduct(User current) {
        List<FavProductDto> list = favRepository.favProductList(current.getUserId());
        return ApiResponse.success(list);
    }
}
