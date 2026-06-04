async function searchJobs() {

    const query =
        document.getElementById("jobQuery").value;

    const location =
        document.getElementById("jobLocation").value;

    try {

        const response =
            await fetch(
                `/api/jobs/search?query=${encodeURIComponent(query)}&location=${encodeURIComponent(location)}`
            );

        const data =
            await response.json();

        const results =
            document.getElementById("jobResults");

        results.innerHTML = "";

        if (!data.data || data.data.length === 0) {

            results.innerHTML =
                "<h2>No Jobs Found</h2>";

            return;
        }

        data.data.forEach(job => {

            results.innerHTML += `

            <div class="job-card">

                <img
                    src="${job.employer_logo || ''}"
                    class="logo">

                <h2>${job.job_title}</h2>

                <h3>${job.employer_name}</h3>

                <p>
                    📍 ${job.job_location}
                </p>

                <p>
                    💼 ${job.job_employment_type}
                </p>

                <p>
                    🕒 ${job.job_posted_at}
                </p>

                <a
                    href="${job.job_apply_link}"
                    target="_blank"
                    class="apply-btn">

                    Apply Now

                </a>

            </div>

            `;
        });

    } catch(error) {

        console.error(error);

        alert("Unable to fetch jobs");

    }
}
function goDashboard(){

    window.location.href = "/dashboard";

}