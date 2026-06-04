
window.onload = function () {

    loadParsedResume();

    bindEvents();

    updatePreview();
};

// ============================
// LOAD PARSED RESUME
// ============================

function loadParsedResume() {

    const storedResume =
        localStorage.getItem(
            "parsedResume"
        );

    if (!storedResume) {
        return;
    }

    const resume =
        JSON.parse(
            storedResume
        );

    setValue(
        "fullName",
        resume.fullName
    );

    setValue(
        "email",
        resume.email
    );

    setValue(
        "phone",
        resume.phone
    );

    setValue(
        "linkedin",
        resume.linkedin
    );

    setValue(
        "github",
        resume.github
    );

    setValue(
        "location",
        resume.location
    );

    setValue(
        "summary",
        resume.summary
    );

    setValue(
        "education",
        resume.education
    );

    setValue(
        "skills",
        resume.skills
    );

    setValue(
        "certifications",
        resume.certifications
    );

    setValue(
        "projects",
        resume.projects
    );

    setValue(
        "activities",
        resume.activities
    );

    updatePreview();
}

// ============================
// SET VALUE
// ============================

function setValue(id, value) {

    const field =
        document.getElementById(id);

    if (field && value) {

        field.value = value;
    }
}

// ============================
// GET VALUE
// ============================

function getValue(id) {

    const field =
        document.getElementById(id);

    return field
        ? field.value
        : "";
}

// ============================
// LIMIT TEXT
// ============================

function limitText(
    text,
    maxLength
) {

    if (!text)
        return "";

    if (text.length <= maxLength)
        return text;

    return text.substring(
        0,
        maxLength
    ) + "...";
}

// ============================
// BIND EVENTS
// ============================

function bindEvents() {

    const fields = [

        "fullName",
        "email",
        "phone",
        "linkedin",
        "github",
        "location",
        "summary",
        "education",
        "skills",
        "certifications",
        "projects",
        "activities"
    ];

    fields.forEach(id => {

        const field =
            document.getElementById(id);

        if (field) {

            field.addEventListener(
                "input",
                updatePreview
            );
        }
    });

    const photoUpload =
        document.getElementById(
            "photoUpload"
        );

    if (photoUpload) {

        photoUpload.addEventListener(
            "change",
            uploadPhoto
        );
    }
}

// ============================
// PHOTO UPLOAD
// ============================

function uploadPhoto(event) {

    const file =
        event.target.files[0];

    if (!file) {
        return;
    }

    const reader =
        new FileReader();

    reader.onload =
        function (e) {

            document
                .getElementById(
                    "previewImage"
                )
                .src =
                e.target.result;
        };

    reader.readAsDataURL(
        file
    );
}

// ============================
// UPDATE PREVIEW
// ============================

function updatePreview() {

    document.getElementById(
        "pName"
    ).innerText =

        getValue(
            "fullName"
        ) || "XXXX YYYY";

    document.getElementById(
        "pContact"
    ).innerHTML =

        (getValue(
            "email"
        ) || "email@example.com")

        +

        "<br>"

        +

        (getValue(
            "phone"
        ) || "+91 XXXXX XXXXX");

    document.getElementById(
        "pGithub"
    ).innerText =

        getValue(
            "github"
        ) || "github.com/username";

    document.getElementById(
        "pLinkedin"
    ).innerText =

        getValue(
            "linkedin"
        ) || "linkedin.com/in/username";

    document.getElementById(
        "pLocation"
    ).innerText =

        getValue(
            "location"
        ) || "City, Country";

    document.getElementById(
        "pSummary"
    ).innerText =

        limitText(
            getValue(
                "summary"
            ),
            500
        ) ||

        "Write your professional summary here...";

    document.getElementById(
        "pEducation"
    ).innerText =

        limitText(
            getValue(
                "education"
            ),
            700
        ) ||

        "Enter your education details...";

    document.getElementById(
        "pSkills"
    ).innerText =

        limitText(
            getValue(
                "skills"
            ),
            500
        ) ||

        "Enter your skills...";

    document.getElementById(
        "pCertifications"
    ).innerText =

        limitText(
            getValue(
                "certifications"
            ),
            400
        ) ||

        "Enter certifications...";

    document.getElementById(
        "pProjects"
    ).innerText =

        limitText(
            getValue(
                "projects"
            ),
            800
        ) ||

        "Enter project details...";

    document.getElementById(
        "pActivities"
    ).innerText =

        limitText(
            getValue(
                "activities"
            ),
            300
        ) ||

        "Enter extracurricular activities...";
}

// ============================
// SAVE RESUME
// ============================

async function saveResume() {

    const resume = {

        fullName:
            getValue(
                "fullName"
            ),

        email:
            getValue(
                "email"
            ),

        phone:
            getValue(
                "phone"
            ),

        linkedin:
            getValue(
                "linkedin"
            ),

        github:
            getValue(
                "github"
            ),

        location:
            getValue(
                "location"
            ),

        summary:
            getValue(
                "summary"
            ),

        education:
            getValue(
                "education"
            ),

        skills:
            getValue(
                "skills"
            ),

        certifications:
            getValue(
                "certifications"
            ),

        projects:
            getValue(
                "projects"
            ),

        activities:
            getValue(
                "activities"
            )
    };

    try {

        const response =
            await fetch(
                "/api/resume/save",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(
                            resume
                        )
                }
            );

        if (response.ok) {

            alert(
                "Resume Saved Successfully"
            );
        }
        else {

            alert(
                "Failed To Save Resume"
            );
        }

    } catch (error) {

        console.error(
            error
        );

        alert(
            "Server Error"
        );
    }
}

// ============================
// DOWNLOAD PDF
// ============================

function downloadResume() {

    const element =
        document.getElementById(
            "resumePreview"
        );

    html2pdf()
        .from(element)
        .set({

            margin: 0,

            filename:
                "CareerAI_Resume.pdf",

            image: {
                type: "jpeg",
                quality: 1
            },

            html2canvas: {
                scale: 2
            },

            jsPDF: {
                unit: "mm",
                format: "a4",
                orientation:
                    "portrait"
            }

        })
        .save();
}

// ============================
// CLEAR RESUME
// ============================

function clearResume() {

    if (!confirm(
        "Clear all details?"
    )) {

        return;
    }

    document
        .querySelectorAll(
            "input, textarea"
        )
        .forEach(field => {

            if (
                field.type !== "file"
            ) {

                field.value = "";
            }
        });

    localStorage.removeItem(
        "parsedResume"
    );

    document
        .getElementById(
            "previewImage"
        )
        .src =
        "/images/default-profile.png";

    updatePreview();
}

// ============================
// DASHBOARD
// ============================

function goDashboard() {

    window.location.href =
        "/dashboard";
}
