package com.beerbuddy.core.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_profile")
public class UserProfile implements User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    protected String name;
    
    protected String email;
    
    @OneToOne
    @JoinColumn(name="user_id")
    protected DefaultUser user;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    protected Set<UserBeerRank> beerRankings;

    public UserProfile() {
	}
    
    public UserProfile(UserProfile profile) {
    	setProfile(profile);
    }
    
    @Override
    public Long getProfileId() {
    	return id;
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<UserBeerRank> getBeerRankings() {
		return beerRankings;
	}
	public void setBeerRankings(Set<UserBeerRank> beerRankings) {
		this.beerRankings = beerRankings;
	}

	@SuppressWarnings("unused")
	//do not want to expose the user object directly
	private DefaultUser getUser() {
		return user;
	}
	public void setUser(User user) {
		if( user instanceof DefaultUser ) {
			this.user = (DefaultUser) user;
		} else if ( user instanceof UserWrapper ){
			while(! (user instanceof DefaultUser) && user != null ) {
				user = ((UserWrapper) user).getUser();
			}
			if( user != null ) {
				this.user = (DefaultUser) user;
			}
		}
	}
	
	@Override
	public String getUsername() {
		if(user != null ) {
			return user.getUsername();
		}
		return null;
	}
	@Override
	public Date getLastLogin() {
		if(user != null ) {
			return user.getLastLogin();
		}
		return null;
	}
	
	@Override
	public void setProfile(UserProfile profile) {
    	this.id = profile.id;
    	this.email = profile.email;
    	this.name = profile.name;
    	this.user = profile.user;
	}
	
}