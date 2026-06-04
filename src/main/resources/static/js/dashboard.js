// ==========================
// USER NAME
// ==========================

const userName = localStorage.getItem("userName");

if (userName && document.getElementById("userName")) {
    document.getElementById("userName").innerText = userName;
}

// ==========================
// LOGOUT
// ==========================

function logout() {
    localStorage.removeItem("userName");
    localStorage.removeItem("email");
    localStorage.removeItem("token");

    window.location.href = "/login";
}

// ==========================
// THEME TOGGLE
// ==========================

document
    .getElementById("themeBtn")
    .addEventListener("click", () => {

        document.body.classList.toggle("dark-mode");

        const themeBtn =
            document.getElementById("themeBtn");

        if (document.body.classList.contains("dark-mode")) {
            themeBtn.innerHTML = "☀️";
        } else {
            themeBtn.innerHTML = "🌙";
        }
    });

// ==========================
// CAREER PROGRESS CHART
// ==========================

const progressCanvas =
    document.getElementById("progressChart");

if (progressCanvas) {

    new Chart(progressCanvas, {
        type: "line",

        data: {
            labels: [
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun"
            ],

            datasets: [
                {
                    label: "Career Growth",

                    data: [
                        45,
                        55,
                        68,
                        78,
                        89,
                        95
                    ],

                    borderColor: "#60a5fa",

                    backgroundColor:
                        "rgba(96,165,250,0.2)",

                    tension: 0.4,

                    fill: true
                }
            ]
        },

        options: {
            responsive: true,
            maintainAspectRatio: false,

            plugins: {
                legend: {
                    labels: {
                        color: "white"
                    }
                }
            },

            scales: {
                x: {
                    ticks: {
                        color: "white"
                    }
                },
                y: {
                    ticks: {
                        color: "white"
                    }
                }
            }
        }
    });
}

// ==========================
// ROADMAP CHART
// ==========================

const roadmapCanvas =
    document.getElementById("roadmapChart");

if (roadmapCanvas) {

    new Chart(roadmapCanvas, {
        type: "doughnut",

        data: {
            labels: [
                "Completed",
                "Pending"
            ],

            datasets: [
                {
                    data: [
                        75,
                        25
                    ],

                    backgroundColor: [
                        "#22c55e",
                        "#f59e0b"
                    ]
                }
            ]
        },

        options: {
            responsive: true,
            maintainAspectRatio: false,

            plugins: {
                legend: {
                    labels: {
                        color: "white"
                    }
                }
            }
        }
    });
}

// ==========================
// PROFILE COMPLETION
// ==========================

async function loadProfileCompletion() {

    const email =
        localStorage.getItem("email");

    if (!email) return;

    try {

        const response =
            await fetch(`/api/profile/${email}`);

        if (!response.ok) return;

        const profile =
            await response.json();

        let completed = 0;
        let total = 12;

        const fields = [
            profile.phone,
            profile.profession,
            profile.currentRole,
            profile.skills,
            profile.location,
            profile.linkedin,
            profile.github,
            profile.portfolio,
            profile.education,
            profile.college,
            profile.website,
            profile.aboutMe
        ];

        fields.forEach(field => {

            if (
                field &&
                field.trim() !== ""
            ) {
                completed++;
            }
        });

        const percentage =
            Math.round(
                (completed / total) * 100
            );

        const cards =
            document.querySelectorAll(".card h1");

        if (cards.length >= 5) {
            cards[4].innerText =
                percentage + "%";
        }

    } catch (error) {

        console.error(error);

    }
}

loadProfileCompletion();

// ==========================
// CARD ANIMATION
// ==========================

function animateCards() {

    const cards =
        document.querySelectorAll(".card h1");

    cards.forEach(card => {

        card.style.opacity = "0";

        setTimeout(() => {

            card.style.opacity = "1";

            card.style.transition =
                "all .8s ease";

        }, 300);

    });

}

animateCards();

// ==========================
// GREETING
// ==========================

const currentHour =
    new Date().getHours();

let greeting = "Welcome";

if (currentHour < 12) {
    greeting = "Good Morning";
}
else if (currentHour < 17) {
    greeting = "Good Afternoon";
}
else {
    greeting = "Good Evening";
}

const heading =
    document.querySelector(".topbar h1");

if (heading) {

    heading.innerHTML = `
        ${greeting},
        <span id="userName">
            ${userName || "User"}
        </span>
        👋
    `;
}