package com.koke.koke_backend.product.mapper.decorator;

import com.koke.koke_backend.product.dto.json.ProductDataDto;
import com.koke.koke_backend.product.entity.Product;
import com.koke.koke_backend.product.mapper.ProductMapper;
import com.koke.koke_backend.roastery.entity.Roastery;
import com.koke.koke_backend.roastery.repository.RoasteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Primary
public abstract class ProductDecorator implements ProductMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductMapper delegate;

    @Autowired
    private RoasteryRepository roasteryRepository;

    @Override
    public Product toEntity(ProductDataDto dto) {
        Product product = delegate.toEntity(dto);
        Roastery roastery = roasteryRepository.findByRoasteryNm(dto.getRoastery());
        return product.toBuilder().roastery(roastery).build();
    }

    @Override
    public List<Product> toEntityList(List<ProductDataDto> productDataDtos) {
        if ( productDataDtos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<>(productDataDtos.size());
        for ( ProductDataDto productDataDto : productDataDtos ) {
            list.add( toEntity( productDataDto ) );
        }

        return list;
    }
}
