import { initBackgroundChanger } from "./randomBackground.js"

document.addEventListener('DOMContentLoaded', () => {
	
	const isLoginPage = document.body.id === 'loginPage'
	
	if(isLoginPage){
		initBackgroundChanger()
	}
});