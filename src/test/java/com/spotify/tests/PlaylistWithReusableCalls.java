package com.spotify.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.api.PlaylistAPI;
import com.spotify.pojo.Playlist;
import authmanager.TokenGenerator;

import io.restassured.response.Response;
import utils.ConfigReader;


public class PlaylistWithReusableCalls {
	
	static String playlistId;

	@Test(priority = 1)
	public void createPlayList() throws IOException
	{
		ConfigReader cr = new ConfigReader();
		Playlist reqPlaylist  = new Playlist();
		
		reqPlaylist.setName(cr.readPropData("name"));
		reqPlaylist.setDescription(cr.readPropData("description"));
		reqPlaylist.setPublic(false);
		
		Response response = PlaylistAPI.post(reqPlaylist, TokenGenerator.renewToken());
		
		Playlist responsePlaylist = response.as(Playlist.class);
		
		playlistId = responsePlaylist.getId();
		
		String reqName = reqPlaylist.getName();
	
		String resName = responsePlaylist.getName();
		
		Assert.assertEquals(resName, reqName);
	}
	

	@Test(priority = 2)
	public void getAPlayList() throws IOException
	{
		Response response = PlaylistAPI.get(playlistId, TokenGenerator.renewToken());
		
		Playlist resPlaylist = response.as(Playlist.class);
		String description = resPlaylist.getDescription();
		
		System.out.println(description);
		
	}
	

	@Test(priority = 3)
	public void validateUpdatePlaylist() throws IOException
	{
		ConfigReader cr = new ConfigReader();
		Playlist reqPlaylist  = new Playlist();
		
		reqPlaylist.setName(cr.readPropData("name")+"updated playlist ");
		reqPlaylist.setDescription(cr.readPropData("description"));
		reqPlaylist.setPublic(false);
		
		Response response = PlaylistAPI.update(reqPlaylist, playlistId, TokenGenerator.renewToken());
		
		int stsCode = response.statusCode();
		
		Assert.assertEquals(stsCode, 200);
	}
	

	@Test(priority = 4)
	public void shouldNotBeAuthorized() throws IOException
	{
		Playlist reqPlaylist  = new Playlist();
		
		reqPlaylist.setName("14th August Playlist Evening Batch ");
		reqPlaylist.setDescription("Create Playlist test3");
		reqPlaylist.setPublic(false);
		
		Response response = PlaylistAPI.post(reqPlaylist, "BQDh_VKeDCGNvWLVvN5tg6v65CXTySbNJVf9NiVMxba7Ft4ZP8pUZeEPYCaNfLdrlqlqAy0FiMv-vSqdSRNzugs1F7b641QwpqvfKB0DsGnyI1ZLFKRBBHdqMjTrj9MmWpvYp2ZJM1bhb0LYoSs_DdnCmNGuvFMda0lA5m6MuIDf02WQT2L8FMVP7uZy-TMmjSjnujseag1Rucg3G1mWIqZaXvM62KkX5o89hq9o1szb3pk74H9l1UM-Jx7uvvrls5xrlmxhd-on_QvN");
		
		int stsCode = response.statusCode();
		
		Assert.assertEquals(stsCode, 401);
	}
	
	
	
	
	
	
	
	
	

}
