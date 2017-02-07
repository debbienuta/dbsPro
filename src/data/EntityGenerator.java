package data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.Collection;
import model.Person;
import model.Playlist;
import model.Track;
import model.User;

public class EntityGenerator
{
	DataSource datasource;
	List<User> users;
	List<Person> persons;
	List<Playlist> playlists;
	List<Track> tracks;
	List<Collection> collections;

	public EntityGenerator()
	{
		datasource = new DataSource();
	}

	public void GenerateMockData()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
			String user = "a1506726";
			String pass = "dbs16";

			// establish connection to database
			Connection con = DriverManager.getConnection(database, user, pass);

			Statement stmt = con.createStatement();
			//InsertMockPersonsIntoPersons(stmt, 3);

			InsertDataIntoPerson(stmt);
			InsertDataIntoUsers(stmt);
			InsertDataIntoPrivacyOption(stmt);
			InsertDataIntoUserToUser(stmt);
			InsertDataIntoNotification(stmt);
			InsertDataIntoCollection(stmt);
			InsertDataIntoPlaylist(stmt);
			InsertDataIntoPlaylistCollection(stmt);
			InsertDataIntoTrack(stmt);
			InsertDataIntoPlaylistTrack(stmt);
			InsertDataIntoComments(stmt);
			InsertDataIntoLikes(stmt);
			InsertDataIntoFavorites(stmt);
			InsertDataIntoUserListensToPlaylist(stmt);
			InsertDataIntoUserListensToTrack(stmt);

			// clean up connections
			stmt.close();
			con.close();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}

	}

	private void InsertMockPersonsIntoPersons(Statement stmt, int numberOfEntries)
			throws SQLException, InterruptedException
	{

		String insertStatement = "";
		persons = datasource.getPersons();
		for (int counter = 1; counter <= numberOfEntries; counter++)
		{
			insertStatement = String.format(
					"INSERT INTO Person (Email,Location,City,Country,College,Website,Bio,LastName,FirstName) "
							+ "VALUES  ('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
					persons.get(counter).getEmail(), persons.get(counter).getLocation(), persons.get(counter).getCity(),
					persons.get(counter).getCountry(), persons.get(counter).getCollege(),
					persons.get(counter).getWebsite(), persons.get(counter).getBio(),
					persons.get(counter).getLastName(), persons.get(counter).getFirstName()) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoPerson(Statement stmt) throws SQLException, InterruptedException
	{
		String insertStatement = "";
		persons = datasource.getPersons().subList(0, 110);
		for (Person item : persons)
		{
			insertStatement = String.format(
					"INSERT INTO Person (Email,Location,City,Country,College,Website,Bio,LastName,FirstName) "
							+ "VALUES  ('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
					item.getEmail(), item.getLocation(), item.getCity(), item.getCountry(), item.getCollege(),
					item.getWebsite(), item.getBio(), item.getLastName(), item.getFirstName()) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoUsers(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		users = datasource.getUsers().subList(0, 110);
		for (User item : users)
		{
			insertStatement = String.format(
					"INSERT INTO Users (JoinDate,Username,Password,ProfilePicture,PersonId) "
							+ "VALUES (TO_DATE('%s','MM/DD/YYYY'),'%s','%s','%s',%s)",
					item.getJoinDate(), item.getUsername(), item.getPassword(), item.getProfilePicture(),
					item.getPersonId()) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoTrack(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		tracks = datasource.getTracks().subList(0, 110);
		for (Track item : tracks)
		{
			insertStatement = String.format(
					"INSERT INTO Track (Name,Artist,Year,Album) " + "VALUES  ('%s','%s',%s,'%s')", item.getName(),
					item.getArtist(), item.getYear(), item.getAlbum()) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoCollection(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		collections = datasource.getCollections().subList(0, 110);
		for (Collection item : collections)
		{
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			insertStatement = String.format(
					"INSERT INTO Collection (Description,Title,UserId) " + "VALUES ('%s','%s',%s)",
					item.getDescription(), item.getTitle(), userId) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoPlaylist(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		playlists = datasource.getPlaylists().subList(0, 110);
		for (Playlist item : playlists)
		{
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			insertStatement = String.format(
					"INSERT INTO Playlist (Title,Description,CoverPicture,Hashtag,Views,UserId,PrivacyOptionId) "
							+ "VALUES ('%s','%s','%s','%s',%s,%s,%s)",
					item.getTitle(), item.getDescription(), item.getCoverPicture(), item.getHashtag(), item.getViews(),
					userId, item.getPrivacyOptionId()) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoUserToUser(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int firstUserId = ThreadLocalRandom.current().nextInt(1, users.size());
			int secondUserId = ThreadLocalRandom.current().nextInt(1, users.size());
			insertStatement = String.format("INSERT INTO UserToUser (FirstUserId,SecondUserId) " + "VALUES (%s,%s)",
					firstUserId, secondUserId) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoNotification(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		List<String> types = datasource.getNotificationTypes().subList(0, 110);
		for (int counter = 1; counter <= 110; counter++)
		{
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			int typeRandom = ThreadLocalRandom.current().nextInt(0, 3);
			insertStatement = String.format("INSERT INTO Notification (Type,UserId) " + "VALUES ('%s',%s)",
					types.get(typeRandom), userId) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoPrivacyOption(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		List<String> types = datasource.getPrivacyOptionTypes().subList(0, 110);
		for (int counter = 1; counter <= 2; counter++)
		{
			insertStatement = String.format("INSERT INTO PrivacyOption (Type) " + "VALUES  ('%s')",
					types.get(counter - 1)) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoPlaylistCollection(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int collectionId = ThreadLocalRandom.current().nextInt(1, collections.size());
			int playlistId = ThreadLocalRandom.current().nextInt(1, playlists.size());
			insertStatement = String.format(
					"INSERT INTO PlaylistCollection (CollectionId,PlaylistId) " + "VALUES  (%s,%s)", collectionId,
					playlistId) + System.lineSeparator();
			// stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoPlaylistTrack(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int trackId = ThreadLocalRandom.current().nextInt(1, tracks.size());
			int playlistId = ThreadLocalRandom.current().nextInt(1, playlists.size());
			insertStatement = String.format("INSERT INTO PlaylistTrack (TrackId,PlaylistId) " + "VALUES  (%s,%s)",
					trackId, playlistId) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoComments(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int day = ThreadLocalRandom.current().nextInt(1, 29);
			int month = ThreadLocalRandom.current().nextInt(1, 13);
			int year = ThreadLocalRandom.current().nextInt(2012, 2017);
			String date = month + "/" + day + "/" + year;
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			int playlistId = ThreadLocalRandom.current().nextInt(1, playlists.size());
			insertStatement = String.format(
					"INSERT INTO Comments (CommentDate,Text,UserId,PlaylistId) "
							+ "VALUES  (TO_DATE('%s','MM/DD/YYYY'),'%s',%s,%s)",
					date, "dummy text for comment", userId, playlistId) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoLikes(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			int playlistId = ThreadLocalRandom.current().nextInt(1, playlists.size());
			insertStatement = String.format("INSERT INTO Likes (UserId,PlaylistId) " + "VALUES  (%s,%s)", userId,
					playlistId) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoFavorites(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int day = ThreadLocalRandom.current().nextInt(1, 29);
			int month = ThreadLocalRandom.current().nextInt(1, 13);
			int year = ThreadLocalRandom.current().nextInt(2012, 2017);
			String date = month + "/" + day + "/" + year;
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			int trackId = ThreadLocalRandom.current().nextInt(1, tracks.size());
			insertStatement = String
					.format("INSERT INTO Favorites (FavoriteDate,UserId,TrackId) "
							+ "VALUES  (%s,TO_DATE('%s','MM/DD/YYYY'),%s,%s)", date, userId, trackId)
					+ System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoUserListensToPlaylist(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			int playlistId = ThreadLocalRandom.current().nextInt(1, playlists.size());
			insertStatement = String.format(
					"INSERT INTO UserListensToPlaylist (PlaylistId,UserId) " + "VALUES  (%s,%s)", playlistId, userId)
					+ System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}

	public void InsertDataIntoUserListensToTrack(Statement stmt) throws SQLException
	{
		String insertStatement = "";
		for (int counter = 1; counter <= 110; counter++)
		{
			int trackId = ThreadLocalRandom.current().nextInt(1, tracks.size());
			int userId = ThreadLocalRandom.current().nextInt(1, users.size());
			insertStatement = String.format("INSERT INTO UserListensToTrack (TrackId,UserId) " + "VALUES  (%s,%s)",
					trackId, userId) + System.lineSeparator();
			stmt.executeUpdate(insertStatement);
		}
	}
}
