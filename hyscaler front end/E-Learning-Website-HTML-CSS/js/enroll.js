const BASE_URL = "http://localhost:8080";
const userId = localStorage.getItem("userId"); // Get logged-in user's ID from localStorage


async function fetchCourses() {
    try {
        const response = await fetch(`${BASE_URL}/course/all`);
        if (!response.ok) throw new Error("Failed to fetch courses.");

        const courses = await response.json();
        const courseSelect = document.getElementById("courseSelect");

        courses.forEach(course => {
            const option = document.createElement("option");
            option.value = course.id;
            option.textContent = `${course.title} (${course.duration} weeks)`;
            courseSelect.appendChild(option);
        });
    } catch (error) {
        console.error("Error fetching courses:", error);
    }
}
async function enrollInCourse(event) {
    event.preventDefault(); 

    const courseId = document.getElementById("courseSelect").value;

    try {
        const response = await fetch(`${BASE_URL}/course/${courseId}/students/${userId}`, {
            method: "POST",
        });

        if (response.ok) {
            alert("Successfully enrolled in the course!");
        } else {
            throw new Error("Failed to enroll in the course.");
        }
    } catch (error) {
        console.error("Error enrolling in course:", error);
        alert("An error occurred. Please try again.");
    }
}


document.addEventListener("DOMContentLoaded", fetchCourses);


document.getElementById("enrollForm").addEventListener("submit", enrollInCourse);
