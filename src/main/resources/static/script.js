document.addEventListener("DOMContentLoaded", function () {
    let toggles = document.querySelectorAll(".toggle-btn");
    let copyButtons = document.querySelectorAll(".copy-btn");

    // Accordion Toggle
    toggles.forEach(btn => {
        btn.addEventListener("click", function () {
            const content = this.parentElement.nextElementSibling;
            const isOpen = content.style.display === "block";

            // Close all other accordions
            document.querySelectorAll(".accordion-content").forEach(item => {
                item.style.display = "none";
            });
            document.querySelectorAll(".toggle-btn").forEach(button => {
                button.textContent = "+";
            });

            // Toggle current one
            if (!isOpen) {
                content.style.display = "block";
                this.textContent = "-";
            }
        });
    });

    // Copy to Clipboard
    copyButtons.forEach(button => {
        button.addEventListener("click", function () {
            const textarea = this.previousElementSibling;
            textarea.select();
            document.execCommand("copy");

            // Change button text temporarily
            this.textContent = "✅ Copied!";
            setTimeout(() => this.textContent = "📋 Copy to Clipboard", 2000);
        });
    });

    let prev = ""
    setInterval(() => {
        $.ajax({

            // Our sample url to make request
            url: '/api/read',

            // Type of Request
            type: "GET",

            // Function to call when to
            // request is ok
            success: function (data) {
                if(JSON.stringify(data) == prev) return

                let fi = ""
                let fj = ""
                let name = ""
                let state = ""
                let resp = ""
                let when = ""
                let finish = ""
                for(let i=0; i<=data.length-1; i++){
                    name = data[i].writeRequest.name
                    if(data[i].writeResponse == null){
                        if(data[i].failed){
                            state = "🔴"
                        }else{
                            state = "🟡"
                        }
                        resp = ""
                    }else{
                        state = "🟢"
                        resp = data[i].writeResponse.plainResponse
                        finish = moment.unix(data[i].endingTime).calendar()
                    }
                    when = moment.unix(data[i].startingTime).calendar();
                    if(new Date(data[i].startingTime * 1000).getDate() == new Date().getDate()){
                        fi += '<div class = "accordion-item"> <div class = "accordion-header"><a style ="color: white" target="_blank" href="'+data[i].writeRequest.job_link+'"> 📜 '+name+' </a> <span>'+state+'</span> <span>'+when+'</span> <button class = "toggle-btn"> + </button> </div> <div class = "accordion-content"><textarea readonly> '+resp+'</textarea> <button class = "copy-btn"> 📋Copy to Clipboard </button> <span></span> <span> Finish: '+finish+'</span></div> </div>'
                    }else{
                        fj += '<div class = "accordion-item"> <div class = "accordion-header"><a style ="color: white" target="_blank" href="'+data[i].writeRequest.job_link+'"> 📜 '+name+' </a> <span>'+state+'</span> <span>'+when+'</span> <button class = "toggle-btn"> + </button> </div> <div class = "accordion-content"><textarea readonly> '+resp+'</textarea> <button class = "copy-btn"> 📋Copy to Clipboard </button> <span></span> <span> Finish: '+finish+'</span></div> </div>'
                    }
                }

                $("#today").html(fi)
                $("#earlier").html(fj)

                toggles = document.querySelectorAll(".toggle-btn");
                toggles.forEach(btn => {
                        btn.addEventListener("click", function () {
                            const content = this.parentElement.nextElementSibling;
                            const isOpen = content.style.display === "block";

                            // Close all other accordions
                            document.querySelectorAll(".accordion-content").forEach(item => {
                                item.style.display = "none";
                            });
                            document.querySelectorAll(".toggle-btn").forEach(button => {
                                button.textContent = "+";
                            });

                            // Toggle current one
                            if (!isOpen) {
                                content.style.display = "block";
                                this.textContent = "-";
                            }
                        });
                    });
                     copyButtons = document.querySelectorAll(".copy-btn");
                    copyButtons.forEach(button => {
                    button.addEventListener("click", function () {
                        const textarea = this.previousElementSibling;
                        textarea.select();
                        document.execCommand("copy");

                        // Change button text temporarily
                        this.textContent = "✅ Copied!";
                        setTimeout(() => this.textContent = "📋 Copy to Clipboard", 2000);
                    });
                    });
                    prev = JSON.stringify(data)
            },

            // Error handling
            error: function (error) {
                console.log(`Error ${error}`);
            }
        });
    }, 1000)
});