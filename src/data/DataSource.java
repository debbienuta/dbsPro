package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Collection;
import model.Person;
import model.Playlist;
import model.Track;
import model.User;

public class DataSource
{
	ObjectMapper mapper;
	
	public DataSource()
	{
		mapper = new ObjectMapper();
	}
	
	public List<User> getUsers()
	{
		try
		{
			return mapper.readValue(new File("users.json"),new TypeReference<List<User>>(){});
		}
		catch (JsonParseException e)
		{
			return new ArrayList<User>();
		}
		catch (JsonMappingException e)
		{
			return new ArrayList<User>();
		}
		catch (IOException e)
		{
			return new ArrayList<User>();
		}
	}
	
	public List<Track> getTracks()
	{
		try
		{
			return mapper.readValue(new File("tracks.json"),new TypeReference<List<Track>>(){});
		}
		catch (JsonParseException e)
		{
			return new ArrayList<Track>();
		}
		catch (JsonMappingException e)
		{
			return new ArrayList<Track>();
		}
		catch (IOException e)
		{
			return new ArrayList<Track>();
		}
	}
	
	public List<Playlist> getPlaylists()
	{
		try
		{
			return mapper.readValue(new File("playlists.json"),new TypeReference<List<Playlist>>(){});
		}
		catch (JsonParseException e)
		{
			return new ArrayList<Playlist>();
		}
		catch (JsonMappingException e)
		{
			return new ArrayList<Playlist>();
		}
		catch (IOException e)
		{
			return new ArrayList<Playlist>();
		}
	}
	
	public List<Person> getPersons()
	{
		try
		{
			return mapper.readValue(new File("persons.json"),new TypeReference<List<Person>>(){});
		}
		catch (JsonParseException e)
		{
			return new ArrayList<Person>();
		}
		catch (JsonMappingException e)
		{
			return new ArrayList<Person>();
		}
		catch (IOException e)
		{
			return new ArrayList<Person>();
		}
	}
	
	public List<Collection> getCollections()
	{
		try
		{
			return mapper.readValue(new File("collections.json"),new TypeReference<List<Collection>>(){});
		}
		catch (JsonParseException e)
		{
			return new ArrayList<Collection>();
		}
		catch (JsonMappingException e)
		{
			return new ArrayList<Collection>();
		}
		catch (IOException e)
		{
			return new ArrayList<Collection>();
		}
	}
	
	public List<String> getNotificationTypes()
	{
		List<String> types = new ArrayList<String>();
		types.add("like");
		types.add("comment");
		types.add("advertisment");
		return types;
	}
	
	public List<String> getPrivacyOptionTypes()
	{
		List<String> types = new ArrayList<String>();
		types.add("private");
		types.add("public");
		return types;
	}
}
