package models;

import lombok.Data;

@Data
public class RegistrationResponseModel {
    String token, error;
    Integer id;
}
