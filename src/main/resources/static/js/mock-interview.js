let currentQuestion = 0;
let questions = [];
let timer = 60;
let timerInterval;

function goDashboard() {
    window.location.href = "/dashboard";
}

async function startInterview() {

    try {

        const requestData = {
            domain: document.getElementById("domain").value,
            difficulty: document.getElementById("difficulty").value,
            questionCount: parseInt(
                document.getElementById("questionCount").value
            )
        };

        console.log("Sending Request:", requestData);

        const response = await fetch(
            "/api/interview/start",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestData)
            }
        );

        console.log("Response Status:", response.status);

        if (!response.ok) {
            throw new Error(
                `Server Error: ${response.status}`
            );
        }

        questions = await response.json();

        console.log("Questions Received:", questions);

        if (!questions || questions.length === 0) {

            alert(
                "No interview questions found.\n\nCheck database records and selected filters."
            );

            return;
        }

        currentQuestion = 0;

        loadQuestion();

        startTimer();

        document.getElementById("interviewSection")
            ?.classList.remove("hidden");

    }
    catch (error) {

        console.error("Interview Error:", error);

        alert(
            "Failed to start interview.\nCheck browser console for details."
        );
    }
}

function loadQuestion() {

    if (
        !questions ||
        questions.length === 0 ||
        !questions[currentQuestion]
    ) {

        console.error(
            "Question not available",
            questions
        );

        return;
    }

    document.getElementById("questionText").innerText =
        questions[currentQuestion].question;

    document.getElementById("questionNumber").innerText =
        `Question ${currentQuestion + 1} / ${questions.length}`;
}

function nextQuestion() {

    if (currentQuestion < questions.length - 1) {

        currentQuestion++;

        loadQuestion();

        document.getElementById("answerBox").value = "";

        timer = 60;
    }
    else {

        clearInterval(timerInterval);

        alert(
            "🎉 Interview Completed Successfully!"
        );

        document.getElementById("questionText").innerText =
            "Interview Completed";

        document.getElementById("questionNumber").innerText =
            `${questions.length}/${questions.length}`;
    }
}

function startTimer() {

    clearInterval(timerInterval);

    timer = 60;

    document.getElementById("timer").innerText = timer;

    timerInterval = setInterval(() => {

        timer--;

        document.getElementById("timer").innerText = timer;

        if (timer <= 0) {

            nextQuestion();

            timer = 60;
        }

    }, 1000);
}

function startVoice() {

    if (!('webkitSpeechRecognition' in window)) {

        alert(
            "Voice recognition not supported in this browser."
        );

        return;
    }

    const recognition =
        new webkitSpeechRecognition();

    recognition.lang = "en-US";

    recognition.start();

    recognition.onresult = (event) => {

        const transcript =
            event.results[0][0].transcript;

        document.getElementById("answerBox").value +=
            transcript + " ";
    };

    recognition.onerror = (event) => {

        console.error(
            "Speech Recognition Error:",
            event.error
        );
    };
}