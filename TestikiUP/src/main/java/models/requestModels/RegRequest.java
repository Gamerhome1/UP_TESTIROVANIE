package models.requestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegRequest {
    public String email;
    public String password;

    public RegRequest(String email) {
        this.email = email;
    }
}
