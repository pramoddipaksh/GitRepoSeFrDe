package companyp.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class DataReader {

// make n number of utilities to get data
	
	// Section 21: Framework Part 4 - Test Strategy- Control Tests Execution- Run Parallel Tests	

	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
// 	read json to string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "src\\test\\java\\companyp\\Data\\PurchaseOrder.json"),StandardCharsets.UTF_8); 
//	use this 'System.getProperty("user.dir")+' to get path dynamically instead of giving local machine path 		

//		String to HashMap through "Jackson databind"(which converts string content to HashMap)
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
//			the method 'readValue' reads the string and convert to HashMap; at the end List is returned	
		});
		
		return data;		
		
	}

}
