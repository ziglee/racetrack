package racetrack

class AdminFilters {

    def filters = {
        all(controller:'*', action:'(create|edit|update|delete|save)') {
            before = {
                if(!session?.user?.admin){
					flash.message = "Sorry, admin only"
					redirect(controller:"race", action:"list")
					return false
				}
            }
        }
    }
    
}
