document
    .getElementById("loginForm")
    .addEventListener("submit", async function (e) {

        e.preventDefault();

        const email =
            document.getElementById("email").value.trim();

        const password =
            document.getElementById("password").value;

        try {

            const response = await fetch(
                "/api/auth/login",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        email,
                        password
                    })
                }
            );

            const data = await response.json();

            if (response.ok && data.token) {

                // Save token
                localStorage.setItem(
                    "token",
                    data.token
                );

                // Save email for analytics
                localStorage.setItem(
                    "email",
                    email
                );

                // Optional backup key
                localStorage.setItem(
                    "userEmail",
                    email
                );

                alert("Login Successful!");

                window.location.href =
                    "/dashboard";

            } else {

                alert(
                    data.message ||
                    "Invalid email or password"
                );
            }

        } catch (error) {

            console.error(error);

            alert(
                "Server error. Please try again."
            );
        }
    });