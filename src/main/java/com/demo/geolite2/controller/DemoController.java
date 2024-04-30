package com.demo.geolite2.controller;

import java.io.IOException;
import java.net.InetAddress;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.geolite2.handler.GeoLite2Handler;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class DemoController {

	
	private final GeoLite2Handler geoLite2Handler;
	
	@GetMapping("/ipcheck")
	public ResponseEntity<?> getIpcheckCityDB(HttpServletRequest request) throws IOException, GeoIp2Exception {
//	    String ip = "211.234.203.50";
	    String ip = "165.243.5.20";
		    
	    return ResponseEntity.ok(geoLite2Handler.getIpInfo(ip));
	    
	}
	
	
//	@PostMapping("/GeoIPTest/{ipAddress}")
//    public GeoIP getLocation(@RequestParam(value="ipAddress", required=true) String ipAddress) throws Exception {
//      
//        GeoIPLocationService<String, GeoIP> locationService = new RawDBDemoGeoIPLocationService();
//        return locationService.getLocation(ipAddress);
//    }
	
	
}
