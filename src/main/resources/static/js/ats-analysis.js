let scoreChart = null;
let sectionChart = null;
let radarChart = null;

pdfjsLib.GlobalWorkerOptions.workerSrc =
"https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.worker.min.js";

const skillKeywords = [

"html",
"css",
"javascript",
"react",
"node",
"express",
"mongodb",
"mysql",
"sql",
"python",
"java",
"spring",
"spring boot",
"hibernate",
"microservices",
"docker",
"aws",
"azure",
"git",
"github",
"rest api",
"api",
"firebase",
"flutter",
"dart",
"angular",
"vue",
"machine learning",
"deep learning",
"nlp",
"data science",
"tensorflow",
"opencv",
"pandas",
"numpy"
];

document
.getElementById("resumeInput")
.addEventListener(
"change",
function(){

if(this.files.length > 0){

document.getElementById(
"fileName"
).innerText =
this.files[0].name;
}
}
);

async function analyzeResume(){

const file =
document.getElementById(
"resumeInput"
).files[0];

if(!file){

alert(
"Please upload a resume PDF"
);

return;
}

document.getElementById(
"loading"
).style.display =
"block";

const reader =
new FileReader();

reader.onload =
async function(){

try{

const typedArray =
new Uint8Array(
this.result
);

const pdf =
await pdfjsLib
.getDocument(
typedArray
)
.promise;

let text = "";

for(
let i=1;
i<=pdf.numPages;
i++
){

const page =
await pdf.getPage(i);

const content =
await page
.getTextContent();

content.items.forEach(
item => {

text +=
item.str + " ";
}
);
}

processResume(text);

}
catch(error){

console.error(error);

alert(
"Unable to analyze resume"
);

document
.getElementById(
"loading"
)
.style.display =
"none";
}
};

reader.readAsArrayBuffer(file);
}

function processResume(text){

text =
text.toLowerCase();

let detectedSkills =
skillKeywords.filter(
skill =>
text.includes(skill)
);

detectedSkills =
[...new Set(
detectedSkills
)];

const atsKeywords = [

"java",
"spring boot",
"spring security",
"hibernate",
"docker",
"microservices",
"aws",
"mongodb",
"sql",
"react",
"git",
"github",
"rest api"
];

const missingSkills =
atsKeywords.filter(
skill =>
!detectedSkills.includes(
skill
)
);

const hasEducation =
text.includes(
"education"
);

const hasProjects =
text.includes(
"project"
);

const hasExperience =
text.includes(
"experience"
) ||
text.includes(
"internship"
);

const hasSkills =
text.includes(
"skills"
);

let skillScore =
Math.min(
detectedSkills.length * 4,
100
);

let projectScore =
hasProjects ? 100 : 0;

let experienceScore =
hasExperience ? 100 : 0;

let educationScore =
hasEducation ? 100 : 0;

let sectionScore =
(
projectScore +
experienceScore +
educationScore
) / 3;

let atsScore =
Math.round(
(
skillScore +
sectionScore
) / 2
);

updateUI(

atsScore,

detectedSkills,

missingSkills,

{
skillScore,
projectScore,
experienceScore,
educationScore
}
);
}

function updateUI(

atsScore,

detectedSkills,

missingSkills,

sections

){

document
.getElementById(
"loading"
)
.style.display =
"none";

document
.getElementById(
"atsScore"
)
.innerText =
atsScore + "%";

document
.getElementById(
"skillsScore"
)
.innerText =
sections.skillScore + "%";

document
.getElementById(
"projectScore"
)
.innerText =
sections.projectScore + "%";

document
.getElementById(
"strengthScore"
)
.innerText =
Math.round(
(
atsScore +
sections.skillScore +
sections.projectScore
) / 3
) + "%";

drawCharts(
atsScore,
sections
);

renderSkills(
detectedSkills,
missingSkills
);

renderFeedback(
atsScore,
missingSkills,
sections
);

generateSummary(
atsScore,
detectedSkills,
missingSkills
);
}

function drawCharts(
atsScore,
sections
){

if(scoreChart)
scoreChart.destroy();

if(sectionChart)
sectionChart.destroy();

if(radarChart)
radarChart.destroy();

scoreChart =
new Chart(

document.getElementById(
"scoreChart"
),

{
type:"doughnut",

data:{
labels:[
"ATS Score",
"Remaining"
],

datasets:[
{
data:[
atsScore,
100 - atsScore
],

backgroundColor:[
"#60a5fa",
"#1e293b"
]
}
]
}
}
);

sectionChart =
new Chart(

document.getElementById(
"sectionChart"
),

{
type:"bar",

data:{
labels:[
"Skills",
"Projects",
"Experience",
"Education"
],

datasets:[
{
label:
"Resume Sections",

data:[
sections.skillScore,
sections.projectScore,
sections.experienceScore,
sections.educationScore
]
}
]
}
}
);

radarChart =
new Chart(

document.getElementById(
"radarChart"
),

{
type:"radar",

data:{
labels:[
"Skills",
"Projects",
"Experience",
"Education"
],

datasets:[
{
label:
"Resume Strength",

data:[
sections.skillScore,
sections.projectScore,
sections.experienceScore,
sections.educationScore
]
}
]
}
}
);
}

function renderSkills(
detected,
missing
){

document
.getElementById(
"skills"
)
.innerHTML =

detected.map(
skill =>
`<span>${skill}</span>`
).join("");

document
.getElementById(
"missing"
)
.innerHTML =

missing.map(
skill =>
`<span>${skill}</span>`
).join("");
}

function renderFeedback(

atsScore,

missingSkills,

sections

){

let feedback = "";

if(
sections.projectScore === 0
){

feedback +=

`<div class="feedback-item feedback-warn">
Add more projects
</div>`;
}

if(
sections.experienceScore === 0
){

feedback +=

`<div class="feedback-item feedback-warn">
Add internship or experience
</div>`;
}

if(
missingSkills.length > 0
){

feedback +=

`<div class="feedback-item feedback-warn">
Missing ATS keywords:
${missingSkills.join(", ")}
</div>`;
}

if(
atsScore >= 80
){

feedback +=

`<div class="feedback-item feedback-good">
Excellent ATS compatibility
</div>`;
}

document
.getElementById(
"feedback"
)
.innerHTML =
feedback;
}

function generateSummary(

atsScore,

detectedSkills,

missingSkills

){

let summary = "";

if(
atsScore >= 90
){

summary =
"Excellent ATS score. Your resume demonstrates strong technical skills, projects and industry-relevant content. It is highly optimized for modern Applicant Tracking Systems.";
}
else if(
atsScore >= 75
){

summary =
"Good ATS score. Your resume contains relevant technical skills and project experience. Adding more ATS keywords and measurable achievements can improve recruiter visibility.";
}
else{

summary =
"Your resume requires improvement. Include additional projects, internships, technical keywords and quantified achievements to increase ATS ranking.";
}

summary +=

" Detected " +
detectedSkills.length +
" technical skills. ";

if(
missingSkills.length > 0
){

summary +=

" Consider adding keywords such as " +
missingSkills.join(", ") +
" to improve ATS matching.";
}

document
.getElementById(
"analysis"
)
.innerText =
summary;
}

function goDashboard(){

window.location.href =
"/dashboard";
}
