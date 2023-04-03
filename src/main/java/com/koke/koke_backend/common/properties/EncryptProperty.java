package com.koke.koke_backend.common.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("encryption")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EncryptProperty {

    private String key;

    private String iv;

}
