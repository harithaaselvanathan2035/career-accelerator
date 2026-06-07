// ==========================================
// GLOBAL VARIABLES
// ==========================================

let currentQuestion = 0;
let questions = [];

let timer = 60;
let timerInterval = null;

let totalScore = 0;

// ==========================================
// DASHBOARD
// ==========================================

function goDashboard() {

    window.location.href = "/dashboard";

}

// ==========================================
// START INTERVIEW
// ==========================================

async function startInterview() {

    try {

        const requestData = {

            domain:
                document.getElementById("domain").value,

            difficulty:
                document.getElementById("difficulty").value,

            questionCount:
                parseInt(
                    document.getElementById(
                        "questionCount"
                    ).value
                )

        };

        const response =
            await fetch(
                "/api/interview/start",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(
                            requestData
                        )
                }
            );

        if (!response.ok) {

            throw new Error(
                "Failed to load questions"
            );

        }

        questions =
            await response.json();

        if (
            !questions ||
            questions.length === 0
        ) {

            alert(
                "No interview questions found."
            );

            return;
        }

        currentQuestion = 0;
        totalScore = 0;

        document.getElementById(
            "liveScore"
        ).innerText = "0";

        loadQuestion();

        startTimer();

    }
    catch (error) {

        console.error(error);

        alert(
            "Unable to start interview."
        );

    }

}

// ==========================================
// LOAD QUESTION
// ==========================================

function loadQuestion() {

    if (
        !questions[currentQuestion]
    ) {
        return;
    }

    const question =
        questions[currentQuestion]
            .question;

    document.getElementById(
        "questionNumber"
    ).innerText =
        `Question ${currentQuestion + 1}
        / ${questions.length}`;

    typeQuestion(question);

    updateProgressBar();

}

// ==========================================
// TYPING EFFECT
// ==========================================

function typeQuestion(text) {

    const element =
        document.getElementById(
            "questionText"
        );

    element.innerHTML = "";

    let index = 0;

    const typing =
        setInterval(() => {

            element.innerHTML +=
                text.charAt(index);

            index++;

            if (
                index >= text.length
            ) {

                clearInterval(
                    typing
                );

            }

        }, 20);

}

// ==========================================
// PROGRESS BAR
// ==========================================

function updateProgressBar() {

    const progress =

        (
            (currentQuestion + 1)
            /
            questions.length
        ) * 100;

    document.getElementById(
        "progressBar"
    ).style.width =
        progress + "%";

}

// ==========================================
// TIMER
// ==========================================

function startTimer() {

    clearInterval(
        timerInterval
    );

    timer = 60;

    document.getElementById(
        "timer"
    ).innerText = timer;

    timerInterval =
        setInterval(() => {

            timer--;

            document.getElementById(
                "timer"
            ).innerText = timer;

            if (timer <= 0) {

                nextQuestion();

            }

        }, 1000);

}

// ==========================================
// NEXT QUESTION
// ==========================================

function nextQuestion() {

    document.getElementById(
        "answerBox"
    ).value = "";

    if (
        currentQuestion <
        questions.length - 1
    ) {

        currentQuestion++;

        loadQuestion();

        startTimer();

    }
    else {

        interviewCompleted();

    }

}

// ==========================================
// EVALUATE ANSWER
// ==========================================

async function evaluateAnswer() {

    const answer =
        document.getElementById(
            "answerBox"
        ).value;

    if (
        answer.trim() === ""
    ) {

        alert(
            "Please enter your answer."
        );

        return;

    }

    try {

        const response =
            await fetch(
                "/api/interview/evaluate",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify({
                            answer: answer
                        })
                }
            );

        if (!response.ok) {

            throw new Error(
                "Evaluation failed"
            );

        }

        const result =
            await response.json();

        document.getElementById(
            "feedbackBox"
        ).innerHTML =

            `
            <strong>Score:</strong>
            ${result.score}/100
            <br><br>
            <strong>Feedback:</strong>
            ${result.feedback}
            `;

        totalScore += result.score;

        const average = Math.round(
            totalScore /
            (currentQuestion + 1)
        );

        document.getElementById(
            "liveScore"
        ).innerText = average;

    }
    catch (error) {

        console.error(error);

        alert(
            "Evaluation failed."
        );

    }

}

// ==========================================
// VOICE INPUT
// ==========================================

function startVoice() {

    if (
        !(
            'webkitSpeechRecognition'
            in window
        )
    ) {

        alert(
            "Voice recognition not supported."
        );

        return;

    }

    const recognition =
        new webkitSpeechRecognition();

    recognition.lang = "en-US";

    recognition.continuous = false;

    recognition.interimResults = false;

    recognition.start();

    recognition.onresult =
        function (event) {

            const transcript =

                event.results[0][0]
                    .transcript;

            document.getElementById(
                "answerBox"
            ).value +=

                transcript + " ";

        };

    recognition.onerror =
        function (event) {

            console.error(
                event.error
            );

        };

}

// ==========================================
// INTERVIEW COMPLETE
// ==========================================

function interviewCompleted() {

    clearInterval(
        timerInterval
    );

    const finalScore =
        Math.round(
            totalScore /
            questions.length
        );

    document.getElementById(
        "questionText"
    ).innerHTML =

        `
        <h2>
            🎉 Interview Completed
        </h2>

        <br>

        <h3>
            Final Score:
            ${finalScore}/100
        </h3>

        <br>

        <p>
            Great work completing
            the AI Mock Interview.
        </p>
        `;

    document.getElementById(
        "questionNumber"
    ).innerText =
        `${questions.length}
         / ${questions.length}`;

    document.getElementById(
        "feedbackBox"
    ).innerHTML =

        `
        Interview Finished Successfully.
        Review your performance and
        continue practicing.
        `;

    document.getElementById(
        "progressBar"
    ).style.width = "100%";

}

// ==========================================
// AUTO SAVE ANSWER (OPTIONAL)
// ==========================================

document.addEventListener(
    "DOMContentLoaded",
    () => {

        const answerBox =
            document.getElementById(
                "answerBox"
            );

        if (answerBox) {

            answerBox.addEventListener(
                "input",
                () => {

                    localStorage.setItem(
                        "mockAnswer",
                        answerBox.value
                    );

                }
            );

            answerBox.value =
                localStorage.getItem(
                    "mockAnswer"
                ) || "";

        }

    }
);