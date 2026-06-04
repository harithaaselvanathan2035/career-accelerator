const email = localStorage.getItem("email");
const userName = localStorage.getItem("userName");

const displayName = document.getElementById("displayName");
const displayEmail = document.getElementById("displayEmail");

displayName.innerText = userName || "Career AI User";
displayEmail.innerText = email || "user@email.com";

document.getElementById("fullName").value =
    userName || "";

document.getElementById("email").value =
    email || "";

document
.getElementById("dashboardBtn")
.addEventListener("click",()=>{

    window.location.href="/dashboard";

});

document
.getElementById("editBtn")
.addEventListener("click",()=>{

    document
    .querySelectorAll(
        "input,textarea,select"
    )
    .forEach(field=>{

        if(field.id !== "email"){
            field.disabled = false;
        }

    });

    document
    .getElementById("saveBtn")
    .style.display="inline-block";

});

document
.getElementById("photoUpload")
.addEventListener(
    "change",
    function(){

        const file =
            this.files[0];

        if(!file) return;

        const reader =
            new FileReader();

        reader.onload =
            function(e){

                document
                .getElementById(
                    "profileImage"
                )
                .src =
                e.target.result;

            };

        reader.readAsDataURL(file);

    });

document
.getElementById("saveBtn")
.addEventListener(
    "click",
    async()=>{

        const profileData = {

            email,

            phone:
            document.getElementById("phone").value,

            profession:
            document.getElementById("profession").value,

            currentRole:
            document.getElementById("currentRole").value,

            skills:
            document.getElementById("skills").value,

            location:
            document.getElementById("location").value,

            linkedin:
            document.getElementById("linkedin").value,

            github:
            document.getElementById("github").value,

            portfolio:
            document.getElementById("portfolio").value,

            experience:
            document.getElementById("experience").value,

            education:
            document.getElementById("education").value,

            college:
            document.getElementById("college").value,

            website:
            document.getElementById("website").value,

            aboutMe:
            document.getElementById("aboutMe").value,

            profilePhoto:
            document.getElementById("profileImage").src
        };

        try{

            const response =
            await fetch(
                "/api/profile/update",
                {
                    method:"PUT",
                    headers:{
                        "Content-Type":
                        "application/json"
                    },
                    body:
                    JSON.stringify(
                        profileData
                    )
                });

            if(response.ok){

                alert(
                    "Profile Updated Successfully"
                );

                document
                .querySelectorAll(
                    "input,textarea,select"
                )
                .forEach(field=>{

                    field.disabled = true;

                });

                document
                .getElementById("saveBtn")
                .style.display="none";

            }else{

                alert(
                    "Failed To Update Profile"
                );

            }

        }catch(error){

            console.error(error);

            alert("Server Error");

        }

    });

async function loadProfile(){

    if(!email) return;

    try{

        const response =
        await fetch(
            `/api/profile/${email}`
        );

        if(!response.ok) return;

        const data =
        await response.json();

        document.getElementById("phone").value =
        data.phone || "";

        document.getElementById("profession").value =
        data.profession || "";

        document.getElementById("currentRole").value =
        data.currentRole || "";

        document.getElementById("skills").value =
        data.skills || "";

        document.getElementById("location").value =
        data.location || "";

        document.getElementById("linkedin").value =
        data.linkedin || "";

        document.getElementById("github").value =
        data.github || "";

        document.getElementById("portfolio").value =
        data.portfolio || "";

        document.getElementById("experience").value =
        data.experience || "";

        document.getElementById("education").value =
        data.education || "";

        document.getElementById("college").value =
        data.college || "";

        document.getElementById("website").value =
        data.website || "";

        document.getElementById("aboutMe").value =
        data.aboutMe || "";

        if(data.profilePhoto){

            document
            .getElementById(
                "profileImage"
            )
            .src =
            data.profilePhoto;

        }

    }catch(error){

        console.error(error);

    }

}

loadProfile();