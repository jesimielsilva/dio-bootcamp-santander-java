export function formSubmit(){
    document.getElementById("loginForm").addEventListener('submit', (event) => {
        event.preventDefault()
        window.location.href = "../../index.html"
    })
}