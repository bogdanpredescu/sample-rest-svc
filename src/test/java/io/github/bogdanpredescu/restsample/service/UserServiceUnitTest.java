package io.github.bogdanpredescu.restsample.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.bogdanpredescu.restsample.models.User;
import io.github.bogdanpredescu.service.DBService;
import io.github.bogdanpredescu.service.UserService;
import io.github.bogdanpredescu.service.UserServiceImp;

class UserServiceUnitTest{
		
	@BeforeAll
	public static void setUp()
	{
		DBService.start();
	}
   
	@Test
	void shouldStoreUserInfo() {
    	    	
        // Given
    	UserService userSvc = new UserServiceImp();

        // When
        userSvc.createUser("gibec", "Gigel", "gi@bec.com");
        
        User user = DBService.getDbSession().get(User.class, "gibec");
    	
        // Then
        if (user != null)
        {
        	assertTrue(user.getName().equals("Gigel") && user.getEmail().equals("gi@bec.com"));
        }
        else
        {
        	fail("user not found in database");
        }
    }
    
    @AfterAll
    public static void tearDown()
    {
    	DBService.stop();
    }
}