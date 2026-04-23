package com.tutor.common;

import com.tutor.enums.Provider;
import com.tutor.enums.UserType;
import jakarta.persistence.AttributeConverter;

public class ProviderConverter implements AttributeConverter<Provider, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Provider provider) {
        if (provider == null) {
            return null;
        }
        return provider.getValue();
    }

    @Override
    public Provider convertToEntityAttribute(Integer dbValue) {
        if (dbValue == null) {
            return null;
        }
        return Provider.fromValue(dbValue);
    }
}
