package racetrack

import grails.test.*

class UserControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIndex() {
		controller.index()
		assertEquals "list", controller.redirectArgs["action"]
	}
	
	void testShow(){
		def jdoe = new User(login:"jdoe")
		def suziq = new User(login:"suziq")
		mockDomain(User, [jdoe, suziq])
		
		controller.params.id = 2
		def map = controller.show()
		assertEquals "suziq", map.userInstance.login
	}
	
	void testAuthenticate(){
		def jdoe = new User(login:"jdoe", password:"password")
		mockDomain(User, [jdoe])
		
		controller.params.login = "jdoe"
		controller.params.password = "password"
		controller.authenticate()
		
		assertNotNull controller.session.user
		assertEquals "jdoe", controller.session.user.login
		
		controller.params.password = "foo"
		controller.authenticate()
		
		assertTrue controller.flash.message.startsWith("Sorry, jdoe")
	}
}
