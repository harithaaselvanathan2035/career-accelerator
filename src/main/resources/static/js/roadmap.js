async function generateRoadmap() {

    const role =
        document.getElementById("role")
        .value
        .trim();

    const level =
        document.getElementById("level")
        .value;

    if (!role) {

        alert("Please enter a domain");

        return;
    }

    const roadmapContainer =
        document.getElementById("roadmapResult");

    roadmapContainer.innerHTML = `

        <div class="loading">

            <div class="spinner"></div>

            <p>
                Generating Roadmap...
            </p>

        </div>

    `;

    try {

        const response = await fetch(
            "/api/roadmap/generate",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({

                    domain: role,
                    level: level

                })
            }
        );

        if (!response.ok) {

            throw new Error(
                "Failed to fetch roadmap"
            );
        }

        const data =
            await response.json();

        console.log("API Response:", data);

        let roadmapSteps = [];

        if (Array.isArray(data.roadmap)) {

            roadmapSteps = data.roadmap;

        } else if (typeof data.roadmap === "string") {

            roadmapSteps = data.roadmap
                .split(/,|\n/)
                .map(step => step.trim())
                .filter(step => step.length > 0);

        } else {

            throw new Error(
                "Invalid roadmap format"
            );
        }

        let html = `

            <div class="roadmap-header">

                <h2>
                    ${role} Roadmap
                </h2>

                <p>
                    ${level} Level Learning Path
                </p>

            </div>

        `;

        roadmapSteps.forEach((step, index) => {

            html += `

                <div class="step">

                    <div class="step-number">

                        ${index + 1}

                    </div>

                    <div class="step-content">

                        <h3>

                            ${step}

                        </h3>

                        <p>

                            Complete this step
                            before moving to
                            the next phase.

                        </p>

                    </div>

                </div>

            `;
        });

        roadmapContainer.innerHTML = html;

        roadmapContainer.scrollIntoView({

            behavior: "smooth"

        });

    } catch (error) {

        console.error(error);

        roadmapContainer.innerHTML = `

            <div class="error-box">

                Failed to generate roadmap.<br>

                ${error.message}

            </div>

        `;
    }
}

window.onload = () => {

    document
        .getElementById("role")
        .focus();
};