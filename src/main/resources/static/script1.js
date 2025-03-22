 function saveTemplate(t){
        $.ajax({

            // Our sample url to make request
            url: '/api/setconfig',

            // Type of Request
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                temp1: t
            }),
            // Function to call when to
            // request is ok
            success: function (data) {
                if(JSON.stringify(data) == "SUCCESS"){
                    alert("done");
                }

            },

            // Error handling
            error: function (error) {
            }
        });
        }

  function saveMeta(t, v){
         $.ajax({

             // Our sample url to make request
             url: '/api/setconfig',

             // Type of Request
             type: "PUT",
             contentType: "application/json",
             data: JSON.stringify({
                 ollamaIp: t,
                 ollamaModel: v
             }),
             // Function to call when to
             // request is ok
             success: function (data) {
                 if(JSON.stringify(data) == "SUCCESS"){
                     alert("done");
                 }

             },

             // Error handling
             error: function (error) {
             }
         });
         }