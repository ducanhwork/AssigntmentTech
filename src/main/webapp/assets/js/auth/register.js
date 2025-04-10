// handle re-password
const password = document.getElementById("password");
const rePassword = document.getElementById("confirmPassword");
const passwordError = document.getElementById("errorMessage");

const form = document.forms.item(0);
form.addEventListener("submit", (event) => {
        event.preventDefault();
        const passwordValue = password.value;
        const rePasswordValue = rePassword.value;

        if (passwordValue !== rePasswordValue) {
            passwordError.innerText = "Passwords do not match";
            return;
        } else {
            passwordError.innerText = "";
        }

        // Submit the form
        form.submit();
    }
);
