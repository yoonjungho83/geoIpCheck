package com.demo.geolite2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeoIP {
	 private String ipAddress;
	    private String city;
	    private String latitude;
	    private String longitude;
}
