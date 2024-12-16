
const BASE_URL = "http://localhost:8080"; // Replace with your backend API URL

// Login Function
async function handleLogin(event) {
    event.preventDefault(); // Prevent form submission

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const resultMessage = document.getElementById("resultMessage");

    // Clear previous messages
    if (resultMessage) resultMessage.remove();

    if (!email || !password) {
        displayMessage("Please provide both email and password.", "red");
        return;
    }

    try {
        // Send login request to the backend
        const response = await fetch(`${BASE_URL}/user/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                email: email,
                password: password,
            }),
        });

        if (response.ok) {
            const data = await response.json();

            // Extract token, role, name, and email from the response
            const token = data.token;
            const role = data.role;
            const userId = data.userId; // Assuming your response includes userId
            const userName = data.userName; // Assuming your response includes userName
            const userEmail = data.userEmail; // Assuming your response includes userEmail
            

            // Store user details and token in localStorage
            localStorage.setItem("jwtToken", token);
            localStorage.setItem("userRole", role);
            localStorage.setItem("userId", userId);
            localStorage.setItem("userName", userName);
            localStorage.setItem("userEmail", userEmail);
           

            // Redirect to the dashboard or a role-specific page
            if (role === "ADMIN") {
                window.location.href = "admin-dashboard.html";
            } else if (role === "INSTRUCTOR") {
                window.location.href = "instructor-dashboard.html";
            } else if (role === "STUDENT") {
                window.location.href = "student-dashboard.html";
            } else {
                displayMessage("Unknown role detected.", "red");
            }
        } else {
            const errorText = await response.text();
            displayMessage(errorText || "Invalid credentials. Please try again.", "red");
        }
    } catch (error) {
        console.error("Error during login:", error);
        displayMessage("An error occurred while logging in. Please try again later.", "red");
    }
}

// Display a message below the form
function displayMessage(message, color) {
    const messageElement = document.createElement("p");
    messageElement.textContent = message;
    messageElement.style.color = color;
    messageElement.id = "resultMessage";

    const form = document.querySelector("form");
    form.appendChild(messageElement);
}

// Attach event listener to the login button
document.getElementById("login").addEventListener("click", handleLogin);
