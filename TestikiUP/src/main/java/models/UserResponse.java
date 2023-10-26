package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.responseModels.DataResponse;
import models.responseModels.SupportResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    public DataResponse data;
    public SupportResponse support;
}