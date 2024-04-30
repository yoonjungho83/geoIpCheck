package com.demo.geolite2.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class DemoController {

	
	
	
	@GetMapping("/ipcheck")
	public ResponseEntity<?> getIpcheckCityDB(HttpServletRequest request) throws IOException, GeoIp2Exception {
//	    String ip = "211.234.203.50";
	    String ip = "165.243.5.20";

	    try {
	    	ClassPathResource resource = new ClassPathResource("data/GeoLite2-City.mmdb");
		    DatabaseReader databaseReader = new DatabaseReader.Builder(resource.getFile()).build();

		    InetAddress ipAddress = InetAddress.getByName(ip);
		    CityResponse response = databaseReader.city(ipAddress);

		    
		    System.out.println(" response.getCountry().getName() = "+ response.getCountry().getName());
		    System.out.println(" response.getSubdivisions().get(0).getNames().get(\"en\") = "+ response.getSubdivisions().get(0).getNames().get("en"));
		    System.out.println(" response.getCity().getName() = "+ response.getCity().getName());
		    System.out.println(" response.getLocation().getTimeZone() = "+response.getLocation().getTimeZone());
		    System.out.println(" response.getTraits().getIpAddress() = "+response.getTraits().getIpAddress());
		    
		    
		    return ResponseEntity.ok(response);
		} catch (AddressNotFoundException e) {
			// TODO: handle exception
			return ResponseEntity.ok("["+ip+"] 해당주소에 도시가 없습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.ok("주소 검색 시스템 에러! 관리자에게 문의하세요");
		}
	    
	}
	
	
//	@PostMapping("/GeoIPTest/{ipAddress}")
//    public GeoIP getLocation(@RequestParam(value="ipAddress", required=true) String ipAddress) throws Exception {
//      
//        GeoIPLocationService<String, GeoIP> locationService = new RawDBDemoGeoIPLocationService();
//        return locationService.getLocation(ipAddress);
//    }
	
	
}
