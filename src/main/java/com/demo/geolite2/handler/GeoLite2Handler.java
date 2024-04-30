package com.demo.geolite2.handler;

import java.io.IOException;
import java.net.InetAddress;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.demo.geolite2.model.GeoLite2Dto;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.model.CityResponse;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GeoLite2Handler {

	
	private DatabaseReader databaseReader;
	
	@PostConstruct
	private void initObject() throws IOException {
		
		try 
		{
			ClassPathResource resource = new ClassPathResource("data/GeoLite2-City.mmdb");
			databaseReader = new DatabaseReader.Builder(resource.getFile()).build();
			
			log.info("                                 ");
			log.info("#################################");
			log.info("GeoLite2 Database  load complete!");
			log.info("#################################");
			log.info("                                 ");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	
	public GeoLite2Dto getIpInfo(String ip) {
		
		try 
		{
			InetAddress ipAddress = InetAddress.getByName(ip);
			CityResponse response = databaseReader.city(ipAddress);
			    
		    log.info("response.getTraits().getIpAddress()         ={}",response.getTraits().getIpAddress()         );
		    log.info("response.getContinent().getNames().get('en')={}",response.getContinent().getNames().get("en"));
		    log.info("response.getCountry().getNames().get('en')  ={}",response.getCountry().getNames().get("en")  );
		    log.info("response.getCity().getNames().get('en')     ={}",response.getCity().getNames().get("en")     );
		    
		    return GeoLite2Dto.builder()
		    		.ip       (response.getTraits().getIpAddress()         )
		    		.continent(response.getContinent().getNames().get("en")) 
		    		.country  (response.getCountry().getNames().get("en")  )
		    		.city     (response.getCity().getNames().get("en")     )
		    		.latitude (response.getLocation().getLatitude())
		    		.longitude(response.getLocation().getLongitude())
		    		.build();
		} 
		catch (AddressNotFoundException e) {
			// TODO: handle exception
			return GeoLite2Dto.builder()
		    		.errorDesc("["+ip+"] 해당주소에 도시가 없습니다.")
		    		.build();
		} catch (Exception e) {
			// TODO: handle exception
			return GeoLite2Dto.builder()
		    		.errorDesc("주소 검색 시스템 에러! 관리자에게 문의하세요")
		    		.build();
		}
	    
	    
	}
	
}
