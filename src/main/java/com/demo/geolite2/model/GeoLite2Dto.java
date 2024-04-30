package com.demo.geolite2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeoLite2Dto {

	private String ip;
	private String continent;//대륙
	private String country;//국가
	private String city;//도시
	private Double latitude;//위도;
	private Double longitude; //경도;
	private String errorDesc;//ip로 못찾을 경우 에러 내역
}
