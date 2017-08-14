package com.devoir;
import java.util.*;

public class PreferenceModel
{
  private UserModel user;
  private ArrayList<OfferModel> offers;
  private OfferModel offer;

	/**
	* Default empty Preference constructor
	*/
	public PreferenceModel() {
		super();
	}

	/**
	* Default Preference constructor
	*/
	public PreferenceModel(UserModel user, ArrayList<OfferModel> offers, OfferModel offer) {
		super();
		this.user = user;
		this.offers = offers;
		this.offer = offer;
	}

	/**
	* Returns value of user
	* @return
	*/
	public UserModel getUserModel() {
		return user;
	}

	/**
	* Sets new value of user
	* @param
	*/
	public void setUserModel(UserModel user) {
		this.user = user;
	}

	/**
	* Returns value of offers
	* @return
	*/
	public ArrayList<OfferModel> getOffers() {
		return offers;
	}

	/**
	* Sets new value of offers
	* @param
	*/
	public void setOffers(ArrayList<OfferModel> offers) {
		this.offers = offers;
	}

	/**
	* Returns value of offer
	* @return
	*/
	public OfferModel getOffer() {
		return offer;
	}

	/**
	* Sets new value of offer
	* @param
	*/
	public void setOffer(OfferModel offer) {
		this.offer = offer;
	}

	/**
	* Create string representation of Preference for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Preference [user=" + user + ", offers=" + offers + ", offer=" + offer + "]";
	}
}
