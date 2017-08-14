package com.devoir;
import com.housing.*;

public class OfferModel
{
  private int id;

  private UserModel user;
  private HousingWithIdModel housing;
  private String dateStart;
  private String dateEnd;
  private String country;

  public String user_mail;
  public int housing_id;

  /**
	* Default OfferModel constructor
	*/
	public OfferModel(int id, UserModel user, HousingWithIdModel housing, String dateStart, String dateEnd, String country) {
		super();
    this.id = id;
		this.user = user;
		this.housing = housing;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.country = country;
	}

  public OfferModel(int id, String user_mail, int housing_id, String dateStart, String dateEnd, String country) {
		super();
    this.id = id;
		this.user_mail = user_mail;
		this.housing_id = housing_id;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.country = country;
	}

  /**
  * Default empty OfferModel constructor
  */
  public OfferModel() {
    super();
  }

  /**
	* Returns value of user
	* @return
	*/
	public int getId() {
		return id;
	}

	/**
	* Sets new value of user
	* @param
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	* Returns value of user
	* @return
	*/
	public UserModel getUser() {
		return user;
	}

	/**
	* Sets new value of user
	* @param
	*/
	public void setUser(UserModel user) {
		this.user = user;
	}

	/**
	* Returns value of housing
	* @return
	*/
	public HousingWithIdModel getHousing() {
		return housing;
	}

	/**
	* Sets new value of housing
	* @param
	*/
	public void setHousing(HousingWithIdModel housing) {
		this.housing = housing;
	}

	/**
	* Returns value of dateStart
	* @return
	*/
	public String getDateStart() {
		return dateStart;
	}

	/**
	* Sets new value of dateStart
	* @param
	*/
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	* Returns value of dateEnd
	* @return
	*/
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	* Sets new value of dateEnd
	* @param
	*/
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	* Returns value of country
	* @return
	*/
	public String getCountry() {
		return country;
	}

	/**HousingModel
	* Sets new value of country
	* @param
	*/
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	* Create string representation of OfferModel for printing
	* @return
	*/
	@Override
	public String toString() {
		return "OfferModel [user=" + user + ", housing=" + housing + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", country=" + country;
	}
}
