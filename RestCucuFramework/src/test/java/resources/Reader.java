package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class Reader {
	
	 Map<String, Object> data = new HashMap<String, Object>();
	public  void getPropertyObject() throws IOException {   
		FileInputStream fp = new FileInputStream(new File("src/test/java/Config/links.yml"));  
		Yaml yaml = new Yaml();
		data = yaml.load(fp);
		}  	
	public  String qagetmail() throws IOException {   
		Map<String, String> qa = (Map<String, String>) data.get("QACreds");
		return qa.get("mail");  
			}
	public  String qagetpassword() throws IOException {   
		Map<String, String> qa = (Map<String, String>) data.get("QACreds");
		return qa.get("password");  
			}  
	public  String getauthgurl() throws IOException {   
		Map<String, String> qa = (Map<String, String>) data.get("BaseURI");
		return qa.get("authuri");  
			}
	public  String getreceivingurl() throws IOException {   
		Map<String, String> qa = (Map<String, String>) data.get("BaseURI");
		return qa.get("receivinguri");  
			}
}
