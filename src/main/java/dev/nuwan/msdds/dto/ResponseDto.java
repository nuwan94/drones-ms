package dev.nuwan.msdds.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

  private Integer status;
  private String message;
  private JSONObject data;
}
