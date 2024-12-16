const BASE_URL = "http://localhost:8080"; // Replace with your backend URL

// Function to fetch enrolled courses for the logged-in user
async function fetchEnrolledCourses() {
    try {
        // Get logged-in user's ID from localStorage
        const userId = localStorage.getItem("userId");
        if (!userId) {
            alert("User is not logged in. Redirecting to login page.");
            window.location.href = "login.html";
            return;
        }

        // Make API call to fetch enrolled courses
        const response = await fetch(`${BASE_URL}/courses/enrolled/${userId}`);
        if (!response.ok) {
            throw new Error("Failed to fetch enrolled courses.");
        }

        const courses = await response.json();
        const tableBody = document.getElementById("coursesTableBody");

        // Clear any existing rows
        tableBody.innerHTML = "";

        // Populate the table with courses
        courses.forEach(course => {
            const row = `
                <tr>
                    <td>${course.id}</td>
                    <td>${course.title}</td>
                    <td>${course.description}</td>
                    <td>${course.duration}</td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error("Error fetching enrolled courses:", error);
        alert("An error occurred while fetching your courses.");
    }
}

// Call the function on page load
document.addEventListener("DOMContentLoaded", fetchEnrolledCourses);
