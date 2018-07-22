package log.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import log.data.LogElement;
import log.data.LogJson;

@RestController
public class LogPathController {
	private static final Logger log = LoggerFactory.getLogger(LogPathController.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

    @RequestMapping("/logpath")
    public List<LogElement> LogPath(@RequestParam(value="path", defaultValue="none") String path) {
        //read file
        File input = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,LogJson> logList = new HashMap<String,LogJson>();

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				log.debug("Read file in line: "+line);
				ObjectMapper objectMapper = new ObjectMapper();
				
			    LogJson alog = objectMapper.readValue(line, LogJson.class);
			    log.debug(alog.toString());
			    
			    if(logList.containsKey(alog.getId())) {
			    	long duration  = Math.abs(alog.getTimestamp() - logList.get(alog.getId()).getTimestamp());
			    	log.debug("Id : " + alog.getId()+" duration: "+duration);
			    	boolean alert = false;
			    	if(4 < duration) {
			    		alert = true;
			    	}
			    	jdbcTemplate.update(
			    		    "INSERT INTO  logs(id, duration,type,host,alert) VALUES (?,?,?,?,?)",
			    		    new Object[]{alog.getId(),duration,alog.getType(),alog.getHost(),alert}
			    	);
			    	logList.remove(alog.getId());
			    	log.debug("remove log");
			    }
			    else {
			    	logList.put(alog.getId(), alog);
			    }
			    
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        jdbcTemplate.query(
                "SELECT * FROM logs", 
                (rs, rowNum) -> new LogElement(
                					rs.getString("id"), 
                					rs.getInt("duration"), 
                					rs.getString("type"), 
                					rs.getString("host"), 
                					rs.getBoolean("alert"
                							)
                )
        ).forEach(loglist -> log.info(loglist.toString()));
        String sql = "SELECT * FROM logs";
        List<LogElement> finalLogs=  jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(LogElement.class));
		log.debug("finish read file");
		return finalLogs;

    }
}

