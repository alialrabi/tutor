package com.tutor.common;

import com.tutor.enums.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserType userType) {
        if (userType == null) {
            return null;
        }
        return userType.getValue();
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbValue) {
        if (dbValue == null) {
            return null;
        }
        return UserType.fromValue(dbValue);
    }
}