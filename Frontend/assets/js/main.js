import { initBackgroundChanger } from "./randomBackground.js"
import { formSubmit } from "./loginFormSubmit.js"

document.addEventListener('DOMContentLoaded', () => {
	
	const isLoginPage = document.body.id === 'loginPage'
	
	if(isLoginPage){
		initBackgroundChanger()
		formSubmit()
	}
});