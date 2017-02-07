package model;

public class Playlist
{
	private int id;
	private String title;
	private String description;
	private String coverPicture;
	private String hashtag;
	private int views;
	private int userId;
	private int privacyOptionId;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getCoverPicture()
	{
		return coverPicture;
	}
	public void setCoverPicture(String coverPicture)
	{
		this.coverPicture = coverPicture;
	}
	public String getHashtag()
	{
		return hashtag;
	}
	public void setHashtag(String hashtag)
	{
		this.hashtag = hashtag;
	}
	public int getViews()
	{
		return views;
	}
	public void setViews(int views)
	{
		this.views = views;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public int getPrivacyOptionId()
	{
		return privacyOptionId;
	}
	public void setPrivacyOptionId(int privacyOptionId)
	{
		this.privacyOptionId = privacyOptionId;
	}
}
