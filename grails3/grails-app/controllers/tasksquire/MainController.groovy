package tasksquire

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils;

@Secured(['ROLE_USER'])
class MainController {

	def springSecurityService

    def index() {
		redirect(controller: 'user', action: 'show', id: springSecurityService.getPrincipal().id)
	}

	def taskbar() {
		def users = SpringSecurityUtils.ifAllGranted("ROLE_ADMIN") ? User.list() : null
		
		render template:'taskbar', model:[user:request.getAttribute("user"), users: users, categories: Category.list()]
	}

}
