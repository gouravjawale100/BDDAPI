package steps;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.spotify.api.SpecBuilders;
import com.spotify.pojo.Playlist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ConfigReader;

public class PlaylistSteps {
	static String playlistId;
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	
	@Given("create playlist payload")
	public void create_playlist_payload() throws IOException {
		ConfigReader cr = new ConfigReader();
		Playlist reqPlaylist  = new Playlist();
		
		reqPlaylist.setName(cr.readPropData("name"));
		reqPlaylist.setDescription(cr.readPropData("description"));
		reqPlaylist.setPublic(false);
				
		res	= given()
				
				.spec(SpecBuilders.reqSpec())
				
				.body(reqPlaylist);
	}

	@When("user calls with {string} http request")
	public void user_calls_with_http_request(String string) {
		response =	res.when()
		
		.post("users/31h7vhk7of7iiimi6yw72ywxpwbe/playlists");
	}

	@Then("the API call executed with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		 Playlist playlist = response.as(Playlist.class);
		 
		 playlistId= playlist.getId();
		 
		 System.out.println("Playlist id is :"+playlistId);
		 
		 assertEquals(response.statusCode(), int1);
	}
	
//	GET a playlist API methods:
	@Given("Get a playlist Payload")
	public void get_a_playlist_payload() throws IOException {
		res = given()
		
		.spec(SpecBuilders.reqSpec())
		.pathParam("pId", playlistId);
	}

	@When("user calls with http request")
	public void user_calls_with_http_request() {
		response = res .when()
		
		.get("playlists/{pId}");
	}

	@Then("the API call got success with status code {int}")
	public void in_response_body_is(Integer statusCode) {
		response.then()
		
		.spec(SpecBuilders.resSpec());
		
		assertEquals(response.statusCode(), statusCode);
	}
// Update playlist 
	
	@Given("Get update playlist Payload")
	public void get_update_playlist_payload() throws IOException {
		ConfigReader cr = new ConfigReader();
		Playlist reqPlaylist  = new Playlist();
		
		reqPlaylist.setName(cr.readPropData("name")+"updated playlist ");
		reqPlaylist.setDescription(cr.readPropData("description"));
		reqPlaylist.setPublic(false);
		
			res	= given()
				
				.spec(SpecBuilders.reqSpec())
				
				.body(reqPlaylist);
		
		
	}

	@When("user calls with PUT http request")
	public void user_calls_with_put_http_request() {
		response = res.when()
		
		.put("playlists/"+playlistId);
	}

	@Then("the API call should get success with status code {int}")
	public void the_api_call_should_get_success_with_status_code(Integer stsCode) {
		response.then()
				.assertThat()
				.statusCode(200);
	}
	
	
	
	

}
