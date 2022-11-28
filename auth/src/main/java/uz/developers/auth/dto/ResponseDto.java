package uz.developers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Integer statusCode;
    private String message;
    private Object object;

    public static ResponseDto getSuccess(int i, String success) {
        return new ResponseDto(i,success,null);
    }
    public static ResponseDto getSuccess(Object object) {
        return new ResponseDto(0,"success",object);
    }
    public static ResponseDto UserNotFound(){return new ResponseDto(-1,"User not found",null);}
}
