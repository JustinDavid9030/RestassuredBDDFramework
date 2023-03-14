package StepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import resources.Reader;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




public class StepDefinations {

	public Map<String, String> Headers(){
		Map<String, String> Headers = new HashMap<>();
		Headers.put("AUTHORIZATIONROLE","US_General_Manager");   
		Headers.put("Accept","application/json, text/plain, */*");       
		Headers.put("partnerCode","en");  
		Headers.put("countryCode","US");  
		Headers.put("languageCode","en");  
		Headers.put("X-Yard","12");  
		Headers.put("source","mobile"); 
		Headers.put("X-device-id", "55a4eaf26faf2f4a");
		Headers.put("correlationid", "OPSMobile-uuid-ceffb650-ab58-11ed-91d7-21e26d949f1e");
		return Headers;
	}
	
//	RequestSpecification response;
//	String resp;
//	String token=null;
//	@Given("^User is on g2 qa portal$")
//	public void user_is_on_g2_qa_portal() throws Throwable {
//		Reader reader = new Reader();
//		reader.getPropertyObject();
//		RestAssured.baseURI = reader.getauthgurl();
//        response = given()
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Basic Y29wYXJ0LWRldjpjYjA3MmI0NzM3YmI0NjBmOTFhNjgwNzU3OWIzMDVlMQ==");
//    }
//
//    @And("^User login using \"([^\"]*)\" and \"([^\"]*)\" with post http requesst$")
//    public void user_login_using_something_and_something_with_post_http_requesst(String strArg1, String strArg2) throws Throwable {
//    	Reader reader = new Reader();
//		reader.getPropertyObject();
//    	RequestSpecification re = response.given().config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
//                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                .config(RestAssured.config.logConfig(LogConfig.logConfig()
//                        .blacklistHeader("Authorization"))).formParam("username", reader.qagetmail())
//                .formParam("grant_type", "password").formParam("password", reader.qagetpassword());
//    }
//
//    @When("^login is successful with status code 200$")
//    public void login_is_successful_with_status_code_200() throws Throwable {
//    	resp= response.when().post("gauthemployee/oauth/token?grant_type=password").then().log().all().assertThat().statusCode(200).extract().response().asString();
//        
//    }
//
//    @Then("^\"([^\"]*)\" code in response body is \"([^\"]*)\"$")
//    public void something_code_in_response_body_is_something(String strArg1, String strArg2) throws Throwable {
//        JsonPath js = new JsonPath(resp);
//        token = js.get("access_token");
//        System.out.println(token);
//    	
//    }
    
    
    
	String token=null;
    @Given("^User got the access token by loging in$")
    public void user_got_the_access_token_by_loging_in() throws Throwable {
    	Reader reader = new Reader();
    	reader.getPropertyObject();
    	RestAssured.baseURI = reader.getauthgurl();
    	String login;
        login = given().log().all()
                .header("Authorization", "Basic Y29wYXJ0LWRldjpjYjA3MmI0NzM3YmI0NjBmOTFhNjgwNzU3OWIzMDVlMQ==")
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .config(RestAssured.config.logConfig(LogConfig.logConfig()
                .blacklistHeader("Authorization"))).formParam("username", reader.qagetmail())
                .formParam("grant_type", "password").formParam("password", reader.qagetpassword())
        .when().post("gauthemployee/oauth/token?grant_type=password").then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(login);
        token = js.get("access_token");
        System.out.println(token);
    }

    @When("^User want to complete receiving for a \"([^\"]*)\"$")
    public void user_want_to_complete_receiving_for_a_something(String strArg1) throws Throwable {
    	Reader reader = new Reader();
    	reader.getPropertyObject();
    	RestAssured.baseURI = reader.getreceivingurl();
    	FileReader fileReader=new FileReader("C:\\Users\\subarre\\eclipse-workspace\\RestCucuFramework\\src\\test\\java\\resources\\receiving_body.json");
    	JSONParser object= new JSONParser();
    	JSONObject receivingBody = (JSONObject) object.parse(fileReader);
        ValidatableResponse Receiving,Images;
        for(int i=1;i<=14;i++) {
        	File image = new File("C:\\Users\\subarre\\eclipse-workspace\\RestCucuFramework\\TEST Images\\Image"+i+".jpg");
        	switch (i) {
        	case 1:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Driver Side Front Angle\",\"image_name_code\":\"DSFA\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 2:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Driver Side Rear Angle\",\"image_name_code\":\"DSRA\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 3:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Passenger Side Rear Angle\",\"image_name_code\":\"PSRA\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 4:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Passenger Side Front Angle\",\"image_name_code\":\"PSFA\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 5:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Direct Front\",\"image_name_code\":\"DIRF\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 6:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Direct Rear\",\"image_name_code\":\"DIRR\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 7:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Driver Entry\",\"image_name_code\":\"DENT\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 8:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Cockpit View\",\"image_name_code\":\"CKPT\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 9:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Odometer/lnstrument Cluster\",\"image_name_code\":\"ODOM\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 10:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"SUV/VAN\",\"image_name_code\":\"SUVN\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 11:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Rear Seat from Passenger Side\",\"image_name_code\":\"PSRS\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 12:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Engine Compartment\",\"image_name_code\":\"ENGN\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 13:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"Loose Parts\",\"image_name_code\":\"LOOS\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
        	case 14:
                Images = given()
                		.headers(Headers())
        				.header("country","USA")
                		.header("Authorization", "bearer "+token)
                		.formParam("fileInfos", "[{\"name\": \"image\"}]")
                		.formParam("uploadType","IMAGE")
                		.formParam("entityType", "LOT")
                		.formParam("metadata", "{\"type\":\"IMG\",\"lot_number\":\""+strArg1+"\",\"yard_number\":12,\"is_blu_img\":\"N\",\"sequence\":\""+i+"\",\"image_name_description\":\"VIN Sticker\",\"image_name_code\":\"VINS\",\"total_image_count\":14}")     
                		.multiPart("image",image)
                		.when().post("https://c-ids-qa4.copart.com/v4/upload")
                		.then();
                break;
                
        	}
        }
        Receiving = given().log().all()
        		.queryParam("isDataOnly", "true").headers(Headers())
        		.header("Content-Type","application/json")
        		.header("Authorization", "bearer "+token)
        		.body(receivingBody)
        		.when().put("/receiving-ws-v2/yards/12/lots/"+strArg1+"?isDataOnly=true")
        		.then().assertThat().statusCode(200);
    }

    @Then("^Lot Stage is updated$")
    public void lot_stage_is_updated() throws Throwable {
        
    }


    
}