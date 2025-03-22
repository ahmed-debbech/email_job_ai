# Email Ai
Write your next job application with Ai. Sounds common ? \
Here is why it is **not**:

* You copy paste the job description if any to it.
* Based on a template prompt you write once about who you are.
* Email Ai will combine the template with the job description to produce a fully generated Email for you.

Also it is **private** because you need to host it on your own. Keep in mind Email Ai is not a public platform, it is only a self-hosted one that you will use privately (see bellow on how to easily set it up) \
PS: You need to install [Ollama](https://ollama.com/) as the ai infrastructure and you can use any model.

#### How frequent you're going to need it?
When you are lazy to write an email, you press a button.

### How to use it ?

0- (you do this after fresh install): You visit `http://your-host-ip:8087/api/config.html` to configure (Ollama IP, Ollama Model, your template)

**1**-  You get the job description. (if any, if not it is okay too).

**2**-  You past the job description to the system through an IOS shortcut (we shared bellow)

note : for now you can use the REST API or the IOS Shortcut, soon we will develop more clients. (more about the API bellow)

**3** - You visit `http://your-host-ip:8087/api/index.html` to see all your done/pending write requests

The IOS Shortcut will ask you to provide:
* job link (optional)
* job name, used to keep track of requests later
* job description (optional)
* things to add to the email (optional) (like: write it in frensh, say that i have already h1b visa, etc...)
* template number to use (default is 1)

### How to install
* For server: build the jar file or you can use our docker image with `docker pull the1dev/email_ai:latest`.
* For IOS Shortcut client: you find [here]() the link to install it on your Iphone.
* Install [Ollama](https://ollama.com/) preferably on same machine and pull your fav model to use (llama3.2 is recommended).

### Technical stuff

**1-  Configuration**: \
Set the ollama server `ip:port` and set the ollama model to use like `llama3.2`, Don't forget to add your template 

Here is an example of a template prompt \
`Write for me an email about a job i found i am X Y with 5 years of Java experience and i have worked on tada tada and tada, you can find my github here link_github and my linked in on link_linkedin. make the email fits this job description "` `&&job_desc` `" and please also add that: "` `&&set_mine` `" make the email unique and combines both my profile and job description` 

note that `&&job_desc` and `&&set_mine` are necessary placeholders that must be set in the template to be able to replace it with job description and optional addition that you wrote in the request.

**2- Endpoints**: \
- POST `/api/write` 
    ```
    {
    "job_link": "https://www.linkedin.com/jobs/collections/recommended/?currentJobId=1",
    "name": "The name",
    "job_desc": "Degree in IT Engineering or similar areas At least 3 years of relevant professional experience in Java programming language Experience with Spring or Spring Boot Experience with Frontend development using Angular Experience with SQL Server Good level of English (B2-C1)",
    "set_mine": "add that i have CCNA certificate, and write in portegeuse",
    "template_num": 1
    }
    ```

    any client should consume this endpoint for sending write requests

- GET  `/api/read` \
    this endpoint is used to see the pending/done requests.


### Open source, so why not to contribute?
Feel free to open a pull request or contact me at my email about changes you want to see. \
As this project is still under developement but acquiring a finished demo you can try.

#### Credits
Ahmed Debbech - Original author
