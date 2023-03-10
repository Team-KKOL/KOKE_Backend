package com.koke.koke_backend.common.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.LongSupplier;

import static org.springframework.util.Assert.notNull;

/**
* @author : 김하빈(danny9643@naver.com)
* @description : GenericJackson2RedisSerializer에서 Page를 직렬화하기 위한 Wrapper 클래스
* @!
* @?
* @TODO
* @Date : 2023-01-11, 수, 21;28
*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class SerializablePageExecutionUtils {

    private static final String TOTAL_SUPPLIER_MUST_NOT_BE_NULL = "TotalSupplier must not be null";
    private static final String PAGEABLE_MUST_NOT_BE_NULL = "Pageable must not be null";
    private static final String CONTENT_MUST_NOT_BE_NULL = "Content must not be null";

    public static <T> SerializablePage<T> getPage(List<T> content, Pageable pageable, LongSupplier totalSupplier) {
        notNull(content, CONTENT_MUST_NOT_BE_NULL);
        notNull(pageable, PAGEABLE_MUST_NOT_BE_NULL);
        notNull(totalSupplier, TOTAL_SUPPLIER_MUST_NOT_BE_NULL);

        if (pageable.isUnpaged() || pageable.getOffset() == 0) {

            if (pageable.isUnpaged() || pageable.getPageSize() > content.size()) {
                return new SerializablePage<>(content, pageable, content.size());
            }

            return new SerializablePage<>(content, pageable, totalSupplier.getAsLong());
        }

        if (content.size() != 0 && pageable.getPageSize() > content.size()) {
            return new SerializablePage<>(content, pageable, pageable.getOffset() + content.size());
        }

        return new SerializablePage<>(content, pageable, totalSupplier.getAsLong());
    }

}
